package br.com.devmedia.projeto.controle_estoque.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexControlador {

	@RequestMapping("/")
	public String obterPaginaPrincipal() {
		return "index";
	}
}
