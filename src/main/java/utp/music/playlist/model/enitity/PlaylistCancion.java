package utp.music.playlist.model.enitity;

import java.time.LocalDateTime;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Table("playlist_cancion")
@Builder
public class PlaylistCancion {

    @Column("playlist_id")
    private Long playlistId;

    @Column("cancion_id")
    private Long cancionId;

    @Column("fecha_insercion")
    private LocalDateTime fechaInsercion;
}
