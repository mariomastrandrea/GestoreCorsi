package it.polito.tdp.gestorecorsi.model;

public class Corso
{
	private String codiceCorso;
	private Integer crediti;
	private String nome;
	private Integer periodoDidattico; 
	
	
	public Corso(String codiceInsegnamento, Integer crediti, String nome, Integer periodoDidattico)
	{
		this.codiceCorso = codiceInsegnamento;
		this.crediti = crediti;
		this.nome = nome;
		this.periodoDidattico = periodoDidattico;
	}

	public String getCodiceInsegnamento()
	{
		return codiceCorso;
	}

	public int getCrediti()
	{
		return crediti;
	}

	public String getNome()
	{
		return nome;
	}

	public int getPeriodoDidattico()
	{
		return periodoDidattico;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceCorso == null) ? 0 : codiceCorso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if(codiceCorso == null)
		{
			if(other.codiceCorso != null)
				return false;
		}
		else if(!codiceCorso.equals(other.codiceCorso))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format("%-13s %-8d %-50s %-17d", codiceCorso, crediti, nome, periodoDidattico);
	}
	
}
