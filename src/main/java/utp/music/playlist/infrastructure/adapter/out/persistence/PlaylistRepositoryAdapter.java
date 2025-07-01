package utp.music.playlist.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.playlist.domain.model.Playlist;
import utp.music.playlist.domain.model.VisibilidadPlaylist;
import utp.music.playlist.domain.port.out.PlaylistRepositoryPort;

@Component
@RequiredArgsConstructor
public class PlaylistRepositoryAdapter implements PlaylistRepositoryPort {

    private final PlaylistJpaRepository playlistJpaRepository;
    private final PlaylistCancionJpaRepository playlistCancionJpaRepository;

    @Override
    public Flux<Playlist> findAll() {
        return playlistJpaRepository.findAll()
                .map(this::toDomainModel);
    }

    @Override
    public Mono<Playlist> findById(Long id) {
        return playlistJpaRepository.findById(id)
                .map(this::toDomainModel);
    }

    @Override
    public Mono<Playlist> save(Playlist playlist) {
        PlaylistEntity entity = toEntity(playlist);
        return playlistJpaRepository.save(entity)
                .map(this::toDomainModel);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return playlistJpaRepository.deleteById(id);
    }

    @Override
    public Flux<Playlist> findByUsuarioId(Long usuarioId) {
        return playlistJpaRepository.findByUsuarioId(usuarioId)
                .map(this::toDomainModel);
    }

    @Override
    public Mono<Void> addCancionToPlaylist(Long playlistId, Long cancionId) {
        PlaylistCancionEntity entity = PlaylistCancionEntity.create(playlistId, cancionId);
        return playlistCancionJpaRepository.save(entity).then();
    }

    @Override
    public Mono<Void> removeCancionFromPlaylist(Long playlistId, Long cancionId) {
        return playlistCancionJpaRepository.deleteByPlaylistIdAndCancionId(playlistId, cancionId);
    }

    @Override
    public Flux<Object> findCancionesByPlaylistId(Long playlistId) {
        return playlistCancionJpaRepository.findCancionesByPlaylistId(playlistId)
                .cast(Object.class);
    }

    private Playlist toDomainModel(PlaylistEntity entity) {
        return Playlist.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .usuarioId(entity.getUsuarioId())
                .visibilidad(VisibilidadPlaylist.valueOf(entity.getVisibilidad().toUpperCase()))
                .fechaCreacion(entity.getFechaCreacion())
                .build();
    }

    private PlaylistEntity toEntity(Playlist domain) {
        return PlaylistEntity.builder()
                .id(domain.getId())
                .nombre(domain.getNombre())
                .usuarioId(domain.getUsuarioId())
                .visibilidad(domain.getVisibilidad().name())
                .fechaCreacion(domain.getFechaCreacion())
                .build();
    }
}
