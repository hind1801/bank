package presentation.vue.ClientFrame;

import presentation.modele.Compte;
import presentation.vue.palette.ConstumButton;
import presentation.vue.palette.CostumLabelValue;

import javax.swing.*;
import java.awt.*;

public class FooterClientPanel extends JPanel {

    private CostumLabelValue back;
    private Compte compte;

    private void initButton(){
        back = new CostumLabelValue("b-o00"+compte.getNumeroCompte() , Color.WHITE  , new Font("optima" ,Font.PLAIN ,20));
        back.setPreferredSize(new Dimension(300,30));
    }
    private void initPanel(){setLayout(new FlowLayout());
    add(back);
    }
    public FooterClientPanel(Compte compte){
        this.compte=compte;
        initButton();
    initPanel();}



}
