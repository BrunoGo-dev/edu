# 🎓 EduTrack - Plataforma Académica

Sistema de gestión académica integral desarrollado con Spring Boot. Permite la administración de cursos, estudiantes, docentes, asistencias, notas y tareas de manera eficiente y escalable.

## 🚀 Tecnologías

*   **Java 21**
*   **Spring Boot 3.5.5** (Web, Data JPA, Validation)
*   **MySQL 8.0**
*   **Docker & Docker Compose**
*   **JWT** (JSON Web Tokens) para seguridad

## ✨ Funcionalidades Principales

*   **👥 Gestión de Usuarios**: Roles de Docente y Estudiante.
*   **📅 Asistencias**: Registro y consulta de asistencia por curso y fecha.
*   **📝 Notas y Calificaciones**: Gestión completa de evaluaciones y promedios.
*   **📚 Tareas y Entregas**: Asignación de tareas y recepción de entregas digitales.
*   **💬 Observaciones**: Feedback cualitativo (Positivo, Negativo, Sugerencia).

## 🛠️ Instalación y Despliegue

### Requisitos Previos
*   Docker y Docker Compose instalados.
*   Opcional: Java 21 y Maven si deseas ejecutarlo localmente sin Docker.

### 🐳 Ejecución con Docker (Recomendado)

1.  **Clonar el repositorio**:
    ```bash
    git clone <url-del-repo>
    cd edu
    ```

2.  **Configurar Variables de Entorno**:
    Crea un archivo `.env` en la raíz del proyecto basándote en el ejemplo:
    ```properties
    SERVER_PORT=8080
    
    # Base de datos (Nube o Local)
    DB_URL=jdbc:mysql://HOST:3306/plataforma_academica
    DB_USERNAME=usuario
    DB_PASSWORD=contraseña
    
    # Seguridad
    JWT_SECRET=TuClaveSecretaSuperSegura
    JWT_EXPIRATION_MS=3600000
    ```

3.  **Levantar la aplicación**:
    ```bash
    docker-compose up --build
    ```
    La API estará disponible en `http://localhost:8080`.

### 💻 Ejecución Local (Desarrollo)

```bash
./mvnw clean package
./mvnw spring-boot:run
```

## 📂 Estructura del Proyecto

El proyecto sigue una arquitectura en capas clásica:
*   `web`: Controladores REST, DTOs y Manejo de Excepciones.
*   `domain`: Lógica de negocio (Servicios).
*   `persistance`: Entidades JPA y Repositorios.

---
Desarrollado para el TP Integrado de Educación.
