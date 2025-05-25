package utp.music.usuario.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor

public class LoginUsuarioDto {
    private String username;
    private String password;
}
