/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.corsi;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.gestorecorsi.model.Corso;
import it.polito.tdp.gestorecorsi.model.GestoreCorsiModel;
import it.polito.tdp.gestorecorsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController 
{
	private GestoreCorsiModel model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPeriodo"
    private TextField txtPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorso"
    private TextField txtCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorsiPerPeriodo"
    private Button btnCorsiPerPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="btnNumeroStudenti"
    private Button btnNumeroStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnStudenti"
    private Button btnStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnDivisioneStudenti"
    private Button btnDivisioneStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void corsiPerPeriodo(ActionEvent event) 
    {
    	this.txtRisultato.clear();
    	String periodoString = this.txtPeriodo.getText();
    	if(periodoString == null || periodoString.length() > 4)
    	{
    		this.txtRisultato.setText("Devi inserire un numero (1 o 2) per il periodo di dattico");
			return;
    	}
    	
    	int periodo;
    	
    	try
		{
			periodo = Integer.parseInt(periodoString);
		}
		catch(NumberFormatException e)
		{
			this.txtRisultato.setText("Devi inserire un numero (1 o 2) per il periodo di dattico");
			return;
		}
    	
    	if(periodo < 1 || periodo > 2)
    	{
    		this.txtRisultato.setText("Devi inserire un numero (1 o 2) per il periodo di dattico");
			return;
    	}
    	
    	List<Corso> corsi = this.model.getCorsiByPeriodo(periodo);
    	
    	/*
    	for(Corso c : corsi)
    	{
    		this.txtRisultato.appendText(String.format("%s\n",c.toString()));
    	}
    	*/
    	StringBuilder sb = new StringBuilder();    	
    	for(Corso c : corsi)
    	{
    		if(sb.length() > 0)
    			sb.append('\n');
    		
    		sb.append(String.format("%-8s %-4d %-50s %-4d", 
    				c.getCodiceInsegnamento(), c.getCrediti(), c.getNome(), c.getPeriodoDidattico()));
    	}
    	txtRisultato.appendText(sb.toString());
    }

    @FXML
    void numeroStudenti(ActionEvent event) 
    {
    	this.txtRisultato.clear();
    	String periodoString = this.txtPeriodo.getText();
    	if(periodoString == null || periodoString.length() > 4)
    	{
    		this.txtRisultato.setText("Devi inserire un numero (1 o 2) per il periodo di dattico");
			return;
    	}
    	
    	int periodo;
    	
    	try
		{
			periodo = Integer.parseInt(periodoString);
		}
		catch(NumberFormatException e)
		{
			this.txtRisultato.setText("Devi inserire un numero (1 o 2) per il periodo di dattico");
			return;
		}
    	
    	if(periodo < 1 || periodo > 2)
    	{
    		this.txtRisultato.setText("Devi inserire un numero (1 o 2) per il periodo di dattico");
			return;
    	}
    	
    	Map<Corso,Integer> iscrittiCorsi = this.model.getNumIscrittiByPeriodo(periodo);
    	for(Corso c : iscrittiCorsi.keySet())
    	{
    		txtRisultato.appendText(c.toString());
    		int numIscritti = iscrittiCorsi.get(c);
    		txtRisultato.appendText(String.format("  numIscritti: %d\n", numIscritti));
    	}
    }

    @FXML
    void stampaDivisione(ActionEvent event) 
    {
    	txtRisultato.clear();
    	
    	String codice = txtCorso.getText();
    	
    	if(!model.esisteCorso(codice))
    	{
    		txtRisultato.appendText(String.format("Il corso \"%s\" non esiste!", codice));
    		return;
    	}
    	
    	Map<String, Integer> divisione = model.getDivisioneCDS(codice);
    	
    	for(String s : divisione.keySet())
    	{
    		txtRisultato.appendText(String.format("%s --> %d studenti\n", s, divisione.get(s)));
    	}
    }

    @FXML
    void stampaStudenti(ActionEvent event) 
    {
    	txtRisultato.clear();
    	String codice = txtCorso.getText();
    	List<Studente> studenti = model.getStudentiByCorso(codice);
    	
    	if(!model.esisteCorso(codice))
    	{
    		txtRisultato.appendText(String.format("Il corso \"%s\" non esiste!", codice));
    	}
    	
    	if(studenti.isEmpty())
    	{
    		txtRisultato.appendText(String.format("Il corso \"%s\" esiste, ma non ha iscritti!", codice));
    		return;
    	}
    	
    	for(Studente s : studenti)
    	{
    		txtRisultato.appendText(s + "\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() 
    {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(GestoreCorsiModel model) 
    {
    	this.model = model;
    	txtRisultato.setStyle("-fx-font-family: monospaced");
    }
    
    
}