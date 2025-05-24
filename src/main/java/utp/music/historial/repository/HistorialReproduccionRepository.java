package utp.music.historial.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import utp.music.historial.model.HistorialReproduccion;

@Repository
public interface HistorialReproduccionRepository extends R2dbcRepository<HistorialReproduccion, Long> {
    Flux<HistorialReproduccion> findByUsuarioId(Long usuarioId);
}
