package br.com.esig.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tarefa")
public class Tarefa implements Serializable {

	private static final long serialVersionUID = 1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero;
	
	@Column(name = "titulo_tarefa", nullable = false)
	private String titulo;
	
	@Column(name = "descricao_tarefa", nullable = false, length = 500)
	private String descricao;
	
    @ManyToOne 
    @JoinColumn(name = "responsavel_nome", referencedColumnName = "responsavel_tarefa")
    private Responsavel responsavel;
	
	@Column(name = "prioridade_tarefa", nullable = false)
	private String prioridade;
	
	@Column(name = "deadline_tarefa")
	private LocalDate deadline;
	
	@Column(name = "concluido_tarefa")
	private boolean concluido;
	
	
	public Tarefa() {
	}
	public Tarefa(String titulo, String descricao, Responsavel responsavel, String prioridade, LocalDate deadline, boolean concluido) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.responsavel = responsavel;
		this.prioridade = prioridade;
		this.deadline = deadline;
		this.concluido = concluido;
	}
	
	public Long getNumero() {return numero;}
	
	public String getTitulo() {return titulo;}
	public void setTitulo(String titulo) {this.titulo = titulo;}
	
	public String getDescricao() {return descricao;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	
	public Responsavel getResponsavel() {return responsavel;}
	public void setResponsavel(Responsavel responsavel) {this.responsavel = responsavel;}
	
	public String getPrioridade() {return prioridade;}
	public void setPrioridade(String prioridade) {this.prioridade = prioridade;}
	
	public LocalDate getDeadline() {return deadline;}
	public void setDeadline(LocalDate  deadline) {this.deadline = deadline;}
	
	public boolean isConcluido() {return concluido;}
	public void setConcluido(boolean concluido) {this.concluido = concluido;}
	
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
		Tarefa other = (Tarefa) obj;
		return Objects.equals(numero, other.numero);
	}
	
	@Override
	public String toString() {
		return "Tarefa [numero=" + numero + ", titulo=" + titulo + ", descricao=" + descricao 
				+ ", responsavel=" + responsavel + ", prioridade=" + prioridade 
				+ ", deadline=" + deadline + ", concluido=" + concluido + "]";
	}
}
