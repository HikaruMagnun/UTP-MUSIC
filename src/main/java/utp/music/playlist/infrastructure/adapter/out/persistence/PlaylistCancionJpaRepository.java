package utp.music.playlist.infrastructure.adapter.out.persistence;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlaylistCancionJpaRepository extends R2dbcRepository<PlaylistCancionEntity, Long> {
    
    @Query("DELETE FROM playlist_cancion WHERE playlist_id = :playlistId AND cancion_id = :cancionId")
    Mono<Void> deleteByPlaylistIdAndCancionId(Long playlistId, Long cancionId);
    
    @Query("SELECT EXISTS(SELECT 1 FROM playlist_cancion WHERE playlist_id = :playlistId AND cancion_id = :cancionId)")
    Mono<Boolean> existsByPlaylistIdAndCancionId(Long playlistId, Long cancionId);
    
    @Query("""
        SELECT c.id, c.titulo, c.duracion, c.archivo_url, c.artista_id, c.album_id 
        FROM playlist_cancion pc 
        JOIN cancion c ON pc.cancion_id = c.id 
        WHERE pc.playlist_id = :playlistId
        ORDER BY pc.fecha_insercion
        """)
    Flux<CancionPlaylistDto> findCancionesByPlaylistId(Long playlistId);
}
