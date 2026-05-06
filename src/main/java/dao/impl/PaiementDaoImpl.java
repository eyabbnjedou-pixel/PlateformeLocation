package dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import config.HibernateUtil;
import dao.PaiementDao;
import model.Paiement;

public class PaiementDaoImpl implements PaiementDao {

    public void save(Paiement p) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(p);
        tx.commit();
        s.close();
    }
}