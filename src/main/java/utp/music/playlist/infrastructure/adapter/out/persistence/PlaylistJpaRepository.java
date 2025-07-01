package utp.music.playlist.infrastructure.adapter.out.persistence;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;

@Repository
public interface PlaylistJpaRepository extends R2dbcRepository<PlaylistEntity, Long> {
    Flux<PlaylistEntity> findByUsuarioId(Long usuarioId);
}
