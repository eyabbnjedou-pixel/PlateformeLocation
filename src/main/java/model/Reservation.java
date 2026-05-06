package model;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 relation Salle (Bien)
    @ManyToOne
    @JoinColumn(name = "id_bien", nullable = false)
    private Bien bien;

    // 🔗 relation Utilisateur
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Utilisateur utilisateur;

    // ⏱️ dates
    @Column(name = "date_heure_debut", nullable = false)
    private LocalDateTime dateHeureDebut;

    @Column(name = "date_heure_fin", nullable = false)
    private LocalDateTime dateHeureFin;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    // 📌 statut
    @Enumerated(EnumType.STRING)
    private StatutReservation statut;

    // 💬 commentaire
    private String commentaire;

    // ⚡ bien active (optionnel)
    private Boolean bienActive;

    private double montantTotal;
    // ✅ constructeur
    public Reservation() {}

    // ===== GETTERS / SETTERS =====

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

    public LocalDateTime getDateHeureDebut() {
        return dateHeureDebut;
    }

    public void setDateHeureDebut(LocalDateTime dateHeureDebut) {
        this.dateHeureDebut = dateHeureDebut;
    }

    public LocalDateTime getDateHeureFin() {
        return dateHeureFin;
    }

    public void setDateHeureFin(LocalDateTime dateHeureFin) {
        this.dateHeureFin = dateHeureFin;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public StatutReservation getStatut() {
        return statut;
    }

    public void setStatut(StatutReservation statut) {
        this.statut = statut;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }


	public Boolean getBienActive() {
		return bienActive;
	}

	public void setBienActive(Boolean bienActive) {
		this.bienActive = bienActive;
	}

	public double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(double montantTotal) {
		this.montantTotal = montantTotal;
	}
}