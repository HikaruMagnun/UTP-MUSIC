package utp.music.cancion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "cancion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cancion {
    @Id
    private Long id;

    private String titulo;

    private Integer duracion;

    @Column("archivo_url")
    private String archivoUrl;

    @Column("artista_id")
    private Long artistaId;

    @Column("album_id")
    private Long albumId;
}
