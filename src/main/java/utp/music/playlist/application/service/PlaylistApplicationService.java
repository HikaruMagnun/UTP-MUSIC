package utp.music.playlist.application.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.playlist.domain.model.ApiResponse;
import utp.music.playlist.domain.model.CreatePlaylistDto;
import utp.music.playlist.domain.model.Playlist;
import utp.music.playlist.domain.model.UpdatePlaylistDto;
import utp.music.playlist.domain.port.in.GetPlaylistUseCase;
import utp.music.playlist.domain.port.in.ManagePlaylistUseCase;

@Service
@RequiredArgsConstructor
public class PlaylistApplicationService {

    private final ManagePlaylistUseCase managePlaylistUseCase;
    private final GetPlaylistUseCase getPlaylistUseCase;

    public Flux<Playlist> getAllPlaylists() {
        return getPlaylistUseCase.findAll();
    }

    public Mono<ApiResponse> addCancionToPlaylist(Long playlistId, Long cancionId) {
        return managePlaylistUseCase.addCancionToPlaylist(playlistId, cancionId)
                .thenReturn(ApiResponse.success("Canción agregada a la playlist exitosamente."));
    }

    public Mono<ApiResponse> removeCancionFromPlaylist(Long playlistId, Long cancionId) {
        return managePlaylistUseCase.removeCancionFromPlaylist(playlistId, cancionId)
                .thenReturn(ApiResponse.success("Canción eliminada de la playlist exitosamente."));
    }

    public Flux<Object> listCancionesFromPlaylist(Long id) {
        return getPlaylistUseCase.listCancionesFromPlaylist(id);
    }

    public Mono<Playlist> createPlaylist(CreatePlaylistDto createPlaylistDto) {
        return managePlaylistUseCase.save(createPlaylistDto);
    }

    public Mono<Playlist> updatePlaylist(UpdatePlaylistDto updatePlaylistDto) {
        return managePlaylistUseCase.updatePlaylist(updatePlaylistDto);
    }

    public Mono<Void> deletePlaylist(Long id) {
        return managePlaylistUseCase.deleteById(id);
    }

    public Flux<Playlist> listPlaylistsByUserId(Long userId) {
        return getPlaylistUseCase.findByUsuarioId(userId);
    }
}
