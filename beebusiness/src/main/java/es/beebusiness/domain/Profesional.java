package es.beebusiness.domain;

import java.io.Serializable;
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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "BB_PROFESIONAL")
@NamedQueries(value = {
		@NamedQuery(name = "getProfesionalByUsername", query = "SELECT u FROM Profesional u WHERE u.username=?"),
		@NamedQuery(name = "getProfesionalSizeByUsername", query = "SELECT COUNT(u) FROM Profesional u WHERE u.username=? GROUP BY u.username ORDER BY u.username ASC"),
		@NamedQuery(name = "getProfesionalAll", query = "SELECT u FROM Profesional u ORDER BY u.username ASC"),
		@NamedQuery(name = "getProfesionalSize", query = "SELECT COUNT(u) FROM Profesional u"),
		@NamedQuery(name = "getPerfilesByUsername", query = "SELECT u.perfiles FROM Profesional u WHERE u.username=?"),
		@NamedQuery(name = "getSectoresByUsername", query = "SELECT u.sectores FROM Profesional u WHERE u.username=?")
		})
public class Profesional extends Auditoria implements Serializable{

	private static final long serialVersionUID = -3968634184295910348L;
	public static final String QUERY_GETBYUSERNAME="getProfesionalByUsername";
	public static final String QUERY_GETTOTALBYUSERNAME="getProfesionalSizeByUsername";
	public static final String QUERY_GETTOTAL="getProfesionalSize";
	public static final String QUERY_GETALL="getProfesionalAll";
	public static final String QUERY_GETPERFILESBYUSERNAME="getPerfilesByUsername";
	public static final String QUERY_GETSECTORESBYUSERNAME="getSectoresByUsername";
	
	private Long id;
	private String nombre;
	private String apellidos;
	private String cargo;
	private Empresa empresa;
	@JsonIgnore
	private Set<Perfil> perfiles;
	@JsonIgnore
	private Set<Sector> sectores;
	private String emailPersonal;
	private String emailProfesional;
	private String username;
	private String password;
	
	
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
	
	

}
