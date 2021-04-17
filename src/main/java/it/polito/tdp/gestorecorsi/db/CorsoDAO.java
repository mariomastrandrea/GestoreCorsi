package it.polito.tdp.gestorecorsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.gestorecorsi.model.Corso;
import it.polito.tdp.gestorecorsi.model.Studente;

//corrisponde alla tabella 'corso' del db
public class CorsoDAO
{

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
				Corso newCorso = new Corso(resultSet.getString("codins"), resultSet.getInt("crediti"), resultSet.getString("nome"), resultSet.getInt("pd"));
				result.add(newCorso);
			}
			
			resultSet.close();
			statement.close();
		}
		catch (SQLException sqle) 
		{
			throw new RuntimeException(sqle);
		}
		
		return result;
	}
	
	public Map<Corso,Integer> getNumIscrittiByPeriodo(int periodo)
	{
		StringBuilder sqlQuery = new StringBuilder()
				.append("SELECT c.codins, c.nome, c.crediti, c.pd, COUNT(*) as tot ")
				.append("FROM corso c, iscrizione i ")
				.append("WHERE c.codins = i.codins AND c.pd = ? ")
				.append("GROUP BY c.codins, c.nome, c.crediti, c.pd");

		Map<Corso,Integer> result = new HashMap<>();
		
		try( Connection connection = DBConnect.getConnection() )
		{
			PreparedStatement statement = connection.prepareStatement(sqlQuery.toString());
			statement.setInt(1, periodo);
			ResultSet resultSet = statement.executeQuery();
		
			while(resultSet.next())
			{
				Corso newCorso = new Corso(resultSet.getString("codins"), resultSet.getInt("crediti"), resultSet.getString("nome"), resultSet.getInt("pd"));
				result.put(newCorso, resultSet.getInt("tot"));
			}
		
			resultSet.close();
			statement.close();
		}
		catch (SQLException sqle) 
		{
			throw new RuntimeException(sqle);
		}
	
		return result;
	}

	public List<Studente> getStudentiByCorso(Corso corso)
	{
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS " 
					+ "FROM studente s, iscrizione i "
					+ "WHERE s.matricola = i.matricola "
					+		"AND i.codins = ?";
		
		
		List<Studente> result = new LinkedList<Studente>();
		try
		{
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodiceInsegnamento());
			ResultSet rs = st.executeQuery();
			
			while(rs.next())
			{
				Studente s = new Studente(rs.getInt("matricola"), 
						rs.getString("nome"), rs.getString("cognome"), rs.getString("CDS"));
				result.add(s);
			}
			
			rs.close();
			st.close();
			conn.close();
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
			throw new RuntimeException("Problema in metodo 'getStudentiByCorso'", sqle);
			
		}
		return result;
	}

	public boolean esisteCorso(Corso corso)
	{
		String sql = "SELECT codins FROM corso WHERE codins = ?";
		boolean result = false;
		
		try
		{
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodiceInsegnamento());
			ResultSet rs = st.executeQuery();
			
			result = rs.next();
			
			rs.close();
			st.close();
			conn.close();
		}
		catch(SQLException sqle)
		{
			sqle.printStackTrace();
			throw new RuntimeException("Problema in metodo 'esisteCorso'", sqle);
		}
		return result;
	}
	
	public Map<String, Integer> getDivisioneStudenti(Corso corso)
	{
		String sql = "SELECT s.CDS, COUNT(*) AS tot "
					+ "FROM studente s, iscrizione i "
					+ "WHERE s.matricola = i.matricola "
					+		"AND i.codins = ? AND s.CDS <> '' "
					+ "GROUP BY s.CDS";
	
	
	Map<String, Integer> result = new HashMap<String, Integer>();
	try
	{
		Connection conn = DBConnect.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, corso.getCodiceInsegnamento());
		ResultSet rs = st.executeQuery();
		
		while(rs.next())
		{
			result.put(rs.getString("CDS"), rs.getInt("tot"));
		}
		
		rs.close();
		st.close();
		conn.close();
	}
	catch(SQLException sqle)
	{
		sqle.printStackTrace();
		throw new RuntimeException("Problema in metodo 'getDivisioneStudenti'", sqle);
		
	}
	return result;
}
	
}
