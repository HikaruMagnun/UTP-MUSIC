package utp.music.artista.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.artista.domain.model.Artista;
import utp.music.artista.domain.model.LoginArtistaDto;
import utp.music.artista.domain.model.RegisterArtistaDto;
import utp.music.artista.domain.port.in.GetArtistaUseCase;
import utp.music.artista.domain.port.in.ManageArtistaUseCase;
import utp.music.artista.domain.port.out.ArtistaRepositoryPort;
import utp.music.artista.domain.port.out.UsuarioRepositoryPort;
import utp.music.usuario.domain.model.Rol;
import utp.music.usuario.domain.model.Usuario;

@Service
@RequiredArgsConstructor
public class ArtistaService implements ManageArtistaUseCase, GetArtistaUseCase {

    private final ArtistaRepositoryPort artistaRepositoryPort;
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public Mono<Artista> registerArtista(RegisterArtistaDto registerDto) {
        // Verificar si ya existe un usuario con el nombre o correo proporcionado
        return usuarioRepositoryPort.findByNombreOrEmail(registerDto.getNombre(), registerDto.getEmail())
                .hasElement()
                .flatMap(existe -> {
                    if (existe) {
                        // Si el usuario ya existe, devolver un error
                        return Mono
                                .error(new IllegalStateException("El nombre o correo ya está en uso por otro usuario"));
                    }
                    // Verificar si ya existe un artista con el nombre artístico proporcionado
                    return artistaRepositoryPort.findByNombreArtistico(registerDto.getNombreArtistico())
                            .hasElement()
                            .flatMap(artistaExiste -> {
                                if (artistaExiste) {
                                    // Si el artista ya existe, devolver un error
                                    return Mono.error(new IllegalStateException(
                                            "El nombre artístico ya está en uso por otro artista"));
                                }
                                // Crear una nueva entidad Usuario con los detalles proporcionados
                                Usuario usuario = Usuario.builder()
                                        .nombre(registerDto.getNombre())
                                        .email(registerDto.getEmail())
                                        .password(registerDto.getPassword())
                                        .fechaRegistro(LocalDateTime.now())
                                        .rol(Rol.ROL_ARTISTA)
                                        .build();
                                // Guardar la entidad Usuario
                                return usuarioRepositoryPort.save(usuario)
                                        .flatMap(usuarioGuardado -> {
                                            // Crear una nueva entidad Artista con los detalles proporcionados
                                            Artista artista = Artista.create(
                                                    registerDto.getNombreArtistico(),
                                                    registerDto.getBiografia(),
                                                    registerDto.getImagenUrl());
                                            // Guardar la entidad Artista
                                            return artistaRepositoryPort.save(artista)
                                                    .flatMap(artistaGuardado -> {
                                                        // Actualizar usuario con ID del artista
                                                        Usuario usuarioActualizado = usuarioGuardado
                                                                .withIdArtista(artistaGuardado.getId());
                                                        // Guardar la entidad Usuario actualizada y devolver el Artista
                                                        // guardado
                                                        return usuarioRepositoryPort.save(usuarioActualizado)
                                                                .thenReturn(artistaGuardado);
                                                    });
                                        });
                            });
                });
    }

    @Override
    public Mono<Artista> loginArtista(LoginArtistaDto loginDto) {
        return usuarioRepositoryPort.findByNombreOrEmail(loginDto.getEmail(), loginDto.getEmail())
                .filter(usuario -> usuario.getPassword().equals(loginDto.getPassword())
                        && usuario.getRol() == Rol.ROL_ARTISTA)
                .flatMap(usuario -> artistaRepositoryPort.findById(usuario.getIdArtista()))
                .switchIfEmpty(Mono
                        .error(new IllegalStateException("Credenciales incorrectas o el usuario no es un artista")));
    }

    @Override
    public Flux<Artista> findAll() {
        return artistaRepositoryPort.findAll();
    }

    @Override
    public Mono<Artista> findById(Long id) {
        return artistaRepositoryPort.findById(id);
    }

    @Override
    public Flux<Artista> searchArtistas(String nombreArtistico) {
        return artistaRepositoryPort.findTop5ByNombreArtisticoContainingIgnoreCase(nombreArtistico);
    }

    @Override
    public Mono<Artista> findByEmail(String email) {
        return usuarioRepositoryPort.findByNombreOrEmail(email, email)
                .filter(usuario -> usuario.getRol() == Rol.ROL_ARTISTA)
                .flatMap(usuario -> artistaRepositoryPort.findByNombreArtistico(usuario.getNombre()))
                .switchIfEmpty(Mono.error(new IllegalStateException("No artist found with the given email")));
    }
}
