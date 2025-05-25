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
import utp.music.usuario.model.entity.Usuario;
import utp.music.usuario.service.UsuarioService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/login")
    public Mono<Usuario> login(@RequestBody @Valid LoginUsuarioDto loginDto) {
        return usuarioService.login(loginDto);
    }

    @PostMapping("/register")
    public Mono<Usuario> register(@RequestBody @Valid RegisterUserDto registerDto) {
        System.out.println("Registering user: " + registerDto);
        return usuarioService.register(registerDto);
    }
}
