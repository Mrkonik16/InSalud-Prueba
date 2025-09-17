# InSalud - Backend

Backend del sistema **InSalud**, desarrollado con **Spring Boot** y **PostgreSQL**, que gestiona:
- Pacientes
- Médicos y especialidades
- Atenciones médicas
- Autenticación y autorización con JWT (roles: `ADMIN` y `PACIENTE`)

---

## Requisitos previos

- **Java 17** o superior
- **Maven 3.9+**
- **PostgreSQL 14+**
- Opcional: **Docker** para levantar la base de datos más rápido

---

## Aclaraciones Importantes
1. Todos los endpoints funcionan correctamente, salvo el `GET atenciones/mias`, esto se debe a un error en el JWS que no pude solucionar a tiempo: Si bien si se muestra para los token de admin, no lo muestra para los token de user.
2. El query personalizado esta declarado, pero no tuve tiempo de implementarlo en el frontend.
3. El archivo DataInitializer.Java genera automaticamente registros en la base de datos, que permiten acceder a las funcionalidades, estos son los siguientes:
   - Credenciales de Admin: `adminInsalud@gmail.com`, `admin123`.
   - Credendiales de Paciente: `userInsalud@gmail.com`, `user123`.

---

## 🛠️ Instalación y configuración

### 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/InSalud.git
cd insalud/backend
```

### 2. Generar la base de datos
El proceso esta detallado en el README.md, debe ser en postgreSQL, en mi caso yo lo hice de forma manual (sin comandos).

### 3. Modificar el archivo Application.Properties
Antes de ejecutar el frontend, se debe cambiar la siguiente línea de código `spring.datasource.password=nico123` por la contraseña respectiva del usuario.

### 4. Ejecutar el backend
Finalmente, hacer click en run o ejecutar `mvn spring-boot:run`, los endpoints se pueden probar en postman, con las credenciales detalladas anteriormente.
