package dao;


import presentation.modele.Utilisateur;

public interface IUtilisateurDAO extends IDao<Utilisateur, Long> {

    Utilisateur findByLoginAndPass(String login, String pass);
}
