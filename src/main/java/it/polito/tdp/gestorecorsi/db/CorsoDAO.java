package it.polito.tdp.gestorecorsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.polito.tdp.gestorecorsi.model.Corso;

//corrisponde alla tabella 'corso' del db
public class CorsoDAO
{
	public Set<String> getAllCodiciCorsi()
	{
		String querySql = "SELECT codins FROM corso";
		
		Set<String> codiciCorsi = new HashSet<>();
		
		try(Connection connection = DBConnect.getConnection())
		{
			Statement statement = connection.createStatement();
			ResultSet queryResult = statement.executeQuery(querySql);
			
			while(queryResult.next())
			{
				String codice = queryResult.getString("codins");
				codiciCorsi.add(codice);
			}
			
			queryResult.close();
			statement.close();
		}
		catch(SQLException sqle)
		{
			throw new RuntimeException("SQL error in .getAllCodiciCorsi()", sqle);
		}
		
		return codiciCorsi;
	}
	
	
	public List<Corso> getCorsiByPeriodo(int periodo)
	{
		StringBuilder sqlQuery = new StringBuilder()
									.append("SELECT * ")
									.append("FROM corso ")
									.append("WHERE pd = ?");
		
		List<Corso> result = new ArrayList<>();
		
		try( Connection connection = DBConnect.getConnection() )
		{
			PreparedStatement statement = connection.prepareStatement(sqlQuery.toString());
			statement.setInt(1, periodo);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				Corso newCorso = new Corso(resultSet.getString("codins"), 
										   resultSet.getInt("crediti"), 
										   resultSet.getString("nome"), 
										   resultSet.getInt("pd"));
				result.add(newCorso);
			}
			
			resultSet.close();
			statement.close();
		}
		catch (SQLException sqle) 
		{
			throw new RuntimeException("SQL Error in .getCorsiByPeriodo()",sqle);
		}
		
		return result;
	}
	
	public Map<Corso,Integer> getIscrittiCorsiByPeriodo(int periodo)
	{
		StringBuilder sqlQuery = new StringBuilder()
				.append("SELECT c.codins, c.crediti, c.nome, c.pd, COUNT(*) as tot ")
				.append("FROM corso c, iscrizione i ")
				.append("WHERE c.codins = i.codins AND c.pd = ? ")
				.append("GROUP BY c.codins, c.crediti, c.nome, c.pd");

		Map<Corso,Integer> result = new HashMap<>();
		
		try( Connection connection = DBConnect.getConnection() )
		{
			PreparedStatement statement = connection.prepareStatement(sqlQuery.toString());
			statement.setInt(1, periodo);
			ResultSet resultSet = statement.executeQuery();
		
			while(resultSet.next())
			{
				Corso newCorso = new Corso(resultSet.getString("codins"), 
										   resultSet.getInt("crediti"), 
										   resultSet.getString("nome"), 
										   resultSet.getInt("pd"));
				
				result.put(newCorso, resultSet.getInt("tot"));
			}
		
			resultSet.close();
			statement.close();
		}
		catch (SQLException sqle) 
		{
			throw new RuntimeException("SQL error in .getIscrittiCorsiByPeriodo()", sqle);
		}
	
		return result;
	}
	
}
