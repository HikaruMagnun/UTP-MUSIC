package utp.music.usuario.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;
import utp.music.usuario.model.entity.Usuario;

@Repository
public interface UsuarioRepository extends R2dbcRepository<Usuario, Long> {

    /**
     * Busca un usuario por su nombre o email.
     *
     * @param username el nombre o email del usuario
     * @return un Mono que contiene el usuario encontrado, o vacío si no se
     *         encuentra
     */
    Mono<Usuario> findByNombreOrEmail(String username, String email);

    Mono<Usuario> findByEmail(String email);
}
