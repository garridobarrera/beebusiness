package es.beebusiness.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import es.beebusiness.dao.IProfesionalDAO;
import es.beebusiness.domain.Perfil;
import es.beebusiness.domain.Profesional;
import es.beebusiness.domain.Sector;

@Repository
public class ProfesionalDAOImpl extends AbstractBaseGenericDAOImpl<Profesional, Long> implements IProfesionalDAO{

	private static final long serialVersionUID = 5472294309480802077L;

	@Override
	public Profesional findByCodigo(String username) {
		TypedQuery<Profesional> query=em.createNamedQuery(Profesional.QUERY_GETBYUSERNAME, Profesional.class);
		query.setParameter(1, username);
		return query.getSingleResult();
	}

	@Override
	public int getTotal() {
		TypedQuery<Long> query=em.createNamedQuery(Profesional.QUERY_GETTOTAL, Long.class);
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Profesional> getAll(Integer inicio, Integer total) {
		TypedQuery<Profesional> query=em.createNamedQuery(Profesional.QUERY_GETALL, Profesional.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		return query.getResultList();
	}

	@Override
	public int getTotalFilter(Profesional profesional) {
		TypedQuery<Long> query=em.createNamedQuery(Profesional.QUERY_GETTOTALBYUSERNAME, Long.class);
		query.setParameter(1, profesional.getUsername());
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Profesional> getAllFilter(Profesional profesional, Integer inicio, Integer total) {
		TypedQuery<Profesional> query=em.createNamedQuery(Profesional.QUERY_GETBYUSERNAME, Profesional.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		query.setParameter(1, profesional.getUsername());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> getPerfiles(String username) {
		Query query=em.createNamedQuery(Profesional.QUERY_GETPERFILESBYUSERNAME);
		query.setParameter(1, username);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sector> getSectores(String username) {
		Query query=em.createNamedQuery(Profesional.QUERY_GETSECTORESBYUSERNAME);
		query.setParameter(1, username);
		return query.getResultList();
	}
}
