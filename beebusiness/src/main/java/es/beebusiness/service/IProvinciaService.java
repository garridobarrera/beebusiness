package es.beebusiness.service;

import java.io.Serializable;
import java.util.List;

import es.beebusiness.dao.IProvinciaDAO;
import es.beebusiness.domain.Provincia;
import es.beebusiness.exception.BusinessException;

public interface IProvinciaService extends Serializable{

	public void setDao(IProvinciaDAO dao);
	public Provincia crear(Provincia p) throws BusinessException;
	public Provincia actualizar(Provincia p);
	public void borrar(Provincia p);
	public Provincia get(Long id);
	public Provincia get(String codigo);
	public int getSizeAll();
	public List<Provincia> getAll(Integer inicio,Integer total);
	public List<Provincia> getAll();
	public int getTotalFilter(Provincia p);
	public List<Provincia> getAllFilter(Provincia p, Integer inicio, Integer total);
}
