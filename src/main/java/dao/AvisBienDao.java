package dao;

import java.util.List;
import model.AvisBien;

public interface AvisBienDao {

    List<AvisBien> findByBien(Long bienId);

    AvisBien findByBienAndUtilisateur(Long bienId, Long userId);

    Double getAverageNoteByBien(Long bienId);

    void save(AvisBien avis);

    void delete(Long id);

	List<AvisBien> findAll();

	
}