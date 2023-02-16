package forms;

import dao.DataBase;
import presentation.modele.Client;
import presentation.modele.Compte;

import java.util.HashMap;
import java.util.Map;

public class AjouterCompteFormValidator {
    private static final String  FIELD_SOLDE = "solde", FIELD_CIN = "CIN";
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
    private void verifierSolde(String montantS) throws FormException {
        Double montant = null;
        if(montantS.isBlank()) throw new FormException("Le montant n'a pas été precisé !");
        try{
            montant = Double.parseDouble(montantS);
        }
        catch (Exception e){
            throw new FormException("Le montant n'est pas valide !");
        }
        if(montant < 100) throw new FormException("Le montant est inférieure à 100.00 DH !");
    }
    private void verifierCIN(String cin) throws FormException{
        if(cin != null && !cin.isBlank()){
            Client client = DataBase.getCliantsDao().findByCin(cin);
            if(client == null) throw new FormException("Le client dont le CIN est : " + cin + " n'existe pas !");
        }
        else throw new FormException("Le CIN est obligatoire !");
    }
    private void validerSolde(String solde){
        try {
            verifierSolde(solde);

        } catch (FormException e) {
            setError(FIELD_SOLDE, e.getMessage());
        }
    }
    private void validerCIN(String cin){

        try {
            verifierCIN(cin);

        } catch (FormException e) {
            setError(FIELD_CIN, e.getMessage());
        }
    }
    public void validerAjout(String soldeStr, String cinClient){
        errors.clear();

        validerSolde(soldeStr);
        validerCIN(cinClient);

        if(Errors().isEmpty()){
            Client client = DataBase.getCliantsDao().findByCin(cinClient);
            Double solde = Double.parseDouble(soldeStr);

            Compte compte =  new Compte();
            compte.setPropriétaire(client);
            compte.setSolde(solde);
            DataBase.getAccountsDao().save(compte);
            DataBase.getLogsDao().save(compte.getLogs().get(0));
        }
    }

}
