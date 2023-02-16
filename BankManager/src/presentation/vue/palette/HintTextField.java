package presentation.vue.palette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class HintTextField extends JTextField {
	
	
	   Font gainFont = new Font("Optima", Font.BOLD, 18);
	   Font lostFont = new Font("Candara", Font.ITALIC, 17);
	   

	   public HintTextField(final String hint) {  
	   
	     setText(hint);  
	     setFont(lostFont);  
	     setForeground(Color.GRAY);
		 setHorizontalAlignment(JTextField.CENTER);


	     this.addFocusListener(new FocusAdapter() {  
	   
	       @Override  
	       public void focusGained(FocusEvent e) {  
	         if (getText().equals(hint)) {  
	           setText("");  
	           setFont(gainFont);  
	         } else {  
	           setText(getText());  
	           setFont(gainFont);  
	         }  
	       }  
	   
	       @Override  
	       public void focusLost(FocusEvent e) {  
	         if (getText().equals(hint)|| getText().length()==0) {  
	           setText(hint);  
	           setFont(lostFont);  
	           //setForeground(Color.GRAY);  
	           setForeground(new Color(186, 85, 211));
	         } else {  
	           setText(getText());  
	           setFont(gainFont);  
	           setForeground(new Color(186, 85, 211));  
	         }  
	       }  
	     });  
	   
	   }

	   public HintTextField(final String hint, Color gainColor, Color lostColor) {

		setText(hint);
		setFont(lostFont);
		setForeground(lostColor);
		setHorizontalAlignment(JTextField.CENTER);



		this.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(hint)) {
					setText("");
					setFont(gainFont);
					setForeground(gainColor);
				} else {
					setText(getText());
					setFont(gainFont);
					setForeground(gainColor);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (getText().equals(hint)|| getText().length()==0) {
					setText(hint);
					setFont(lostFont);
					setForeground(lostColor);
				} else {
					setText(getText());
					setFont(gainFont);
					setForeground(gainColor);
				}
			}
		});

	}










	public HintTextField(final String hint, Font f, Color textColor, Color backColor, boolean opaque) {

		setText(hint);
		setFont(f);
		setForeground(textColor);
		setBackground(backColor);
		setOpaque(opaque);

		this.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(hint)) {
					setText("");
					setFont(f);
				} else {
					setText(getText());
					setFont(f);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (getText().equals(hint)|| getText().length()==0) {
					setText(hint);
					setFont(lostFont);
					//setForeground(Color.GRAY);
					setForeground(textColor);
				} else {
					setText(getText());
					setFont(f);
					setForeground(Color.blue);
				}
			}
		});

	}

}
