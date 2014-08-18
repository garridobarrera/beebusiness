package es.beebusiness.rest.profesional.domain;

import java.io.Serializable;
import java.util.Set;

import es.beebusiness.domain.Perfil;
import es.beebusiness.domain.Profesional;
import es.beebusiness.domain.Sector;
/**
 * Entidad creada para registrar y actualizar profesionales mediante servicios REST
 * */
public class Registro implements Serializable {

	private static final long serialVersionUID = -8246157983356372902L;
	private Profesional profesional;

	private Set<Perfil> perfiles;
	private Set<Sector> sectores;
	private Set<Sector> tematicas;

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public Set<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(Set<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public Set<Sector> getSectores() {
		return sectores;
	}

	public void setSectores(Set<Sector> sectores) {
		this.sectores = sectores;
	}

	public Set<Sector> getTematicas() {
		return tematicas;
	}

	public void setTematicas(Set<Sector> tematicas) {
		this.tematicas = tematicas;
	}

}
