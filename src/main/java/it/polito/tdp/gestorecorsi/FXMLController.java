/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.gestorecorsi;

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
import javafx.scene.input.KeyEvent;

public class FXMLController 
{
	private GestoreCorsiModel model;
	
    @FXML 
    private ResourceBundle resources;

    @FXML 
    private URL location;

    @FXML 
    private TextField txtPeriodo; 

    @FXML 
    private TextField txtCorso; 

    @FXML 
    private Button btnCorsiPerPeriodo;

    @FXML 
    private Button btnNumeroStudenti; 

    @FXML 
    private Button btnStudenti; 

    @FXML 
    private Button btnDivisioneStudenti; 

    @FXML 
    private TextArea txtRisultato; 

    @FXML
    void handleCorsiPerPeriodo(ActionEvent event) 
    {
    	String periodoString = this.txtPeriodo.getText();
    	
    	if(periodoString == null || periodoString.length() > 4 || periodoString.isBlank())
    	{
    		printPeriodoError();
			return;
    	}
    	
    	int periodo;
    	
    	try
		{
			periodo = Integer.parseInt(periodoString);
		}
		catch(NumberFormatException e)
		{
			printPeriodoError();
			return;
		}
    	
    	if(periodo < 1 || periodo > 2)
    	{
    		printPeriodoError();
			return;
    	}
    	
    	List<Corso> corsi = this.model.getCorsiByPeriodo(periodo);
    	
    	if(corsi.isEmpty())
    	{
        	this.txtRisultato.setText("Non esistono corsi per il periodo specificato(" + periodo + ")");
        	return;
    	}
    	
    	StringBuilder sb = new StringBuilder(); 
    	
    	for(Corso c : corsi)
    	{
    		if(sb.length() > 0)
    			sb.append('\n');
    		
    		sb.append(c.toString());
    	}
    	this.txtRisultato.setText(String.format("%-13s %-8s %-50s %-17s\n%s",
    								"CODICE CORSO", "CREDITI", "NOME", "PERIODO DIDATTICO",	sb.toString()));
    }
  
    private void printPeriodoError()
	{
		this.txtRisultato.setText("Devi inserire un numero (1 o 2) per il periodo di dattico");
	}

    @FXML
    void handleNumeroStudentiPerPeriodo(ActionEvent event) 
    {
    	String periodoString = this.txtPeriodo.getText();
    	
    	if(periodoString == null || periodoString.length() > 4 || periodoString.isBlank())
    	{
    		printPeriodoError();
			return;
    	}
    	
    	int periodo;
    	
    	try
		{
			periodo = Integer.parseInt(periodoString);
		}
		catch(NumberFormatException e)
		{
			printPeriodoError();
			return;
		}
    	
    	if(periodo < 1 || periodo > 2)
    	{
    		printPeriodoError();
			return;
    	}
    	
    	Map<Corso,Integer> iscrittiCorsi = this.model.getIscrittiCorsiByPeriodo(periodo);
    	
    	if(iscrittiCorsi.isEmpty())
    	{
        	this.txtRisultato.setText("Non esistono corsi per il periodo specificato(" + periodo + ")");
        	return;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for(Corso c : iscrittiCorsi.keySet())
    	{
    		if(sb.length() > 0)
    			sb.append("\n");
    		
    		int numIscritti = iscrittiCorsi.get(c);
    		sb.append(String.format("%-18d %-50s %-5d", c.getPeriodoDidattico(), c.getNome(), numIscritti));
    	}    	
    	
    	this.txtRisultato.setText(String.format("%-18s %-50s %-10s\n%s", "PERIODO DIDATTICO", "NOME CORSO", "ISCRITTI", sb.toString()));
    }
    
    @FXML
    void handleStampaStudentiCorso(ActionEvent event) 
    {
    	String codiceCorso = this.txtCorso.getText().toUpperCase();
    	
    	if(!this.model.esisteCorso(codiceCorso))
    	{
    		printCorsoInesistenteError(codiceCorso);
    		return;
    	}
    	
    	List<Studente> studenti = this.model.getStudentiByCorso(codiceCorso);
    	
    	if(studenti.isEmpty())
    	{
    		printCorsoSenzaIscrittiError(codiceCorso);
    		return;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for(Studente s : studenti)
    	{
    		if(sb.length() > 0)
    			sb.append("\n");
    		
    		sb.append(s.toString());
    	}
    	
    	this.txtRisultato.setText(String.format("%-10s %-25s %-25s %-10s\n%s",
    											"MATRICOLA", "NOME", "COGNOME", "CORSO DI STUDI", 
    											sb.toString()));
    }

    @FXML
    void handleStampaDivisioneCDSinCorso(ActionEvent event) 
    {    	
    	String codiceCorso = this.txtCorso.getText().toUpperCase();
    	
    	if(!this.model.esisteCorso(codiceCorso))
    	{
    		printCorsoInesistenteError(codiceCorso);
    		return;
    	}
    	
    	Map<String, Integer> divisioneCDS = this.model.getDivisioneCDSin(codiceCorso);
    	
    	if(divisioneCDS.isEmpty())
    	{
    		printCorsoSenzaIscrittiError(codiceCorso);
    		return;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for(String cds : divisioneCDS.keySet())
    	{
    		int numStudenti = divisioneCDS.get(cds);
    		
    		if(sb.length() > 0)
    			sb.append("\n");
    		
    		sb.append(String.format("%-20s %-16d", cds, numStudenti));
    	}
    	
    	this.txtRisultato.setText(String.format("%-20s %-16s\n%s", "CORSO DI STUDI", "NUMERO STUDENTI", sb.toString()));
    } 
    
    private void printCorsoInesistenteError(String codiceCorso)
    {
		this.txtRisultato.setText(String.format("Il corso \"%s\" non esiste!", codiceCorso));
    }
    
    private void printCorsoSenzaIscrittiError(String codiceCorso)
    {
		this.txtRisultato.setText(String.format("Il corso \"%s\" esiste, ma non ha iscritti!", codiceCorso));
    }
    
    @FXML
    void handlePeriodoDidattico(KeyEvent event)
    {
    	if(this.txtPeriodo.getText().isBlank())
    	{
    		this.btnCorsiPerPeriodo.setDisable(true);
    		this.btnNumeroStudenti.setDisable(true);
    	}
    	else 
    	{
    		this.btnCorsiPerPeriodo.setDisable(false);
    		this.btnNumeroStudenti.setDisable(false);
		}
    }
    
    @FXML
    void handleCodiceCorso(KeyEvent event)
    {
    	if(this.txtCorso.getText().isBlank())
    	{
    		this.btnStudenti.setDisable(true);
    		this.btnDivisioneStudenti.setDisable(true);
    	}
    	else 
    	{
    		this.btnStudenti.setDisable(false);
    		this.btnDivisioneStudenti.setDisable(false);
		}
    }

    @FXML 
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
    	this.txtRisultato.setStyle("-fx-font-family: monospaced");
    	this.txtRisultato.setFocusTraversable(false);
    }
    
}