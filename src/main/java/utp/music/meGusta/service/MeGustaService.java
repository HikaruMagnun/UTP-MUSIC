package utp.music.meGusta.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.meGusta.model.MeGusta;
import utp.music.meGusta.repository.MeGustaRepository;

@RequiredArgsConstructor
@Service
public class MeGustaService {

    private final MeGustaRepository meGustaRepository;

    // Método para encontrar todos los me gusta por usuario
    public Flux<MeGusta> findAllByUsuarioId(Long usuarioId) {
        return meGustaRepository.findByUsuarioId(usuarioId);
    }

    // Método para guardar un me gusta
    public Mono<MeGusta> save(MeGusta meGusta) {
        return meGustaRepository.save(meGusta);
    }

    // Método para eliminar un me gusta
    public Mono<Void> deleteByUsuarioIdAndCancionId(Long usuarioId, Long cancionId) {
        return meGustaRepository.deleteByUsuarioIdAndCancionId(usuarioId, cancionId);
    }
}
