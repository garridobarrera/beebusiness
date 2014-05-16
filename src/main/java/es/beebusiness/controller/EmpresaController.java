package es.beebusiness.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.beebusiness.domain.Empresa;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IEmpresaService;

@Controller
public class EmpresaController implements Serializable{


	private static final long serialVersionUID = -1198004873235769986L;
	@Autowired
	private IEmpresaService empresaService;
	public List<Empresa> obtenerEmpresas(){
		return empresaService.getAll();
	}
	
	public void altaEmpresa(Empresa emp){
		try{
			if(emp.getId()!=null && !"".equals(emp.getId())){
			empresaService.actualizar(emp);	
			FacesMessage msg = new FacesMessage("Empresa actualizada correctamente");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			}else{
			empresaService.crear(emp);
			FacesMessage msg = new FacesMessage("Empresa creada correctamente");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		
		}catch (BusinessException e) {
			FacesMessage msg = new FacesMessage("Ha sucedido un error al crear la empresa, puede que la empresa ya exista");  
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	
	public void eliminarEmpresa(String id){
		Empresa emp=new Empresa();
		emp.setId(new Long(id));
		empresaService.borrar(emp);
		FacesMessage msg = new FacesMessage("Empresa eliminada correctamente");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	
	public Empresa obtenerEmpresa(String id){
		Empresa emp=new Empresa();
		emp.setId(new Long(id));
		return empresaService.get(new Long(id));
	}

}
