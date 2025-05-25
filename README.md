# UTP-MUSIC

UTP-MUSIC es una aplicación de música que permite a los usuarios gestionar playlists, canciones y más. Este proyecto utiliza tecnologías reactivas para ofrecer una experiencia fluida y escalable.

## Funcionalidades

### Gestión de Playlists

- Crear una playlist.
- Actualizar una playlist.
- Eliminar una playlist.
- Agregar canciones a una playlist.
- Eliminar canciones de una playlist.
- Listar canciones en una playlist.

### Gestión de Canciones

- Subir canciones.
- Listar canciones disponibles.

### Gestión de Usuarios

- Registro y autenticación de usuarios.
- Gestión de historial y "me gusta".

## Tecnologías Utilizadas

- **Java** con **Spring WebFlux** para programación reactiva.
- **PostgreSQL** como base de datos.
- **Docker** para contenedores.
- **Postman** para pruebas de API.

## Pruebas de API

Puedes encontrar las pruebas de API en el siguiente enlace de Postman:
[Postman Collection - Reactive APIs](https://postman.co/workspace/Reactive-apis~07eec7e1-6fe1-4732-8d2c-efa9bfd1e7dd/collection/24875264-1f287f53-aa4f-4abb-a57d-54c38a5ebc73?action=share&creator=24875264)

## Cómo Ejecutar el Proyecto

1. Clona este repositorio:

   ```bash
   git clone <URL_DEL_REPOSITORIO>
   ```

2. Navega al directorio del proyecto:

   ```bash
   cd UTP-MUSIC
   ```

3. Construye el proyecto con Maven:

   ```bash
   ./mvnw clean install
   ```

4. Levanta los servicios con Docker:

   ```bash
   docker-compose up
   ```

5. Accede a la aplicación en `http://localhost:8080`.

## Contribuciones

¡Las contribuciones son bienvenidas! Por favor, abre un issue o envía un pull request para cualquier mejora o corrección.
