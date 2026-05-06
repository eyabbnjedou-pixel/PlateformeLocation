package model;

public class Admin {

    private Utilisateur utilisateur;

    public Admin(Utilisateur utilisateur) {
        if (!"ADMIN".equals(utilisateur.getRole())) {
            throw new IllegalArgumentException("Utilisateur n'est pas un ADMIN");
        }
        this.utilisateur = utilisateur;
    }

    public void ajouterAgent(Utilisateur agent) {
        System.out.println("Admin ajoute un agent: " + agent.getLogin());
    }

    public void supprimerAgent(Utilisateur agent) {
        System.out.println("Admin supprime un agent: " + agent.getLogin());
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}