package com.javavilela.agendador_horarios.services;

import com.javavilela.agendador_horarios.exceptions.EmailExistsException;
import com.javavilela.agendador_horarios.exceptions.ServiceExc;
import com.javavilela.agendador_horarios.infrastructure.entity.Usuario;
import com.javavilela.agendador_horarios.infrastructure.repository.UsuarioDao;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UsuarioService {

    private UsuarioDao repositorioUsuario;

    public UsuarioService(UsuarioDao repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    //Método para salvar um novo usuário no banco
    public void salvarUsuario(Usuario user) {

        // Verifica se já existe email
        if (repositorioUsuario.findByEmail(user.getEmail()) != null) {
            throw new EmailExistsException("Já possui um email cadastrado para: " + user.getEmail());
        }

        // Criptografa senha
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setSenha(encoder.encode(user.getSenha()));

        repositorioUsuario.save(user);
    }

    public Usuario loginUser(String email, String senha) throws ServiceExc {

        Usuario userLogin = repositorioUsuario.buscarLogin(email, senha);

        if (userLogin == null) {
            System.out.println("⚠️ Nenhum usuário encontrado com essas credenciais!");
        } else {
            System.out.println("✅ Usuário encontrado: " + userLogin.getEmail());
        }

        return userLogin;
    }

}
