package utp.music.playlist.infrastructure.adapter.out.persistence;

import java.time.LocalDateTime;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "playlist_cancion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistCancionEntity {
    
    @Column("playlist_id")
    private Long playlistId;
    
    @Column("cancion_id")
    private Long cancionId;
    
    @Column("fecha_insercion")
    private LocalDateTime fechaInsercion;
    
    public static PlaylistCancionEntity create(Long playlistId, Long cancionId) {
        return PlaylistCancionEntity.builder()
                .playlistId(playlistId)
                .cancionId(cancionId)
                .fechaInsercion(LocalDateTime.now())
                .build();
    }
}
