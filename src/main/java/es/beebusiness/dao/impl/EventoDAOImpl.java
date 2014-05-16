package es.beebusiness.dao.impl;

import org.springframework.stereotype.Repository;

import es.beebusiness.dao.IEventoDAO;
import es.beebusiness.domain.Evento;

@Repository
public class EventoDAOImpl extends AbstractBaseGenericDAOImpl<Evento, Long> implements IEventoDAO{

}
