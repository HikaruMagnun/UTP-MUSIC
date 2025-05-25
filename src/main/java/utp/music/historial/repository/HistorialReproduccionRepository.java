package utp.music.historial.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.historial.model.HistorialReproduccion;

@Repository
public interface HistorialReproduccionRepository extends R2dbcRepository<HistorialReproduccion, Long> {
    Flux<HistorialReproduccion> findByUsuarioId(Long usuarioId);

    Flux<HistorialReproduccion> findByUsuarioIdOrderByFechaHoraDesc(Long usuarioId);

    @Query("SELECT COUNT(*) FROM historial_reproduccion hr JOIN cancion c ON hr.cancion_id = c.id WHERE c.artista_id = :idArtista")
    Mono<Long> countByArtistaId(Long idArtista);
}
