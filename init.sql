-- Inicialización de la base de datos UtpMusic
-- Crear la base de datos si no existe
DO
$$
BEGIN
    IF NOT EXISTS (
        SELECT FROM pg_database WHERE datname = 'UtpMusic'
    ) THEN
        CREATE DATABASE UtpMusic;
    END IF;
END
$$;

-- 1. Tabla Rol
CREATE TABLE rol (
    id BIGINT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

-- 2. Tabla Usuario
CREATE TABLE usuario (
    id BIGINT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    rol_id BIGINT NOT NULL,
    FOREIGN KEY (rol_id) REFERENCES rol(id)
);

-- 3. Tabla Artista
CREATE TABLE artista (
    id BIGINT PRIMARY KEY,
    usuario_id BIGINT UNIQUE NOT NULL,
    nombre_artistico VARCHAR(100) NOT NULL,
    biografia TEXT,
    imagen_url VARCHAR(255),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- 4. Tabla Album
CREATE TABLE album (
    id BIGINT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    artista_id BIGINT NOT NULL,
    fecha_lanzamiento DATE,
    portada_url VARCHAR(255),
    FOREIGN KEY (artista_id) REFERENCES artista(id)
);

-- 5. Tabla Cancion
CREATE TABLE cancion (
    id BIGINT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    duracion INT NOT NULL, -- en segundos
    archivo_url VARCHAR(255) NOT NULL,
    artista_id BIGINT NOT NULL,
    album_id BIGINT,
    FOREIGN KEY (artista_id) REFERENCES artista(id),
    FOREIGN KEY (album_id) REFERENCES album(id)
);

-- 6. Tabla Genero
CREATE TABLE genero (
    id BIGINT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

-- 7. Tabla intermedia Cancion_Genero
CREATE TABLE cancion_genero (
    cancion_id BIGINT NOT NULL,
    genero_id BIGINT NOT NULL,
    PRIMARY KEY (cancion_id, genero_id),
    FOREIGN KEY (cancion_id) REFERENCES cancion(id),
    FOREIGN KEY (genero_id) REFERENCES genero(id)
);

-- 8. Tabla Playlist
CREATE TABLE playlist (
    id BIGINT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    usuario_id BIGINT NOT NULL,
    visibilidad VARCHAR(20) CHECK (visibilidad IN ('PUBLICA', 'PRIVADA')),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- 9. Tabla intermedia Playlist_Cancion
CREATE TABLE playlist_cancion (
    playlist_id BIGINT NOT NULL,
    cancion_id BIGINT NOT NULL,
    orden INT,
    PRIMARY KEY (playlist_id, cancion_id),
    FOREIGN KEY (playlist_id) REFERENCES playlist(id),
    FOREIGN KEY (cancion_id) REFERENCES cancion(id)
);

-- 10. Tabla Historial_Reproduccion
CREATE TABLE historial_reproduccion (
    id BIGINT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    cancion_id BIGINT NOT NULL,
    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (cancion_id) REFERENCES cancion(id)
);

-- 11. Tabla Me_Gusta
CREATE TABLE me_gusta (
    usuario_id BIGINT NOT NULL,
    cancion_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, cancion_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (cancion_id) REFERENCES cancion(id)
);

-- Otorgar permisos al usuario postgres
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO postgres;