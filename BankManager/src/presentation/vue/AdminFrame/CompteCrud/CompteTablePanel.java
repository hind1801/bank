package presentation.vue.AdminFrame.CompteCrud;


import dao.DataBase;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.vue.AdminFrame.ClientCrud.ClientsTableModel;
import presentation.vue.AdminFrame.ClientCrud.ClientsTablePanel;
import presentation.vue.palette.JTableUtilities;
import presentation.vue.palette.SearchPanel;
import presentation.vue.palette.TableModel;
import presentation.vue.palette.TablePanel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;


public class CompteTablePanel extends TablePanel<CompteTaleModel> {

    private JTable table;
    private CompteTaleModel tableModel;
    private JScrollPane scrollPane;
    private SearchPanel searchPanel;



    private void initTable(){

        tableModel  = new CompteTaleModel(DataBase.getAccountsDao().findAll());
        tableModel.initData(DataBase.getAccountsDao().findAll());

        table       = new JTable(tableModel);
        table.setFont(new Font("Optima", Font.BOLD, 17));
        table.setForeground(new Color(16, 44, 114));
        table.setRowHeight(35);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Optima", Font.BOLD, 20));
        header.setForeground(new Color(198, 113, 34));
        header.setBackground(Color.WHITE);

        ((DefaultTableCellRenderer)header.getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);

        JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);

        scrollPane = new JScrollPane(table);

        searchPanel = new SearchPanel(Color.white);

        initActions();
    }

    private void initActions(){


        searchPanel.getCrudPanel().deleteBtn().addActionListener(e->{

            int row = table.getSelectedRow();
            if(row == -1){

                JOptionPane.showMessageDialog(this,
                        "Veuillez choisir un client d'abord !!!",
                        "A L E R T",
                        JOptionPane.ERROR_MESSAGE);
            }
                else{

                        String id           =  (String)tableModel.getValueAt(row, 0);


                  if( DataBase.getAccountsDao().deleteById(5L))
                  {
                      System.out.println("Done");
                  }
                    List<Compte> list = DataBase.getAccountsDao().findAll();
                    tableModel.initData(list);

                    JOptionPane.showMessageDialog(this,
                            "Le Compte "+id+ " a été supprimé avec succès",
                            "I N F O",
                            JOptionPane.INFORMATION_MESSAGE);
                }

        });

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

            var clients =  DataBase.getAccountsDao().findByKeywordLike(keyword);

            tableModel.initData(clients);

        });
    }
    public CompteTablePanel(){

        initTable();
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {

        JFrame jFrame =  new JFrame();
        jFrame.setLayout(new FlowLayout());
        jFrame.add(new CompteTablePanel());
        jFrame.setVisible(true);new CompteTablePanel();

    }
}
