package it.polito.tdp.gestorecorsi.model;

public class Corso
{
	private String codiceInsegnamento;
	private Integer crediti;
	private String nome;
	private Integer periodoDidattico; 
	
	
	public Corso(String codiceInsegnamento, Integer crediti, String nome, Integer periodoDidattico)
	{
		this.codiceInsegnamento = codiceInsegnamento;
		this.crediti = crediti;
		this.nome = nome;
		this.periodoDidattico = periodoDidattico;
	}

	public String getCodiceInsegnamento()
	{
		return codiceInsegnamento;
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
		result = prime * result + ((codiceInsegnamento == null) ? 0 : codiceInsegnamento.hashCode());
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
		if(codiceInsegnamento == null)
		{
			if(other.codiceInsegnamento != null)
				return false;
		}
		else if(!codiceInsegnamento.equals(other.codiceInsegnamento))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format("Corso [codiceInsegnamento = %s, crediti = %d, nome = %s, periodoDidattico = %d]", 
				codiceInsegnamento, crediti, nome, periodoDidattico);
	}
	
}
