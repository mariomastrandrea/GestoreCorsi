package it.polito.tdp.gestorecorsi.model;

public class Studente
{
	private Integer matricola;
	private String nome;
	private String cognome;
	private String cds;
	
	
	public Studente(int matricola, String nome, String cognome, String cds)
	{
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.cds = cds;
	}

	public Integer getMatricola()
	{
		return matricola;
	}

	public String getNome()
	{
		return nome;
	}

	public String getCognome()
	{
		return cognome;
	}

	public String getCds()
	{
		return cds;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cds == null) ? 0 : cds.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((matricola == null) ? 0 : matricola.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (cds == null)
		{
			if (other.cds != null)
				return false;
		}
		else if (!cds.equals(other.cds))
			return false;
		if (cognome == null)
		{
			if (other.cognome != null)
				return false;
		}
		else if (!cognome.equals(other.cognome))
			return false;
		if (matricola == null)
		{
			if (other.matricola != null)
				return false;
		}
		else if (!matricola.equals(other.matricola))
			return false;
		if (nome == null)
		{
			if (other.nome != null)
				return false;
		}
		else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format("Studente [matricola=%s, nome=%s, cognome=%s, cds=%s]", matricola, nome, cognome, cds);
	}
	
}
