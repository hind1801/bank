package presentation.vue.ClientFrame;

import forms.ModifierClientFormValidator;
import presentation.modele.Client;
import presentation.modele.Sexe;
import presentation.vue.palette.ConstumButton;
import presentation.vue.palette.CostumLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EditInfoPanel extends JPanel {
        private CostumLabel prenom , nom , login , mdp , cin , email , tel ;
        private JTextField txtPrenom , txtNom , txtLogin , txtCin , txtEmail , txtTel;
        private JPasswordField txtPassword;
        private ConstumButton submit;
        private Client client;

        private void initLabel() {
            Font font = new Font("Optima", Font.PLAIN, 18);
            prenom = new CostumLabel("Prenom : " ,Color.black , font);
            nom = new CostumLabel("Nom : " ,Color.black , font);
            login = new CostumLabel("Login : " ,Color.black , font);
            mdp = new CostumLabel("Password : " ,Color.black , font);
            cin = new CostumLabel("CIN : " ,Color.black , font);
            email = new CostumLabel("Email : " ,Color.black , font);
            tel = new CostumLabel("Phone : " ,Color.black , font);
        }
        private void initTextField() {
            txtCin = new JTextField();
            txtPrenom = new JTextField();
            txtNom = new JTextField();
            txtLogin = new JTextField();
            txtEmail = new JTextField();
            txtTel = new JTextField();
            txtPassword = new JPasswordField();
            txtPrenom.setPreferredSize(new Dimension(400,30));
            txtCin.setPreferredSize(new Dimension(400,30));
            txtNom.setPreferredSize(new Dimension(400,30));
            txtLogin.setPreferredSize(new Dimension(400,30));
            txtEmail.setPreferredSize(new Dimension(400,30));
            txtTel.setPreferredSize(new Dimension(400,30));
            txtPassword.setPreferredSize(new Dimension(400,30));

            txtCin.setText(client.getCin());
            txtPrenom.setText(client.getPrenom());
            txtNom.setText(client.getNom());
            txtLogin.setText(client.getLogin());
            txtEmail.setText(client.getEmail());
            txtTel.setText(client.getTel());
            txtPassword.setText(client.getMotDePasse());

        }
        private void initButton() {
            submit = new ConstumButton("Edit" , Color.blue , Color.black , new Font("Optima" , Font.BOLD ,20));
        }
        private void initAction() {
            submit.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String prenomN = String.valueOf(txtPrenom.getText());
                    String nomN = String.valueOf(txtNom.getText());
                    String loginN = String.valueOf(txtLogin.getText());
                    String cinN = String.valueOf(txtCin.getText());
                    String emailN = String.valueOf(txtEmail.getText());
                    String telN = String.valueOf(txtTel.getText());
                    String mdpN = String.valueOf(txtPassword.getPassword());
                    ModifierClientFormValidator formValidator = new ModifierClientFormValidator();

                    client = formValidator.validerModification(client ,nomN , prenomN ,loginN ,mdpN ,emailN , cinN , telN ,client.getSexe());
                    if(formValidator.Errors().isEmpty())
                        JOptionPane.showMessageDialog(null, formValidator.ResultMsg(), "", JOptionPane.INFORMATION_MESSAGE);
                    else
                    {
                        String errorMessage = "";
                        int size = formValidator.Errors().keySet().size();
                        String[] errorKeys = formValidator.Errors().keySet().toArray(new String[0]);
                        for (int i = 0; i < size; i++) errorMessage += errorKeys[i] + " > " + formValidator.Errors().get(errorKeys[i]) + "\n";
                        JOptionPane.showMessageDialog(null, errorMessage, "ERRORS", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        }
        private void initPanel(){
        initButton();
            initAction();
            initLabel();
            initTextField();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 15, 0);

        add(prenom, gbc);
        gbc.gridy++;
        add(txtPrenom, gbc);
        gbc.gridy++;
        add(nom, gbc);
        gbc.gridy++;
        add(txtNom, gbc);
        gbc.gridy++;
        add(login, gbc);
        gbc.gridy++;
        add(txtLogin, gbc);
        gbc.gridy++;
        add(mdp, gbc);
        gbc.gridy++;
        add(txtPassword, gbc);
        gbc.gridy++;
        add(cin, gbc);
        gbc.gridy++;
        add(txtCin, gbc);
        gbc.gridy++;
        add(email, gbc);
        gbc.gridy++;
        add(txtEmail, gbc);
        gbc.gridy++;
        add(tel, gbc);
        gbc.gridy++;
        add(txtTel, gbc);
        gbc.gridy++;
        add(submit, gbc);


    }
        public EditInfoPanel(Client client){this.client=client; initPanel();}
}
