package es.beebusiness.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

import es.beebusiness.controller.lazy.EmpresaLazyDataModel;
import es.beebusiness.controller.lazy.PerfilLazyDataModel;
import es.beebusiness.controller.lazy.ProfesionalLazyDataModel;
import es.beebusiness.controller.lazy.SectorLazyDataModel;
import es.beebusiness.domain.Empresa;
import es.beebusiness.domain.Perfil;
import es.beebusiness.domain.Profesional;
import es.beebusiness.domain.Sector;

public class ProfesionalBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2425686197129350247L;
	private Profesional profesional;
	private ProfesionalLazyDataModel lazy;
	private EmpresaLazyDataModel searchEmpresa;
	private List<Empresa> empresasSeleccionadas;
	private PerfilLazyDataModel searchPerfil;
	private List<Perfil> perfilesSeleccionados;
	private SectorLazyDataModel searchSector;
	private List<Sector> sectoresSeleccionados;
	
	
	public Profesional getProfesional() {
		return profesional;
	}
	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}
	public ProfesionalLazyDataModel getLazy() {
		return lazy;
	}
	public void setLazy(ProfesionalLazyDataModel lazy) {
		this.lazy = lazy;
	}

	public EmpresaLazyDataModel getSearchEmpresa() {
		return searchEmpresa;
	}
	public void setSearchEmpresa(EmpresaLazyDataModel searchEmpresa) {
		this.searchEmpresa = searchEmpresa;
	}
	public List<Empresa> getEmpresasSeleccionadas() {
		return empresasSeleccionadas;
	}
	public void setEmpresasSeleccionadas(List<Empresa> empresasSeleccionadas) {
		this.empresasSeleccionadas = empresasSeleccionadas;
	}
	public PerfilLazyDataModel getSearchPerfil() {
		return searchPerfil;
	}
	public void setSearchPerfil(PerfilLazyDataModel searchPerfil) {
		this.searchPerfil = searchPerfil;
	}
	public List<Perfil> getPerfilesSeleccionados() {
		return perfilesSeleccionados;
	}
	public void setPerfilesSeleccionados(List<Perfil> perfilesSeleccionados) {
		this.perfilesSeleccionados = perfilesSeleccionados;
	}
	public SectorLazyDataModel getSearchSector() {
		return searchSector;
	}
	public void setSearchSector(SectorLazyDataModel searchSector) {
		this.searchSector = searchSector;
	}
	public List<Sector> getSectoresSeleccionados() {
		return sectoresSeleccionados;
	}
	public void setSectoresSeleccionados(List<Sector> sectoresSeleccionados) {
		this.sectoresSeleccionados = sectoresSeleccionados;
	}
	
	public void componer(){
		if(perfilesSeleccionados!=null)
			profesional.setPerfiles(new HashSet<Perfil>(perfilesSeleccionados));
		if(sectoresSeleccionados!=null)
			profesional.setSectores(new HashSet<Sector>(sectoresSeleccionados));
		if(empresasSeleccionadas!=null && empresasSeleccionadas.size()>0)
			profesional.setEmpresa(empresasSeleccionadas.get(0));
	}


}
