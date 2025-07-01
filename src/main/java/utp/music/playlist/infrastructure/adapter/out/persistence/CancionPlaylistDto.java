package utp.music.playlist.infrastructure.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CancionPlaylistDto {
    private Long id;
    private String titulo;
    private Integer duracion;
    private String archivoUrl;
    private Long artistaId;
    private Long albumId;
}
