package utp.music.historial.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Table(name = "historial_reproduccion")
@Data
@Builder
public class HistorialReproduccion {
    @Id
    private Long id;

    @Column("usuario_id")
    private Long usuarioId;

    @Column("cancion_id")
    private Long cancionId;

    @Column("fecha_hora")
    private LocalDateTime fechaHora;
}
