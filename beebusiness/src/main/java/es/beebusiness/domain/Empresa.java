package es.beebusiness.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "BB_EMPRESA")
@NamedQueries(value = {
		@NamedQuery(name = "getEmpresaByNombre", query = "SELECT emp FROM Empresa emp WHERE emp.nombre=?"),
		@NamedQuery(name = "getEmpresaSizeByNombre", query = "SELECT COUNT(emp) FROM Empresa emp WHERE emp.nombre=? GROUP BY emp.nombre ORDER BY emp.nombre ASC"),
		@NamedQuery(name = "getEmpresaAll", query = "SELECT emp FROM Empresa emp ORDER BY emp.nombre ASC"),
		@NamedQuery(name = "getEmpresaSize", query = "SELECT COUNT(emp) FROM Empresa emp") })
public class Empresa implements Serializable {

	private static final long serialVersionUID = -4767358454536511177L;
	public static final String QUERY_GETBYNOMBRE = "getEmpresaByNombre";
	public static final String QUERY_GETTOTALBYNOMBRE = "getEmpresaSizeByNombre";
	public static final String QUERY_GETTOTAL = "getEmpresaSize";
	public static final String QUERY_GETALL = "getEmpresaAll";
	private Long id;
	private String codigo;
	private String nombre;
	private String dominio;
	private String telefono;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public String toString() {
		StringBuffer sf = new StringBuffer();
		sf.append("(").append(codigo).append(") ").append(nombre);
		if (telefono != null && !"".equals(telefono)) {
			sf.append(" - ").append(telefono);
		}
		if (dominio != null && !"".equals(dominio)) {
			sf.append(" /").append(dominio).append(" ");
		}
		return sf.toString();
	}


}
