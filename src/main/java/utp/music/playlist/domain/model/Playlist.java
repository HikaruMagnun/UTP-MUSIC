package utp.music.playlist.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Playlist {
    private final Long id;
    private final String nombre;
    private final Long usuarioId;
    private final VisibilidadPlaylist visibilidad;
    private final LocalDateTime fechaCreacion;

    public static Playlist create(String nombre, Long usuarioId, VisibilidadPlaylist visibilidad) {
        return Playlist.builder()
                .nombre(nombre)
                .usuarioId(usuarioId)
                .visibilidad(visibilidad)
                .fechaCreacion(LocalDateTime.now())
                .build();
    }
}
