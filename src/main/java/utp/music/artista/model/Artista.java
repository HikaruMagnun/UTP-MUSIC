package utp.music.artista.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Table(name = "artista")
@Data
@Builder
public class Artista {
    @Id
    private Long id;

    @Column("nombre_artistico")
    private String nombreArtistico;

    @Column("biografia")
    private String biografia;

    @Column("imagen_url")
    private String imagenUrl;
}
