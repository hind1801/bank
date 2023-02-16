package presentation.vue.ClientFrame;

import presentation.modele.Client;

import javax.swing.*;
import java.awt.*;

public class ChoiseAccountClient extends JFrame {
            private PanelChoiseAccountClient panelChoiseAccountClient;

            private void initFram(){
                setTitle("Lise des comptes");
                setSize(new Dimension(600,300));
                setLocationRelativeTo(null);
                setResizable(false);
                setLayout(new FlowLayout());
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setVisible(true);


            }
         public   ChoiseAccountClient(Client client)
            {
                initFram();
                panelChoiseAccountClient = new PanelChoiseAccountClient(client);
                add(panelChoiseAccountClient);
            }





}
