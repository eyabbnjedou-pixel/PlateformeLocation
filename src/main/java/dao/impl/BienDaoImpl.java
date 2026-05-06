package dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import config.HibernateUtil;
import dao.BienDao;
import model.Bien;

public class BienDaoImpl implements BienDao {

    @Override
    public Bien findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Bien.class, id);
        }
    }

    @Override
    public List<Bien> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Bien ORDER BY nom", Bien.class).list();
        }
    }

    @Override
    public void save(Bien bien) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.saveOrUpdate(bien);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Bien bien = session.get(Bien.class, id);
            if (bien != null) {
                session.delete(bien);
            }

            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @Override
    public List<Bien> findAvailable(LocalDateTime debut,
                                    LocalDateTime fin,
                                    Integer capaciteMin,
                                    String equipementsContains,
                                    String localisation) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            StringBuilder hql = new StringBuilder("FROM Bien b WHERE b.active = true");

            if (capaciteMin != null) {
                hql.append(" AND b.capacite >= :capaciteMin");
            }

            if (equipementsContains != null && !equipementsContains.isEmpty()) {
                hql.append(" AND lower(b.equipements) LIKE lower(:equipements)");
            }

            if (localisation != null && !localisation.trim().isEmpty()) {
                hql.append(" AND lower(b.localisation) LIKE lower(:localisation)");
            }

            hql.append("""
                AND b.id NOT IN (
                    SELECT r.bien.id
                    FROM Reservation r
                    WHERE r.statut IN ('EN_ATTENTE','CONFIRMEE','PAYEE')
                    AND r.dateHeureDebut < :fin
                    AND r.dateHeureFin > :debut
                )
            """);

            Query<Bien> query = session.createQuery(hql.toString(), Bien.class);

            if (capaciteMin != null)
                query.setParameter("capaciteMin", capaciteMin);

            if (equipementsContains != null && !equipementsContains.isEmpty())
                query.setParameter("equipements", "%" + equipementsContains + "%");

            if (localisation != null && !localisation.trim().isEmpty())
                query.setParameter("localisation", "%" + localisation.trim() + "%");

            query.setParameter("debut", debut);
            query.setParameter("fin", fin);

            return query.list();
        }
    }

    @Override
    public List<Bien> findDisponibles() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Bien WHERE active = true", Bien.class).list();
        }
    }
}