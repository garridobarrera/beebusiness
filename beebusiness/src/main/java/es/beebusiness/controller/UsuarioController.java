package es.beebusiness.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.beebusiness.authenticator.Encrypter;
import es.beebusiness.domain.Usuario;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IUsuarioService;

@Controller
public class UsuarioController implements Serializable{



	private static final long serialVersionUID = 3009711842773993659L;
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private Encrypter encode;
	public List<Usuario> obtenerUsuarios(){
		return usuarioService.getAll();
	}
	
	public void altaUsuario(Usuario usuario){
		try{
			usuario.setPassword(encode.encode(usuario.getPassword()));
			if(usuario.getId()!=null && !"".equals(usuario.getId())){
				usuarioService.actualizar(usuario);	
				FacesMessage msg = new FacesMessage("Usuario actualizado correctamente");  
				FacesContext.getCurrentInstance().addMessage(null, msg);
			}else{
				usuarioService.crear(usuario);
			FacesMessage msg = new FacesMessage("Usuario actualizado correctamente");  
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			}
		
		}catch (BusinessException e) {
			FacesMessage msg = new FacesMessage("Ha sucedido un error al crear el usuario, puede que el usuario ya exista");  
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		  
	}
	
	
	public void eliminarUsuario(String id){
		Usuario usuario=new Usuario();
		usuario.setId(new Long(id));
		usuarioService.borrar(usuario);
		FacesMessage msg = new FacesMessage("Usuario eliminado correctamente");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	
	public Usuario obtenerUsuario(String id){
		return usuarioService.get(new Long(id));
	}

}
