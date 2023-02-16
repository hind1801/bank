package presentation.modele;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Compte {
    private static long          compteur = 1;
    private Long          numeroCompte;
    private Double          solde;
    private LocalDate   dateCreation;
    private Client          propriétaire;
    private List<Log>       logs = new ArrayList<>();

    public void setDateCreation() { this.dateCreation = LocalDate.now(); }
    public void setPropriétaire(Client propriétaire) {
        this.propriétaire = propriétaire;
    }
    public void setSolde(Double solde) {
        this.solde = solde;
    }
    public void setLog(TypeLog type, String msg , Compte compte) {

        Log log = new Log(LocalDate.now(), LocalTime.now(), type, msg , compte);
        logs.add(log);
    }

    public Client           getPropriétaire() {
        return propriétaire;
    }
    public Double           getSolde() {
        return solde;
    }
    public Long getNumeroCompte() {
        return numeroCompte;
    }
    public void setNumeroCompte() {
        this.numeroCompte =  compteur++;
    }
    public LocalDate    getDateCreation() {
        return dateCreation;
    }
    public List<Log>        getLogs() {
        return logs;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setNumeroCompte(Long numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public Compte(){
        setNumeroCompte();
        setDateCreation();
        setSolde(0.0);
        logs.add(new Log(LocalDate.now() , LocalTime.now() , TypeLog.CREATION , "Creattion de compte" , this));
        setPropriétaire(null);
    }

    @Override
    public String toString() {

        String      compteStr  = "------------------------------------------------------\n";
                    compteStr += "| N° de Compte            : "   + getNumeroCompte()   + "\n";
                    compteStr += "| Solde du Compte         : "   + getSolde()    + " Dh\n" ;
                    compteStr += "| Propriétaire du Compte  : "   + getPropriétaire().getNomComplet() + "\n" ;
                    compteStr += "------------------------------------------------------\n";

        return compteStr;
    }



}
