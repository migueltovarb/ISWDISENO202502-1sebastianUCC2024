# Backend - Sistema de Gestión de Inventarios

API REST desarrollada con Spring Boot y MongoDB Atlas.

## 🚀 Inicio Rápido

### Prerrequisitos
- Java 17+
- Maven 3.6+
- MongoDB Atlas (cuenta configurada)

### Configuración

1. **Configurar MongoDB Atlas**

Editar `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb+srv://usuario:password@cluster.mongodb.net/inventariodb
spring.data.mongodb.database=inventariodb
```

2. **Compilar**

```bash
mvn clean install
```

3. **Ejecutar**

```bash
mvn spring-boot:run
```

El servidor estará disponible en: `http://localhost:8080`

## 📁 Estructura

```
src/main/java/com/inventario/
├── model/              # Entidades MongoDB
│   ├── Producto.java
│   ├── Categoria.java
│   ├── Proveedor.java
│   ├── Usuario.java
│   ├── EntradaProducto.java
│   ├── SalidaProducto.java
│   └── LogAuditoria.java
│
├── repository/         # Repositorios MongoDB
│   ├── ProductoRepository.java
│   ├── CategoriaRepository.java
│   ├── ProveedorRepository.java
│   ├── UsuarioRepository.java
│   ├── EntradaProductoRepository.java
│   ├── SalidaProductoRepository.java
│   └── LogAuditoriaRepository.java
│
├── service/           # Lógica de negocio
│   ├── ProductoService.java
│   ├── CategoriaService.java
│   ├── ProveedorService.java
│   ├── UsuarioService.java
│   ├── EntradaProductoService.java
│   ├── SalidaProductoService.java
│   └── LogAuditoriaService.java
│
└── controller/        # API REST Controllers
    ├── ProductoController.java
    ├── CategoriaController.java
    ├── ProveedorController.java
    ├── UsuarioController.java
    ├── EntradaProductoController.java
    ├── SalidaProductoController.java
    └── LogAuditoriaController.java
```

## 🔌 Endpoints

### Productos
- `GET /api/productos` - Listar todos
- `GET /api/productos/{id}` - Por ID
- `GET /api/productos/codigo/{codigo}` - Por código
- `GET /api/productos/categoria/{categoriaId}` - Por categoría
- `GET /api/productos/proveedor/{proveedorId}` - Por proveedor
- `GET /api/productos/bajo-stock` - Con stock bajo
- `POST /api/productos` - Crear
- `PUT /api/productos/{id}` - Actualizar
- `DELETE /api/productos/{id}` - Eliminar

### Categorías
- `GET /api/categorias` - Listar todas
- `POST /api/categorias` - Crear
- `PUT /api/categorias/{id}` - Actualizar
- `DELETE /api/categorias/{id}` - Eliminar

### Proveedores
- `GET /api/proveedores` - Listar todos
- `GET /api/proveedores/nit/{nit}` - Por NIT
- `POST /api/proveedores` - Crear
- `PUT /api/proveedores/{id}` - Actualizar
- `DELETE /api/proveedores/{id}` - Eliminar

### Entradas/Salidas
- `GET /api/entradas` - Listar entradas
- `POST /api/entradas` - Registrar entrada
- `GET /api/salidas` - Listar salidas
- `POST /api/salidas` - Registrar salida

### Usuarios
- `GET /api/usuarios` - Listar usuarios
- `GET /api/usuarios/rol/{rol}` - Por rol
- `POST /api/usuarios` - Crear
- `PUT /api/usuarios/{id}` - Actualizar
- `DELETE /api/usuarios/{id}` - Eliminar

### Auditoría
- `GET /api/logs` - Listar logs
- `GET /api/logs/usuario/{usuarioId}` - Por usuario
- `GET /api/logs/entidad/{entidad}` - Por entidad

## 🗄️ Modelos de Datos

### Producto
```json
{
  "codigo": "PROD001",
  "nombre": "Laptop Dell",
  "descripcion": "Laptop Dell Inspiron 15",
  "precio": 1500.00,
  "cantidad": 10,
  "categoriaId": "cat123",
  "proveedorId": "prov456",
  "stockMinimo": 5,
  "stockMaximo": 100,
  "activo": true
}
```

### Entrada/Salida
```json
{
  "codigoProducto": "PROD001",
  "cantidad": 5,
  "usuarioId": "user789",
  "fecha": "2025-11-24T10:30:00",
  "observaciones": "Compra a proveedor XYZ"
}
```

## 🛠️ Tecnologías

- Spring Boot 3.2.0
- Spring Data MongoDB
- Spring Web
- Spring Validation
- Lombok
- Maven

## 📦 Dependencias

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

## 🔐 Configuración

### application.properties

```properties
# MongoDB Atlas
spring.data.mongodb.uri=mongodb+srv://usuario:password@cluster.mongodb.net/inventariodb
spring.data.mongodb.database=inventariodb

# Server
server.port=8080

# Application
spring.application.name=gestion-inventarios

# Logging
logging.level.org.springframework.data.mongodb.core.MongoTemplate=DEBUG
```

## 🧪 Pruebas

Usar Postman con la colección incluida en `../docs/Postman_Collection.json`

## 📝 Notas

- Las entradas incrementan automáticamente el stock
- Las salidas decrementan el stock y validan disponibilidad
- Todas las operaciones se registran en el log de auditoría
- Los productos con stock <= stockMinimo aparecen en alertas
