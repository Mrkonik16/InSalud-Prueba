# Prueba Técnica - InSalud
Repositorio destinado a almacenar el frontend y backend de la prueba técnica proporcionada por InSalud, dentro del cual se encuentran los códigos fuente del frontend y el backend de la aplicación solicitada.

## Contenido del repositorio
- `backend/` - código backend (Spring Boot).
- `frontend/` - código frontend (React + Vite).
- `README-backend.md` - instrucciones detalladas del backend.
- `README-frontend.md` - instrucciones detalladas del frontend.

## Requisitos (local)
- Java 17 (para el backend)
- Maven o Gradle (según el build)
- Node 18+ y npm (para el frontend)
- Docker & docker-compose (opcional para base de datos)
- pgAdmin (opcional)

## Aclaraciones Importantes
1. Para este proyecto, he decidido usar ReactJS, ya que a pesar de no haberlo utilizado nunca en un proyecto real, cuento con cierto conocimiento del lenguaje y quise aplicarlo para seguir la rúbrica; del mismo modo, durante mi carrera se me enseño a trabajar principalmente con MySQL Workbench, por lo que quisiera disculparme en caso las instrucciones del Script de creación de la base de datos no son del todo claras.
2. Los entornos de desarrollo fueron los siguientes: Visual Studio Code (frontend), JetBrains IntelliJ (backend) y PostgreSQL (BD).
3. Instrucciones detalladas de cómo levantar tanto el frontend como el backend se encuentran dentro de las carpetas respectivas.
4. Del mismo modo, se encuentra detallado en dichos archivos README lo realizado en cada uno de los códigos.

## Script de Ejecución de la base de datos
Para ejecutar la Base de Datos, realize lo siguiente:

1. Ingresar a pgAdmin con las credenciales respectivas
2. Ejecutar el siguiente script:
```sql
   CREATE TABLE IF NOT EXISTS pacientes (
  id BIGSERIAL PRIMARY KEY,
  nombre VARCHAR(200) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  estado estado_paciente NOT NULL DEFAULT 'ACTIVO',
  created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);

CREATE TABLE IF NOT EXISTS paciente_roles (
  paciente_id BIGINT NOT NULL REFERENCES pacientes(id) ON DELETE CASCADE,
  role rol_enum NOT NULL,
  PRIMARY KEY (paciente_id, role)
);

CREATE INDEX IF NOT EXISTS idx_paciente_email ON pacientes(email);

CREATE TABLE IF NOT EXISTS medicos (
  id BIGSERIAL PRIMARY KEY,
  nombre VARCHAR(200) NOT NULL,
  estado estado_paciente NOT NULL DEFAULT 'ACTIVO',
  created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_medico_nombre ON medicos(nombre);

CREATE TABLE IF NOT EXISTS especialidades (
  id BIGSERIAL PRIMARY KEY,
  nombre VARCHAR(200) NOT NULL UNIQUE,
  estado estado_paciente NOT NULL DEFAULT 'ACTIVO'
);

CREATE TABLE IF NOT EXISTS medico_especialidad (
  medico_id BIGINT NOT NULL REFERENCES medicos(id) ON DELETE CASCADE,
  especialidad_id BIGINT NOT NULL REFERENCES especialidades(id) ON DELETE CASCADE,
  PRIMARY KEY (medico_id, especialidad_id)
);

CREATE TABLE IF NOT EXISTS atenciones (
  id BIGSERIAL PRIMARY KEY,
  fecha TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  motivo TEXT,
  paciente_id BIGINT NOT NULL REFERENCES pacientes(id) ON DELETE CASCADE,
  medico_id BIGINT NOT NULL REFERENCES medicos(id) ON DELETE CASCADE,
  estado estado_atencion NOT NULL DEFAULT 'PENDIENTE',
  created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);

CREATE INDEX IF NOT EXISTS idx_atencion_fecha ON atenciones(fecha);
CREATE INDEX IF NOT EXISTS idx_atencion_medico ON atenciones(medico_id);
```

## Siguiente Paso
Una vez creada la base de datos, por favor acceder a los readme tanto del frontend como del backend donde se encuentran instrucciones mas detalladas.
