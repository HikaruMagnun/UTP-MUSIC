package utp.music.usuario.infrastructure.adapter.out.persistence;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "usuario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {
    @Id
    private Long id;

    private String nombre;

    private String email;

    private String password;

    @Column("fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column("id_artista")
    private Long idArtista;

    private RolEntity rol;
}
