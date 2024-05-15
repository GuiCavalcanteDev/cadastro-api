package br.com.criandoapi.projeto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.criandoapi.projeto.model.Usuario;
import br.com.criandoapi.projeto.service.UsuarioService;
import jakarta.validation.Valid;

@RestController // classe de controlle, endpoint
@CrossOrigin("*")//significa qualquer requisição para essa rota esta liberada
@RequestMapping("/usuarios") //rota 
public class UsuarioController {
	
	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping //buscar a lista de usuarios
	public ResponseEntity<List<Usuario>> listaUsuarios(){
		return ResponseEntity.status(200).body(usuarioService.listarUsuario()); //O status 200 significa que conseguiu se conectar com a aplicação e vai apresetar a lista de usuario
	}
	
	@PostMapping //criar dados de um usuario
	public ResponseEntity<Usuario> criarUsuario (@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.status(201).body(usuarioService.criarUsuario(usuario)); //O status 201 significa que foi criado o usuario
	}
	
	@PutMapping //alterar dados de um usuario
	public ResponseEntity<Usuario> editarUsuario (@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.status(200).body(usuarioService.editarUsuario(usuario));
	}
	
	@DeleteMapping("/{id}") //
	public ResponseEntity<?> excluirUsuario(@PathVariable Integer id) { //com o ? significa que é um usuario generico, então não vai presentar mais o usuario que foi deletado
		usuarioService.excluirUsuario(id);
		return ResponseEntity.status(204).build();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
