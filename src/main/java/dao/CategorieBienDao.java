package dao;

import java.util.List;
import model.CategorieBien;

public interface CategorieBienDao {
    List<CategorieBien> findAll();
    void save(CategorieBien c);
}