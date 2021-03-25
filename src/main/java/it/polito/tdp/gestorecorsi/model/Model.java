package it.polito.tdp.gestorecorsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.gestorecorsi.db.CorsoDAO;

public class Model 
{
	private CorsoDAO corsoDao;
	
	public Model()
	{
		this.corsoDao = new CorsoDAO();
	}
	
	public List<Corso> getCorsiByPeriodo(int periodo)
	{
		return this.corsoDao.getCorsiByPeriodo(periodo);
	}
	
	public Map<Corso,Integer> getNumIscrittiByPeriodo(int periodo)
	{
		return this.corsoDao.getNumIscrittiByPeriodo(periodo);
	}
	
}
