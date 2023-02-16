package forms;

import dao.DataBase;
import presentation.modele.Compte;
import presentation.modele.Log;
import presentation.modele.TypeLog;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class VerserFormValidator {
    private static final String FIELD_MONTANT ="montant";

    private Map<String , String> errors = new HashMap<>();
    private String resultMsg;

    public Map<String, String> Errors() {
        return errors;
    }
    public void setError(String fieldName, String errorMsg) {
        Errors().put(fieldName, errorMsg);
    }

    public String ResultMsg() {
        return resultMsg;
    }
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    private void verifierMontant(String montantS) throws FormException {
        Double montant = null;
        if(montantS.isEmpty()) throw new FormException("Le montant n'est pas precisé !");
        try{
            montant = Double.parseDouble(montantS);
        }
        catch (Exception e){
            throw new FormException("Le montant n'est pas valide !");
        }
        if(montant < 100) throw new FormException("Le montant est inférieure à 100.00 DH !");
    }
    private void validerMontant(String montantS){

        try {
            verifierMontant(montantS);

        } catch (FormException e) {
            setError(FIELD_MONTANT, e.getMessage());
        }
    }

    public Log validerVersement(Compte compte, String montantS){

        errors.clear();

        validerMontant(montantS);

        Log log = null;

        if(Errors().isEmpty()){

            Double montant = Double.parseDouble(montantS);
            Double soldeAJour = montant + compte.getSolde();
            compte.setSolde(soldeAJour);
            DataBase.getAccountsDao().update(compte);
            log = new Log(LocalDate.now() , LocalTime.now() , TypeLog.VERSEMENT, "Vous avez versé un montant de " + montantS + " DH.", compte );
            DataBase.getLogsDao().save(log);
            setResultMsg("Versement de " + montantS + " DH fait avec succès");
            return log;
        }

        return null;
    }
}
