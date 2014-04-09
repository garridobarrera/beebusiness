package es.beebusiness.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "BB_EVENTO")
public class Evento implements Serializable {


	private static final long serialVersionUID = 6657727355936858994L;
	private Long id;
	private String nombre;
	private String descripcion;
	private Long asistentes;
	private Float precio;
	private Date fechaInicio;
	private Date fechaFin;
	private TipoEvento tipoEvento;
	private Empresa empresa;
	private Direccion direccion;
	
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

	public Long getAsistentes() {
		return asistentes;
	}

	public void setAsistentes(Long asistentes) {
		this.asistentes = asistentes;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
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

	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="tipo")
	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="empresa")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@ManyToOne(fetch=FetchType.LAZY,optional=false,cascade=CascadeType.ALL)
	@JoinColumn(name="direccion")
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	

	

}
