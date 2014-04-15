package es.beebusiness.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import es.beebusiness.dao.IPerfilDAO;
import es.beebusiness.domain.Perfil;

@Repository
public class PerfilDAOImpl extends AbstractBaseGenericDAOImpl<Perfil, Long> implements IPerfilDAO{

	@Override
	public Perfil findByCodigo(String codigo) {
		TypedQuery<Perfil> query=em.createNamedQuery(Perfil.QUERY_GETBYNOMBRE, Perfil.class);
		query.setParameter(1, codigo);
		return query.getSingleResult();
	}

	@Override
	public int getTotal() {
		TypedQuery<Long> query=em.createNamedQuery(Perfil.QUERY_GETTOTAL, Long.class);
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Perfil> getAll(Integer inicio, Integer total) {
		TypedQuery<Perfil> query=em.createNamedQuery(Perfil.QUERY_GETALL, Perfil.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		return query.getResultList();
	}

	@Override
	public int getTotalFilter(Perfil p) {
		TypedQuery<Long> query=em.createNamedQuery(Perfil.QUERY_GETTOTALBYNOMBRE, Long.class);
		query.setParameter(1, p.getNombre());
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Perfil> getAllFilter(Perfil p, Integer inicio, Integer total) {
		TypedQuery<Perfil> query=em.createNamedQuery(Perfil.QUERY_GETBYNOMBRE, Perfil.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		query.setParameter(1, p.getNombre());
		return query.getResultList();
	}

}
