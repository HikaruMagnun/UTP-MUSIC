package utp.music.historial.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class HistorialReproduccion {
    private final Long id;
    private final Long usuarioId;
    private final Long cancionId;
    private final LocalDateTime fechaHora;

    public static HistorialReproduccion create(Long usuarioId, Long cancionId) {
        return HistorialReproduccion.builder()
                .usuarioId(usuarioId)
                .cancionId(cancionId)
                .fechaHora(LocalDateTime.now())
                .build();
    }
}
