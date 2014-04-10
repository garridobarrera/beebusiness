package es.beebusiness.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "BB_PROGRAMA")
public class Programa implements Serializable{

	private static final long serialVersionUID = -3489987100425132231L;
	private Long id;
	private String nombre;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private Set<Ponente> ponentes;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	@ManyToMany(fetch=FetchType.LAZY,targetEntity=Ponente.class)
	@JoinTable(name="BB_PROG_PONENTE",joinColumns={@JoinColumn(name="programa")},inverseJoinColumns={@JoinColumn(name="ponente")})
	@ForeignKey(name="FK_PROGRAMA_PROGRAMA",inverseName="FK_PROGRAMA_PONENTE")
	public Set<Ponente> getPonentes() {
		return ponentes;
	}
	public void setPonentes(Set<Ponente> ponentes) {
		this.ponentes = ponentes;
	}
	

}
