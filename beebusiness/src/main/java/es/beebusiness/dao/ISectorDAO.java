package es.beebusiness.dao;

import java.util.List;

import es.beebusiness.domain.Sector;

public interface ISectorDAO extends IBaseGenericDAO<Sector, Long> {
	public Sector findByCodigo(String codigo);
	public int getTotal();
	public List<Sector> getAll(Integer inicio,Integer total);
	public int getTotalFilter(Sector p);
	public List<Sector> getAllFilter(Sector p,Integer inicio,Integer total);
}
