package es.beebusiness.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import es.beebusiness.dao.IEventoDAO;
import es.beebusiness.domain.Empresa;
import es.beebusiness.domain.Evento;
import es.beebusiness.domain.Perfil;
import es.beebusiness.domain.Sector;

@Repository
public class EventoDAOImpl extends AbstractBaseGenericDAOImpl<Evento, Long> implements IEventoDAO{

	private static final long serialVersionUID = 3218952896504645672L;

	@Override
	public Evento findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotal() {
		TypedQuery<Long> query=em.createNamedQuery(Evento.QUERY_GETTOTAL, Long.class);
		return query.getSingleResult().intValue();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int getTotal(String filtro) {
		 CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		 CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);		 
		 Root<Evento> root=criteriaQuery.from(Evento.class);
		 criteriaQuery.select(criteriaBuilder.count((Expression)root.get("id")));
		 Predicate predicadoNombre=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("nombre")), "%"+filtro.toUpperCase()+"%");
		 Predicate predicadoDescripcion=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("descripcion")), "%"+filtro.toUpperCase()+"%");
		 criteriaQuery.where(criteriaBuilder.or(predicadoNombre,predicadoDescripcion));
		 TypedQuery<Long> query = em.createQuery(criteriaQuery);
		 Long tam=query.getSingleResult();
		 return tam.intValue();
	}

	@Override
	public List<Evento> getAll(Integer inicio, Integer total) {
		TypedQuery<Evento> query=em.createNamedQuery(Evento.QUERY_GETALL, Evento.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		return query.getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Evento> getAll(Integer inicio, Integer total, String filtro) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		 CriteriaQuery<Evento> criteriaQuery = criteriaBuilder.createQuery(Evento.class);
		 Root<Evento> root=criteriaQuery.from(Evento.class);
		 Predicate predicadoNombre=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("nombre")), "%"+filtro.toUpperCase()+"%");
		 Predicate predicadoDescripcion=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("descripcion")), "%"+filtro.toUpperCase()+"%");
		 criteriaQuery.where(criteriaBuilder.or(predicadoNombre,predicadoDescripcion));
		 TypedQuery<Evento> query = em.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Override
	public int getTotalFilter(Evento evento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Evento> getAllFilter(Evento evento, Integer inicio,
			Integer total) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Perfil> getPerfiles(Long id) {
		Query query=em.createNamedQuery(Evento.QUERY_GETPERFILES);
		query.setParameter(1, id);
		return query.getResultList();
	}

	@Override
	public List<Sector> getSectores(Long id) {
		Query query=em.createNamedQuery(Evento.QUERY_GETSECTORES);
		query.setParameter(1, id);
		return query.getResultList();
	}

	@Override
	public List<Sector> getTematicas(Long id) {
		Query query=em.createNamedQuery(Evento.QUERY_GETTEMATICAS);
		query.setParameter(1, id);
		return query.getResultList();
	}

	@Override
	public List<Empresa> getEmpresas(Long id) {
		Query query=em.createNamedQuery(Evento.QUERY_GETEMPRESAS);
		query.setParameter(1, id);
		return query.getResultList();
	}

}
