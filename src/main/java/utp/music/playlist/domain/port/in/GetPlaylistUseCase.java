package utp.music.playlist.domain.port.in;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.playlist.domain.model.Playlist;

public interface GetPlaylistUseCase {
    Flux<Playlist> findAll();

    Mono<Playlist> findById(Long id);

    Flux<Playlist> findByUsuarioId(Long usuarioId);

    Flux<Object> listCancionesFromPlaylist(Long playlistId);
}
