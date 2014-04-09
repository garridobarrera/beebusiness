package es.beebusiness.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BB_PROVINCIA")
public class Provincia implements Serializable{


	private static final long serialVersionUID = -1634145769640074151L;
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

}
