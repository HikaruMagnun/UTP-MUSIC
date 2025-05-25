package utp.music.playlist.model.enitity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Table(name = "playlist")
@Data
@Builder
public class Playlist {
    @Id
    private Long id;

    private String nombre;

    @Column("usuario_id")
    private Long usuarioId;

    private VisibilidadPlaylist visibilidad;

    @Column("fecha_creacion")
    private LocalDateTime fechaCreacion;
}
