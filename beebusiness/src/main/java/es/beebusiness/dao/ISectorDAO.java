package es.beebusiness.dao;

import java.util.List;

import es.beebusiness.domain.Sector;

public interface ISectorDAO extends IBaseGenericDAO<Sector, Long> {
	public Sector findByCodigo(String codigo);
	public int getTotal();
	public int getTotal(String tipo);
	public List<Sector> getAll(Integer inicio,Integer total);
	public List<Sector> getAll(Integer inicio,Integer total,String tipo);
	public int getTotalFilter(Sector p);
	public List<Sector> getAllFilter(Sector p,Integer inicio,Integer total);
	List<Sector> getAllFilter(Integer inicio, Integer total, String filtro);
	int getsize(String filtro);
}
