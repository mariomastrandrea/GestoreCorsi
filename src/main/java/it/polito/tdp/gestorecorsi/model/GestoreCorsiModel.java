package it.polito.tdp.gestorecorsi.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polito.tdp.gestorecorsi.db.CorsoDAO;
import it.polito.tdp.gestorecorsi.db.StudenteDAO;

public class GestoreCorsiModel 
{
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	private Set<String> codiciCorsiEsistenti;
						
	
	public GestoreCorsiModel()
	{
		this.corsoDao = new CorsoDAO();
		this.studenteDao = new StudenteDAO();
	}
	
	//1
	public List<Corso> getCorsiByPeriodo(int periodo)
	{
		return this.corsoDao.getCorsiByPeriodo(periodo);
	}
	
	public Map<Corso,Integer> getIscrittiCorsiByPeriodo(int periodo)
	{
		return this.corsoDao.getIscrittiCorsiByPeriodo(periodo);
	}
	
	//2
	public List<Studente> getStudentiByCorso(String codiceCorso)
	{
		return this.studenteDao.getStudentiByCorso(codiceCorso);
	}

	public boolean esisteCorso(String codiceCorso)
	{
		if(this.codiciCorsiEsistenti == null)
			this.codiciCorsiEsistenti = this.corsoDao.getAllCodiciCorsi();
		
		return this.codiciCorsiEsistenti.contains(codiceCorso);		
	}
	
	public Map<String,Integer> getDivisioneCDSin(String codiceCorso)
	{
		/*
		 * >>> less efficient: it's better to use db
		 * 
		Map<String, Integer> divisione = new HashMap<String,Integer>();
		List<Studente> studenti = this.getStudentiByCorso(codiceCorso);
		
		for(Studente s : studenti)
		{
			String corsoDiStudi = s.getCds();
			if(corsoDiStudi != null && !corsoDiStudi.isBlank())
			{
				if(!divisione.containsKey(corsoDiStudi))
					divisione.put(corsoDiStudi, 0);
				
				int newCount = divisione.get(corsoDiStudi) + 1;
				divisione.put(corsoDiStudi, newCount);	
			}
		}
		return divisione;
		*/
		
		return this.studenteDao.getDivisioneStudentiIn(codiceCorso);
	}
}
