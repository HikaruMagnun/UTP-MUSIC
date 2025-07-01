package utp.music.playlist.infrastructure.adapter.out.persistence;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "playlist")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistEntity {
    @Id
    private Long id;

    private String nombre;

    @Column("usuario_id")
    private Long usuarioId;

    private String visibilidad;

    @Column("fecha_creacion")
    private LocalDateTime fechaCreacion;
}
