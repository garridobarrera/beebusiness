package es.beebusiness.dao.impl;

import org.springframework.stereotype.Repository;

import es.beebusiness.dao.IEmpresaDAO;
import es.beebusiness.domain.Empresa;

@Repository
public class EmpresaDAOImpl extends AbstractBaseGenericDAOImpl<Empresa, Long> implements IEmpresaDAO{

}
