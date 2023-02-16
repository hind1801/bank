package dao.daoFiles;

import dao.IUtilisateurDAO;
import presentation.modele.Utilisateur;

import java.util.List;

public class UtilisateurDao implements IUtilisateurDAO {
    @Override
    public List<Utilisateur> findAll() {
        return null;
    }

    @Override
    public Utilisateur findById(Long aLong) {
        return null;
    }

    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        throw new RuntimeException("Message");
    }

    @Override
    public List<Utilisateur> saveAll(List<Utilisateur> liste) {
        return null;
    }

    @Override
    public Utilisateur update(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Boolean delete(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }

    @Override
    public Utilisateur findByLoginAndPass(String login, String pass) {



        return null;
    }
}
