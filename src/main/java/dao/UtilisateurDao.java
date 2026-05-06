package dao;

import java.util.List;
import model.Utilisateur;

public interface UtilisateurDao {

    Utilisateur findById(Long id);

    List<Utilisateur> findAll();

    void save(Utilisateur utilisateur);

    void delete(Utilisateur utilisateur);

    Utilisateur findByLogin(String login);

	Utilisateur findByLoginAndPassword(String login, String password);

	void delete(Long id);
}