package br.com.devmedia.projeto.controle_estoque.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.devmedia.projeto.controle_estoque.modelo.Produto;
import br.com.devmedia.projeto.controle_estoque.repositorio.ProdutoRepositorio;

@Controller
@RequestMapping("produtos")
public class ProdutoControlador {

	@Autowired
	private ProdutoRepositorio produtoRepositorio;

	@RequestMapping(value = "listar", method = RequestMethod.GET)
	public ModelAndView listarProdutos() {
		ModelAndView modeloEVisao = new ModelAndView("produto/listarProdutos");
		modeloEVisao.addObject("produtos", produtoRepositorio.findAll());
		return modeloEVisao;
	}

	@RequestMapping(value = "formCadastrar", method = RequestMethod.GET)
	public ModelAndView formCadastrarProduto(Produto produto) {
		ModelAndView modeloEVisao = new ModelAndView("produto/cadastrarProduto");
		return modeloEVisao;
	}

	@RequestMapping(value = "acaoCadastrar", method = RequestMethod.POST)
	public ModelAndView salvarProduto(@Valid Produto produto, BindingResult bindingResult,
			RedirectAttributes redirecionarAtributos) {
		// verifica se existe erros na validação
		if (bindingResult.hasErrors()) {
			return formCadastrarProduto(produto);
		}
		produtoRepositorio.save(produto);
		redirecionarAtributos.addFlashAttribute("sucesso", "Produto " + produto.getNome() + " cadastrado com sucesso!");
		return new ModelAndView("redirect:/produtos/formCadastrar");
	}

	@RequestMapping(value = "formAtualizar/{id}", method = RequestMethod.GET)
	public ModelAndView formAtualizarProduto(@PathVariable Integer id) {
		ModelAndView modeloEVisao = new ModelAndView("produto/atualizarProduto");
		modeloEVisao.addObject("produto", produtoRepositorio.findOne(id));
		return modeloEVisao;
	}

	@RequestMapping(value = "acaoAtualizar", method = RequestMethod.POST)
	public ModelAndView atualizarProduto(@Valid Produto produto, BindingResult bindingResult,
			RedirectAttributes redirecionarAtributos) {
		// verifica se existe erros na validação
		if (bindingResult.hasErrors()) {
			ModelAndView modeloEVisao = new ModelAndView("produto/atualizarProduto");
			modeloEVisao.addObject("produto", produto);
			return modeloEVisao;
		}
		produtoRepositorio.save(produto);
		redirecionarAtributos.addFlashAttribute("sucesso", "Produto " + produto.getNome() + " atualizado com sucesso!");
		ModelAndView modeloEVisao = new ModelAndView("redirect:/produtos/formAtualizar/" + produto.getId());
		return modeloEVisao;
	}

	@RequestMapping(value = "acaoExcluir/{id}", method = RequestMethod.GET)
	public String excluirProduto(@PathVariable Integer id, RedirectAttributes redirecionarAtributos) {
		produtoRepositorio.delete(id);
		redirecionarAtributos.addFlashAttribute("sucesso", "Produto excluído com sucesso!");
		return "redirect:/produtos/listar";
	}

}
