package es.beebusiness.service.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.beebusiness.dao.IUsuarioDAO;
import es.beebusiness.domain.Usuario;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IUsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl implements IUsuarioService {

	private static final long serialVersionUID = -3043786790669364026L;
	@Autowired
	private IUsuarioDAO dao;

	public void setDao(IUsuarioDAO dao) {
		this.dao = dao;
	}

	@Override
	public Usuario crear(Usuario u) throws BusinessException {
		try {
			return dao.create(u);
		} catch (PersistenceException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@Override
	public Usuario actualizar(Usuario u) {
		return dao.update(u);
	}

	@Override
	public void borrar(Usuario u) {
		dao.delete(u);
	}

	@Override
	public Usuario get(Long id) {
		return dao.findById(id);
	}

	@Override
	public Usuario get(String username) throws BusinessException {
		try {
			return dao.findByCodigo(username);
		} catch (NoResultException e) {
			throw new BusinessException("No se encuentra el usuario indicado",
					e);
		}
	}

	@Override
	public int getSizeAll() {
		return dao.getTotal();
	}

	@Override
	public List<Usuario> getAll(Integer inicio, Integer total) {
		return dao.getAll(inicio, total);
	}

	@Override
	public List<Usuario> getAll() {
		return dao.getAll(null, null);
	}

	@Override
	public int getTotalFilter(Usuario u) {
		return dao.getTotalFilter(u);
	}

	@Override
	public List<Usuario> getAllFilter(Usuario u, Integer inicio, Integer total) {
		return dao.getAllFilter(u, inicio, total);
	}

}
