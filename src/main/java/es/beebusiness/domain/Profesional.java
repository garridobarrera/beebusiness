package es.beebusiness.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "BB_PROFESIONAL")
@NamedQueries(value = {
		@NamedQuery(name = "getProfesionalByUsername", query = "SELECT u FROM Profesional u WHERE u.username=?"),
		@NamedQuery(name = "getProfesionalSizeByUsername", query = "SELECT COUNT(u) FROM Profesional u WHERE u.username=? GROUP BY u.username ORDER BY u.username ASC"),
		@NamedQuery(name = "getProfesionalAll", query = "SELECT u FROM Profesional u ORDER BY u.username ASC"),
		@NamedQuery(name = "getProfesionalSize", query = "SELECT COUNT(u) FROM Profesional u"),
		@NamedQuery(name = "getPerfilesByUsername", query = "SELECT u.perfiles FROM Profesional u WHERE u.username=?"),
		@NamedQuery(name = "getSectoresByUsername", query = "SELECT u.sectores FROM Profesional u WHERE u.username=?"),
		@NamedQuery(name = "getTematicasByUsername", query = "SELECT u.tematicas FROM Profesional u WHERE u.username=?")
		})
public class Profesional extends Auditoria implements Serializable{

	private static final long serialVersionUID = -3968634184295910348L;
	public static final String QUERY_GETBYUSERNAME="getProfesionalByUsername";
	public static final String QUERY_GETTOTALBYUSERNAME="getProfesionalSizeByUsername";
	public static final String QUERY_GETTOTAL="getProfesionalSize";
	public static final String QUERY_GETALL="getProfesionalAll";
	public static final String QUERY_GETPERFILESBYUSERNAME="getPerfilesByUsername";
	public static final String QUERY_GETSECTORESBYUSERNAME="getSectoresByUsername";
	public static final String QUERY_GETTEMATICASBYUSERNAME="getTematicasByUsername";
	
	private Long id;
	private String nombre;
	private String apellidos;
	private String cargo;
	private Empresa empresa;
	@JsonIgnore
	private Set<Perfil> perfiles;
	@JsonIgnore
	private Set<Sector> sectores;
	@JsonIgnore
	private Set<Sector> tematicas;
	private String emailPersonal;
	private String emailProfesional;
	private String username;
	private String password;
	private String direccion;
	private Provincia provincia;
	private Date fechaNacimiento;
	private String formacion;
	
	@JsonIgnore
	private String mimeType;
	@JsonIgnore
	protected byte[] imageFile;
	@JsonIgnore
	private String nombreImagen;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(nullable=false)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(name="BB_PROF_EMPRESA",joinColumns={@JoinColumn(name="profesional")},inverseJoinColumns={@JoinColumn(name="empresa")})
	@ForeignKey(name="FK_PROFESIONAL_PROFESIONAL",inverseName="FK_PROFESIONAL_EMPRESA")
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@ManyToMany(fetch=FetchType.LAZY,targetEntity=Perfil.class)
	@JoinTable(name="BB_PROF_PERFIL",joinColumns={@JoinColumn(name="profesional")},inverseJoinColumns={@JoinColumn(name="perfil")})
	@ForeignKey(name="FK_PROFESIONAL_PROFESIONAL",inverseName="FK_PROFESIONAL_PERFIL")
	public Set<Perfil> getPerfiles() {
		return perfiles;
	}
	public void setPerfiles(Set<Perfil> perfiles) {
		this.perfiles = perfiles;
	}
	
	@ManyToMany(fetch=FetchType.LAZY,targetEntity=Sector.class)
	@JoinTable(name="BB_PROF_SECTOR",joinColumns={@JoinColumn(name="profesional")},inverseJoinColumns={@JoinColumn(name="sector")})
	@ForeignKey(name="FK_PROFESIONAL_PROFESIONAL",inverseName="FK_PROFESIONAL_SECTOR")
	public Set<Sector> getSectores() {
		return sectores;
	}
	public void setSectores(Set<Sector> sectores) {
		this.sectores = sectores;
	}
	
