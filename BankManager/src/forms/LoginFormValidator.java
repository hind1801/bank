package forms;


import dao.DataBase;
import presentation.modele.Admin;
import presentation.modele.Utilisateur;

import java.util.HashMap;
import java.util.Map;

public class LoginFormValidator implements Verifiable {

    private static final String FIELD_LOGIN ="login", FIELD_PASS = "password";


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


    private void verifierLogin(String login) throws FormException {
        if(login!= null && login.trim().length()!=0){
            if(login.trim().length()<4)
                throw new FormException("Le login doit contenir plus de 4 caractères !!");
        }else{
            throw new FormException("Login est obligatoire !");
        }
    }
    private void verifierPass(String pass) throws FormException {
        if(pass!= null && pass.trim().length()!=0){
            if(pass.trim().length()<4)
                throw new FormException("Le mot de passe doit contenir plus de 4 caractères !!");
        }else{
            throw new FormException("Le mot de passe est obligatoire !");
        }
    }
    private void validerLogin(String login){
        try {
            verifierLogin(login);
        } catch (FormException e) {
            setError(FIELD_LOGIN, e.getMessage());
        }
    }
    private void validerPass(String pass){
        try {
            verifierPass(pass);
        } catch (FormException e) {
            setError(FIELD_PASS, e.getMessage());
        }
    }
    public Utilisateur validerSession(String login, String password){
        errors.clear();
        Utilisateur session = null;
        validerLogin(login);
        validerPass(password);
        session = DataBase.getCliantsDao().findByLoginAndPassword(login, password);
        if(Errors().isEmpty()){
            if (session!= null)
                setResultMsg("Connexion effcetuée avec succés [CLIENT] " + session.getNomComplet());
            else if(Admin.isAdmin(login , password)){
                setResultMsg("Connexion effcetuée avec succés [ADMIN] " + Admin.getInstance().getNomComplet());
                session = Admin.getInstance();
            }
            else {
                setResultMsg("Login et mot de passe non valides !");
            }
        }
        return session;
    }
}
