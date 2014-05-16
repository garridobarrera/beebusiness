package es.beebusiness.dao.association.impl;

import org.springframework.stereotype.Repository;

import es.beebusiness.dao.association.IAsistenteDAO;
import es.beebusiness.dao.impl.AbstractBaseGenericDAOImpl;
import es.beebusiness.domain.association.Asistente;

@Repository
public class AsistenteDAOImpl extends AbstractBaseGenericDAOImpl<Asistente, Long> implements IAsistenteDAO{

}
