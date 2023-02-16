package presentation.vue.palette;

import javax.swing.*;
import java.awt.*;

public class CostumLabelValue extends JLabel {

    public CostumLabelValue(String text  , Color frColor , Font font) {
        setText(text);
        setForeground(frColor);
        setFont(font);}
}
