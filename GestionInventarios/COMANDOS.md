# ⚡ Comandos Rápidos

Referencia rápida de comandos para trabajar con el proyecto.

## 🚀 Inicio del Sistema

### Iniciar Backend
```bash
cd backend
mvn spring-boot:run
```
✅ Backend: `http://localhost:8080`

### Iniciar Frontend
```bash
cd frontend
npm start
```
✅ Frontend: `http://localhost:3000`

## 🔧 Backend (Maven/Spring Boot)

### Compilación
```bash
# Limpiar y compilar
mvn clean install

# Solo compilar
mvn compile

# Compilar sin tests
mvn clean install -DskipTests
```

### Ejecución
```bash
# Ejecutar en desarrollo
mvn spring-boot:run

# Ejecutar con perfil específico
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Ejecutar JAR compilado
java -jar target/gestion-inventarios-1.0.0.jar
```

### Empaquetado
```bash
# Crear JAR ejecutable
mvn package

# Crear JAR sin tests
mvn package -DskipTests
```

### Limpieza
```bash
# Limpiar archivos compilados
mvn clean

# Limpiar caché de Maven
mvn dependency:purge-local-repository
```

## 🎨 Frontend (npm/React)

### Instalación
```bash
# Instalar dependencias
npm install

# Instalar dependencia específica
npm install axios

# Instalar dependencia de desarrollo
npm install --save-dev eslint
```

### Ejecución
```bash
# Modo desarrollo (hot reload)
npm start

# Build para producción
npm run build

# Ejecutar tests
npm test

# Ver tests en modo watch
npm test -- --watch
```

### Limpieza
```bash
# Limpiar node_modules
rm -rf node_modules
npm install

# Limpiar caché de npm
npm cache clean --force

# Reinstalar todo
rm -rf node_modules package-lock.json
npm install
```

## 🗄️ MongoDB

### Conexión Local (si usas MongoDB local)
```bash
# Iniciar MongoDB
mongod

# Conectar con mongo shell
mongosh

# Usar base de datos
use inventariodb

# Ver colecciones
show collections

# Ver documentos
db.productos.find()
```

### MongoDB Atlas
```bash
# Conectar con mongo shell
mongosh "mongodb+srv://cluster.mongodb.net/inventariodb" --username usuario

# Exportar datos
mongoexport --uri="mongodb+srv://..." --collection=productos --out=productos.json

# Importar datos
mongoimport --uri="mongodb+srv://..." --collection=productos --file=productos.json
```

## 🧪 Pruebas

### Backend
```bash
# Ejecutar todos los tests
mvn test

# Ejecutar test específico
mvn test -Dtest=ProductoServiceTest

# Ver cobertura
mvn test jacoco:report
```

### Frontend
```bash
# Ejecutar tests
npm test

# Ejecutar tests con cobertura
npm test -- --coverage

# Ejecutar tests específicos
npm test -- Dashboard.test.js
```

## 📦 Despliegue

### Backend
```bash
# Crear JAR para producción
mvn clean package -DskipTests

# Ejecutar en producción
java -jar target/gestion-inventarios-1.0.0.jar

# Con variables de entorno
java -jar target/gestion-inventarios-1.0.0.jar \
  --spring.data.mongodb.uri=mongodb+srv://... \
  --server.port=8080
```

### Frontend
```bash
# Build para producción
npm run build

# Servir build con servidor simple
npx serve -s build

# O copiar carpeta build/ a servidor web (nginx, apache)
```

## 🔍 Debugging

### Backend
```bash
# Ejecutar con debug habilitado
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"

# Ver logs detallados
mvn spring-boot:run -Dlogging.level.root=DEBUG
```

### Frontend
```bash
# Ejecutar con source maps
GENERATE_SOURCEMAP=true npm start

# Ver bundle size
npm run build -- --stats
npx webpack-bundle-analyzer build/bundle-stats.json
```

## 🛠️ Utilidades

### Git
```bash
# Inicializar repositorio
git init

# Agregar archivos
git add .

# Commit
git commit -m "Initial commit"

# Push a GitHub
git remote add origin https://github.com/usuario/repo.git
git push -u origin main
```

### Docker (opcional)
```bash
# Backend
docker build -t inventario-backend ./backend
docker run -p 8080:8080 inventario-backend

# Frontend
docker build -t inventario-frontend ./frontend
docker run -p 3000:3000 inventario-frontend
```

## 📊 Monitoreo

### Backend
```bash
# Ver logs en tiempo real
tail -f logs/spring.log

# Verificar salud de la aplicación
curl http://localhost:8080/actuator/health

# Ver métricas
curl http://localhost:8080/actuator/metrics
```

### Frontend
```bash
# Analizar bundle
npm run build
npx source-map-explorer build/static/js/*.js
```

## 🔄 Actualización de Dependencias

### Backend
```bash
# Ver dependencias desactualizadas
mvn versions:display-dependency-updates

# Actualizar versión de Spring Boot
mvn versions:update-parent
```

### Frontend
```bash
# Ver dependencias desactualizadas
npm outdated

# Actualizar todas las dependencias
npm update

# Actualizar dependencia específica
npm install react@latest
```

## 🚨 Solución Rápida de Problemas

### Puerto ocupado
```bash
# Windows - Matar proceso en puerto 8080
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac - Matar proceso en puerto 8080
lsof -ti:8080 | xargs kill -9
```

### Limpiar todo y empezar de nuevo
```bash
# Backend
cd backend
mvn clean
rm -rf target

# Frontend
cd frontend
rm -rf node_modules build
npm install
```

## 📝 Variables de Entorno

### Backend (application.properties)
```properties
# MongoDB
spring.data.mongodb.uri=${MONGODB_URI}
spring.data.mongodb.database=${MONGODB_DB:inventariodb}

# Server
server.port=${PORT:8080}

# Logging
logging.level.root=${LOG_LEVEL:INFO}
```

### Frontend (.env)
```bash
REACT_APP_API_URL=http://localhost:8080
REACT_APP_ENV=development
```

## 🎯 Atajos Útiles

### Reiniciar todo
```bash
# Terminal 1 - Backend
cd backend && mvn spring-boot:run

# Terminal 2 - Frontend
cd frontend && npm start
```

### Ver logs
```bash
# Backend
tail -f backend/logs/spring.log

# Frontend
# Los logs aparecen en la consola donde ejecutaste npm start
```

### Verificar que todo funciona
```bash
# Backend
curl http://localhost:8080/api/productos

# Frontend
# Abrir http://localhost:3000 en el navegador
```

---

**💡 Tip**: Guarda este archivo como referencia rápida. Puedes crear alias en tu terminal para los comandos más usados.
