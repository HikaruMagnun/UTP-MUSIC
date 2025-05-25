package utp.music.cancion.dto;

import lombok.Data;

@Data
public class UpdateCancionDto {
    private Long id;
    private String nombre;
    private int duracion;
    private Long album;
    private Long artista;
}
