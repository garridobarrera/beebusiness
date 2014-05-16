package es.beebusiness.service;

import java.io.Serializable;
import java.util.List;

import es.beebusiness.dao.IPerfilDAO;
import es.beebusiness.domain.Perfil;
import es.beebusiness.exception.BusinessException;

public interface IPerfilService extends Serializable{

	public void setDao(IPerfilDAO dao);
	public Perfil crear(Perfil p) throws BusinessException;
	public Perfil actualizar(Perfil p);
	public void borrar(Perfil p);
	public Perfil get(Long id);
	public Perfil get(String codigo);
	public int getSizeAll();
	public List<Perfil> getAll(Integer inicio,Integer total);
	public List<Perfil> getAll();
	public int getTotalFilter(Perfil p);
	public List<Perfil> getAllFilter(Perfil p, Integer inicio, Integer total);
	List<Perfil> getAll(Integer inicio, Integer total, String busqueda);
	int getSizeAll(String filtro);
}
