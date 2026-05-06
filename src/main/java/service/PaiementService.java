package service;

import dao.PaiementDao;
import dao.impl.PaiementDaoImpl;
import model.*;

import java.time.LocalDateTime;

public class PaiementService {

    private PaiementDao paiementDao = new PaiementDaoImpl();

    public void payer(Reservation reservation) {

        Paiement p = new Paiement();
        p.setReservation(reservation);
        p.setMontant(reservation.getMontantTotal());
        p.setDatePaiement(LocalDateTime.now());

        paiementDao.save(p);

        System.out.println("💰 Paiement effectué !");
    }
}