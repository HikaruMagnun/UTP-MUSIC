package utp.music.playlist.dto;

import lombok.Builder;
import lombok.Data;
import utp.music.playlist.model.enitity.VisibilidadPlaylist;

@Data
@Builder
public class UpdatePlaylistDto {
    private Long id;
    private String nombre;
    private VisibilidadPlaylist visibilidad;
}
