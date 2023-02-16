package presentation.vue.ClientFrame;

import presentation.modele.Log;
import presentation.vue.palette.TableModel;

import java.util.List;

public class LogsTableModel extends TableModel<Log> {


    public LogsTableModel(List<Log> objects) {
        super(objects , "Type" , "Message" , "Date");
    }

    @Override
    public void initData(List<Log> objects) {
        data = new Object[objects.size()][columnsNames.length];
        int i = 0;
        for(Log log : objects){

            data[i][0] =  log.getType().toString();
            data[i][1] =  log.getMessage();
            data[i][2] =  log.getDate().toString().substring(0, 10);

            i++;
        }
        this.fireTableDataChanged();
    }
}
