package utp.music.artista.domain.port.out;

import reactor.core.publisher.Mono;
import utp.music.usuario.domain.model.Usuario;

public interface UsuarioRepositoryPort {
    Mono<Usuario> findByNombreOrEmail(String nombre, String email);

    Mono<Usuario> save(Usuario usuario);
}
