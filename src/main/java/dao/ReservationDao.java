package dao;

import java.util.List;
import model.Reservation;
import model.StatutReservation;
import model.Utilisateur;

public interface ReservationDao {

    Reservation findById(Long id);

    List<Reservation> findAll();

    void save(Reservation reservation);

    void delete(Reservation reservation);

    List<Reservation> findByUtilisateur(Utilisateur user);


    List<Reservation> findByStatut(StatutReservation statut);

    List<Reservation> findByBien(Long idBien);

	void updateStatus(Long id, String newStatus, String defaultComment);
}