package utp.music.usuario.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Table(name = "usuario")
@Data
@Builder
public class Usuario {
    @Id
    private Long id;

    private String nombre;

    private String email;

    private String password;

    @Column("fecha_registro")
    private LocalDateTime fechaRegistro;

    private Rol rol;
}
