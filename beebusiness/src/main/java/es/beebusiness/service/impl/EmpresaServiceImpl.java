package es.beebusiness.service.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.beebusiness.dao.IEmpresaDAO;
import es.beebusiness.domain.Empresa;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IEmpresaService;

@Service
@Transactional
public class EmpresaServiceImpl implements IEmpresaService {


	private static final long serialVersionUID = 21232329863467824L;
	@Autowired
	private IEmpresaDAO dao;

	public void setDao(IEmpresaDAO dao) {
		this.dao = dao;
	}

	@Override
	public Empresa crear(Empresa emp) throws BusinessException{
		try{
		return dao.create(emp);
		}catch(PersistenceException e){
			throw new BusinessException(e.getMessage(),e);
		}
	}

	@Override
	public Empresa actualizar(Empresa emp) {
		return dao.update(emp);
	}

	@Override
	public void borrar(Empresa emp) {
		dao.delete(emp);
	}

	@Override
	public Empresa get(Long id) {
		return dao.findById(id);
	}

	@Override
	public Empresa get(String codigo) {
		return dao.findByCodigo(codigo);
	}

	@Override
	public int getSizeAll() {
		return dao.getTotal();
	}

	@Override
	public List<Empresa> getAll(Integer inicio, Integer total) {
		return dao.getAll(inicio, total);
	}

	@Override
	public List<Empresa> getAll() {
		return dao.getAll(null,null);
	}

	@Override
	public int getTotalFilter(Empresa emp) {
		return dao.getTotalFilter(emp);
	}

	@Override
	public List<Empresa> getAllFilter(Empresa emp, Integer inicio, Integer total) {
		return dao.getAllFilter(emp, inicio, total);
	}

}
