package utp.music.historial.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.historial.model.HistorialReproduccion;
import utp.music.historial.repository.HistorialReproduccionRepository;

@RequiredArgsConstructor
@Service
public class HistorialReproduccionService {

    private final HistorialReproduccionRepository historialRepository;

    public Flux<HistorialReproduccion> findAll() {
        return historialRepository.findAll();
    }

    public Mono<HistorialReproduccion> findById(Long id) {
        return historialRepository.findById(id);
    }

    public Flux<HistorialReproduccion> findByUsuarioId(Long usuarioId) {
        return historialRepository.findByUsuarioId(usuarioId);
    }

    public Mono<HistorialReproduccion> save(HistorialReproduccion historial) {
        return historialRepository.save(historial);
    }

    public Mono<Void> deleteById(Long id) {
        return historialRepository.deleteById(id);
    }
}
