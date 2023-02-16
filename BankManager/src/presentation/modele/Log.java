package presentation.modele;

import java.time.LocalDate;
import java.time.LocalTime;

public class Log {
    private static long compteur = 1;
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private TypeLog type;
    private String message;
    private Compte compte;

    public Log(LocalDate date, String msg , Compte compte){
        this.id = compteur++;
        this.date = date;
        this.message = msg;
        this.compte = compte;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public LocalDate getDate() {
        return date;
    }

    public TypeLog getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public Log(LocalDate date, LocalTime time, TypeLog type, String msg,Compte compte){
        this.id = compteur++;
        this.date = date;
        this.message = msg;
        this.time = time;
        this.type = type;
        this.compte = compte;
    }

    public LocalTime getTime() {
        return time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String logStr = "[" + date + "]["+time+"][{"+type+"}] : " +  message;

        return logStr;
    }
}
