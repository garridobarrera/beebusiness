package es.beebusiness.dao;

import java.util.List;
import java.util.Set;

import es.beebusiness.domain.Usuario;

public interface IUsuarioDAO extends IBaseGenericDAO<Usuario, Long> {
	public Usuario findByCodigo(String username);
	public int getTotal();
	public List<Usuario> getAll(Integer inicio,Integer total);
	public int getTotalFilter(Usuario u);
	public List<Usuario> getAllFilter(Usuario u,Integer inicio,Integer total);
}
