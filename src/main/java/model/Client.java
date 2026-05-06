package model;

import javax.persistence.Entity;

@Entity
public class Client extends Utilisateur {

    public Client() {
        super();
        this.setRole("CLIENT");
    }

    // réserver un bien
    public void reserverBien() {
        System.out.println("Client réserve un bien");
    }

    // annuler une réservation
    public void annulerReservation() {
        System.out.println("Client annule une réservation");
    }
}