package utp.music.cancion.controller;

import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import utp.music.cancion.dto.AddCancionDto;
import utp.music.cancion.dto.UpdateCancionDto;
import utp.music.cancion.model.Cancion;
import utp.music.cancion.service.CancionService;

import jakarta.validation.Valid;
import java.io.File;
import org.springframework.core.io.buffer.DataBufferUtils;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    private final CancionService cancionService;

    public CancionController(CancionService cancionService) {
        this.cancionService = cancionService;
    }

    @PostMapping("/add")
    public Mono<Cancion> addCancion(@RequestParam String nombre,
            @RequestParam Long album,
            @RequestParam Long idArtista,
            @RequestParam int duracion,
            @RequestPart("file") Mono<FilePart> filePart) {
        return filePart.flatMap(fp -> {

            String directoryPath = "src/main/resources/canciones/";
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs(); // crea la carpeta si no existe
            }
            String fileName = nombre + "_" + java.util.UUID.randomUUID() + ".mp3";
            String filePath = "src/main/resources/canciones/" + fileName;
            return fp.transferTo(new File(filePath))
                    .then(Mono.defer(() -> {
                        AddCancionDto addCancionDto = AddCancionDto.builder()
                                .nombre(nombre)
                                .duracion(duracion)
                                .idArtista(idArtista)
                                .albumId(album != null && album != 0 ? album : null)
                                .filePath(fileName)
                                .build();
                        return cancionService.addCancion(addCancionDto);
                    }));
        });

    }

    @DeleteMapping("/delete")
    public Mono<String> deleteCancion(@RequestParam Long idCancion) {
        return cancionService.deleteCancion(idCancion).thenReturn("Canción eliminada exitosamente");
    }

    @PutMapping("/update")
    public Mono<String> updateCancion(@RequestBody @Valid UpdateCancionDto updateCancionDto) {
        return cancionService.updateCancion(updateCancionDto).thenReturn("Canción actualizada exitosamente");
    }

    @GetMapping("/list")
    public Flux<Cancion> listCanciones() {
        return cancionService.listCanciones();
    }

    @GetMapping("/search")
    public Flux<Cancion> searchCanciones(@RequestParam String titulo) {
        return cancionService.searchCanciones(titulo);
    }

    @GetMapping("/stream/{filename}")
    public Mono<Void> streamCancion(@PathVariable String filename, ServerHttpResponse response) {
        String filePath = "src/main/resources/canciones/" + filename;
        File file = new File(filePath);

        if (!file.exists()) {
            return Mono.error(new RuntimeException("Archivo no encontrado"));
        }

        response.getHeaders().setContentType(MediaType.APPLICATION_OCTET_STREAM); // o usa otro MediaType si sabes que es mp3
        response.getHeaders().setContentDisposition(
            ContentDisposition.builder("inline").filename(file.getName()).build()
        );

        return response.writeWith(
            DataBufferUtils.read(file.toPath(), response.bufferFactory(), 4096)
        );
    }


    @GetMapping("/details")
    public Mono<Cancion> getCancionDetails(@RequestParam Long idCancion) {
        return cancionService.getCancionDetails(idCancion);
    }

    @GetMapping("/by-artist")
    public Flux<Cancion> listCancionesByArtista(@RequestParam Long idArtista) {
        return cancionService.listCancionesByArtista(idArtista);
    }

    @GetMapping("/by-album")
    public Flux<Cancion> listCancionesByAlbum(@RequestParam Long idAlbum) {
        return cancionService.listCancionesByAlbum(idAlbum);
    }
}
