package service;

import dao.UtilisateurDao;
import dao.impl.UtilisateurDaoImpl;
import model.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;

public class UtilisateurService {

    private UtilisateurDao userDao = new UtilisateurDaoImpl();

    public void register(Utilisateur user) {

        Utilisateur existing = userDao.findByLogin(user.getLogin());

        if (existing != null) {
            throw new RuntimeException("Login déjà utilisé !");
        }

        userDao.save(user);
        System.out.println("✅ Utilisateur créé !");
    }

    public Utilisateur login(String login, String password) {

        Utilisateur user = userDao.findByLogin(login);

        if (user == null) {
            return null;
        }

        // 🔐 BCrypt check
        if (BCrypt.checkpw(password, user.getMotDePasse())) {
            return user;
        }

        return null;
    }
}