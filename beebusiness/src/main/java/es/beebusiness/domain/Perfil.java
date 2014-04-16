package es.beebusiness.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "BB_PERFIL")
@NamedQueries(value = {
		@NamedQuery(name = "getPerfilByNombre", query = "SELECT p FROM Perfil p WHERE p.nombre=?"),
		@NamedQuery(name = "getPerfilSizeByNombre", query = "SELECT COUNT(p) FROM Perfil p WHERE p.nombre=? GROUP BY p.nombre ORDER BY p.nombre ASC"),
		@NamedQuery(name = "getPerfilAll", query = "SELECT p FROM Perfil p ORDER BY p.nombre ASC"),
		@NamedQuery(name = "getPerfilSize", query = "SELECT COUNT(p) FROM Perfil p")
		})
public class Perfil implements Serializable{

	private static final long serialVersionUID = 4462145394243600871L;
	public static final String QUERY_GETBYNOMBRE="getPerfilByNombre";
	public static final String QUERY_GETTOTALBYNOMBRE="getPerfilSizeByNombre";
	public static final String QUERY_GETTOTAL="getPerfilSize";
	public static final String QUERY_GETALL="getPerfilAll";
	private Long id;
	private String nombre;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(nullable=false,unique=true)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
