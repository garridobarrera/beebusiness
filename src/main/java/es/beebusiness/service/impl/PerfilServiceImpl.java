package es.beebusiness.service.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.beebusiness.dao.IPerfilDAO;
import es.beebusiness.domain.Perfil;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IPerfilService;

@Service
@Transactional
public class PerfilServiceImpl implements IPerfilService {

	private static final long serialVersionUID = -6580258167920702997L;
	@Autowired
	private IPerfilDAO dao;

	public void setDao(IPerfilDAO dao) {
		this.dao = dao;
	}

	@Override
	public Perfil crear(Perfil p) throws BusinessException{
		try{
		return dao.create(p);
		}catch(PersistenceException e){
			throw new BusinessException(e.getMessage(),e);
		}
	}

	@Override
	public Perfil actualizar(Perfil p) {
		return dao.update(p);
	}

	@Override
	public void borrar(Perfil p) {
		try{
		dao.delete(p);
		}catch(PersistenceException e){
			throw new BusinessException(e.getMessage(),e);
		}
	}

	@Override
	public Perfil get(Long id) {
		return dao.findById(id);
	}

	@Override
	public Perfil get(String codigo) {
		return dao.findByCodigo(codigo);
	}

	@Override
	public int getSizeAll() {
		return dao.getTotal();
	}

	@Override
	public List<Perfil> getAll(Integer inicio, Integer total) {
		return dao.getAll(inicio, total);
	}

	@Override
	public List<Perfil> getAll() {
		return dao.getAll(null,null);
	}

	@Override
	public int getTotalFilter(Perfil p) {
		return dao.getTotalFilter(p);
	}

	@Override
	public List<Perfil> getAllFilter(Perfil p, Integer inicio, Integer total) {
		return dao.getAllFilter(p, inicio, total);
	}
	
	@Override
	public List<Perfil> getAll(Integer inicio, Integer total, String busqueda) {
		if(busqueda==null || "".equals(busqueda))
			return dao.getAll(inicio, total);
		else
			return dao.getAll(inicio, total, busqueda);
	}

	@Override
	public int getSizeAll(String filtro) {
		if(filtro==null || "".equals(filtro))
			return dao.getTotal();
		else
			return dao.getTotal(filtro);
	}

}
