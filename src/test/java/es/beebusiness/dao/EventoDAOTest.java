package es.beebusiness.dao;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.beebusiness.domain.Direccion;
import es.beebusiness.domain.Empresa;
import es.beebusiness.domain.Evento;
import es.beebusiness.domain.Perfil;
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
	@Autowired
	IPerfilDAO perfilDAO;

	Long idTipoEvento;

	Long idEmpresa;

	Long idEvento;
	
	Long idPerfil;
	
	Long idProvincia;


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
			idProvincia=provincia.getId();
			Direccion direccion=new Direccion();
			direccion.setProvincia(provincia);
			direccion.setDireccion("C\\ Menorca nº 13");
			Evento evento=new Evento();
			evento.setNombre("Evento de prueba");
			Set<Empresa> empresas=new HashSet<Empresa>();
			empresas.add(empresaBBDD);
			evento.setEmpresas(empresas);
			evento.setTipoEvento(tipoBBDD);
			//evento.setDireccion(direccion);
			idEvento = eventoDAO.create(evento).getId();
		}
		Perfil perfilCreado=new Perfil();
		perfilCreado.setNombre("CONSULTOR");
		idPerfil=perfilDAO.create(perfilCreado).getId();
	}

	@After
	public void tearDown() {
		eventoDAO.delete(eventoDAO.getReference(idEvento));
		empresaDAO.delete(empresaDAO.getReference(idEmpresa));
		tipoEventoDAO.delete(tipoEventoDAO.getReference(idTipoEvento));
		perfilDAO.delete(perfilDAO.getReference(idPerfil));
		provinciaDAO.delete(provinciaDAO.getReference(idProvincia));
	}
	

	
	@Test
	public void crearEventoCorrecto(){
		Provincia provincia=provinciaDAO.findById(idProvincia);
		Direccion direccion=new Direccion();
		direccion.setDireccion("Almería nº 48");
		direccion.setProvincia(provincia);
		Perfil perfil=perfilDAO.findById(idPerfil);
		Empresa empresa=empresaDAO.findById(idEmpresa);
		TipoEvento tipoEvento=tipoEventoDAO.findById(idTipoEvento);
		Evento evento=new Evento();
		evento.setAsistentes(4l);
		evento.setDescripcion("Evento con perfiles");
		//evento.setDireccion(direccion);
		Set<Empresa> empresas=new HashSet<Empresa>();
		empresas.add(empresa);
		evento.setEmpresas(empresas);
		evento.setTipoEvento(tipoEvento);
		Set<Perfil> perfiles=new HashSet<Perfil>();
		perfiles.add(perfil);
		evento.setPerfiles(perfiles);
		evento=eventoDAO.create(evento);
		eventoDAO.delete(eventoDAO.getReference(evento.getId()));
	}

	@Test
	public void getEventoExistente() {
		Evento evento = eventoDAO.findById(idEvento);
		Assert.assertNotNull(evento);
		Assert.assertEquals(idEvento, evento.getId());
		Assert.assertEquals("Evento de prueba", evento.getNombre());
	}


}