	@ManyToMany(fetch=FetchType.LAZY,targetEntity=Sector.class)
	@JoinTable(name="BB_PROF_TEMATICA",joinColumns={@JoinColumn(name="profesional")},inverseJoinColumns={@JoinColumn(name="tematica")})
	@ForeignKey(name="FK_PROFESIONAL_PROFESIONAL",inverseName="FK_PROFESIONAL_TEMATICA")
	public Set<Sector> getTematicas() {
		return tematicas;
	}
	public void setTematicas(Set<Sector> tematicas) {
		this.tematicas = tematicas;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(name="BB_PROF_PROVINCIA",joinColumns={@JoinColumn(name="profesional")},inverseJoinColumns={@JoinColumn(name="provincia")})
	@ForeignKey(name="FK_PROFESIONAL_PROFESIONAL",inverseName="FK_PROFESIONAL_PROVINCIA")
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	public String getEmailPersonal() {
		return emailPersonal;
	}
	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}
	public String getEmailProfesional() {
		return emailProfesional;
	}
	public void setEmailProfesional(String emailProfesional) {
		this.emailProfesional = emailProfesional;
	}
	@Column(nullable=false,unique=true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getFormacion() {
		return formacion;
	}
	public void setFormacion(String formacion) {
		this.formacion = formacion;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	@Lob
	@Basic(fetch = FetchType.LAZY)
	public byte[] getImageFile() {
		return imageFile;
	}
	public void setImageFile(byte[] imageFile) {
		this.imageFile = imageFile;
	}
	public String getNombreImagen() {
		return nombreImagen;
	}
	public void setNombreImagen(String nombreImagen) {
		this.nombreImagen = nombreImagen;
	}
	
	/**
	 * Metodo para copiar datos basicos de un profesional a otro, excluyendo los campos: password, sectores, tematicas y perfiles.
	 * @param profesional Profesional a copiar
	 * @param vacios boolean que indica si se van a copiar datos vacios
	 * */
	public void copiarDatosBasicos(Profesional profesional,boolean vacios){
		if(vacios || !StringUtils.isEmpty(profesional.getNombre())){
			this.nombre=profesional.getNombre();
		}
		
		if(vacios || !StringUtils.isEmpty(profesional.getApellidos())){
			this.apellidos=profesional.getApellidos();
		}
		
		if(vacios || !StringUtils.isEmpty(profesional.getCargo())){
			this.cargo=profesional.getCargo();
		}
		
		if(vacios || profesional.getEmpresa()!=null){
			this.empresa=profesional.getEmpresa();
		}

		if(vacios || !StringUtils.isEmpty(profesional.getEmailPersonal())){
			this.emailPersonal=profesional.getEmailPersonal();
		}
		
		if(vacios || !StringUtils.isEmpty(profesional.getEmailProfesional())){
			this.emailProfesional=profesional.getEmailProfesional();
		}
		
		if(vacios || !StringUtils.isEmpty(profesional.getDireccion())){
			this.direccion=profesional.getDireccion();
		}
		
		if(vacios || profesional.getProvincia()!=null){
			this.provincia=profesional.getProvincia();
		}
		
		if(vacios || profesional.getFechaNacimiento()!=null){
			this.fechaNacimiento=profesional.getFechaNacimiento();
		}
		
		if(vacios || !StringUtils.isEmpty(profesional.getFormacion())){
			this.formacion=profesional.getFormacion();
		}

	}
	
	@Override
	public String toString() {
		StringBuffer sf = new StringBuffer();
		sf.append(nombre).append(" ");
		if(apellidos!=null && !"".equals(apellidos))
			sf.append(apellidos).append(" ");
		sf.append("(").append(username).append(")").append(" ");
		return sf.toString();
	}
	
	

}
