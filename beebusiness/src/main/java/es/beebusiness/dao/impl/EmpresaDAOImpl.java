package es.beebusiness.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import es.beebusiness.dao.IEmpresaDAO;
import es.beebusiness.domain.Empresa;

@Repository
public class EmpresaDAOImpl extends AbstractBaseGenericDAOImpl<Empresa, Long> implements IEmpresaDAO{

	private static final long serialVersionUID = 5716963953547544333L;

	@Override
	public Empresa findByCodigo(String codigo) {
		TypedQuery<Empresa> query=em.createNamedQuery(Empresa.QUERY_GETBYNOMBRE, Empresa.class);
		query.setParameter(1, codigo);
		return query.getSingleResult();
	}

	@Override
	public int getTotal() {
		TypedQuery<Long> query=em.createNamedQuery(Empresa.QUERY_GETTOTAL, Long.class);
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Empresa> getAll(Integer inicio, Integer total) {
		TypedQuery<Empresa> query=em.createNamedQuery(Empresa.QUERY_GETALL, Empresa.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		return query.getResultList();
	}

	@Override
	public int getTotalFilter(Empresa emp) {
		TypedQuery<Long> query=em.createNamedQuery(Empresa.QUERY_GETTOTALBYNOMBRE, Long.class);
		query.setParameter(1, emp.getNombre());
		return query.getSingleResult().intValue();
	}

	@Override
	public List<Empresa> getAllFilter(Empresa emp, Integer inicio, Integer total) {
		TypedQuery<Empresa> query=em.createNamedQuery(Empresa.QUERY_GETBYNOMBRE, Empresa.class);
		if(inicio!=null && total!=null){
			query.setFirstResult(inicio);
			query.setMaxResults(total);
		}
		query.setParameter(1, emp.getNombre());
		return query.getResultList();
	}
}
