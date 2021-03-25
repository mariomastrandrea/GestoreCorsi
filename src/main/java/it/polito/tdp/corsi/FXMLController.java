/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.corsi;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.gestorecorsi.model.Corso;
import it.polito.tdp.gestorecorsi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController 
{

	@SuppressWarnings("unused")
	private Model model;
	
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
    	
    	for(Corso c : corsi)
    	{
    		this.txtRisultato.appendText(String.format("%s\n",c.toString()));
    	}
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

    }

    @FXML
    void stampaStudenti(ActionEvent event) 
    {

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
    
    public void setModel(Model model) 
    {
    	this.model = model;
    }
    
    
}