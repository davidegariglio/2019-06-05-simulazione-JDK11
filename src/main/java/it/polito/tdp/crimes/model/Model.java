package it.polito.tdp.crimes.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	
	private EventsDao dao;
	private Graph<Distretto, DefaultWeightedEdge> grafo;
	
	public Model() {
		this.dao = new EventsDao();
	}

	public List<Integer> getAnni() {
		return this.dao.getAnni();
	}

	public void creaGrafo(Integer anno) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, this.dao.getVertici());
		
		for(Distretto d1 : this.grafo.vertexSet()) {
			for(Distretto d2 : this.grafo.vertexSet()) {
				if(!d1.equals(d2) && this.grafo.containsVertex(d1) && this.grafo.containsVertex(d2) && this.grafo.getEdge(d1, d2) == null) {
					double peso = LatLngTool.distance(d1.getLatlng(), d2.getLatlng(), LengthUnit.KILOMETER);
					this.grafo.addEdge(d1, d2);
					this.grafo.setEdgeWeight(this.grafo.getEdge(d1, d2), peso);
				}
			}
		}	
	}
	
	public Integer getVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public Integer getArchi() {
		return this.grafo.edgeSet().size();
	}
	
}
