package br.com.ghabriel.ProjetoFinalBackend;

import br.com.ghabriel.ProjetoFinalBackend.model.Rol;
import br.com.ghabriel.ProjetoFinalBackend.model.Usuario;
import br.com.ghabriel.ProjetoFinalBackend.model.UsuarioRol;
import br.com.ghabriel.ProjetoFinalBackend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ProjetoFinalBackendApplication implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoFinalBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		Usuario usuario = new Usuario();
//
//		usuario.setNome("Ghabriel");
//		usuario.setUsername("gh");
//		usuario.setPassword(passwordEncoder.encode("9999"));
//		usuario.setEmail("gh@gmail.com");
//		usuario.setTelefone("1234354345");
//		usuario.setPerfil("foto.png");
//
//		Rol rol = new Rol();
//		rol.setRolId(2L);
//		rol.setRolNome("USER");
//
//		Set<UsuarioRol> usuariosRoles = new HashSet<>();
//		UsuarioRol usuarioRol = new UsuarioRol();
//		usuarioRol.setRol(rol);
//		usuarioRol.setUsuario(usuario);
//		usuariosRoles.add(usuarioRol);
//
//		Usuario usuarioSALVO = usuarioService.salvarUsuario(usuario,usuariosRoles);
//		System.out.println(usuarioSALVO.getUsername());

	}


}

