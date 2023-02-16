package presentation.vue.palette;

import javax.swing.*;
import java.awt.*;

public class CostumLabel extends JLabel {

    public CostumLabel(String text , Color frColor , Font font) {
        setText(text);

        setForeground(frColor);
        setFont(font);}


}
