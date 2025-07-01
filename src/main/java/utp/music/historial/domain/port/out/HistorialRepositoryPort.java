package utp.music.historial.domain.port.out;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.historial.domain.model.DailyHistorialCount;
import utp.music.historial.domain.model.HistorialReproduccion;

public interface HistorialRepositoryPort {
    Flux<HistorialReproduccion> findAll();

    Mono<HistorialReproduccion> findById(Long id);

    Flux<HistorialReproduccion> findByUsuarioId(Long usuarioId);

    Flux<HistorialReproduccion> findByUsuarioIdOrderByFechaHoraDesc(Long usuarioId);

    Mono<HistorialReproduccion> save(HistorialReproduccion historial);

    Mono<Void> deleteById(Long id);

    Mono<Long> countByArtistaId(Long artistaId);

    Flux<DailyHistorialCount> countByArtistaIdGroupByDay(Long artistaId);
}
