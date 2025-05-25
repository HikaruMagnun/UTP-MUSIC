package utp.music.playlist.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.cancion.model.Cancion;
import utp.music.playlist.model.enitity.PlaylistCancion;

@Repository
public interface PlaylistCancionRepository extends R2dbcRepository<PlaylistCancion, Long> {

    @Query("SELECT c.* FROM cancion c JOIN playlist_cancion pc ON c.id = pc.cancion_id WHERE pc.playlist_id = :playlistId")
    Flux<Cancion> findCancionesByPlaylistId(Long playlistId);

    Mono<Void> deleteByPlaylistIdAndCancionId(Long playlistId, Long cancionId);

    Mono<Void> deleteByPlaylistId(Long playlistId);
}
