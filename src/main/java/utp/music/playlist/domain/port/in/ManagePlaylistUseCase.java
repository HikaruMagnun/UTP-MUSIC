package utp.music.playlist.domain.port.in;

import reactor.core.publisher.Mono;
import utp.music.playlist.domain.model.CreatePlaylistDto;
import utp.music.playlist.domain.model.Playlist;
import utp.music.playlist.domain.model.UpdatePlaylistDto;

public interface ManagePlaylistUseCase {
    Mono<Playlist> save(CreatePlaylistDto createPlaylistDto);

    Mono<Playlist> updatePlaylist(UpdatePlaylistDto updatePlaylistDto);

    Mono<Void> deleteById(Long id);

    Mono<Void> addCancionToPlaylist(Long playlistId, Long cancionId);

    Mono<Void> removeCancionFromPlaylist(Long playlistId, Long cancionId);
}
