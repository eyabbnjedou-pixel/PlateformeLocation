package dao;

import model.Client;

public interface ClientDao {
    Client findById(Long id);
    void save(Client client);
}