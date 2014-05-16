package es.beebusiness.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "BB_EVENTO")
public class Evento extends Auditoria implements Serializable {


	private static final long serialVersionUID = 6657727355936858994L;
	private Long id;
	private String nombre;
	private String descripcion;
	private Long asistentes;
	private Float precio;
	private Date fechaInicio;
	private Date fechaFin;
	private TipoEvento tipoEvento;
	private Set<Empresa> empresas;
	private Direccion direccion;
	private Set<Perfil> perfiles;
	private Set<Sector> sectores;
	private Set<Programa> programas;
	
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
	@ForeignKey(name="FK_EVENTO_TIPO")
	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}

	@ManyToMany(fetch=FetchType.LAZY,targetEntity=Empresa.class)
	@JoinTable(name="BB_EVEN_EMPRESA",joinColumns={@JoinColumn(name="evento")},inverseJoinColumns={@JoinColumn(name="empresa")})
	@ForeignKey(name="FK_EVENTO_EVENTO",inverseName="FK_EVENTO_EMPRESA")
	public Set<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
	}

	@ManyToOne(fetch=FetchType.LAZY,optional=false,cascade=CascadeType.ALL)
	@JoinColumn(name="direccion")
	@ForeignKey(name="FK_EVENTO_DIRECCION")
	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	@ManyToMany(fetch=FetchType.LAZY,targetEntity=Perfil.class)
	@JoinTable(name="BB_EVEN_PERF",joinColumns={@JoinColumn(name="evento")},inverseJoinColumns={@JoinColumn(name="perfil")})
	@ForeignKey(name="FK_EVENTO_EVENTO",inverseName="FK_EVENTO_PERFIL")
	public Set<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(Set<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	@ManyToMany(fetch=FetchType.LAZY,targetEntity=Sector.class)
	@JoinTable(name="BB_EVEN_SECT",joinColumns={@JoinColumn(name="evento")},inverseJoinColumns={@JoinColumn(name="sector")})
	@ForeignKey(name="FK_EVENTO_EVENTO",inverseName="FK_EVENTO_SECTOR")
	public Set<Sector> getSectores() {
		return sectores;
	}

	public void setSectores(Set<Sector> sectores) {
		this.sectores = sectores;
	}

	@OneToMany(fetch=FetchType.LAZY,targetEntity=Programa.class)	
	@JoinTable(name="BB_EVEN_PROGRAMA",joinColumns={@JoinColumn(name="evento")},inverseJoinColumns={@JoinColumn(name="programa")})
	@ForeignKey(name="FK_EVENTO_EVENTO",inverseName="FK_EVENTO_PROGRAMA")
	public Set<Programa> getProgramas() {
		return programas;
	}

	public void setProgramas(Set<Programa> programas) {
		this.programas = programas;
	}


}
