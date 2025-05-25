package utp.music.artista.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterArtistaDto {
    @NotBlank
    private String nombreArtistico;

    private String biografia;

    private String imagenUrl;

    private String nombre;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
