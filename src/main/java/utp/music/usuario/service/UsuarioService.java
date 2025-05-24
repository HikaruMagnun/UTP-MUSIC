package utp.music.usuario.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.usuario.model.entity.Usuario;
import utp.music.usuario.repository.UsuarioRepository;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Flux<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Mono<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Mono<Usuario> guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Mono<Void> eliminar(Long id) {
        return usuarioRepository.deleteById(id);
    }
}
