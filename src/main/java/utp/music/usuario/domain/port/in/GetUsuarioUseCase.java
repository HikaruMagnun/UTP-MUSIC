package utp.music.usuario.domain.port.in;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.usuario.domain.model.Usuario;

public interface GetUsuarioUseCase {
    Flux<Usuario> listarTodos();

    Mono<Usuario> buscarPorId(Long id);

    Mono<Usuario> guardar(Usuario usuario);

    Mono<Void> eliminar(Long id);
}
