package utp.music.artista.infrastructure.adapter.out.persistence;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.artista.domain.model.Artista;
import utp.music.artista.domain.port.out.ArtistaRepositoryPort;

@Component
@RequiredArgsConstructor
public class ArtistaRepositoryAdapter implements ArtistaRepositoryPort {

    private final ArtistaJpaRepository artistaJpaRepository;

    @Override
    public Flux<Artista> findAll() {
        return artistaJpaRepository.findAll()
                .map(this::toDomainModel);
    }

    @Override
    public Mono<Artista> findById(Long id) {
        return artistaJpaRepository.findById(id)
                .map(this::toDomainModel);
    }

    @Override
    public Mono<Artista> save(Artista artista) {
        ArtistaEntity entity = toEntity(artista);
        return artistaJpaRepository.save(entity)
                .map(this::toDomainModel);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return artistaJpaRepository.deleteById(id);
    }

    @Override
    public Mono<Artista> findByNombreArtistico(String nombreArtistico) {
        return artistaJpaRepository.findByNombreArtistico(nombreArtistico)
                .map(this::toDomainModel);
    }

    @Override
    public Flux<Artista> findTop5ByNombreArtisticoContainingIgnoreCase(String nombreArtistico) {
        return artistaJpaRepository.findTop5ByNombreArtisticoContainingIgnoreCase(nombreArtistico)
                .map(this::toDomainModel);
    }

    private Artista toDomainModel(ArtistaEntity entity) {
        return Artista.builder()
                .id(entity.getId())
                .nombreArtistico(entity.getNombreArtistico())
                .biografia(entity.getBiografia())
                .imagenUrl(entity.getImagenUrl())
                .build();
    }

    private ArtistaEntity toEntity(Artista domain) {
        return ArtistaEntity.builder()
                .id(domain.getId())
                .nombreArtistico(domain.getNombreArtistico())
                .biografia(domain.getBiografia())
                .imagenUrl(domain.getImagenUrl())
                .build();
    }
}
