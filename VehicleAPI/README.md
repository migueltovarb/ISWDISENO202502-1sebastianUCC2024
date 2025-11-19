# Vehicle API - CRUD con Spring Boot y MongoDB Atlas

API REST para gestionar vehículos usando Spring Boot 3, Java 17 y MongoDB Atlas.

## Configuración

### 1. MongoDB Atlas
Edita `src/main/resources/application.properties` y reemplaza la URI de conexión:
```
spring.data.mongodb.uri=mongodb+srv://[username]:[password]@[cluster].mongodb.net/vehicledb
```

### 2. Compilar y Ejecutar
```bash
mvn clean install
mvn spring-boot:run
```

La API estará disponible en: `http://localhost:8080`

## Endpoints

### Obtener todos los vehículos
```
GET /api/vehicles
```

### Obtener vehículo por ID
```
GET /api/vehicles/{id}
```

### Obtener vehículo por placa
```
GET /api/vehicles/placa/{placa}
```

### Obtener vehículos por marca
```
GET /api/vehicles/marca/{marca}
```

### Crear vehículo
```
POST /api/vehicles
Content-Type: application/json

{
  "marca": "Toyota",
  "modelo": "Corolla",
  "anio": 2023,
  "color": "Blanco",
  "placa": "ABC123",
  "tipo": "Sedan"
}
```

### Actualizar vehículo
```
PUT /api/vehicles/{id}
Content-Type: application/json

{
  "marca": "Toyota",
  "modelo": "Corolla",
  "anio": 2024,
  "color": "Negro",
  "placa": "ABC123",
  "tipo": "Sedan"
}
```

### Eliminar vehículo
```
DELETE /api/vehicles/{id}
```

## Tecnologías
- Java 17
- Spring Boot 3.2.0
- Spring Data MongoDB
- MongoDB Atlas
- Lombok
- Maven
