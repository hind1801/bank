package presentation.vue.ClientFrame;

import dao.DataBase;
import presentation.modele.Compte;
import presentation.modele.Log;
import presentation.vue.palette.TablePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Comparator;
import java.util.List;


public class LogsTablePanel extends TablePanel<LogsTableModel> {
    private Compte compteActuel;

    public  void refresh(){
        List<Log> logs = DataBase.getLogsDao().findByIdAccount(compteActuel.getNumeroCompte());
        tableModel.initData(logs);
    }


    private void initActions() {
        searchPanel.getTxt_search().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    searchPanel.getBtn_search().doClick();
                }
            }
        });
        searchPanel.getBtn_search().addActionListener(e->{
            String keyword = searchPanel.getTxt_search().getText();
            List<Log> logs;
            if(keyword.isEmpty()) logs = DataBase.getLogsDao().findByIdAccount(compteActuel.getNumeroCompte())
                    .stream()
                    .sorted(new Comparator<Log>() {
                        @Override
                        public int compare(Log o1, Log o2) {
                            if(o1.getDate().isAfter(o2.getDate())) return -1;
                            else if(o1.getDate().isBefore(o2.getDate())) return 1;
                            else return 0;
                        }
                    })
                    .toList();
            else logs =  DataBase.getLogsDao().findByKeywordLike(keyword)
                    .stream()
                    .filter(log -> log.getCompte().equals(compteActuel))
                    .sorted(new Comparator<Log>() {
                        @Override
                        public int compare(Log o1, Log o2) {
                            if(o1.getDate().isAfter(o2.getDate())) return -1;
                            else if(o1.getDate().isBefore(o2.getDate())) return 1;
                            else return 0;
                        }
                    })
                    .toList();
            tableModel.initData(logs);
        });
    }



    public LogsTablePanel(List<Log> objects, Compte compteActuel) {
        super(new LogsTableModel(objects));
        this.compteActuel = compteActuel;
        initActions();
        searchPanel.remove(searchPanel.getCrudPanel());
    }

}
