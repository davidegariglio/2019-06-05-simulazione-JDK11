package it.polito.tdp.crimes.model;

import java.time.LocalDate;
import java.util.PriorityQueue;

public class Simulator {
	
	private PriorityQueue<Evento> eventi;
	private LocalDate data;
	private Integer nAgenti;

	public Simulator(LocalDate data, Integer n) {
		this.data = data;
		this.nAgenti = n;
	}
	
	public void init() {
		this.eventi = new PriorityQueue<>();
	}

}
