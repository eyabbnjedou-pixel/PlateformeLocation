package service;

import dao.ReservationDao;
import dao.impl.ReservationDaoImpl;
import model.*;

import java.time.LocalDateTime;

public class ReservationService {

    private ReservationDao reservationDao = new ReservationDaoImpl();

    public Reservation reserver(Utilisateur user, Bien bien,
                                LocalDateTime debut, LocalDateTime fin) {

        // ✅ validation
        if (!fin.isAfter(debut)) {
            throw new RuntimeException("❌ Fin doit être après début");
        }

        if (debut.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("❌ Date doit être dans le futur");
        }

        // 🔥 création réservation
        Reservation r = new Reservation();
        r.setUtilisateur(user);
        r.setBien(bien);
        r.setDateHeureDebut(debut);
        r.setDateHeureFin(fin);
        r.setDateCreation(LocalDateTime.now());
        r.setStatut(StatutReservation.EN_ATTENTE);
        long hours = java.time.Duration.between(debut, fin).toHours();

        double montant = hours * bien.getPrixParHeure(); // أو getPrixParHeure()

        r.setMontantTotal(montant);
        double montant1 = hours * bien.getPrixParHeure();
        r.setMontantTotal(montant1);
        reservationDao.save(r);

        return r;
    }

    public void annuler(Reservation r) {

        if (r.getStatut() == StatutReservation.ANNULEE) {
            throw new RuntimeException("Déjà annulée");
        }

        r.setStatut(StatutReservation.ANNULEE);

        reservationDao.save(r);
    }
}