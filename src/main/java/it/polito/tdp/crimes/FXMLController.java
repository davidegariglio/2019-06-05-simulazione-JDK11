/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import it.polito.tdp.crimes.model.Model;
import it.polito.tdp.crimes.model.Vicino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ComboBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxMese"
    private ComboBox<Integer> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="boxGiorno"
    private ComboBox<Integer> boxGiorno; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaReteCittadina"
    private Button btnCreaReteCittadina; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaReteCittadina(ActionEvent event) {
    	if(this.boxAnno.getValue() == null) {
    		txtResult.appendText("SELEZIONARE UN ANNO PRIMA DI CREARE LA RETE CITTADINA");
    		return;
    	}
    	this.model.creaGrafo(this.boxAnno.getValue());
    	this.txtResult.appendText(String.format("GRAFO CREATO CON %d VERTICI E %d ARCHI", this.model.getVertici().size(), this.model.getArchi()));
    	this.txtResult.appendText("DISTANZE TRA I VARI DISTRETTI: \n\n");
    	for(Integer d : this.model.getVertici()) {
    		txtResult.appendText(d+":\n");
    		for(Vicino v : this.model.getVicini(d)) {
    			txtResult.appendText(v.toString()+"\n");
    		}
    	}
    	this.boxMese.getItems().addAll(this.model.getMesi(this.boxAnno.getValue()));
    	this.boxGiorno.getItems().addAll(this.model.getGiorni(this.boxAnno.getValue()));

    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	Integer m;
    	Integer g;
    	Integer n;
    	
    	if(this.boxMese.getValue() == null) {
    		this.txtResult.appendText("OCCORRE SELZIONARE UN MESE");
    		return;
    	}
    	if(this.boxGiorno.getValue() == null) {
    		this.txtResult.appendText("OCCORRE SELZIONARE UN MESE");
    		return;
    	}
    	m = this.boxMese.getValue();
    	g = this.boxMese.getValue();
    	
    	try {
    		n = Integer.parseInt(this.txtN.getText());
    	}catch(NumberFormatException e) {
    		txtResult.appendText("INSERIRE UN NUMERO INTERO VALIDO NEL CAMPO N");
    		return;
    	}
    	if(n <1 || n > 10) {
    		txtResult.appendText("INSERIRE UN NUMERO COMPRESO TRA 1 E 10 (estremi inclusi)");
    		return;
    	}
    	LocalDate data = LocalDate.of(this.boxAnno.getValue(), m, g);
    	this.model.simula(data, n);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxGiorno != null : "fx:id=\"boxGiorno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaReteCittadina != null : "fx:id=\"btnCreaReteCittadina\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.boxAnno.getItems().addAll(this.model.getAnni());
    }
}
