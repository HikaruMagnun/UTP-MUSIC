package utp.music.historial.infrastructure.adapter.out.persistence;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.historial.domain.model.DailyHistorialCount;

@Repository
public interface HistorialJpaRepository extends R2dbcRepository<HistorialEntity, Long> {
    Flux<HistorialEntity> findByUsuarioId(Long usuarioId);

    Flux<HistorialEntity> findByUsuarioIdOrderByFechaHoraDesc(Long usuarioId);

    @Query("SELECT COUNT(*) FROM historial_reproduccion hr JOIN cancion c ON hr.cancion_id = c.id WHERE c.artista_id = :idArtista")
    Mono<Long> countByArtistaId(Long idArtista);

    @Query("SELECT DATE(hr.fecha_hora) as fecha, COUNT(*) as conteo FROM historial_reproduccion hr " +
            "JOIN cancion c ON hr.cancion_id = c.id " +
            "WHERE c.artista_id = :idArtista " +
            "GROUP BY DATE(hr.fecha_hora) " +
            "ORDER BY fecha DESC")
    Flux<DailyHistorialCount> countByArtistaIdGroupByDay(Long idArtista);
}
