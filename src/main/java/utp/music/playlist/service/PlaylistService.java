package utp.music.playlist.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.cancion.model.Cancion;
import utp.music.playlist.dto.CreatePlaylistDto;
import utp.music.playlist.dto.UpdatePlaylistDto;
import utp.music.playlist.model.enitity.Playlist;
import utp.music.playlist.model.enitity.PlaylistCancion;
import utp.music.playlist.model.enitity.VisibilidadPlaylist;
import utp.music.playlist.repository.PlaylistRepository;
import utp.music.playlist.repository.PlaylistCancionRepository;

@RequiredArgsConstructor
@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final PlaylistCancionRepository playlistCancionRepository;

    public Flux<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    public Mono<Playlist> findById(Long id) {
        return playlistRepository.findById(id);
    }

    public Flux<Playlist> findByUsuarioId(Long usuarioId) {
        return playlistRepository.findByUsuarioId(usuarioId);
    }

    public Mono<Playlist> save(CreatePlaylistDto playlistDto) {
        return playlistRepository.save(
                Playlist.builder()
                        .nombre(playlistDto.getNombre())
                        .usuarioId(playlistDto.getUsuarioId())
                        .visibilidad(VisibilidadPlaylist.valueOf(playlistDto.getVisibilidad()))
                        .fechaCreacion(LocalDateTime.now())
                        .build());
    }

    public Mono<Playlist> save(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public Mono<Void> deleteById(Long id) {
        return playlistCancionRepository.deleteByPlaylistId(id)
                .then(playlistRepository.deleteById(id));
    }

    public Mono<Void> addCancionToPlaylist(Long playlistId, Long cancionId) {
        PlaylistCancion playlistCancion = PlaylistCancion.builder()
                .playlistId(playlistId)
                .cancionId(cancionId)
                .fechaInsercion(LocalDateTime.now())
                .build();
        return playlistCancionRepository.save(playlistCancion).then();
    }

    public Mono<Void> removeCancionFromPlaylist(Long playlistId, Long cancionId) {
        return playlistCancionRepository.deleteByPlaylistIdAndCancionId(playlistId, cancionId);
    }

    public Flux<Cancion> listCancionesFromPlaylist(Long playlistId) {
        return playlistCancionRepository.findCancionesByPlaylistId(playlistId);
    }

    public Mono<Playlist> updatePlaylist(UpdatePlaylistDto updatePlaylistDto) {
        return findById(updatePlaylistDto.getId())
                .flatMap(existingPlaylist -> {
                    existingPlaylist.setNombre(updatePlaylistDto.getNombre());
                    existingPlaylist.setVisibilidad(updatePlaylistDto.getVisibilidad());
                    return save(existingPlaylist);
                });
    }
}
