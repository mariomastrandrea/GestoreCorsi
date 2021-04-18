package it.polito.tdp.gestorecorsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.gestorecorsi.model.Studente;

//corrisponde alla tabella 'studente' del db
public class StudenteDAO
{
	public List<Studente> getStudentiByCorso(String codiceCorso)
	{
		String sqlQuery = String.format("%s %s %s %s", 
									"SELECT s.matricola, s.cognome, s.nome, s.CDS",
									"FROM studente s, iscrizione i",
									"WHERE s.matricola = i.matricola",
											"AND i.codins = ?");
		
		List<Studente> result = new LinkedList<Studente>();
		
		try
		{
			Connection connection = DBConnect.getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, codiceCorso);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				Studente s = new Studente(rs.getInt("matricola"), 
										  rs.getString("nome"), 
										  rs.getString("cognome"), 
										  rs.getString("CDS"));
				result.add(s);
			}
			
			rs.close();
			statement.close();
			connection.close();
		}
		catch(SQLException sqle)
		{
			throw new RuntimeException("SQL error in .getStudentiByCorso()", sqle);
		}
		
		return result;
	}
	
	public Map<String, Integer> getDivisioneStudentiIn(String codiceCorso)
	{
		String sqlQuery = String.format("%s %s %s %s %s",
									"SELECT s.cds, COUNT(*) AS totStudenti",
									"FROM studente s, iscrizione i",
									"WHERE s.matricola = i.matricola AND i.codins = ?",
											"AND s.cds <> '' AND s.cds IS NOT NULL",
									"GROUP BY s.cds");
	
		Map<String, Integer> divisioneStudenti = new HashMap<String, Integer>();
		
		try(Connection connection = DBConnect.getConnection())
		{
			PreparedStatement statement = connection.prepareStatement(sqlQuery);
			statement.setString(1, codiceCorso);
			ResultSet queryResult = statement.executeQuery();
			
			while(queryResult.next())
			{
				divisioneStudenti.put(queryResult.getString("cds"), queryResult.getInt("totStudenti"));
			}
			
			queryResult.close();
			statement.close();
		}
		catch(SQLException sqle)
		{
			throw new RuntimeException("SQL Error in .getDivisioneStudenti()", sqle);
		}
		
		return divisioneStudenti;
	}
}
