package es.beebusiness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.beebusiness.dao.IEventoDAO;
import es.beebusiness.domain.Evento;

@Controller
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	private IEventoDAO dao;

	@RequestMapping(value="/{name}", method = RequestMethod.GET)
	public String getMovie(@PathVariable String name, ModelMap model) {

		model.addAttribute("movie", name);
		return "list";

	}
	
	@RequestMapping(value="/json", method = RequestMethod.GET)
	public @ResponseBody Evento getJson(){
		Evento evento=new Evento();
		evento.setId(1l);
		evento.setNombre("nombre");
		return evento;
	}
	
}