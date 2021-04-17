package it.polito.tdp.gestorecorsi.db;

import it.polito.tdp.gestorecorsi.model.Corso;

public class TestDao
{
	public static void main(String[] args)
	{
		CorsoDAO dao = new CorsoDAO();
		System.out.println(dao.getStudentiByCorso(new Corso("02CIXPG",null,null,null)));
	}
}
