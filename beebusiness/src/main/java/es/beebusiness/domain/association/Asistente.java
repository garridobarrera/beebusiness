package es.beebusiness.domain.association;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import es.beebusiness.domain.Evento;
import es.beebusiness.domain.Profesional;

@Entity
@Table(name = "BB_PROF_EVENTO", uniqueConstraints = { @UniqueConstraint(name = "UK_ASISTENTE", columnNames = {
		"PROFESIONAL", "EVENTO" }) })
@NamedQueries(value = {
		@NamedQuery(name = "getEventosByProfesional", query = "SELECT a.evento FROM Asistente a WHERE a.profesional.id=?"),
		@NamedQuery(name = "getTotalAsistentesByEvento", query = "SELECT count(a.profesional) FROM Asistente a WHERE a.evento.id=?"),
		@NamedQuery(name = "getAsistentesByEvento", query = "SELECT a.profesional FROM Asistente a WHERE a.evento.id=?")})
public class Asistente implements Serializable {

	private static final long serialVersionUID = -3009456277998445318L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Profesional profesional;
	private Evento evento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(targetEntity=Profesional.class,optional=false,fetch=FetchType.EAGER)
	@JoinColumn(name = "profesional")
	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	@ManyToOne(targetEntity=Evento.class,optional=false,fetch=FetchType.EAGER)
	@JoinColumn(name = "evento")
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
