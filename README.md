# P5
Aplicación web que usa Spring JPA para persistir los datos de un API REST de gestión de usuarios.
El API permite el registro de nuevos usuarios y su identificación mediante email y password.
Una vez identificados, se emplea una cookie de sesión para autenticar las peticiones que permiten 
a los usuarios leer, modificar y borrar sus datos. También existe un endpoint para cerrar la sesión.  

## Endpoints

// TODO#1: rellena la tabla siguiente analizando el código del proyecto

| Método      | Ruta                         | Descripción                                   | Respuestas                                                                                                   |
|-------------|------------------------------|-----------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| **POST**    | `/api/users`                 | Registrar un nuevo usuario.                   | `201 Created` + `ProfileResponse` (JSON) cuando se registra<br> `409 Conflict` si email repetido             |
| **POST**    | `/api/users/me/session`      | Iniciar sesión (login).                       | `201 Created` + Cookie `session=[token]` cuando credenciales correctas<br> `401 Unauthorized` si fallan      |
| **DELETE**  | `/api/users/me/session`      | Cerrar sesión (logout).                       | `204 No Content` + expira cookie `session` cuando hay sesión válida<br>` 401 Unauthorized` si no autenticado |
| **GET**     | `/api/users/me`              | Obtener datos del perfil del usuario.         | `200 OK` + `ProfileResponse` (JSON)<br> `401 Unauthorized` si no autenticado                                 |
| **PUT**     | `/api/users/me`              | Modificar datos del perfil del usuario.       | `200 OK` + `ProfileResponse` (JSON)<br> `401 Unauthorized` si no autenticado                                 |
| **DELETE**  | `/api/users/me`              | Borrar (dar de baja) al usuario autenticado.  | `204 No Content` cuando se borra correctamente<br> `401 Unauthorized` si no autenticado                      |


## Comandos 

- Construcción: 
  ```sh
  ./mvnw clean package
  ```

- Ejecución: 
  ```sh
  ./mvnw spring-boot:run
  ```

- Tests:
  ```sh
  ./mvnw test
  ```
