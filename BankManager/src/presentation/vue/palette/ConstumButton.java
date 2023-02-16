package presentation.vue.palette;

import javax.swing.*;
import java.awt.*;

public class ConstumButton extends JButton {
    public ConstumButton(String name , Color foreColor , Color backGroundColor , Font font ) {
            setText(name);
            setBackground(backGroundColor);
            setFont(font);
            setForeground(foreColor);

    }
}
