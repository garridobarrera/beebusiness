package es.beebusiness.service;

import java.io.Serializable;
import java.util.List;

import es.beebusiness.dao.IUsuarioDAO;
import es.beebusiness.domain.Usuario;
import es.beebusiness.exception.BusinessException;

public interface IUsuarioService extends Serializable{

	public void setDao(IUsuarioDAO dao);
	public Usuario crear(Usuario u) throws BusinessException;
	public Usuario actualizar(Usuario u);
	public void borrar(Usuario u);
	public Usuario get(Long id);
	public Usuario get(String username) throws BusinessException;
	public int getSizeAll();
	public List<Usuario> getAll(Integer inicio,Integer total);
	public List<Usuario> getAll();
	public int getTotalFilter(Usuario u);
	public List<Usuario> getAllFilter(Usuario u, Integer inicio, Integer total);
}
