package br.com.criandoapi.projeto.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data

//Para o codigo não ficar muito grande, pode realizar a instalação do lombok e colocar as anotações Getter e Setter, e remover os metodos acessores,
//você consegue acessar normalmente, como se tivesse os metodos criados

//O data ja é uma anotação completa, onde tem Get e Set em apenas uma anotação, caso tenha apreferencia de apenas Get ou apenas Set, pode ultilizar as anotações,@Getter ou @Setter

@Entity //entidade do banco de dados
@Table(name = "usuario") //tabela no banco de dados
public class Usuario {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Valor auto incremente
	@Column(name = "id")
	private Integer id;
	
	@NotBlank(message =  "O email é obrigatorio!")//Campo não pode ser vazio ou nullo 
	@Size(min = 3, message = "O nome deve ter no minimo 3 caracteres!")
	@Column(name = "nome", length = 200, nullable = false) //length serve para colocar um tamanho e o nullable não pode ser um campo vazio
	private String nome;
	
	@Email(message = "Insira um email valido!")
	@NotBlank(message =  "O email é obrigatório!") 
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	
	@NotBlank(message =  "A senha é obrigatória!") 
	@Column(name = "senha", columnDefinition = "TEXT", nullable = false)
	private String senha;
	
	@NotBlank(message =  "O telefone é obrigatório!") 
	@Column(name = "telefone", length = 15, nullable = false)
	private String telefone;
	
}

