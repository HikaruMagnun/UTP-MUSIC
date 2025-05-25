package utp.music.cancion.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.cancion.model.Cancion;
import utp.music.cancion.repository.CancionRepository;
import utp.music.cancion.dto.AddCancionDto;
import utp.music.cancion.dto.UpdateCancionDto;

@RequiredArgsConstructor
@Service
public class CancionService {

    private final CancionRepository cancionRepository;

    public Mono<Cancion> findById(Long id) {
        return cancionRepository.findById(id);
    }

    public Flux<Cancion> findByArtistaId(Long artistaId) {
        return cancionRepository.findByArtistaId(artistaId);
    }

    public Flux<Cancion> findByAlbumId(Long albumId) {
        return cancionRepository.findByAlbumId(albumId);
    }

    public Mono<Cancion> findByTitulo(String titulo) {
        return cancionRepository.findByTitulo(titulo);
    }

    public Flux<Cancion> findTop5ByTituloContainingIgnoreCase(String titulo) {
        return cancionRepository.findTop5ByTituloContainingIgnoreCase(titulo);
    }

    public Mono<Cancion> save(Cancion cancion) {
        return cancionRepository.save(cancion);
    }

    public Mono<Void> deleteById(Long id) {
        return cancionRepository.deleteById(id);
    }

    public Mono<Cancion> addCancion(AddCancionDto addCancionDto) {
        Cancion cancion = Cancion.builder()
                .titulo(addCancionDto.getNombre())
                .albumId(addCancionDto.getAlbumId())
                .artistaId(addCancionDto.getIdArtista())
                .duracion(addCancionDto.getDuracion())
                .archivoUrl(addCancionDto.getFilePath()) // Set the file path
                .build();
        return cancionRepository.save(cancion);
    }

    public Mono<Void> deleteCancion(Long idCancion) {
        return cancionRepository.deleteById(idCancion);
    }

    public Mono<Void> updateCancion(UpdateCancionDto updateCancionDto) {
        return cancionRepository.findById(updateCancionDto.getId())
                .flatMap(cancion -> {
                    Cancion updatedCancion = Cancion.builder()
                            .id(cancion.getId())
                            .titulo(updateCancionDto.getNombre())
                            .duracion(updateCancionDto.getDuracion())
                            .albumId(updateCancionDto.getAlbum())
                            .artistaId(updateCancionDto.getArtista())
                            .archivoUrl(cancion.getArchivoUrl()) // Preserve existing file path
                            .build();
                    return cancionRepository.save(updatedCancion);
                })
                .then();
    }

    public Flux<Cancion> listCanciones() {
        return cancionRepository.findAll();
    }

    public Flux<Cancion> searchCanciones(String query) {
        return cancionRepository.findTop5ByTituloContainingIgnoreCase(query);
    }

    public Mono<Cancion> getCancionDetails(Long idCancion) {
        return cancionRepository.findById(idCancion);
    }

    public Flux<Cancion> listCancionesByArtista(Long idArtista) {
        return cancionRepository.findByArtistaId(idArtista);
    }

    public Flux<Cancion> listCancionesByAlbum(Long idAlbum) {
        return cancionRepository.findByAlbumId(idAlbum);
    }

    public Mono<String> getCancionFilePath(Long idCancion) {
        return cancionRepository.findById(idCancion)
                .map(Cancion::getArchivoUrl)
                .switchIfEmpty(Mono.error(new RuntimeException("Canción no encontrada")));
    }

}
