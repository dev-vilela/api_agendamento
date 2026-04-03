package com.javavilela.agendador_horarios.controller;

import com.javavilela.agendador_horarios.exceptions.EmailExistsException;
import com.javavilela.agendador_horarios.infrastructure.entity.Usuario;
import com.javavilela.agendador_horarios.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {

        Usuario userLogin = usuarioService.loginUser(usuario.getEmail(), usuario.getSenha());

        if (userLogin != null) {
            return ResponseEntity.ok(userLogin);
        } else {
            return ResponseEntity.status(401).body("Usuário ou senha inválidos");
        }
    }

    @PostMapping("/cadastro")
    public Object cadastrar(@RequestBody Usuario usuario) {
        try {
            usuarioService.salvarUsuario(usuario);
            return new SuccessResponse("Usuário cadastrado com sucesso!");
        } catch (EmailExistsException e) {
            return new ErrorResponse("E-mail já cadastrado!");
        } catch (Exception e) {
            return new ErrorResponse("Erro ao cadastrar: " + e.getMessage());
        }
    }

    // Classes auxiliares para retorno
    public record SuccessResponse(String message) {}
    public record ErrorResponse(String message) {}
}