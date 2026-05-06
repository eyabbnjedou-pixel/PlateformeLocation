package model;

public class StatsBien {

    private String nomBien;
    private long nombreReservations;

    public StatsBien() {}

    public StatsBien(String nomBien, long nombreReservations) {
        this.setNomBien(nomBien);
        this.setNombreReservations(nombreReservations);
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