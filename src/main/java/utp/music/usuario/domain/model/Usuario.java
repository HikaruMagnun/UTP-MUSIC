package utp.music.usuario.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Usuario {
    private final Long id;
    private final String nombre;
    private final String email;
    private final String password;
    private final LocalDateTime fechaRegistro;
    private final Long idArtista;
    private final Rol rol;

    public static Usuario create(String nombre, String email, String password, Rol rol) {
        return Usuario.builder()
                .nombre(nombre)
                .email(email)
                .password(password)
                .fechaRegistro(LocalDateTime.now())
                .rol(rol)
                .build();
    }

    public Usuario withIdArtista(Long idArtista) {
        return this.toBuilder().idArtista(idArtista).build();
    }
}
