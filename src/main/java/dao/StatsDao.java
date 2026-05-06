package dao;

import java.util.List;
import java.util.Map;

import model.Bien;
import model.BienStats;

public interface StatsDao {

    long countTotalReservations() throws Exception;

    Map<String, Long> countReservationsByStatus() throws Exception;

    List<BienStats> topbiens(int limit) throws Exception;

    List<Bien> topbiens() throws Exception;
}