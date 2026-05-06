package dao.impl;

import java.util.*;

import org.hibernate.Session;

import config.HibernateUtil;
import dao.StatsDao;
import model.Bien;
import model.BienStats;

public class StatsDaoImpl implements StatsDao {

    @Override
    public long countTotalReservations() throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Long count = session.createQuery(
                "SELECT COUNT(r) FROM Reservation r",
                Long.class
        ).uniqueResult();

        session.close();
        return count != null ? count : 0;
    }

    @Override
    public Map<String, Long> countReservationsByStatus() throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Map<String, Long> map = new LinkedHashMap<>();

        map.put("EN_ATTENTE", 0L);
        map.put("VALIDEE", 0L);
        map.put("REFUSEE", 0L);
        map.put("ANNULEE", 0L);

        List<Object[]> results = session.createQuery(
                "SELECT r.statut, COUNT(r) " +
                "FROM Reservation r " +
                "GROUP BY r.statut",
                Object[].class
        ).list();

        for (Object[] row : results) {
            String statut = row[0].toString();
            Long count = (Long) row[1];

            map.put(statut, count);
        }

        session.close();
        return map;
    }

    // =========================
    // TOP BIENS AVEC LIMIT
    // =========================
    @Override
    public List<BienStats> topbiens(int limit) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Object[]> results = session.createQuery(
                "SELECT r.bien.nom, COUNT(r) " +
                "FROM Reservation r " +
                "WHERE r.statut IN ('EN_ATTENTE','VALIDEE') " +
                "GROUP BY r.bien.id, r.bien.nom " +
                "ORDER BY COUNT(r) DESC",
                Object[].class
        )
        .setMaxResults(limit)
        .list();

        List<BienStats> list = new ArrayList<>();

        for (Object[] row : results) {
            String nom = (String) row[0];
            Long nb = (Long) row[1];

            list.add(new BienStats(nom, nb));
        }

        session.close();
        return list;
    }

    // =========================
    // TOP BIENS SANS LIMIT
    // =========================
    @Override
    public List<Bien> topbiens() throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Bien> list = session.createQuery(
                "SELECT r.bien FROM Reservation r " +
                "GROUP BY r.bien.id " +
                "ORDER BY COUNT(r) DESC",
                Bien.class
        ).list();

        session.close();
        return list;
    }
}