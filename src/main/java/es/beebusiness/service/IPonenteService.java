package es.beebusiness.service;

import java.io.Serializable;
import java.util.List;

import es.beebusiness.dao.IPonenteDAO;
import es.beebusiness.domain.Ponente;
import es.beebusiness.exception.BusinessException;

public interface IPonenteService extends Serializable{

	public void setDao(IPonenteDAO dao);
	public Ponente crear(Ponente p) throws BusinessException;
	public Ponente actualizar(Ponente p);
	public void borrar(Ponente p);
	public Ponente get(Long id);
	public int getSizeAll();
	public List<Ponente> getAll(Integer inicio,Integer total);
	public List<Ponente> getAll();
	public int getTotalFilter(Ponente p);
	public List<Ponente> getAllFilter(Ponente p, Integer inicio, Integer total);
	List<Ponente> getAll(Integer inicio, Integer total, String busqueda);
	int getSizeAll(String filtro);
	public List<Ponente> getAllSoloListado(Integer inicio, Integer total, String busqueda);
}
