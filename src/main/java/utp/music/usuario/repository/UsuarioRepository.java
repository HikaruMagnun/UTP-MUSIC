package utp.music.usuario.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import utp.music.usuario.model.entity.Usuario;

@Repository
public interface UsuarioRepository extends R2dbcRepository<Usuario, Long> {
}
