package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "avis_bien")
public class AvisBien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 relation مع Bien
    @ManyToOne
    @JoinColumn(name = "bien_id", nullable = false)
    private Bien bien;

    // 🔗 relation مع Utilisateur
    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false)
    private Utilisateur utilisateur;

    @Column(nullable = false)
    private int note; // 1 à 5

    @Column(length = 1000)
    private String commentaire;

    private LocalDateTime dateCreation;

    // ⚠️ هذوما مش مخزنين في DB (فقط affichage)
    @Transient
    private String bienNom;

    @Transient
    private String utilisateurLogin;

    // ✅ constructeur
    public AvisBien() {
        this.dateCreation = LocalDateTime.now();
    }

    // getters & setters

    public Long getId() {
        return id;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public String getBienNom() {
        return bienNom;
    }

    public void setBienNom(String bienNom) {
        this.bienNom = bienNom;
    }

    public String getUtilisateurLogin() {
        return utilisateurLogin;
    }

    public void setUtilisateurLogin(String utilisateurLogin) {
        this.utilisateurLogin = utilisateurLogin;
    }
}