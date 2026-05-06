package model;

public class BienStats {
    private String nomBien;
    private long nombreReservations;

    public BienStats() {
    }

    public BienStats(String nomBien, long nombreReservations) {
        this.nomBien = nomBien;
        this.nombreReservations = nombreReservations;
    }

    public String getNomBien() {
        return nomBien;
    }

    public void setNomBien(String nomBien) {
        this.nomBien = nomBien;
    }

    public long getNombreReservations() {
        return nombreReservations;
    }

    public void setNombreReservations(long nombreReservations) {
        this.nombreReservations = nombreReservations;
    }

	
}