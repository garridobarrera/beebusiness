package es.beebusiness.dao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.beebusiness.dao.IDireccionDAO;
import es.beebusiness.dao.IEmpresaDAO;
import es.beebusiness.dao.IEventoDAO;
import es.beebusiness.dao.IProvinciaDAO;
import es.beebusiness.dao.ITipoEventoDAO;
import es.beebusiness.domain.Direccion;
import es.beebusiness.domain.Empresa;
import es.beebusiness.domain.Evento;
import es.beebusiness.domain.Provincia;
import es.beebusiness.domain.TipoEvento;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/mvc-dispatcher-test-servlet.xml")
public class EventoDAOTest {

	@Autowired
	IEventoDAO eventoDAO;
	@Autowired
	ITipoEventoDAO tipoEventoDAO;
	@Autowired
	IEmpresaDAO empresaDAO;
	@Autowired
	IDireccionDAO direccionDAO;
	@Autowired
	IProvinciaDAO provinciaDAO;

	Long idTipoEvento;

	Long idEmpresa;

	Long idEvento;

	@Transactional
	@Before
	public void setup() {
		TipoEvento tipoBBDD=null;
		Empresa empresaBBDD=null;
		if (idTipoEvento == null) {
			TipoEvento tipoEvento = new TipoEvento();
			tipoEvento.setNombre("Tipo de prueba");
			tipoBBDD = tipoEventoDAO.create(tipoEvento);
			idTipoEvento = tipoBBDD.getId();
		}
		if (idEmpresa == null) {
			Empresa empresa = new Empresa();
			empresa.setCodigo("XXX");
			empresa.setNombre("Beebusiness");
			empresaBBDD = empresaDAO.create(empresa);
			idEmpresa = empresaBBDD.getId();
		}
		if (idEvento == null) {
			Provincia provincia=new Provincia();
			provincia.setIne("0024");
			provincia.setNombre("Sevilla");
			provincia=provinciaDAO.create(provincia);
			Direccion direccion=new Direccion();
			direccion.setProvincia(provincia);
			direccion.setDireccion("C\\ Menorca nº 13");
			Evento evento=new Evento();
			evento.setNombre("Evento de prueba");
			evento.setEmpresa(empresaBBDD);
			evento.setTipoEvento(tipoBBDD);
			evento.setDireccion(direccion);
			idEvento = eventoDAO.create(evento).getId();
		}
	}

	@After
	public void tearDown() {

	}

	@Test
	public void getEventoExistente() {
		Evento evento = eventoDAO.findById(idEvento);
		Assert.assertNotNull(evento);
		Assert.assertEquals(idEvento, evento.getId());
		Assert.assertEquals("Evento de prueba", evento.getNombre());
	}


}
