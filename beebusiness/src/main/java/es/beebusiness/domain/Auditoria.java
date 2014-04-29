package es.beebusiness.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@MappedSuperclass
public class Auditoria {
private String usuarioAuditoria;
private Date fechaAuditoria;
public String getUsuarioAuditoria() {
	return usuarioAuditoria;
}
public void setUsuarioAuditoria(String usuarioAuditoria) {
	this.usuarioAuditoria = usuarioAuditoria;
}
@Temporal(TemporalType.TIMESTAMP)
public Date getFechaAuditoria() {
	return fechaAuditoria;
}
public void setFechaAuditoria(Date fechaAuditoria) {
	this.fechaAuditoria = fechaAuditoria;
}


@PrePersist
@PreUpdate
public void generarAuditoria(){
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String usuario=auth.getName();
	this.usuarioAuditoria=usuario;
	this.fechaAuditoria=Calendar.getInstance().getTime();
}

}
