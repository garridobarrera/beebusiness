package es.beebusiness.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import es.beebusiness.dao.ISectorDAO;
import es.beebusiness.domain.Sector;

@Repository
public class SectorDAOImpl extends AbstractBaseGenericDAOImpl<Sector, Long> implements ISectorDAO{

	private static final long serialVersionUID = -2258430640831149302L;

	@Override
	public Sector findByCodigo(String codigo) {
		TypedQuery<Sector> query=em.createNamedQuery(Sector.QUERY_GETBYNOMBRE, Sector.class);
		query.setParameter(1, codigo);
		return query.getSingleResult();
	}

	@Override
	public int getTotal() {
		TypedQuery<Long> query=em.createNamedQuery(Sector.QUERY_GETTOTAL, Long.class);
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Sector> getAll(Integer inicio, Integer total) {
		TypedQuery<Sector> query=em.createNamedQuery(Sector.QUERY_GETALL, Sector.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		return query.getResultList();
	}

	@Override
	public int getTotalFilter(Sector s) {
		TypedQuery<Long> query=em.createNamedQuery(Sector.QUERY_GETTOTALBYNOMBRE, Long.class);
		query.setParameter(1, s.getNombre());
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Sector> getAllFilter(Sector s, Integer inicio, Integer total) {
		TypedQuery<Sector> query=em.createNamedQuery(Sector.QUERY_GETBYNOMBRE, Sector.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		query.setParameter(1, s.getNombre());
		return query.getResultList();
	}

	@Override
	public int getTotal(String tipo) {
		if(!"".equals(tipo)){
			TypedQuery<Long> query=em.createNamedQuery(Sector.QUERY_GETTOTALBYTIPO, Long.class);
			query.setParameter(1, tipo);
			return query.getSingleResult().intValue();
		}else{
			return getTotal();
		}
	}

	@Override
	public List<Sector> getAll(Integer inicio, Integer total, String tipo) {
		if(tipo!=null && !"".equals(tipo)){
			TypedQuery<Sector> query=em.createNamedQuery(Sector.QUERY_GETALLBYTIPO, Sector.class);
			if(inicio!=null && total!=null){
				query.setFirstResult(inicio);
				query.setMaxResults(total);
			}
			query.setParameter(1, tipo);
			return query.getResultList();
		}else{
			return getAll(inicio, total);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Sector> getAllFilter(Integer inicio, Integer total,
			String filtro) {
		 CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		 CriteriaQuery<Sector> criteriaQuery = criteriaBuilder.createQuery(Sector.class);
		 Root<Sector> root=criteriaQuery.from(Sector.class);
		 Predicate predicadoNombre=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("nombre")), "%"+filtro.toUpperCase()+"%");
		 Predicate predicadoTipo=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("tipo")), "%"+filtro.toUpperCase()+"%");
		 criteriaQuery.where(criteriaBuilder.or(predicadoNombre,predicadoTipo));
		 TypedQuery<Sector> query = em.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int getsize(String filtro) {
		 CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		 CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);		 
		 Root<Sector> root=criteriaQuery.from(Sector.class);
		 criteriaQuery.select(criteriaBuilder.count((Expression)root.get("id")));
		 Predicate predicadoNombre=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("nombre")), "%"+filtro.toUpperCase()+"%");
		 Predicate predicadoTipo=criteriaBuilder.like(criteriaBuilder.upper((Expression)root.get("tipo")), "%"+filtro.toUpperCase()+"%");
		 criteriaQuery.where(criteriaBuilder.or(predicadoNombre,predicadoTipo));
		 TypedQuery<Long> query = em.createQuery(criteriaQuery);
		 Long tam=query.getSingleResult();
		 return tam.intValue();
	}
}
