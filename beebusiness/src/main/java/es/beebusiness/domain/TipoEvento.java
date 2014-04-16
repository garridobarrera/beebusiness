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
@Table(name = "BB_TIPO_EVENTO")
@NamedQueries(value = {
		@NamedQuery(name = "getTipoEventoByNombre", query = "SELECT tev FROM TipoEvento tev WHERE tev.nombre=?"),
		@NamedQuery(name = "getTipoEventoSizeByNombre", query = "SELECT COUNT(tev) FROM TipoEvento tev WHERE tev.nombre=? GROUP BY tev.nombre ORDER BY tev.nombre ASC"),
		@NamedQuery(name = "getTipoEventoAll", query = "SELECT tev FROM TipoEvento tev ORDER BY tev.nombre ASC"),
		@NamedQuery(name = "getTipoEventoSize", query = "SELECT COUNT(tev) FROM TipoEvento tev")
		})
public class TipoEvento implements Serializable{

	private static final long serialVersionUID = -1481635638584438985L;
	public static final String QUERY_GETBYNOMBRE="getTipoEventoByNombre";
	public static final String QUERY_GETTOTALBYNOMBRE="getTipoEventoSizeByNombre";
	public static final String QUERY_GETTOTAL="getTipoEventoSize";
	public static final String QUERY_GETALL="getTipoEventoAll";
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
