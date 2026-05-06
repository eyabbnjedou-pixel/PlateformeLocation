package dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import config.HibernateUtil;
import dao.ReservationDao;
import model.Reservation;
import model.StatutReservation;
import model.Utilisateur;

public class ReservationDaoImpl implements ReservationDao {

    public Reservation findById(Long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Reservation r = s.get(Reservation.class, id);
        s.close();
        return r;
    }

    public List<Reservation> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Reservation> list = s.createQuery("from Reservation", Reservation.class).list();
        s.close();
        return list;
    }

    public void save(Reservation r) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(r);
        tx.commit();
        s.close();
    }

    public void delete(Reservation r) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.delete(r);
        tx.commit();
        s.close();
    }

    @Override
    public List<Reservation> findByUtilisateur(Utilisateur user) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Reservation> list = s.createQuery(
            "FROM Reservation r WHERE r.utilisateur = :user",
            Reservation.class
        ).setParameter("user", user)
         .list();

        s.close();
        return list;
    }

    public List<Reservation> findByStatut(StatutReservation statut) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Reservation> list = s.createQuery(
                "FROM Reservation r WHERE r.statut = :statut",
                Reservation.class)
                .setParameter("statut", statut)
                .list();

        s.close();
        return list;
    }

    public List<Reservation> findByBien(Long idBien) {
        Session s = HibernateUtil.getSessionFactory().openSession();

        List<Reservation> list = s.createQuery(
                "FROM Reservation r WHERE r.bien.id = :id",
                Reservation.class)
                .setParameter("id", idBien)
                .list();

        s.close();
        return list;
    }
    @Override
    public void updateStatus(Long id, String newStatus, String defaultComment) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            session.createQuery(
                "UPDATE Reservation r SET r.statut = :statut, r.commentaire = :comment WHERE r.id = :id"
            )
            .setParameter("statut", StatutReservation.valueOf(newStatus))
            .setParameter("comment", defaultComment)
            .setParameter("id", id)
            .executeUpdate();

            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;

        } finally {
            session.close();
        }
    }
}