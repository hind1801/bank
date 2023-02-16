package presentation.vue.ClientFrame;

import dao.DataBase;
import presentation.modele.Client;
import presentation.modele.Compte;
import presentation.vue.palette.ConstumButton;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PanelChoiseAccountClient extends JPanel {
         private  JComboBox<String> numComptes;
         private ConstumButton btnChoix;
         private DefaultListCellRenderer listRenderer;
         private static Font FONT_COMBOBOX = new Font("Optima" , Font.BOLD ,20);
         private static Font FONT_BUTTON = new Font("Optima" , Font.BOLD ,20);

    private void initComboBox(Client client) {
             List<Compte> comptes = DataBase.getAccountsDao().findByIdClient(client.getId());
             String num[] = new String[(int)comptes.stream().count()];
             for (int i = 0; i <num.length; i++)
                    num[i] =comptes.get(i).getNumeroCompte().toString();
             numComptes = new JComboBox<>(num);
           numComptes.setPreferredSize(new Dimension(500,30));
             listRenderer = new DefaultListCellRenderer();
             listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
             numComptes.setRenderer(listRenderer);
             numComptes.setFont(FONT_COMBOBOX);
         }
    private void initButton()
    {   btnChoix = new ConstumButton("OK" ,Color.WHITE ,Color.BLUE , FONT_BUTTON);
        btnChoix.setPreferredSize(new Dimension(300,30));
    }
    private void initAction()
    {
        btnChoix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = numComptes.getSelectedIndex();
                new MainClientFrame(DataBase.getAccountsDao().findById(Long.parseLong(numComptes.getItemAt(n))));


            }
        });

    }
    private void initPanel(Client client)
    {
        initButton();
        initComboBox(client);
        setLayout(new FlowLayout());
        setSize(new Dimension(600,300));
        initAction();

        add(numComptes);
        add(btnChoix);
    }
         public PanelChoiseAccountClient(Client client)
         {
    initPanel(client);
         }




}
