package br.com.devmedia.projeto.controle_estoque.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginControlador {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modeloEVisao = new ModelAndView("login");
		return modeloEVisao;
	}

}
