package com.fatec.scc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
@Entity
public class Processo{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min = 1, max = 50, message = "Tribunal do tribunal deve ser preenchido")
	private String tribunal;
	@NotNull
	@Size(min = 1, max = 50, message = "Tribunal do orgão judicial deve ser preenchido")
	private String orgaoJudicial;
	@NotNull
	private String dataDeAbertura;
	@NotNull
	private String descProcesso;
	@NotNull
	private String statusProcesso;
	public Processo() {
	}
	public Processo(Long id, @NotNull String tribunal, @NotNull String orgaoJudicial, @NotNull String dataDeAbertura, @NotNull String descProcesso, @NotNull String statusProcesso) {
		this.id = id;
		this.tribunal = tribunal;
		this.orgaoJudicial = orgaoJudicial;
		this.dataDeAbertura = dataDeAbertura;
		this.descProcesso = descProcesso;
		this.statusProcesso = statusProcesso;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTribunal() {
		return tribunal;
	}
	public void setTribunal(String tribunal) {
		this.tribunal = tribunal;
	}
	public String getOrgaoJudicial() {
		return orgaoJudicial;
	}
	public void setOrgaoJudicial(String orgaoJudicial) {
		this.orgaoJudicial = orgaoJudicial;
	}
	public String getDataDeAbertura() {
		return dataDeAbertura;
	}
	public void setDataDeAbertura(String dataDeAbertura) {
		this.dataDeAbertura = dataDeAbertura;
	}
	public String getDescProcesso() {
		return descProcesso;
	}
	public void setDescProcesso(String descProcesso) {
		this.descProcesso = descProcesso;
	}
	public String getStatusProcesso() {
		return statusProcesso;
	}
	public void setStatusProcesso(String statusProcesso) {
		this.statusProcesso = statusProcesso;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Processo other = (Processo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (dataDeAbertura == null) {
			if (other.dataDeAbertura != null)
				return false;
		} else if (!dataDeAbertura.equals(other.dataDeAbertura))
			return false;
		if (orgaoJudicial == null) {
			if (other.orgaoJudicial != null)
				return false;
		} else if (!orgaoJudicial.equals(other.orgaoJudicial))
			return false;
		if (descProcesso == null) {
			if (other.descProcesso != null)
				return false;
		} else if (!descProcesso.equals(other.descProcesso))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tribunal == null) {
			if (other.tribunal != null)
				return false;
		} else if (!tribunal.equals(other.tribunal))
			return false;
		if (statusProcesso == null) {
			if (other.statusProcesso != null)
				return false;
		} else if (!statusProcesso.equals(other.statusProcesso))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Processo [id=" + id + ", tribunal=" + tribunal + ", orgaoJudicial=" + orgaoJudicial + ", dataDeAbertura=" + dataDeAbertura
				+ ", descProcesso=" + descProcesso + ", statusProcesso=" + statusProcesso + "]";
	}
	

}

