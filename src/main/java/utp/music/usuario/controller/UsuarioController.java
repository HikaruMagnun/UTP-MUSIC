package utp.music.usuario.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import utp.music.usuario.model.dto.LoginUsuarioDto;
import utp.music.usuario.model.dto.RegisterUserDto;
import utp.music.usuario.model.dto.TokenDto;
import utp.music.usuario.model.entity.Usuario;
import utp.music.usuario.service.UsuarioService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public Mono<ResponseEntity<TokenDto>> login(@RequestBody @Valid LoginUsuarioDto loginDto) {
        return usuarioService.login(loginDto)
                .map(token -> ResponseEntity.ok(token))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<Usuario>> register(@RequestBody @Valid RegisterUserDto registerDto) {
        return usuarioService.register(registerDto)
                .map(user -> ResponseEntity.status(HttpStatus.CREATED).body(user))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));
    }
}
