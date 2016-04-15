package br.com.devmedia.projeto.controle_estoque.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.devmedia.projeto.controle_estoque.modelo.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Integer> {
	
}
