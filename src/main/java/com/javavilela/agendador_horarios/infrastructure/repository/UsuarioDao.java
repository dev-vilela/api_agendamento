package com.javavilela.agendador_horarios.infrastructure.repository;

import com.javavilela.agendador_horarios.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioDao extends JpaRepository<Usuario, Long> {

    @Query("select i from Usuario i where i.email= :email")
    public Usuario findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha")
    Usuario buscarLogin(@Param("email") String email, @Param("senha") String senha);

}
