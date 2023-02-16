package presentation.vue.palette;

import javax.swing.*;
import java.awt.*;

public class TableCrudPanel extends JPanel {

    private ClassLoader cl = getClass().getClassLoader();
    private JButton btn_add, btn_edit, btn_remove;


    public JButton deleteBtn() {
        return btn_remove;
    }

    private void initButtons(){

        btn_add = new JButton(new ImageIcon(cl.getResource("images/icons/add.png")));
        btn_add.setBorderPainted(false);

        btn_edit = new JButton(new ImageIcon(cl.getResource("images/icons/edit.png")));
        btn_edit.setBorderPainted(false);

        btn_remove = new JButton(new ImageIcon(cl.getResource("images/icons/delete.png")));
        btn_remove.setBorderPainted(false);

    }

    public TableCrudPanel(){

        initButtons();
        setLayout(new FlowLayout());
        setBackground(Color.white);
        add(btn_add);
        add(btn_edit);
        add(btn_remove);
    }


}
