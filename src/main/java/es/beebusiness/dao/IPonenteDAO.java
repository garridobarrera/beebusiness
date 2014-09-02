package es.beebusiness.dao;

import java.util.List;

import es.beebusiness.domain.Ponente;


public interface IPonenteDAO extends IBaseGenericDAO<Ponente, Long> {
	public int getTotal();
	public List<Ponente> getAll(Integer inicio,Integer total);
	public int getTotalFilter(Ponente ponente);
	public List<Ponente> getAllFilter(Ponente ponente,Integer inicio,Integer total);
	public List<Ponente> getAll(Integer inicio, Integer total, String filtro);
	public int getTotal(String filtro);
	public List<Ponente> getAllSoloListado(Integer inicio,Integer total,String filtro);
}
