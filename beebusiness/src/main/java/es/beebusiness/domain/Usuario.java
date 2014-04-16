package es.beebusiness.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "BB_USUARIO")
@NamedQueries(value = {
		@NamedQuery(name = "getUsuarioByUsername", query = "SELECT u FROM Usuario u WHERE u.username=?"),
		@NamedQuery(name = "getUsuarioSizeByUsername", query = "SELECT COUNT(u) FROM Usuario u WHERE u.username=? GROUP BY u.username ORDER BY u.username ASC"),
		@NamedQuery(name = "getUsuarioAll", query = "SELECT u FROM Usuario u ORDER BY u.username ASC"),
		@NamedQuery(name = "getUsuarioSize", query = "SELECT COUNT(u) FROM Perfil u")
		})
public class Usuario implements Serializable{

	private static final long serialVersionUID = -4410070469854677050L;
	public static final String QUERY_GETBYUSERNAME="getUsuarioByUsername";
	public static final String QUERY_GETTOTALBYUSERNAME="getUsuarioSizeByUsername";
	public static final String QUERY_GETTOTAL="getUsuarioSize";
	public static final String QUERY_GETALL="getUsuarioAll";
	
	private Long id;
	private String nombre;
	private String apellidos;
	private Set<Empresa> empresas;
	private String emailPersonal;
	private String emailProfesional;
	private Set<String> roles;
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
	
	@ManyToMany(fetch=FetchType.LAZY,targetEntity=Empresa.class)
	@JoinTable(name="BB_USUARIO_EMPRESA",joinColumns={@JoinColumn(name="usuario")},inverseJoinColumns={@JoinColumn(name="empresa")})
	@ForeignKey(name="FK_USUARIO_USUARIO",inverseName="FK_USUARIO_EMPRESA")
	public Set<Empresa> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
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
	
	@ElementCollection
	@JoinTable(name="BB_USUARIO_ROL",joinColumns={@JoinColumn(name="usuario")})
	@Column(name="rol")
	@ForeignKey(name="FK_USUARIO_ROL")
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
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
