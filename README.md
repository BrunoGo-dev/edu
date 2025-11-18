## 🎯 Key Features

### Attendance Management (`/api/asistencias`)

- ✅ Full CRUD operations
- ✅ Query by student, class, course, date
- ✅ Attendance summary with statistics
- ✅ Duplicate prevention
- ✅ Multiple attendance states

### Grade Management (`/api/notas`)

- ✅ Full CRUD operations
- ✅ Query by student, evaluation, course
- ✅ Validation (0-100 range)
- ✅ Observations support
- ✅ Duplicate prevention

### Assignment Management (`/api/tareas`)

- ✅ Full CRUD operations
- ✅ Query by course, active status
- ✅ Pending assignments filter
- ✅ Date-based filtering
- ✅ Active/inactive status

### Observation Management (`/api/observaciones`)

- ✅ Full CRUD operations
- ✅ Query by student, teacher, course, type
- ✅ 4 observation types: POSITIVA, NEGATIVA, NEUTRAL, SUGERENCIA
- ✅ Rich filtering capabilities

## 🔧 Technologies & Patterns

### Technologies

- ✅ Spring Boot 3.5.5
- ✅ Java 17
- ✅ Spring Data JPA
- ✅ MySQL Database
- ✅ Jakarta Bean Validation
- ✅ Maven Build Tool

## 🚀 Build & Run

### Build the project

```bash
./mvnw clean package
```

### Run the application

```bash
./mvnw spring-boot:run
```

### Access the API

```
http://localhost:8080/api/
```

---

## 📁 Project Structure

```
edu/
├── src/main/java/com/TpIntegrado/edu/
│   ├── domain/
│   │   └── service/              # 4 Service classes
│   ├── persistance/
│   │   ├── entity/               # 5 new + 8 existing entities
│   │   └── repository/           # 8 Repository interfaces
│   └── web/
│       ├── controller/           # 5 REST controllers
│       ├── dto/                  # 9 DTOs
│       └── mapper/               # 5 Mapper classes
├── src/main/resources/
│   └── application.properties    # Database configuration
├── API_DOCUMENTATION.md          # Complete API reference
├── IMPLEMENTATION_SUMMARY.md     # Technical details
└── README.md                     # This file
```

---
