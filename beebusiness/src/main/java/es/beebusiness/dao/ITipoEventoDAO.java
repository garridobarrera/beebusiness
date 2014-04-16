package es.beebusiness.dao;

import java.util.List;

import es.beebusiness.domain.TipoEvento;

public interface ITipoEventoDAO extends IBaseGenericDAO<TipoEvento, Long> {
	public TipoEvento findByCodigo(String codigo);
	public int getTotal();
	public List<TipoEvento> getAll(Integer inicio,Integer total);
	public int getTotalFilter(TipoEvento tev);
	public List<TipoEvento> getAllFilter(TipoEvento tev,Integer inicio,Integer total);
}
