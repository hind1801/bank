package presentation.controleur;

import dao.DataBase;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.modele.Sexe;
import presentation.vue.ClientFrame.MainClientFrame;

import java.util.ArrayList;
import java.util.List;

public class MaBanque {
    public static void main(String[] args) {


    /*
        List<Client> c = new ArrayList<>();
        c.add(c1);
        c.add(c1);
        c.add(c1);
        c.add(c1);
        DataBase.getCliantsDao().saveAll(c);*/

     new MainClientFrame(DataBase.getAccountsDao().findById(1L));

    }
}