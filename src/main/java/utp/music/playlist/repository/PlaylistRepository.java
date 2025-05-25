package utp.music.playlist.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import utp.music.playlist.model.enitity.Playlist;

@Repository
public interface PlaylistRepository extends R2dbcRepository<Playlist, Long> {
    Flux<Playlist> findByUsuarioId(Long usuarioId);
}
