package forms;

import dao.DataBase;
import presentation.modele.Client;
import presentation.modele.Sexe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AjouterClientFormValidator {
    private static final String FIELD_NOM="nom", FIELD_PRENOM="prénom", FIELD_LOGIN="login", FIELD_PASS="pass",
            FIELD_CONFIRMATION="confirmation pass", FIELD_EMAIL="email", FIELD_CIN="cin", FIELD_TEL="téléphone";

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

    private void verifierNom(String nom) throws FormException{
        if(nom == null || nom.isBlank()) throw new FormException("Le champ nom est obligatoire !");
    }
    private void verifierPrenom(String prenom) throws FormException{
        if(prenom == null || prenom.isBlank()) throw new FormException("Le champ prénom est obligatoire !");
    }
    private void verifierLogin(String login) throws FormException {
        if(login!= null && login.trim().length()!=0){
            if(login.trim().length()<4)
                throw new FormException("Login doit contenir plus de 4 caractères !");
            List<Client> clients = DataBase.getCliantsDao().findAll()
                    .stream()
                    .filter(client -> client.getLogin().toLowerCase().equals(login.toLowerCase()))
                    .toList();
            if(clients.size() > 0) throw new FormException("Le client existe déja!");
        }else{
            throw new FormException("le login est obligatoire !");
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
    private void verifierPassConfirmation(String pass, String confirmation) throws FormException {
        if (confirmation != null && confirmation.trim().length() != 0) {
            if (!pass.equals(confirmation))
                throw new FormException("Le mot de passe et la confirmation ne sont pas compatibles !");
        } else {
            throw new FormException("Confirmation de mot de passe requis !");
        }
    }
    private void verifierCin(String cin) throws FormException{
        cin = cin.toLowerCase();
        final String cinf = cin;
        if(cin != null && !cin.isBlank()){
            List<Client> clients = DataBase.getCliantsDao().findAll()
                    .stream()
                    .filter(client -> client.getCin().equals(cinf))
                    .toList();
            if(clients.size() > 0) throw new FormException("Le CIN du client existe déja !");
            if(!cin.matches("^[a-z]{2}[0-9]{6}$")) throw new FormException("Le CIN n'est pas dans le bon format !");
        }
        else throw new FormException("Le CIN est obligatoire !");
    }
    private void verifierTel(String tel) throws FormException{
        if(tel != null && !tel.isBlank()){
            List<Client> clients = DataBase.getCliantsDao().findAll()
                    .stream()
                    .filter(client -> client.getTel() != null && client.getTel().equals(tel))
                    .toList();
            if(clients.size() > 0) throw new FormException("Le numéro de téléphone du client existe déja !");
            if(!tel.matches("^0[0-9]{9}$")) throw new FormException("Le numéro de téléphone n'est pas dans le bon format !");
        }
    }
    private void verifierEmail(String email) throws FormException{
        if(email != null && !email.isBlank()){
            List<Client> clients = DataBase.getCliantsDao().findAll()
                    .stream()
                    .filter(client -> client.getEmail() != null && client.getEmail().toLowerCase().equals(email.toLowerCase()))
                    .toList();
            if(clients.size() > 0) throw new FormException("L'email du client existe déja !");
            if(!email.matches("^[a-zA-Z.1-9]+@[a-zA-Z.1-9]+\\.[a-zA-Z]{2,4}$")) throw new FormException("L'email n'est pas dans le bon format !");
        }
    }

    private void validerNom(String nom){

        try {
            verifierNom(nom);

        } catch (FormException e) {
            setError(FIELD_NOM, e.getMessage());
        }
    }
    private void validerPrenom(String prenom){

        try {
            verifierPrenom(prenom);

        } catch (FormException e) {
            setError(FIELD_PRENOM, e.getMessage());
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
    private void validerPassConfirmation(String pass, String confirmation){

        try {
            verifierPassConfirmation(pass, confirmation);


        } catch (FormException e) {
            setError(FIELD_CONFIRMATION, e.getMessage());
        }
    }
    private void validerCin(String cin){

        try {
            verifierCin(cin);

        } catch (FormException e) {
            setError(FIELD_CIN, e.getMessage());
        }
    }
    private void validerTel(String tel){

        try {
            verifierTel(tel);

        } catch (FormException e) {
            setError(FIELD_TEL, e.getMessage());
        }
    }
    private void validerEmail(String email){

        try {
            verifierEmail(email);

        } catch (FormException e) {
            setError(FIELD_EMAIL, e.getMessage());
        }
    }

    public void validerAjout(
            String nom, String prenom, String login, String mdp,
            String mdpConfirmation, String email, String cin, String tel,
            Sexe sexe
    ){
        errors.clear();

        validerNom(nom);
        validerPrenom(prenom);
        validerLogin(login);
        validerPass(mdp);
        validerPassConfirmation(mdp, mdpConfirmation);
        validerEmail(email);
        validerCin(cin);
        validerTel(tel);

        if(Errors().isEmpty()){
            String emailStr = (email.isBlank()) ? null : email;
            String telStr = (tel.isBlank()) ? null : tel;
            Client client = new Client(login, mdp, nom, prenom, emailStr, cin, telStr, sexe);
            DataBase.getCliantsDao().save(client);
            setResultMsg("Client ajouté avec succès.");
        }
    }

}
