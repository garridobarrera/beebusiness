package es.beebusiness.dao;

import java.util.List;

import es.beebusiness.domain.Provincia;

public interface IProvinciaDAO extends IBaseGenericDAO<Provincia, Long> {
	public Provincia findByCodigo(String codigo);
	public int getTotal();
	public List<Provincia> getAll(Integer inicio,Integer total);
	public int getTotalFilter(Provincia p);
	public List<Provincia> getAllFilter(Provincia p,Integer inicio,Integer total);
	List<Provincia> getAll(Integer inicio, Integer total, String filtro);
	int getTotal(String filtro);
}
