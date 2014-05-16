package es.beebusiness.service;

import java.io.Serializable;
import java.util.List;

import es.beebusiness.dao.IEmpresaDAO;
import es.beebusiness.domain.Empresa;
import es.beebusiness.exception.BusinessException;

public interface IEmpresaService extends Serializable{

	public void setDao(IEmpresaDAO dao);
	public Empresa crear(Empresa emp) throws BusinessException;
	public Empresa actualizar(Empresa emp);
	public void borrar(Empresa emp);
	public Empresa get(Long id);
	public Empresa get(String codigo);
	public int getSizeAll();
	public List<Empresa> getAll(Integer inicio,Integer total);
	public List<Empresa> getAll();
	public int getTotalFilter(Empresa emp);
	public List<Empresa> getAllFilter(Empresa emp, Integer inicio, Integer total);
}
