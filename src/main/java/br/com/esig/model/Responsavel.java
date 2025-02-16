package br.com.esig.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "responsavel")
public class Responsavel implements Serializable {

	private static final long serialVersionUID = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero;
	
	@Column(name = "responsavel_tarefa", nullable = false)
	private String responsavel;
	

    @OneToMany(mappedBy = "responsavel")
    private List<Tarefa> tarefas;
	
	public Responsavel() {
	}
	public Responsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
	public Long getNumero() {return numero;}
	
	public String getResponsavel() {return responsavel;}
	public void setResponsavel(String responsavel) {this.responsavel = responsavel;}
	
	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Responsavel other = (Responsavel) obj;
		return Objects.equals(numero, other.numero);
	}
	
	@Override
	public String toString() {
		return responsavel;
	}
}
