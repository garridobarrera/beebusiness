package es.beebusiness.service;

import java.io.Serializable;
import java.util.List;

import es.beebusiness.dao.ITipoEventoDAO;
import es.beebusiness.domain.TipoEvento;
import es.beebusiness.exception.BusinessException;

public interface ITipoEventoService extends Serializable{

	public void setDao(ITipoEventoDAO dao);
	public TipoEvento crear(TipoEvento u) throws BusinessException;
	public TipoEvento actualizar(TipoEvento tev);
	public void borrar(TipoEvento tev);
	public TipoEvento get(Long id);
	public TipoEvento get(String codigo) throws BusinessException;
	public int getSizeAll();
	public List<TipoEvento> getAll(Integer inicio,Integer total);
	public List<TipoEvento> getAll();
	public int getTotalFilter(TipoEvento tev);
	public List<TipoEvento> getAllFilter(TipoEvento tev, Integer inicio, Integer total);
	List<TipoEvento> getAll(Integer inicio, Integer total, String busqueda);
	int getSizeAll(String filtro);
}
