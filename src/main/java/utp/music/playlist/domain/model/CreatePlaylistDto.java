package utp.music.playlist.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePlaylistDto {
    private String nombre;
    private Long usuarioId;
    private String visibilidad;
}
