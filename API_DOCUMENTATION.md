# Educational Platform API Documentation

## Overview
This API provides endpoints for managing an educational platform with attendance tracking, grades, assignments, and teacher-student observations.

## Architecture
- **Pattern**: Data Mapper Pattern
- **Repositories**: CrudRepository with Query Methods
- **Database**: MySQL
- **Framework**: Spring Boot 3.5.5

## Entities

### Core Entities
- **Usuario**: Users (students, teachers, administrators)
- **Curso**: Courses
- **Clase**: Class sessions
- **Materia**: Subjects

### Feature Entities
- **Asistencia**: Attendance records
- **Nota**: Grades/Scores
- **Tarea**: Assignments
- **Observacion**: Teacher observations
- **Evaluacion**: Evaluations/Exams

## API Endpoints

### Attendance Management (`/api/asistencias`)

#### Get all attendance records
```
GET /api/asistencias
Response: List<AsistenciaDTO>
```

#### Get attendance by ID
```
GET /api/asistencias/{id}
Response: AsistenciaDTO
```

#### Get attendance by student
```
GET /api/asistencias/estudiante/{estudianteId}
Response: List<AsistenciaDTO>
```

#### Get attendance by class
```
GET /api/asistencias/clase/{claseId}
Response: List<AsistenciaDTO>
```

#### Get attendance by student and course
```
GET /api/asistencias/estudiante/{estudianteId}/curso/{cursoId}
Response: List<AsistenciaDTO>
```

#### Get attendance by course and date
```
GET /api/asistencias/curso/{cursoId}/fecha/{fecha}
Parameters: fecha format YYYY-MM-DD
Response: List<AsistenciaDTO>
```

#### Get attendance summary
```
GET /api/asistencias/estudiante/{estudianteId}/curso/{cursoId}/resumen
Response: {
  "presentes": Long,
  "ausentes": Long,
  "tardanzas": Long,
  "justificadas": Long
}
```

#### Create attendance
```
POST /api/asistencias
Body: {
  "claseId": Long,
  "estudianteId": Long,
  "estado": "PRESENTE|AUSENTE|TARDANZA|JUSTIFICADO",
  "observaciones": String (optional)
}
Response: AsistenciaDTO
```

#### Update attendance
```
PUT /api/asistencias/{id}
Body: {
  "estado": "PRESENTE|AUSENTE|TARDANZA|JUSTIFICADO",
  "observaciones": String (optional)
}
Response: AsistenciaDTO
```

#### Delete attendance
```
DELETE /api/asistencias/{id}
Response: {"message": "Asistencia eliminada exitosamente"}
```

---

### Grades Management (`/api/notas`)

#### Get all grades
```
GET /api/notas
Response: List<NotaDTO>
```

#### Get grade by ID
```
GET /api/notas/{id}
Response: NotaDTO
```

#### Get grades by student
```
GET /api/notas/estudiante/{estudianteId}
Response: List<NotaDTO>
```

#### Get grades by evaluation
```
GET /api/notas/evaluacion/{evaluacionId}
Response: List<NotaDTO>
```

#### Get grades by course
```
GET /api/notas/curso/{cursoId}
Response: List<NotaDTO>
```

#### Get grades by student and course
```
GET /api/notas/estudiante/{estudianteId}/curso/{cursoId}
Response: List<NotaDTO>
```

#### Create grade
```
POST /api/notas
Body: {
  "estudianteId": Long,
  "evaluacionId": Long,
  "nota": BigDecimal,
  "observaciones": String (optional)
}
Response: NotaDTO
```

#### Update grade
```
PUT /api/notas/{id}
Body: {
  "nota": BigDecimal,
  "observaciones": String (optional)
}
Response: NotaDTO
```

#### Delete grade
```
DELETE /api/notas/{id}
Response: {"message": "Nota eliminada exitosamente"}
```

---

### Assignments Management (`/api/tareas`)

#### Get all assignments
```
GET /api/tareas
Response: List<TareaDTO>
```

#### Get assignment by ID
```
GET /api/tareas/{id}
Response: TareaDTO
```

#### Get assignments by course
```
GET /api/tareas/curso/{cursoId}
Response: List<TareaDTO>
```

#### Get active assignments by course
```
GET /api/tareas/curso/{cursoId}/activas
Response: List<TareaDTO>
```

#### Get all active assignments
```
GET /api/tareas/activas
Response: List<TareaDTO>
```

#### Get pending assignments by course
```
GET /api/tareas/curso/{cursoId}/pendientes
Response: List<TareaDTO>
```

#### Create assignment
```
POST /api/tareas
Body: {
  "cursoId": Long,
  "titulo": String,
  "descripcion": String (optional),
  "fechaLimite": "YYYY-MM-DD"
}
Response: TareaDTO
```

#### Update assignment
```
PUT /api/tareas/{id}
Body: {
  "titulo": String,
  "descripcion": String (optional),
  "fechaLimite": "YYYY-MM-DD",
  "activo": Boolean (optional)
}
Response: TareaDTO
```

