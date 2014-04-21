package es.beebusiness.dao;

import java.util.List;

import es.beebusiness.domain.Empresa;

public interface IEmpresaDAO extends IBaseGenericDAO<Empresa, Long> {
	public Empresa findByCodigo(String codigo);
	public int getTotal();
	public List<Empresa> getAll(Integer inicio,Integer total);
	public int getTotalFilter(Empresa emp);
	public List<Empresa> getAllFilter(Empresa emp,Integer inicio,Integer total);
}
