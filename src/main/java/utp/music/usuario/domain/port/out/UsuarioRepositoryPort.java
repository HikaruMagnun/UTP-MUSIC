package utp.music.usuario.domain.port.out;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.usuario.domain.model.Usuario;

public interface UsuarioRepositoryPort {
    Flux<Usuario> findAll();

    Mono<Usuario> findById(Long id);

    Mono<Usuario> save(Usuario usuario);

    Mono<Void> deleteById(Long id);

    Mono<Usuario> findByNombreOrEmail(String nombre, String email);

    Mono<Usuario> findByEmail(String email);
}
