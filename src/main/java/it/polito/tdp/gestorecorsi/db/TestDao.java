package it.polito.tdp.gestorecorsi.db;

import java.util.List;

import it.polito.tdp.gestorecorsi.model.Studente;

public class TestDao
{
	public static void main(String[] args)
	{
		StudenteDAO dao = new StudenteDAO();
		
		List<Studente> studentiCorso = dao.getStudentiByCorso("02CIXPG");
		
		for(Studente s : studentiCorso)
			System.out.println(s);

	}
}
