package utp.music.artista.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.artista.model.Artista;
import utp.music.artista.model.dto.LoginArtistaDto;
import utp.music.artista.model.dto.RegisterArtistaDto;
import utp.music.artista.repository.ArtistaRepository;
import utp.music.usuario.model.entity.Rol;
import utp.music.usuario.model.entity.Usuario;
import utp.music.usuario.repository.UsuarioRepository;

@RequiredArgsConstructor
@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;
    private final UsuarioRepository usuarioRepository;

    public Flux<Artista> findAll() {
        return artistaRepository.findAll();
    }

    public Mono<Artista> findById(Long id) {
        return artistaRepository.findById(id);
    }

    public Mono<Artista> save(Artista artista) {
        return artistaRepository.save(artista);
    }

    public Mono<Void> deleteById(Long id) {
        return artistaRepository.deleteById(id);
    }

    public Mono<Artista> registerArtista(RegisterArtistaDto registerDto) {
        // Verificar si ya existe un usuario con el nombre o correo proporcionado
        return usuarioRepository.findByNombreOrEmail(registerDto.getNombre(), registerDto.getEmail())
            .hasElement()
            .flatMap(existe -> {
            if (existe) {
                // Si el usuario ya existe, devolver un error
                return Mono.error(new IllegalStateException("El nombre o correo ya está en uso por otro usuario"));
            }
            // Verificar si ya existe un artista con el nombre artístico proporcionado
            return artistaRepository.findByNombreArtistico(registerDto.getNombreArtistico())
                .hasElement()
                .flatMap(artistaExiste -> {
                if (artistaExiste) {
                    // Si el artista ya existe, devolver un error
                    return Mono.error(new IllegalStateException("El nombre artístico ya está en uso por otro artista"));
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
                return usuarioRepository.save(usuario)
                    .flatMap(usuarioGuardado -> {
                    // Crear una nueva entidad Artista con los detalles proporcionados
                    Artista artista = Artista.builder()
                        .nombreArtistico(registerDto.getNombreArtistico())
                        .biografia(registerDto.getBiografia())
                        .imagenUrl(registerDto.getImagenUrl())
                        .build();
                    // Guardar la entidad Artista
                    return artistaRepository.save(artista)
                        .flatMap(artistaGuardado -> {
                        // Asociar el Artista guardado al Usuario estableciendo el ID del artista
                        usuarioGuardado.setIdArtista(artistaGuardado.getId());
                        // Guardar la entidad Usuario actualizada y devolver el Artista guardado
                        return usuarioRepository.save(usuarioGuardado)
                            .thenReturn(artistaGuardado);
                        });
                    });
                });
            });
    }

    public Mono<Artista> loginArtista(LoginArtistaDto loginDto) {
        return usuarioRepository.findByNombreOrEmail(loginDto.getEmail(), loginDto.getEmail())
                .filter(usuario -> usuario.getPassword().equals(loginDto.getPassword())
                        && usuario.getRol() == Rol.ROL_ARTISTA)
                .flatMap(usuario -> artistaRepository.findById(usuario.getIdArtista()))
                .switchIfEmpty(Mono
                        .error(new IllegalStateException("Credenciales incorrectas o el usuario no es un artista")));
    }

    public Flux<Artista> searchArtistas(String nombreArtistico) {
        return artistaRepository.findTop5ByNombreArtisticoContainingIgnoreCase(nombreArtistico);
    }

    public Flux<Artista> listarArtistas() {
        return artistaRepository.findAll();
    }

    public Mono<Artista> findByEmail(String email) {
        return usuarioRepository.findByNombreOrEmail(email, email)
                .filter(usuario -> usuario.getRol() == Rol.ROL_ARTISTA)
                .flatMap(usuario -> artistaRepository.findByNombreArtistico(usuario.getNombre()))
                .switchIfEmpty(Mono.error(new IllegalStateException("No artist found with the given email")));
    }
}
