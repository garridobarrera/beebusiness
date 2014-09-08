package es.beebusiness.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "BB_EVENTO")
@NamedQueries(value = {
		@NamedQuery(name = "getEventoAll", query = "SELECT e FROM Evento e ORDER BY e.fechaInicio ASC"),
		@NamedQuery(name = "getEventoSize", query = "SELECT COUNT(e) FROM Evento e"),
		@NamedQuery(name = "getEmpresasEvento", query = "SELECT e.empresas FROM Evento e WHERE e.id=?"),
		@NamedQuery(name = "getPerfilesEvento", query = "SELECT e.perfiles FROM Evento e WHERE e.id=?"),
		@NamedQuery(name = "getSectoresEvento", query = "SELECT e.sectores FROM Evento e WHERE e.id=?"),
		@NamedQuery(name = "getTematicaEvento", query = "SELECT e.tematicas FROM Evento e WHERE e.id=?"),
		@NamedQuery(name = "getPonentesEvento", query = "SELECT e.ponentes FROM Evento e WHERE e.id=?"),
		})
public class Evento extends Auditoria implements Serializable {


	private static final long serialVersionUID = 6657727355936858994L;
	public static final String QUERY_GETALL="getEventoAll";
	public static final String QUERY_GETTOTAL="getEventoSize";
	public static final String QUERY_GETEMPRESAS="getEmpresasEvento";
	public static final String QUERY_GETPERFILES="getPerfilesEvento";
	public static final String QUERY_GETSECTORES="getSectoresEvento";
	public static final String QUERY_GETTEMATICAS="getTematicaEvento";
	public static final String QUERY_GETPONENTES="getPonentesEvento";
	
	private Long id;
	private String nombre;
	private String descripcion;
	private Long asistentes;
	private Float precio;
	private Date fechaInicio;
	private Date fechaFin;
	private TipoEvento tipoEvento;
	@JsonIgnore
	private Empresa promotora;
	@JsonIgnore
	private Set<Empresa> empresas;
	private String direccion;
	private Provincia provincia;
	@JsonIgnore
	private Set<Perfil> perfiles;
	@JsonIgnore
	private Set<Sector> sectores;
	@JsonIgnore
	private Set<Sector> tematicas;
	private Float latitud;
	private Float longitud;
	private Integer  aforo;
	@JsonIgnore
	private Set<Ponente> ponentes;
	private String estado;
	
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

	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="tipo")
	@ForeignKey(name="FK_EVENTO_TIPO")
	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}

	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="promotora")
	@ForeignKey(name="FK_EVENTO_PROMOTORA")
	public Empresa getPromotora() {
		return promotora;
	}

	public void setPromotora(Empresa promotora) {
		this.promotora = promotora;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(name="BB_EVEN_PROVINCIA",joinColumns={@JoinColumn(name="evento")},inverseJoinColumns={@JoinColumn(name="provincia")})
	@ForeignKey(name="FK_EVENTO_EVENTO",inverseName="FK_EVENTO_PROVINCIA")
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
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

	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
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


	@ManyToMany(fetch=FetchType.LAZY,targetEntity=Sector.class)
	@JoinTable(name="BB_EVEN_TEM",joinColumns={@JoinColumn(name="evento")},inverseJoinColumns={@JoinColumn(name="tema")})
	@ForeignKey(name="FK_EVENTO_EVENTO",inverseName="FK_EVENTO_TEMA")
	public Set<Sector> getTematicas() {
		return tematicas;
	}

	public void setTematicas(Set<Sector> tematicas) {
		this.tematicas = tematicas;
	}
	
	public Float getLatitud() {
		return latitud;
	}

	public void setLatitud(Float latitud) {
		this.latitud = latitud;
	}

	public Float getLongitud() {
		return longitud;
	}

	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}

	public Integer getAforo() {
		return aforo;
	}

	public void setAforo(Integer aforo) {
		this.aforo = aforo;
	}
	
	@ManyToMany(fetch=FetchType.LAZY,targetEntity=Ponente.class)
	@JoinTable(name="BB_EVENTO_PONENTE",joinColumns={@JoinColumn(name="evento")},inverseJoinColumns={@JoinColumn(name="ponente")})
	@ForeignKey(name="FK_EVENTO_EVENTO",inverseName="FK_EVENTO_PONENTE")
	public Set<Ponente> getPonentes() {
		return ponentes;
	}
	public void setPonentes(Set<Ponente> ponentes) {
		this.ponentes = ponentes;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm");
		StringBuffer sf = new StringBuffer();
		sf.append(nombre).append(" ");
		if(descripcion!=null && !"".equals(descripcion))
			sf.append("- ").append(descripcion);
		if(fechaInicio!=null)
			sf.append(" (").append(sdf.format(fechaInicio)).append(")");
		return sf.toString();
	}



}
