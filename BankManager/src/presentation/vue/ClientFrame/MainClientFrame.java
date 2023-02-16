package presentation.vue.ClientFrame;

import dao.DataBase;
import presentation.modele.Compte;

import javax.swing.*;
import java.awt.*;

public class MainClientFrame extends JFrame {

        private JTabbedPane menu;
        private FooterClientPanel footer;
        private HeaderClientPanel headerClientPanel;
        private InfoPanel info;
        private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        private Compte compte;
        private EditInfoPanel edit;
        private  AccountOperationsPanel accountOperationsPanel;
        private LogsTablePanel logsTablePanel;

        private void initEdit() {
                edit = new EditInfoPanel(compte.getPropriétaire());
        }
        private void initTablePanel(){logsTablePanel = new LogsTablePanel(DataBase.getLogsDao().findByIdAccount(compte.getNumeroCompte()) ,compte);}
        private void initInfo(){info = new InfoPanel(compte);}
        private void initHeader(){headerClientPanel = new HeaderClientPanel(compte.getPropriétaire());}
        private void initOperation() {
                accountOperationsPanel = new AccountOperationsPanel(compte);
        }
        private void initFooter(){footer=new FooterClientPanel(compte);}
        private void initMenu(){
               menu = new JTabbedPane();
                menu.setFont(new Font("Optima", Font.BOLD, 20));
                menu.addTab("Details de compte", info);
                menu.addTab("Faire des transactions", accountOperationsPanel);
                menu.addTab("Histoire des transactions", logsTablePanel);
                menu.addTab("Modifier Profile", edit);


        }
        private void initFrame(){
            setLayout(new BorderLayout());
            add(headerClientPanel, BorderLayout.NORTH);
            add(footer, BorderLayout.SOUTH);
            add(menu, BorderLayout.CENTER);
            setSize(screenSize);
            setResizable(false);
            setVisible(true);
        }
        public MainClientFrame(Compte compteActuel) {
            this.compte = compteActuel;
        initEdit();
        initFooter();
        initEdit();
        initOperation();
        initHeader();
        initTablePanel();
        initInfo();
        initMenu();
        initFrame();
    }

}
