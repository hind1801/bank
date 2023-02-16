package presentation.vue.palette;

import javax.swing.*;
import java.awt.*;

public class ConstumeTextField extends JTextField {

    public ConstumeTextField()
    {
        setForeground(Color.BLACK);
        setBackground(Color.white);
        setPreferredSize(new Dimension(250, 30));
        setMaximumSize(new Dimension(300, 30));
    }
    public ConstumeTextField(String textInitial)
    {
        setText(textInitial);
        setForeground(Color.BLACK);
        setBackground(Color.white);
        setPreferredSize(new Dimension(250, 30));
        setMaximumSize(new Dimension(300, 30));
    }



}
