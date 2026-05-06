package dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import config.HibernateUtil;
import dao.CategorieBienDao;
import model.CategorieBien;

public class CategorieBienDaoImpl implements CategorieBienDao {

    public List<CategorieBien> findAll() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<CategorieBien> list = s.createQuery("from CategorieBien", CategorieBien.class).list();
        s.close();
        return list;
    }

    public void save(CategorieBien c) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(c);
        tx.commit();
        s.close();
    }
}