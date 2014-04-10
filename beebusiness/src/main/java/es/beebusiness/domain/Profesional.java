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
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "BB_PROFESIONAL")
public class Profesional implements Serializable{

	private static final long serialVersionUID = -3968634184295910348L;
	private Long id;
	private String nombre;
	private String apellidos;
	private String cargo;
	private Empresa empresa;
	private Set<Perfil> perfiles;
	private Set<Sector> sectores;
	private String emailPersonal;
	private String emailProfesional;
	private String username;
	private String password;
	private Set<Evento> eventos;
	
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
	
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
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
	
	@ManyToMany(fetch=FetchType.LAZY,targetEntity=Evento.class)
	@JoinTable(name="BB_PROF_EVENTO",joinColumns={@JoinColumn(name="profesional")},inverseJoinColumns={@JoinColumn(name="evento")})
	@ForeignKey(name="FK_PROFESIONAL_PROFESIONAL",inverseName="FK_PROFESIONAL_EVENTO")
	public Set<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}

}
