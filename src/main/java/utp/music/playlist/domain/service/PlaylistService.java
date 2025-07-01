package utp.music.playlist.domain.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.playlist.domain.model.CreatePlaylistDto;
import utp.music.playlist.domain.model.Playlist;
import utp.music.playlist.domain.model.UpdatePlaylistDto;
import utp.music.playlist.domain.model.VisibilidadPlaylist;
import utp.music.playlist.domain.port.in.GetPlaylistUseCase;
import utp.music.playlist.domain.port.in.ManagePlaylistUseCase;
import utp.music.playlist.domain.port.out.PlaylistRepositoryPort;

@Service
@RequiredArgsConstructor
public class PlaylistService implements ManagePlaylistUseCase, GetPlaylistUseCase {

    private final PlaylistRepositoryPort playlistRepositoryPort;

    @Override
    public Mono<Playlist> save(CreatePlaylistDto createPlaylistDto) {
        VisibilidadPlaylist visibilidad = VisibilidadPlaylist.valueOf(createPlaylistDto.getVisibilidad().toUpperCase());
        Playlist playlist = Playlist.create(
                createPlaylistDto.getNombre(),
                createPlaylistDto.getUsuarioId(),
                visibilidad);
        return playlistRepositoryPort.save(playlist);
    }

    @Override
    public Mono<Playlist> updatePlaylist(UpdatePlaylistDto updatePlaylistDto) {
        return playlistRepositoryPort.findById(updatePlaylistDto.getId())
                .map(existing -> existing.toBuilder()
                        .nombre(updatePlaylistDto.getNombre())
                        .visibilidad(VisibilidadPlaylist.valueOf(updatePlaylistDto.getVisibilidad().toUpperCase()))
                        .build())
                .flatMap(playlistRepositoryPort::save);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return playlistRepositoryPort.deleteById(id);
    }

    @Override
    public Mono<Void> addCancionToPlaylist(Long playlistId, Long cancionId) {
        return playlistRepositoryPort.addCancionToPlaylist(playlistId, cancionId);
    }

    @Override
    public Mono<Void> removeCancionFromPlaylist(Long playlistId, Long cancionId) {
        return playlistRepositoryPort.removeCancionFromPlaylist(playlistId, cancionId);
    }

    @Override
    public Flux<Playlist> findAll() {
        return playlistRepositoryPort.findAll();
    }

    @Override
    public Mono<Playlist> findById(Long id) {
        return playlistRepositoryPort.findById(id);
    }

    @Override
    public Flux<Playlist> findByUsuarioId(Long usuarioId) {
        return playlistRepositoryPort.findByUsuarioId(usuarioId);
    }

    @Override
    public Flux<Object> listCancionesFromPlaylist(Long playlistId) {
        return playlistRepositoryPort.findCancionesByPlaylistId(playlistId);
    }
}
