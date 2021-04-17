package it.polito.tdp.gestorecorsi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.gestorecorsi.db.CorsoDAO;

public class GestoreCorsiModel 
{
	private CorsoDAO corsoDao;
						
	public GestoreCorsiModel()
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
	
	public List<Studente> getStudentiByCorso(String codiceCorso)
	{
		return corsoDao.getStudentiByCorso(new Corso(codiceCorso, null, null, null));
	}

	public boolean esisteCorso(String codiceCorso)
	{
		return corsoDao.esisteCorso(new Corso(codiceCorso, null, null, null));
	}
	
	public Map<String,Integer> getDivisioneCDS(String codiceCorso)
	{
		
		Map<String, Integer> divisione = new HashMap<String,Integer>();
		List<Studente> studenti = this.getStudentiByCorso(codiceCorso);
		
		for(Studente s : studenti)
		{
			String corso = s.getCds();
			if(corso != null && !corso.isBlank())
			{
				if(!divisione.containsKey(corso))
					divisione.put(corso, 0);
				
				divisione.put(corso, divisione.get(corso) + 1);	
			}
		}
		return divisione;
		
		/*
		return this.corsoDao.getDivisioneStudenti(new Corso(codiceCorso, null, null, null));
		*/
	}
	
	
}
