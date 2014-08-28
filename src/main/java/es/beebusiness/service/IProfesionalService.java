package es.beebusiness.service;

import java.io.Serializable;
import java.util.List;

import es.beebusiness.dao.IProfesionalDAO;
import es.beebusiness.domain.Perfil;
import es.beebusiness.domain.Profesional;
import es.beebusiness.domain.Sector;
import es.beebusiness.exception.BusinessException;

public interface IProfesionalService extends Serializable{

	public void setDao(IProfesionalDAO dao);
	public Profesional crear(Profesional profesional) throws BusinessException;
	public Profesional actualizar(Profesional profesional);
	public void borrar(Profesional profesional);
	public Profesional get(Long id);
	public Profesional get(String username) throws BusinessException;
	public int getSizeAll();
	public int getSizeAll(String filtro);
	public List<Profesional> getAll(Integer inicio,Integer total);
	public List<Profesional> getAll();
	public int getTotalFilter(Profesional profesional);
	public List<Profesional> getAllFilter(Profesional profesional, Integer inicio, Integer total);
	public List<Perfil> getPerfiles(String username);
	public List<Sector> getSectores(String username);
	public List<Sector> getTematicas(String username);
	public List<Profesional> getAll(Integer inicio, Integer total, String busqueda);
	public List<Profesional> getAllSoloListado(Integer inicio, Integer total, String busqueda);
}
