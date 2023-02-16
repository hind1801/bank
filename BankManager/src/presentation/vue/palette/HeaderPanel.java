package presentation.vue.palette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HeaderPanel extends JPanel {

    private JLabel lbl_logo;
    private JButton btn_toggleMenu;


    public JButton getToggleMenu() {
        return btn_toggleMenu;
    }

    private void initLabel(Icon icon, String text, Color color, Font font){

        lbl_logo = new JLabel(text);
        lbl_logo.setIcon(icon);
        lbl_logo.setForeground(color);
        lbl_logo.setFont(font);
        lbl_logo.setHorizontalTextPosition(JLabel.CENTER);
        lbl_logo.setVerticalTextPosition(JLabel.BOTTOM);

    }

    private void initButton(Icon icon, String text, Color color, Font font){

        btn_toggleMenu = new JButton(text);
        btn_toggleMenu.setIcon(icon);
        btn_toggleMenu.setForeground(color);
        btn_toggleMenu.setFont(font);
        btn_toggleMenu.setHorizontalTextPosition(JLabel.CENTER);
        btn_toggleMenu.setVerticalTextPosition(JLabel.BOTTOM);
        btn_toggleMenu.setBorderPainted(false);
        btn_toggleMenu.setFocusable(false);
    }



    public  HeaderPanel(Color bgColor,
                        Icon iconlbl, String textlbl, Color colorlbl, Font fontlbl,
                        Icon iconbtn, String textbtn, Color colorbtn, Font fontbtn){

        initLabel( iconlbl,  textlbl,  colorlbl,  fontlbl);
        initButton( iconbtn,  textbtn,  colorbtn,  fontbtn);
        setLayout(new BorderLayout());
        setBackground(bgColor);
        setBorder(new EmptyBorder(10,20,10,20));

        add(btn_toggleMenu, BorderLayout.WEST);
        add(lbl_logo, BorderLayout.EAST);
    }

}
