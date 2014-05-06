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
@Table(name = "BB_SECTOR")
@NamedQueries(value = {
		@NamedQuery(name = "getSectorByNombre", query = "SELECT s FROM Sector s WHERE s.nombre=?"),
		@NamedQuery(name = "getSectorSizeByNombre", query = "SELECT COUNT(s) FROM Sector s WHERE s.nombre=? GROUP BY s.nombre ORDER BY s.nombre ASC"),
		@NamedQuery(name = "getSectorAll", query = "SELECT s FROM Sector s ORDER BY s.nombre ASC"),
		@NamedQuery(name = "getSectorAllByTipo", query = "SELECT s FROM Sector s WHERE s.tipo=? ORDER BY s.nombre ASC"),
		@NamedQuery(name = "getSectorSize", query = "SELECT COUNT(s) FROM Sector s"),
		@NamedQuery(name = "getSectorSizeByTipo", query = "SELECT COUNT(s) FROM Sector s WHERE s.tipo=?")
		})
public class Sector extends Auditoria implements Serializable{
	
	public static final String QUERY_GETBYNOMBRE="getSectorByNombre";
	public static final String QUERY_GETTOTALBYNOMBRE="getSectorSizeByNombre";
	public static final String QUERY_GETTOTAL="getSectorSize";
	public static final String QUERY_GETTOTALBYTIPO="getSectorSizeByTipo";
	public static final String QUERY_GETALL="getSectorAll";
	public static final String QUERY_GETALLBYTIPO="getSectorAllByTipo";
	private static final long serialVersionUID = -563054798417517312L;
	private Long id;
	private String nombre;
	private String tipo;
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
	@Column(nullable=false)
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
