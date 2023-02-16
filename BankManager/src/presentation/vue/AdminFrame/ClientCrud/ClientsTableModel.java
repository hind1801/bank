package presentation.vue.AdminFrame.ClientCrud;

import presentation.modele.Client;
import presentation.modele.Log;
import presentation.vue.palette.TableModel;

import java.util.List;

public class ClientsTableModel extends TableModel<Client> {
    public ClientsTableModel(List<Client> objects) {
        super(objects,"Id","Nom","Prenom","Email","Telephone","CIN","Sex","Login","Password");
    }

    @Override
    public void initData(List<Client> objects) {
        data = new Object[objects.size()][columnsNames.length];
        int i = 0;
        for(Client cl : objects){

            data[i][0] =  cl.getId().toString();
            data[i][1] =  cl.getPrenom();
            data[i][2] =  cl.getPrenom();
            data[i][3] =  cl.getEmail();
            data[i][4] =  cl.getTel();
            data[i][5] =  cl.getCin();
            data[i][6] =  cl.getSexe();
            data[i][7] =  cl.getLogin();
            data[i][8] =  cl.getMotDePasse();
            i++;
        }
        this.fireTableDataChanged();

    }
}
