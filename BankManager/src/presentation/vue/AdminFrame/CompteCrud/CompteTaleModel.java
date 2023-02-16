package presentation.vue.AdminFrame.CompteCrud;

import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.vue.palette.TableModel;

import java.time.LocalDate;
import java.util.List;

public class CompteTaleModel extends TableModel<Compte> {
    public CompteTaleModel(List<Compte> objects){
        super(objects,"Numero de Compte" , "Solde" , "Date", "Nom de Client");
    }

    @Override
    public void initData(List<Compte> objects) {
        data = new Object[objects.size()][columnsNames.length];
        int i = 0;
        for(Compte cl : objects){

            data[i][0] =  cl.getNumeroCompte().toString();
            data[i][1] =  cl.getSolde();
            data[i][2] =  cl.getDateCreation();
            data[i][3] =  cl.getPropriétaire().getNomComplet();
            i++;
        }
        this.fireTableDataChanged();
    }
}
