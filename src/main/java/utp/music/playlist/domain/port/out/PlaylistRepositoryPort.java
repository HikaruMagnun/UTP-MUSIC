package utp.music.playlist.domain.port.out;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.playlist.domain.model.Playlist;

public interface PlaylistRepositoryPort {
    Flux<Playlist> findAll();

    Mono<Playlist> findById(Long id);

    Mono<Playlist> save(Playlist playlist);

    Mono<Void> deleteById(Long id);

    Flux<Playlist> findByUsuarioId(Long usuarioId);

    Mono<Void> addCancionToPlaylist(Long playlistId, Long cancionId);

    Mono<Void> removeCancionFromPlaylist(Long playlistId, Long cancionId);

    Flux<Object> findCancionesByPlaylistId(Long playlistId);
}
