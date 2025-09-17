# InSalud - Frontend

Frontend del sistema **InSalud**, desarrollado en **React + Vite + React Router**, que permite:

- Login de usuario con JWT
- Gestión de atenciones (admin)
- Consulta de mis atenciones (paciente)
- Redirecciones según rol
- Estilo con Tailwind CSS

---

## Requisitos previos

- **Node.js 18+**
- **npm** o **yarn**

---

## Acalaraciones Importantes
1. Al ser mi primer frontend realizado con ReactJS, pido disculpas en caso pueda verse algo rudimentario.
2. Si bien para ambos usuarios (Admin y Paciente) se le muestra el navbar y ambos pueden acceder a los mismos, unicamente el usuario con rol admin puede ejecutar los endpoints de manera exitosa. (verificable)

---

## 🛠Instalación y configuración

### 1. Clonar el repositorio
```bash
git clone
cd insalud/frontend
```

### 2. Instalar dependencias
Ejecutar `npm install`

### 3. Ejecutar en modo desarrollo
`npm run dev` IMPORTANTE: Se debe tener activado el backend antes de utilizar el frontend. El frontend estará disponible en: `http://localhost:5173`. Recordar usar las credenciales: `adminInsalud@gmail.com / admin123 o userInsalud@gmail.com / user123`.
