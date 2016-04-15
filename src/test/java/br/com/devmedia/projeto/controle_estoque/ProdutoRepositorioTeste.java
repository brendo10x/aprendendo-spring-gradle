package br.com.devmedia.projeto.controle_estoque;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.devmedia.projeto.controle_estoque.modelo.Produto;
import br.com.devmedia.projeto.controle_estoque.repositorio.ProdutoRepositorio;
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringApplicationConfiguration(classes = AprendendoSpringGradleApplication.class)
@WebAppConfiguration 
public class ProdutoRepositorioTeste {

	@Autowired
	private ProdutoRepositorio produtoRepositorio;

	@Test
	public void teste_1_salvarProdutos() {

		Produto p1 = new Produto();
		p1.setNome("Frango");
		p1.setDescricao("Descrição do Frango");
		p1.setEstoque(12);
		p1.setValor(12.20);
		p1.setFornecedor("Big Frango");

		produtoRepositorio.save(p1);

		Produto p2 = new Produto();
		p2.setNome("Salsicha");
		p2.setDescricao("Descrição da Salsicha");
		p2.setEstoque(50);
		p2.setValor(5.00);
		p2.setFornecedor("FRIGOCENTER");

		produtoRepositorio.save(p2);

		Produto p3 = new Produto();
		p3.setNome("Pizza");
		p3.setDescricao("Descrição da Pizza");
		p3.setEstoque(18);
		p3.setValor(20.50);
		p3.setFornecedor("Pasto && Pizza");

		produtoRepositorio.save(p3);

	}

	@Test
	public void teste_2_atualizarProdutoP1() {

		Produto p1 = produtoRepositorio.findOne(2); // p1 com id=2
		p1.setEstoque(50);
		p1.setFornecedor("Mercado do Norte");

		produtoRepositorio.save(p1);

		assertEquals(50, p1.getEstoque().intValue());
		assertEquals("Mercado do Norte", p1.getFornecedor());

	}

	@Test
	public void teste_3_buscarProdutoP1() {

		Produto p1 = produtoRepositorio.findOne(2); // p1 com id=2

		assertEquals("Frango", p1.getNome());
		assertEquals("Descrição do Frango", p1.getDescricao());

	}

	@Test
	public void teste_4_buscarTodosOsProdutos() {

		List<Produto> produtos = produtoRepositorio.findAll();

		assertTrue(produtos.size() > 0);
		assertThat(produtos.size(), is(3));
	}

	@Test
	public void teste_5_verificarSeProdutoP1Existe() {

		Boolean p1Existe = produtoRepositorio.exists(2); // p1 com id=2

		assertTrue(p1Existe);
	}

	@Test
	public void teste_6_verificarOTotalDeProdutos() {

		Long totalProdutos = produtoRepositorio.count();

		assertTrue(3L == totalProdutos.longValue());

	}

	@Test
	public void teste_7_excluirP3() {

		produtoRepositorio.delete(4); // p3 com id=4

		Boolean p3Existe = produtoRepositorio.exists(4);

		assertFalse(p3Existe);
	}

	@Test
	public void teste_8_excluirTodosOsProdutos() {

		produtoRepositorio.deleteAll();

		List<Produto> produtos = produtoRepositorio.findAll();

		assertTrue(produtos.size() == 0);
	}

}