package it.polito.tdp.crimes.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	
	private EventsDao dao;
	private List<Integer> vertici;
	private Graph<Integer, DefaultWeightedEdge> grafo;
	private Simulator s;
	
	public Model() {
		this.dao = new EventsDao();
	}

	public List<Integer> getAnni() {
		return this.dao.getAnni();
	}

	public void creaGrafo(Integer anno) {
		vertici = this.dao.getVertici();
		grafo = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, vertici);
		
		for(Integer v1 : this.grafo.vertexSet()) {
			for(Integer v2 : this.grafo.vertexSet()) {
				if(!v1.equals(v2)) {
					if(this.grafo.getEdge(v1, v2) == null) {
						Double latMediaV1 = dao.getLatMedia(anno, v1);
						Double latMediaV2 = dao.getLatMedia(anno, v2);
						
						Double lonMediaV1 = dao.getLonMedia(anno, v1);
						Double lonMediaV2 = dao.getLonMedia(anno, v2);
						
						Double distanzaMedia = LatLngTool.distance(new LatLng(latMediaV1,lonMediaV1), 
																	new LatLng(latMediaV2, lonMediaV2), 
																	LengthUnit.KILOMETER);
						
						Graphs.addEdgeWithVertices(this.grafo, v1, v2, distanzaMedia);
						
					}
				}
			}
		}
		System.out.println("Grafo creato!");
		System.out.println("# VERTICI: " +  this.grafo.vertexSet().size());
		System.out.println("# ARCHI: " +  this.grafo.edgeSet().size());

	}
	
	public Set<Integer> getVertici() {
		return this.grafo.vertexSet();
	}
	
	public Integer getArchi() {
		return this.grafo.edgeSet().size();
	}

	public List<Vicino> getVicini(Integer d) {
		List<Vicino> result = new ArrayList<>();
	
		for(Integer vicino : Graphs.neighborListOf(this.grafo, d))
			result.add(new Vicino(vicino, this.grafo.getEdgeWeight(this.grafo.getEdge(d, vicino))));
		
		Collections.sort(result);
		return result;
	}

	public List<Integer> getMesi(Integer anno) {
		return this.dao.getMesi(anno);
	}

	public List<Integer> getGiorni(Integer anno) {
		return this.dao.getGiorni(anno);

	}
	
	public Integer getDistrettoSimulazione(Integer anno) {
		return this.dao.getDistrettoSimulazione(anno);
	}

	public void simula(LocalDate data, Integer n) {
		this.s = new Simulator(data, n);
	}
	
}
