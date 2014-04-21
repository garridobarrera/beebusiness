package es.beebusiness.controller.lazy;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import es.beebusiness.domain.Usuario;
import es.beebusiness.service.IUsuarioService;


public class UsuarioLazyDataModel extends LazyDataModel<Usuario>{

	private static final long serialVersionUID = -8117874649477106123L;
	
	public UsuarioLazyDataModel(){
		super();
		super.setPageSize(5);
	}
	
	
	private IUsuarioService service;
	
	
	private int currentPage=1;
	
	
	private List<Usuario> usuarios;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public IUsuarioService getService() {
		return service;
	}

	@Autowired
	public void setService(IUsuarioService service) {
		this.service = service;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	
	@Override
	public List<Usuario> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		    usuarios= service.getAll(first, pageSize);
		    return usuarios;
	}
	
	@Override
	public Object getRowKey(Usuario usuario) {
		return usuario.getId();
	}
	
	@Override
	public int getRowCount() {
		return service.getSizeAll();
	}
	

	

}
