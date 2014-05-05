package es.beebusiness.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.security.core.context.SecurityContextHolder;

@MappedSuperclass
public class Auditoria {
@JsonIgnore
private String usuarioAuditoria;
@JsonIgnore
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
	this.usuarioAuditoria=SecurityContextHolder.getContext().getAuthentication().getName();
	this.fechaAuditoria=Calendar.getInstance().getTime();
}

}
