package dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import config.HibernateUtil;
import dao.ClientDao;
import model.Client;

public class ClientDaoImpl implements ClientDao {

    public Client findById(Long id) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Client c = s.get(Client.class, id);
        s.close();
        return c;
    }

    public void save(Client client) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        s.saveOrUpdate(client);
        tx.commit();
        s.close();
    }
}