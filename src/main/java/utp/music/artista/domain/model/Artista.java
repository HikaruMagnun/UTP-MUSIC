package utp.music.artista.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Artista {
    private final Long id;
    private final String nombreArtistico;
    private final String biografia;
    private final String imagenUrl;

    public static Artista create(String nombreArtistico, String biografia, String imagenUrl) {
        return Artista.builder()
                .nombreArtistico(nombreArtistico)
                .biografia(biografia)
                .imagenUrl(imagenUrl)
                .build();
    }
}
