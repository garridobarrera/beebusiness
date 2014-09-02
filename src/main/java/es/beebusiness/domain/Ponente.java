package es.beebusiness.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "BB_PONENTE")
@NamedQueries(value = {
		@NamedQuery(name = "getPonentesAll", query = "SELECT p FROM Ponente p ORDER BY p.nombre ASC"),
		@NamedQuery(name = "getPonentesSize", query = "SELECT COUNT(p) FROM Ponente p")
		})
public class Ponente extends Auditoria implements Serializable{

	private static final long serialVersionUID = -3489987100425132231L;
	public static final String QUERY_GETTOTAL="getPonentesSize";
	public static final String QUERY_GETALL="getPonentesAll";
	private Long id;
	private String nombre;
	private String apellidos;
	private String cargo;
	
	@JsonIgnore
	private String mimeType;
	@JsonIgnore
	protected byte[] imageFile;
	@JsonIgnore
	private String nombreImagen;
	
	public Ponente(){
	}
	
	public Ponente(Long id,String nombre,String apellidos, String cargo){
		this.id=id;
		this.nombre=nombre;
		this.apellidos=apellidos;
		this.cargo=cargo;
	}
	
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
	
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
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
	@Override
	public String toString() {
		return this.nombre+" "+this.apellidos+" ("+cargo+")";
	}
	
	

}
