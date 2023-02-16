package presentation.vue.ClientFrame;

import presentation.modele.Compte;
import presentation.vue.palette.CostumLabel;
import presentation.vue.palette.CostumLabelValue;

import javax.swing.*;
import java.awt.*;

public class InfoPanel  extends JPanel {
    private CostumLabel lblInfoCompte,lblNumCompte ,lblsolde,lbldateCreation;
    private CostumLabelValue numCompteValue,soldeValue,dateCreationValue , nomClient;
    private Compte compte;

    private void initLabels()
    {
        Font fontTitle = new Font("Optima", Font.BOLD, 19);
        Font fontValue = new Font("Optima", Font.PLAIN, 19);
        lblsolde = new CostumLabel("Solde :" , new Color(162, 23, 23) , fontTitle);
        lblNumCompte = new CostumLabel("Numero de COmpte :" , new Color(162, 23, 23) , fontTitle);
        lblInfoCompte = new CostumLabel("Nom complet :" , new Color(162, 23, 23) , fontTitle);
        lbldateCreation = new CostumLabel("Date :" , new Color(162, 23, 23) , fontTitle);

        numCompteValue = new CostumLabelValue("b-o00"+compte.getNumeroCompte() ,Color.black , fontValue );
        soldeValue = new CostumLabelValue(compte.getSolde().toString() ,Color.black , fontValue );
        dateCreationValue = new CostumLabelValue(compte.getDateCreation().toString() ,Color.black , fontValue );
        nomClient = new CostumLabelValue(compte.getPropriétaire().getNomComplet() ,Color.black , fontValue );
    }
    private void initPanel() {
        initLabels();
     /*   setLayout(new GridLayout(0,2));
        add(lblNumCompte);
        add(numCompteValue);

        add(lblsolde);
        add(lbldateCreation);

        add(soldeValue);
        add(dateCreationValue);*/
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        setMinimumSize(new Dimension(1000, 600));
        setMaximumSize(new Dimension(1000, 600));
        setPreferredSize(new Dimension(1000, 600));

        gbc.gridx = 0;
        gbc.gridy = 0;
        //gbc.anchor = GridBagConstraints.BOTH;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets = new Insets(0, 0, 15, 0);
        gbc.gridwidth = 2;
        add(nomClient, gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 15, 15);
        add(lblInfoCompte, gbc);
        gbc.gridy++;
        add(lblNumCompte, gbc);
        gbc.gridy++;
        add(lblsolde, gbc);
        gbc.gridy++;
        add(lbldateCreation, gbc);


        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 15, 0);
        add(nomClient, gbc);
        gbc.gridy++;
        add(numCompteValue, gbc);
        gbc.gridy++;
        add(soldeValue, gbc);
        gbc.gridy++;
        add(dateCreationValue, gbc);
    }

    public InfoPanel(Compte compte)
    {
        this.compte = compte;
        initPanel();
    }




}
