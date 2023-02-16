package presentation.vue.ClientFrame;

import dao.DataBase;
import forms.RetirerFormValidator;
import forms.VerserFormValidator;
import forms.VirementFormValidator;
import presentation.modele.Compte;
import presentation.modele.Log;
import presentation.vue.palette.ConstumButton;
import presentation.vue.palette.ConstumeTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Map;

public class AccountOperationsPanel extends JPanel {
    private Compte compteActuel;

    private JLabel verserLabel;
    private ConstumeTextField verserText;
    private ConstumButton verserBtn;

    private JLabel retirerLabel;
    private ConstumeTextField retirerText;
    private ConstumButton retirerBtn;

    private JLabel numCompteLabel;
    private ConstumeTextField numCompteText;
    private  JLabel virementMontantLabel;
    private ConstumeTextField virementMontantText;
    private ConstumButton virementBtn;

    private void initComponents(){
        Font font = new Font("Optima", Font.PLAIN, 18);

        verserLabel = new JLabel("Montant à vérser");
        verserLabel.setFont(font);
        verserText = new ConstumeTextField();
        verserBtn = new ConstumButton("Verser", null , null , null);

        retirerLabel = new JLabel("Montant à retirer");
        retirerLabel.setFont(font);
        retirerText = new ConstumeTextField();
        retirerBtn = new ConstumButton("Retirer" , null , null , null);

        numCompteLabel = new JLabel("Numéro de compte");
        numCompteLabel.setFont(font);
        numCompteText = new ConstumeTextField();
        virementMontantLabel = new JLabel("Montant de virement");
        virementMontantLabel.setFont(font);
        virementMontantText = new ConstumeTextField();
        virementBtn = new ConstumButton("Effectuer", null , null , null);

    }


    private void afficherLesErreursEtImprimerLeTiquet(Map<String , String> errors, Log log, String resultMessage){
        if(!errors.isEmpty()){
            String errorMessage = "";
            int size = errors.keySet().size();
            String[] errorKeys = errors.keySet().toArray(new String[0]);
            for (int i = 0; i < size; i++) errorMessage += errorKeys[i] + " > " + errors.get(errorKeys[i]) + "\n";
            JOptionPane.showMessageDialog(null, errorMessage, "ERRORS", JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showConfirmDialog(null, resultMessage, log.getMessage(), JOptionPane.OK_OPTION);
        }
    }

    private void initActions(){
        verserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String montantS = verserText.getText();
                VerserFormValidator form = new VerserFormValidator();

                Log log  = form.validerVersement(compteActuel, montantS);
                new LogsTablePanel(DataBase.getLogsDao().findByIdAccount(compteActuel.getNumeroCompte()),compteActuel ).refresh();

                afficherLesErreursEtImprimerLeTiquet(form.Errors(), log, form.ResultMsg());
            }
        });

        retirerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String montantS = retirerText.getText();
                RetirerFormValidator form = new RetirerFormValidator();

                Log ticket  = form.validerRetrait(compteActuel, montantS);
                new LogsTablePanel(DataBase.getLogsDao().findByIdAccount(compteActuel.getNumeroCompte()),compteActuel ).refresh();

                afficherLesErreursEtImprimerLeTiquet(form.Errors(), ticket, form.ResultMsg());
            }
        });

        virementBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String montantS = virementMontantText.getText();
                Long numCompte = Long.parseLong(numCompteText.getText());

                VirementFormValidator form = new VirementFormValidator();

                Log log  = form.validerVirement(compteActuel, montantS, numCompte);
                new LogsTablePanel(DataBase.getLogsDao().findByIdAccount(compteActuel.getNumeroCompte()),compteActuel ).refresh();

                afficherLesErreursEtImprimerLeTiquet(form.Errors(), log, form.ResultMsg());
            }
        });
    }
    private void initPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.insets = new Insets(0, 0, 15, 0);
        add(verserLabel, gbc);
        gbc.gridy++;
        add(verserText, gbc);
        gbc.insets = new Insets(0, 0, 45, 0);
        gbc.gridy++;
        add(verserBtn, gbc);

        gbc.insets = new Insets(0, 0, 15, 0);
        gbc.gridy++;
        add(retirerLabel, gbc);
        gbc.gridy++;
        add(retirerText, gbc);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 45, 0);
        add(retirerBtn, gbc);

        gbc.insets = new Insets(0, 0, 15, 0);
        gbc.gridy++;
        add(numCompteLabel, gbc);
        gbc.gridy++;
        add(numCompteText, gbc);
        gbc.gridy++;
        add(virementMontantLabel, gbc);
        gbc.gridy++;
        add(virementMontantText, gbc);
        gbc.gridy++;
        add(virementBtn, gbc);
    }

    public AccountOperationsPanel(Compte compteActuel) {
        this.compteActuel = compteActuel;
        initComponents();
        initActions();
        initPanel();
    }
}
