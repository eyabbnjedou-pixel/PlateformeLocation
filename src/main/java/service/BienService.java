package service;

import dao.BienDao;
import dao.impl.BienDaoImpl;
import model.Bien;

import java.time.LocalDateTime;
import java.util.List;

public class BienService {

    private BienDao bienDao = new BienDaoImpl();

    public void ajouterBien(Bien bien) throws Exception {

        bien.setActive(true);

        bienDao.save(bien);

        System.out.println("🏠 Bien ajouté !");
    }

    public List<Bien> getDisponibles(LocalDateTime debut,
                                     LocalDateTime fin,
                                     Integer capaciteMin,
                                     String equipementsContains,
                                     String localisation) throws Exception {

        return bienDao.findAvailable(
                debut,
                fin,
                capaciteMin,
                equipementsContains,
                localisation
        );
    }
}