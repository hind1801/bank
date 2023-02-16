package presentation.vue.Login;


import dao.DataBase;
import forms.LoginFormValidator;
import presentation.modele.Client;
import presentation.modele.Utilisateur;
import presentation.vue.ClientFrame.ChoiseAccountClient;
import presentation.vue.MyFrame;

import javax.swing.*;
import java.awt.*;


public class LoginPalet extends JFrame {

	private JLabel   lblLogin , lblPassword;
	private JTextField txtLogin ;
	private JPasswordField txtPassword;
	private JButton submitForm ;
	private JPanel panelHeader, panelCentre , panelBottom , panelGeneral;

	private void initLabels(){
		lblLogin = new JLabel("User :");
		lblLogin.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		lblLogin.setBounds(30, 37, 250, 23);
	//	lblLogin.setIcon(emailIcon);

		lblPassword = new JLabel("Password : ");
		lblPassword.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		lblPassword.setBounds(30, 130, 250, 23);
	//	lblPassword.setIcon(passwordIcon);
	}
	 private void initTextArea(){
		txtLogin = new JTextField();
		txtLogin.setBounds(45,65 , 300, 40);
		txtLogin.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(45, 158, 300, 40);
		txtPassword.setColumns(10);
	}
	private void initButtons(){
		submitForm = new JButton("Se Connecter \r\n");
		submitForm.setFont(new Font("Berlin Sans FB Demi", Font.BOLD,23));
		submitForm.setBounds(20,260 , 300, 40);
		submitForm.setBackground(Color.red);
		submitForm.setForeground(Color.white);
		submitForm.setBorderPainted(false);
		submitForm.addActionListener(e -> {
		LoginFormValidator lf	= new LoginFormValidator();
			Utilisateur c = lf.validerSession(txtLogin.getText() , txtPassword.getText());
			if (c!=null){
				if(c.getRole().equals("Admin"))
					new MyFrame("Hind Bank");
				else
					new ChoiseAccountClient((Client)c );
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(null,"Coordonées Invalides",null,JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	private void initPanel() {
		initButtons();
		initLabels();
		initTextArea();
		panelHeader = new JPanel();
		panelHeader.setBackground(Color.red);

		panelGeneral = new JPanel();

		panelCentre = new JPanel();
		panelCentre.setBackground(Color.white);
		panelCentre.setLayout(null);
		panelCentre.add(lblLogin);
		panelCentre.add(lblPassword);
		panelCentre.add(txtLogin);
		panelCentre.add(txtPassword);

		panelBottom = new JPanel();
		panelBottom.setBackground(new Color(0xF5F5F5));

		panelBottom.add(submitForm);

		panelGeneral = new JPanel();
		panelGeneral.setLayout(new BorderLayout());
		panelGeneral.setBackground(Color.blue);
		panelGeneral.add(panelHeader,BorderLayout.NORTH);
		panelGeneral.add(panelCentre,BorderLayout.CENTER);
		panelGeneral.add(panelBottom,BorderLayout.SOUTH);
	}
	public LoginPalet(){

		initPanel();
		getContentPane().add(panelGeneral);
		setTitle("Login Dashboard");
		setLocation(0,0);
		setUndecorated(true);
		setSize(400,370);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}


	public static void main(String[] args) {
		 new LoginPalet();


	}

	
	

}
