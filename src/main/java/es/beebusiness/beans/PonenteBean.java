package es.beebusiness.beans;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import es.beebusiness.controller.lazy.PonenteLazyDataModel;
import es.beebusiness.domain.Ponente;

public class PonenteBean implements Serializable{


	private static final long serialVersionUID = -7544774824496436092L;	
	private Ponente ponente;
	private PonenteLazyDataModel lazy;
	
	
	public PonenteLazyDataModel getLazy() {
		return lazy;
	}
	public void setLazy(PonenteLazyDataModel lazy) {
		this.lazy = lazy;
	}
	public Ponente getPonente() {
		return ponente;
	}
	public void setPonente(Ponente ponente) {
		this.ponente = ponente;
	}
	
	public void upload(FileUploadEvent event) {
		ponente.setMimeType(event.getFile().getContentType());
		ponente.setImageFile(event.getFile().getContents());
		ponente.setNombreImagen(event.getFile().getFileName());
	}

	public StreamedContent getDownload() {
		InputStream input=new ByteArrayInputStream(ponente.getImageFile());
		StreamedContent file = new DefaultStreamedContent(input, ponente.getMimeType(), ponente.getNombreImagen());
		return file;
	}


}
