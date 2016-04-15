package br.com.devmedia.projeto.controle_estoque.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "produto")
public @Data class Produto {

	@Id
	@SequenceGenerator(name = "produtoSeq", sequenceName = "produto_id_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produtoSeq")
	private Integer id;

	@Column(name = "nome")
	@Size(max = 60, message = "Para o campo nome, 60 é o tamanho máximo de caracteres permitidos")
	@NotEmpty(message = "O campo nome não pode ser vazio")
	private String nome;

	@Column(name = "descricao")
	@Size(max = 100, message = "Para o campo descrição, 100 é o tamanho máximo de caracteres permitidos")
	@NotEmpty(message = "O campo descrição não pode ser vazio")
	private String descricao;

	@Column(name = "valor")
	@NotNull(message = "O campo valor é obrigatório")
	private Double valor;

	@Column(name = "fornecedor")
	@Size(max = 60, message = "Para o campo fornecedor, 60 é o tamanho máximo de caracteres permitidos")
	private String fornecedor;

	@Column(name = "estoque")
	@NotNull(message = "O campo estoque é obrigatório")
	private Integer estoque;

}
