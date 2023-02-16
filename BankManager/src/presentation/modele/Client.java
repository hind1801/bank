package presentation.modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Client extends Utilisateur implements AffichageInfos {

    private String email, cin, tel;
    private Sexe sexe;
    private List<Compte> comptesClient;


    public String       getCin() {
        return cin;
    }
    public String       getTel() {
        return tel;
    }
    public String       getEmail() {
        return email;
    }
    public List<Compte> getComptesClient() {
        return comptesClient;
    }
    public Sexe getSexe() {
        return sexe;
    }
    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }
    public void         setEmail(String email) {
        this.email = email;
    }
    public void         setTel(String tel) {
        this.tel = tel;
    }
    public void         setCin(String cin) {
        this.cin = cin;
    }
    public void         setComptesClient(List<Compte> comptesClient) {
        this.comptesClient = comptesClient;
    }

    public Client(){

        comptesClient = new ArrayList<>();
    }

    public Client(String login, String pass){
        super(login, pass, "Client");
        comptesClient = new ArrayList<>();
    }

    public Client(String login, String pass, String n, String p){
        super(login, pass, "Client");
        setNom(n);
        setPrenom(p);
        comptesClient = new ArrayList<>();
    }
    public Client(String login, String pass, String n, String p, String mail, String cin, String tel, Sexe sexe){
        super(login, pass, "Client");
        setNom(n);
        setPrenom(p);
        setTel(tel);
        setEmail(mail);
        setCin(cin);
        setSexe(sexe);
        comptesClient = new ArrayList<Compte>();
    }

    @Override
    public String toString() {
        String      clientStr  = "------------------------------------------------------\n";
                    clientStr += "| Identifiant du Client     : "   + this.id        + "\n";
                    clientStr += "| Nom Complet               : "   + this.getNomComplet() + "\n" ;
                    clientStr += "| Adresse email             : "   + this.email     + "\n" ;
                    clientStr += "| Numéro téléphone          : "   + this.tel       + "\n" ;
                    clientStr += "| Numéro de CIN             : "   + this.cin       + "\n" ;
                    clientStr += "------------------------------------------------------\n";

        return clientStr;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return Objects.equals(getId(), client.getId());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getCin());
    }

    @Override
    public void afficherBref() {

    }

    @Override
    public void afficherLesLogs() {
        comptesClient.forEach(compte -> {compte.getLogs().forEach(log ->System.out.println(log.toString()));});}

    @Override
    public void afficherInformations() {

    }

    @Override
    public void afficherInformationsDétaillées() {

    }
}
