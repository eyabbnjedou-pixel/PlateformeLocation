package dao;

import java.util.List;
import java.time.LocalDateTime;
import model.Bien;

public interface BienDao {

    Bien findById(Long id) throws Exception;

    List<Bien> findAll() throws Exception;

    void save(Bien bien) throws Exception;

    void delete(Long id) throws Exception;

    List<Bien> findAvailable(LocalDateTime debut,
                             LocalDateTime fin,
                             Integer capaciteMin,
                             String equipementsContains,
                             String localisation) throws Exception;

    List<Bien> findDisponibles() throws Exception;
}