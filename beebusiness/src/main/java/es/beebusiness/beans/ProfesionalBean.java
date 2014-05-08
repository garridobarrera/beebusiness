package es.beebusiness.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

import es.beebusiness.controller.lazy.EmpresaLazyDataModel;
import es.beebusiness.controller.lazy.PerfilLazyDataModel;
import es.beebusiness.controller.lazy.ProfesionalLazyDataModel;
import es.beebusiness.controller.lazy.ProvinciaLazyDataModel;
import es.beebusiness.controller.lazy.TopicSectorLazyDataModel;
import es.beebusiness.controller.lazy.TopicTematicaLazyDataModel;
import es.beebusiness.domain.Empresa;
import es.beebusiness.domain.Perfil;
import es.beebusiness.domain.Profesional;
import es.beebusiness.domain.Provincia;
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
	private TopicSectorLazyDataModel searchSector;
	private List<Sector> sectoresSeleccionados;
	private TopicTematicaLazyDataModel searchTematica;
	private List<Sector> tematicasSeleccionadas;
	private ProvinciaLazyDataModel searchProvincia;
	private Provincia provinciaSeleccionada;
	
	
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
	public TopicSectorLazyDataModel getSearchSector() {
		return searchSector;
	}
	public void setSearchSector(TopicSectorLazyDataModel searchSector) {
		this.searchSector = searchSector;
	}
	public List<Sector> getSectoresSeleccionados() {
		return sectoresSeleccionados;
	}
	public void setSectoresSeleccionados(List<Sector> sectoresSeleccionados) {
		this.sectoresSeleccionados = sectoresSeleccionados;
	}
	
	public TopicTematicaLazyDataModel getSearchTematica() {
		return searchTematica;
	}
	public void setSearchTematica(TopicTematicaLazyDataModel searchTematica) {
		this.searchTematica = searchTematica;
	}
	public List<Sector> getTematicasSeleccionadas() {
		return tematicasSeleccionadas;
	}
	public void setTematicasSeleccionadas(List<Sector> tematicasSeleccionadas) {
		this.tematicasSeleccionadas = tematicasSeleccionadas;
	}

	public ProvinciaLazyDataModel getSearchProvincia() {
		return searchProvincia;
	}
	public void setSearchProvincia(ProvinciaLazyDataModel searchProvincia) {
		this.searchProvincia = searchProvincia;
	}
	public Provincia getProvinciaSeleccionada() {
		return provinciaSeleccionada;
	}
	public void setProvinciaSeleccionada(Provincia provinciaSeleccionada) {
		this.provinciaSeleccionada = provinciaSeleccionada;
	}
	public void componer(){
		if(perfilesSeleccionados!=null)
			profesional.setPerfiles(new HashSet<Perfil>(perfilesSeleccionados));
		if(sectoresSeleccionados!=null)
			profesional.setSectores(new HashSet<Sector>(sectoresSeleccionados));
		if(empresasSeleccionadas!=null && empresasSeleccionadas.size()>0)
			profesional.setEmpresa(empresasSeleccionadas.get(0));
		if(tematicasSeleccionadas!=null && tematicasSeleccionadas.size()>0)
			profesional.setTematicas(new HashSet<Sector>(tematicasSeleccionadas));
		profesional.setProvincia(provinciaSeleccionada);
	}
	
	public void componerActualizarInformacion(){
		if(empresasSeleccionadas!=null && empresasSeleccionadas.size()>0)
			profesional.setEmpresa(empresasSeleccionadas.get(0));
		else
			profesional.setEmpresa(null);
		profesional.setProvincia(provinciaSeleccionada);
	}
	
	public void componerActualizarPreferencias(){
		if(perfilesSeleccionados!=null)
			profesional.setPerfiles(new HashSet<Perfil>(perfilesSeleccionados));
		if(sectoresSeleccionados!=null)
			profesional.setSectores(new HashSet<Sector>(sectoresSeleccionados));
		if(tematicasSeleccionadas!=null && tematicasSeleccionadas.size()>0)
			profesional.setTematicas(new HashSet<Sector>(tematicasSeleccionadas));
	}


}
