package utp.music.playlist.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePlaylistDto {
    private Long id;
    private String nombre;
    private String visibilidad;
}
