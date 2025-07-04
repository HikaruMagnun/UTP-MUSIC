package utp.music.artista.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class LoginArtistaDto {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
