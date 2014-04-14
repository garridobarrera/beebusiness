package es.beebusiness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.beebusiness.dao.IPerfilDAO;
import es.beebusiness.domain.Perfil;
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
	public Perfil crear(Perfil p) {
		return dao.create(p);
	}

	@Override
	public Perfil actualizar(Perfil p) {
		return dao.update(p);
	}

	@Override
	public void borrar(Perfil p) {
		dao.delete(p);
	}

}
