package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double montant;
    private String methode;
    private String statut;

    private LocalDateTime datePaiement;

    @OneToOne
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

    public Paiement() {}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getMethode() {
		return methode;
	}

	public void setMethode(String methode) {
		this.methode = methode;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	
	public void setDatePaiement(LocalDateTime localDateTime) {
		this.datePaiement = localDateTime;
	}

	public LocalDateTime getDatePaiement() {
		return datePaiement;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

}