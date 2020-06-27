package it.polito.tdp.crimes.model;

import com.javadocmd.simplelatlng.LatLng;

public class Distretto {

	private Integer codice;
	private LatLng latlng;
	
	public Distretto(Integer codice, LatLng latlng) {
		super();
		this.codice = codice;
		this.latlng = latlng;
	}
	public Integer getCodice() {
		return codice;
	}
	public void setCodice(Integer codice) {
		this.codice = codice;
	}
	public LatLng getLatlng() {
		return latlng;
	}
	public void setLatlng(LatLng latlng) {
		this.latlng = latlng;
	}
	
	public String toString() {
		return Integer.toString(this.codice);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
		result = prime * result + ((latlng == null) ? 0 : latlng.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Distretto other = (Distretto) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		if (latlng == null) {
			if (other.latlng != null)
				return false;
		} else if (!latlng.equals(other.latlng))
			return false;
		return true;
	}
	
	
}
