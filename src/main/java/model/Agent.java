package model;

public class Agent {

    private Utilisateur utilisateur;

    public Agent(Utilisateur utilisateur) {
        if (!"AGENT".equals(utilisateur.getRole())) {
            throw new IllegalArgumentException("Utilisateur n'est pas un AGENT");
        }
        this.utilisateur = utilisateur;
    }

    public void ajouterBien() {
        System.out.println("Agent ajoute un bien");
    }

    public void modifierBien() {
        System.out.println("Agent modifie un bien");
    }

    public void supprimerBien() {
        System.out.println("Agent supprime un bien");
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}