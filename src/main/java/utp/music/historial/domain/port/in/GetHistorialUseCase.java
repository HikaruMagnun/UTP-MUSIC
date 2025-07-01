package utp.music.historial.domain.port.in;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.historial.domain.model.HistorialReproduccion;

public interface GetHistorialUseCase {
    Flux<HistorialReproduccion> findAll();

    Mono<HistorialReproduccion> findById(Long id);

    Flux<HistorialReproduccion> findByUsuarioId(Long usuarioId);

    Flux<HistorialReproduccion> findTop20ByUsuarioId(Long usuarioId);
}
