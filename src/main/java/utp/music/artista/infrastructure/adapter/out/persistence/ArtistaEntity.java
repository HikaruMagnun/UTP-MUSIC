package utp.music.artista.infrastructure.adapter.out.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "artista")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistaEntity {
    @Id
    private Long id;

    @Column("nombre_artistico")
    private String nombreArtistico;

    @Column("biografia")
    private String biografia;

    @Column("imagen_url")
    private String imagenUrl;
}
