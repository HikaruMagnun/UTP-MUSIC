package utp.music.meGusta.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "me_gusta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeGusta {
    @Id
    private Long id;

    @Column("usuario_id")
    private Long usuarioId;

    @Column("cancion_id")
    private Long cancionId;
}
