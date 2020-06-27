package it.polito.tdp.crimes.model;

import java.time.LocalDateTime;


public class Evento implements Comparable<Evento>{

	private LocalDateTime dataOra;
	private double distanza;
	
	public Evento(LocalDateTime dataOra, double distanza) {
		super();
		this.dataOra = dataOra;
		this.distanza = distanza;
	}


	public LocalDateTime getDataOra() {
		return dataOra;
	}


	public void setDataOra(LocalDateTime dataOra) {
		this.dataOra = dataOra;
	}


	public double getDistanza() {
		return distanza;
	}


	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}


	@Override
	public int compareTo(Evento other) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
