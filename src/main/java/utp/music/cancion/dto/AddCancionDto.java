package utp.music.cancion.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCancionDto {
    private String nombre;
    private Long albumId;
    private int duracion;
    private Long idArtista;
    private String filePath;
}
