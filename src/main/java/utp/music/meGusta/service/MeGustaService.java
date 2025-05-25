package utp.music.meGusta.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.cancion.model.Cancion;
import utp.music.cancion.service.CancionService;
import utp.music.meGusta.model.MeGusta;
import utp.music.meGusta.repository.MeGustaRepository;

@RequiredArgsConstructor
@Service
public class MeGustaService {

    private final MeGustaRepository meGustaRepository;
    private final CancionService cancionService;

    // Método para encontrar todos los me gusta por usuario
    public Flux<Cancion> findAllByUsuarioId(Long usuarioId) {
        return meGustaRepository.findByUsuarioId(usuarioId)
                .flatMap(meGusta -> cancionService.findById(meGusta.getCancionId()));
    }

    // Método para guardar un me gusta
    public Mono<MeGusta> save(MeGusta meGusta) {
        return meGustaRepository.save(meGusta);
    }

    // Método para eliminar un me gusta
    public Mono<Void> deleteByUsuarioIdAndCancionId(Long usuarioId, Long cancionId) {
        return meGustaRepository.deleteByUsuarioIdAndCancionId(usuarioId, cancionId);
    }

    // Método para añadir un me gusta
    public Mono<Void> addMeGusta(Long idUsuario, Long idCancion) {
        MeGusta meGusta = new MeGusta();
        meGusta.setUsuarioId(idUsuario);
        meGusta.setCancionId(idCancion);
        return meGustaRepository.save(meGusta).then();
    }

    // Método para quitar un me gusta
    public Mono<Void> removeMeGusta(Long idUsuario, Long idCancion) {
        return meGustaRepository.deleteByUsuarioIdAndCancionId(idUsuario, idCancion);
    }
}
