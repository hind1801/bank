package presentation.modele;

public class Admin  extends Utilisateur{

    private static  Admin ADMIN = new Admin();

    private Admin(){

        login       = "admin";
        motDePasse  = "1234";
        role        = "Admin";
        nom         =  "Nadir";
        prenom      = "Hind";

    }


    public static Admin getInstance(){

        return ADMIN;
    }
    public static boolean isAdmin(String login , String pass) {
        if(login.equals(getInstance().login) && pass.equals(getInstance().motDePasse))
            return true;
        return false;
    }

}
