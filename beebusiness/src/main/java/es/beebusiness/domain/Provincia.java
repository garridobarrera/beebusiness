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
@Table(name = "BB_PROVINCIA")
@NamedQueries(value = {
		@NamedQuery(name = "getProvinciaByNombre", query = "SELECT p FROM Provincia p WHERE p.nombre=?"),
		@NamedQuery(name = "getProvinciaSizeByNombre", query = "SELECT COUNT(p) FROM Provincia p WHERE p.nombre=? GROUP BY p.nombre ORDER BY p.nombre ASC"),
		@NamedQuery(name = "getProvinciaAll", query = "SELECT p FROM Provincia p ORDER BY p.nombre ASC"),
		@NamedQuery(name = "getProvinciaSize", query = "SELECT COUNT(p) FROM Provincia p")
		})
public class Provincia extends Auditoria implements Serializable{


	private static final long serialVersionUID = -1634145769640074151L;
	public static final String QUERY_GETBYNOMBRE="getProvinciaByNombre";
	public static final String QUERY_GETTOTALBYNOMBRE="getProvinciaSizeByNombre";
	public static final String QUERY_GETTOTAL="getProvinciaSize";
	public static final String QUERY_GETALL="getProvinciaAll";
	private Long id;
	private String ine;
	private String nombre;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(unique=true,nullable=false)
	public String getIne() {
		return ine;
	}
	public void setIne(String ine) {
		this.ine = ine;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString(){
		StringBuffer sf = new StringBuffer();
		sf.append("(").append(ine).append(") ").append(nombre);
		return sf.toString();
	}

}
