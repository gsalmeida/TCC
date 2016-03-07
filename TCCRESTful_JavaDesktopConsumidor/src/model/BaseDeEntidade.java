package model;

import java.util.Date;

public abstract class BaseDeEntidade {
	
	private Long id;
	
	
	private Date dataDeCriacao = new Date();
	
	private Date dataDeAtualizacao = new Date();
	
	private Boolean ativo = Boolean.TRUE;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getDataDeCriacao() {
		return dataDeCriacao;
	}
	
	public void setDataDeCriacao(Date dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}
	
	public Date getDataDeAtualizacao() {
		return dataDeAtualizacao;
	}
	
	/*
	public void setDataDeAtualizacao(Date data) {
		this.dataDeAtualizacao = data;
	}
	public void setDataDeAtualizacao() {
		this.dataDeAtualizacao = new Date();
	}
	*/

	
	public void setDataDeAtualizacao(Date data) {
		this.dataDeAtualizacao = new Date();
	}
	
	public Boolean isAtivo() {
		return ativo;
	}
	
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? super.hashCode() : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseDeEntidade other = (BaseDeEntidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
			else 
				return super.equals(obj);
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
