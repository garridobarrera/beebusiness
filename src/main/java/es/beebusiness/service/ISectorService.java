package es.beebusiness.service;

import java.io.Serializable;
import java.util.List;

import es.beebusiness.dao.ISectorDAO;
import es.beebusiness.domain.Sector;
import es.beebusiness.exception.BusinessException;

public interface ISectorService extends Serializable{

	public void setDao(ISectorDAO dao);
	public Sector crear(Sector s) throws BusinessException;
	public Sector actualizar(Sector s);
	public void borrar(Sector s);
	public Sector get(Long id);
	public Sector get(String codigo);
	public int getSizeAll();
	public int getSizeAll(String tipo);
	public List<Sector> getAll(Integer inicio,Integer total);
	public List<Sector> getAll(Integer inicio,Integer total,String tipo);
	public List<Sector> getAll();
	public List<Sector> getAll(String tipo);
	public int getTotalFilter(Sector s);
	public List<Sector> getAllFilter(Sector s, Integer inicio, Integer total);
	List<Sector> getAllFilter(Integer inicio, Integer total, String filtro);
	int getsize(String filtro);
}