#### Delete assignment
```
DELETE /api/tareas/{id}
Response: {"message": "Tarea eliminada exitosamente"}
```

---

### Observations Management (`/api/observaciones`)

#### Get all observations
```
GET /api/observaciones
Response: List<ObservacionDTO>
```

#### Get observation by ID
```
GET /api/observaciones/{id}
Response: ObservacionDTO
```

#### Get observations by student
```
GET /api/observaciones/estudiante/{estudianteId}
Response: List<ObservacionDTO>
```

#### Get observations by teacher
```
GET /api/observaciones/docente/{docenteId}
Response: List<ObservacionDTO>
```

#### Get observations by course
```
GET /api/observaciones/curso/{cursoId}
Response: List<ObservacionDTO>
```

#### Get observations by student and course
```
GET /api/observaciones/estudiante/{estudianteId}/curso/{cursoId}
Response: List<ObservacionDTO>
```

#### Get observations by student and type
```
GET /api/observaciones/estudiante/{estudianteId}/tipo/{tipo}
Parameters: tipo = POSITIVA|NEGATIVA|NEUTRAL|SUGERENCIA
Response: List<ObservacionDTO>
```

#### Create observation
```
POST /api/observaciones
Body: {
  "estudianteId": Long,
  "docenteId": Long,
  "cursoId": Long,
  "titulo": String,
  "contenido": String,
  "tipo": "POSITIVA|NEGATIVA|NEUTRAL|SUGERENCIA"
}
Response: ObservacionDTO
```

#### Update observation
```
PUT /api/observaciones/{id}
Body: {
  "titulo": String,
  "contenido": String,
  "tipo": "POSITIVA|NEGATIVA|NEUTRAL|SUGERENCIA"
}
Response: ObservacionDTO
```

#### Delete observation
```
DELETE /api/observaciones/{id}
Response: {"message": "Observación eliminada exitosamente"}
```

---

## Data Models

### AsistenciaDTO
```json
{
  "id": Long,
  "claseId": Long,
  "estudianteId": Long,
  "estudianteNombre": String,
  "estado": "PRESENTE|AUSENTE|TARDANZA|JUSTIFICADO",
  "fechaRegistro": "2025-10-11T21:00:00",
  "observaciones": String
}
```

### NotaDTO
```json
{
  "id": Long,
  "estudianteId": Long,
  "estudianteNombre": String,
  "evaluacionId": Long,
  "evaluacionNombre": String,
  "nota": BigDecimal,
  "observaciones": String
}
```

### TareaDTO
```json
{
  "id": Long,
  "cursoId": Long,
  "cursoNombre": String,
  "titulo": String,
  "descripcion": String,
  "fechaLimite": "2025-10-11",
  "activo": Boolean
}
```

### ObservacionDTO
```json
{
  "id": Long,
  "estudianteId": Long,
  "estudianteNombre": String,
  "docenteId": Long,
  "docenteNombre": String,
  "cursoId": Long,
  "cursoNombre": String,
  "titulo": String,
  "contenido": String,
  "tipo": "POSITIVA|NEGATIVA|NEUTRAL|SUGERENCIA",
  "fechaCreacion": "2025-10-11T21:00:00"
}
```

## Error Responses

All endpoints return standard HTTP status codes:
- `200 OK`: Success
- `201 Created`: Resource created successfully
- `400 Bad Request`: Invalid request data
- `404 Not Found`: Resource not found
- `500 Internal Server Error`: Server error

Error response format:
```json
{
  "error": "Error message description"
}
```

## Technical Implementation

### Repositories
All repositories extend `CrudRepository` and use Query Methods for custom queries:
- `AsistenciaRepository`
- `NotaRepository`
- `TareaRepository`
- `ObservacionRepository`
- `ClaseRepository`
- `UsuarioRepository`
- `CursoRepository`
- `EvaluacionRepository`

### Services
Business logic is implemented in service classes:
- `AsistenciaService`
- `NotaService`
- `TareaService`
- `ObservacionService`

### Mappers (Data Mapper Pattern)
DTOs are mapped to/from entities using dedicated mapper classes:
- `AsistenciaMapper`
- `NotaMapper`
- `TareaMapper`
- `ObservacionMapper`
- `ClaseMapper`

## Database Configuration

Configure database connection in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/plataforma_academica
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## Running the Application

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## Testing Endpoints

Example using curl:

### Create attendance
```bash
curl -X POST http://localhost:8080/api/asistencias \
  -H "Content-Type: application/json" \
  -d '{
    "claseId": 1,
    "estudianteId": 1,
    "estado": "PRESENTE",
    "observaciones": "Asistió puntualmente"
  }'
```

### Get student attendance
```bash
curl http://localhost:8080/api/asistencias/estudiante/1
```

### Create grade
```bash
curl -X POST http://localhost:8080/api/notas \
  -H "Content-Type: application/json" \
  -d '{
    "estudianteId": 1,
    "evaluacionId": 1,
    "nota": 85.5,
    "observaciones": "Buen desempeño"
  }'
```
