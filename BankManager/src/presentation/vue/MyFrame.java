package presentation.vue;


import presentation.modele.Client;
import presentation.vue.palette.HeaderPanel;
import presentation.vue.palette.SideMenuPanel;
import presentation.vue.palette.TablePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyFrame extends JFrame {

    ClassLoader cl = getClass().getClassLoader();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Container  container;
    TablePanel tablePanel;
    HeaderPanel header;
    SideMenuPanel menuPanel;

    private void initPanels(){
        tablePanel = new TablePanel();
        menuPanel = new SideMenuPanel("Ajouter","Modifier","Supprimer","Chercher");

        Font logoFont = new Font("Optima", Font.BOLD, 15);
        header = new HeaderPanel(Color.WHITE,
                new ImageIcon(cl.getResource("images/icons/bankIcon64.png")), "myBank", Color.BLACK, logoFont,
                new ImageIcon(cl.getResource("images/icons/menu.png")), "", Color.BLACK, logoFont);


        initActions();
    }

    private void initContainer(){
        initPanels();
        container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(tablePanel, BorderLayout.CENTER);
        container.add(menuPanel, BorderLayout.WEST);
        container.add(header, BorderLayout.NORTH);
    }


    private void initActions(){

       var buttons = menuPanel.getButtons();

       buttons.get("Ajouter")
               .addActionListener(click -> System.out.println("btn Ajouter cliqué"));

        buttons.get("Modifier")
                .addActionListener(click -> System.out.println("btn Modifier cliqué"));

        buttons.get("Supprimer")
                .addActionListener(click -> System.out.println("btn Supprimé cliqué"));

        buttons.get("Chercher")
                .addActionListener(click -> System.out.println("btn Chercher cliqué"));

        buttons.get("Ajouter")
                .addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {

                        buttons.get("Ajouter").setForeground(Color.RED);
                        buttons.get("Ajouter").setFont(new Font("Optima", Font.BOLD, 20));

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        buttons.get("Ajouter").setForeground(Color.BLUE);
                        buttons.get("Ajouter").setFont(new Font("Optima", Font.BOLD, 18));

                    }
                });

        header.getToggleMenu().addActionListener(e->{

            if(menuPanel.isVisible()) menuPanel.setVisible(false);
            else menuPanel.setVisible(true);
        });

    }



    public MyFrame(String title){

        initContainer();
        setTitle(title);
        setLocation(0,0);
        setSize(screenSize.width,screenSize.height-90);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyFrame("MyBank Manager");
    }
}
