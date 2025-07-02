package utp.music.usuario.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutocompleteResponse {
    private String dni;
    private String nombreCompleto;
    private boolean found;
    private String message;

    public static AutocompleteResponse success(String dni, String nombreCompleto) {
        return AutocompleteResponse.builder()
                .dni(dni)
                .nombreCompleto(nombreCompleto)
                .found(true)
                .build();
    }

    public static AutocompleteResponse notFound(String dni) {
        return AutocompleteResponse.builder()
                .dni(dni)
                .found(false)
                .message("DNI no encontrado")
                .build();
    }

    public static AutocompleteResponse invalid(String dni) {
        return AutocompleteResponse.builder()
                .dni(dni)
                .found(false)
                .message("DNI no válido")
                .build();
    }
}
