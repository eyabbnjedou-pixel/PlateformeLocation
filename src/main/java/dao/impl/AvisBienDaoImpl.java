package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import config.HibernateUtil;
import dao.AvisBienDao;
import model.AvisBien;

public class AvisBienDaoImpl implements AvisBienDao {

    @Override
    public void save(AvisBien a) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = s.beginTransaction();

        s.saveOrUpdate(a);

        tx.commit();

        s.close();
    }

    @Override
    public List<AvisBien> findByBien(Long bienId) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        List<AvisBien> list = s.createQuery(
                "FROM AvisBien a WHERE a.bien.id = :id",
                AvisBien.class)
                .setParameter("id", bienId)
                .list();

        s.close();

        return list;
    }

    @Override
    public AvisBien findByBienAndUtilisateur(Long bienId, Long userId) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        AvisBien result = s.createQuery(
                "FROM AvisBien a WHERE a.bien.id = :b AND a.utilisateur.id = :u",
                AvisBien.class)
                .setParameter("b", bienId)
                .setParameter("u", userId)
                .uniqueResult();

        s.close();

        return result;
    }

    @Override
    public Double getAverageNoteByBien(Long bienId) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        Double avg = s.createQuery(
                "SELECT AVG(a.note) FROM AvisBien a WHERE a.bien.id = :id",
                Double.class)
                .setParameter("id", bienId)
                .uniqueResult();

        s.close();

        return (avg != null) ? avg : 0.0;
    }

    @Override
    public void delete(Long id) {

        Session s = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = s.beginTransaction();

        AvisBien a = s.get(AvisBien.class, id);

        if (a != null) {
            s.delete(a);
        }

        tx.commit();

        s.close();
    }
    @Override
    public List<AvisBien> findAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<AvisBien> list = null;

        try {
            list = session.createQuery("FROM AvisBien", AvisBien.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }
}