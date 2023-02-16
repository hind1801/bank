package presentation.vue.ClientFrame;

import presentation.modele.Client;
import presentation.vue.palette.CostumLabel;
import presentation.vue.palette.CostumLabelValue;
import presentation.vue.palette.HeaderPanel;

import javax.swing.*;
import java.awt.*;

public class HeaderClientPanel  extends JPanel {

    private CostumLabel title;
    Client client;
    Color color ;


    private void init()
    {
        color = Color.black;
        title = new CostumLabel(client.getNomComplet() , color , new Font("Optima" , Font.PLAIN ,20));
    }
    private void initPanel() {
        init();
        setLayout(new BorderLayout());
        add(title , BorderLayout.EAST);
    }
    public HeaderClientPanel(Client client){this.client = client;
    initPanel();}

}
