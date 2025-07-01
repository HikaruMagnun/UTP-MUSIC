package utp.music.historial.infrastructure.adapter.out.persistence;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "historial_reproduccion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistorialEntity {
    @Id
    private Long id;

    @Column("usuario_id")
    private Long usuarioId;

    @Column("cancion_id")
    private Long cancionId;

    @Column("fecha_hora")
    private LocalDateTime fechaHora;
}
