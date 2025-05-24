package utp.music.usuario.model.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RegisterUserDto {
    @NotBlank
    private String nombre;
    @Email(message = "email no valido")
    @NotBlank
    private String email;
    @Size(min = 4,max=20, message = "contraseña minimo 4 y maximo 20 digitos")
    @NotBlank
    private String password;

}
