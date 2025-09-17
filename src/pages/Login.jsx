import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../lib/api";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    setError(null);
    try {
      const res = await api.post("/auth/login", { email, password });
      const { token, role } = res.data;

      localStorage.setItem("token", token);
      localStorage.setItem("email", email);
      localStorage.setItem("role", role);

      // Redirigir según rol
      if (role === "ROLE_ADMIN") {
        navigate("/admin/atenciones");
      } else {
        navigate("/admin/atenciones");
      }
    } catch (err) {
      console.error(err);
      setError("Credenciales inválidas. Intente de nuevo.");
    }
  };

  return (
    <div className="flex justify-center items-center h-screen">
      <form onSubmit={handleLogin} className="p-6 rounded shadow-md bg-white">
        <h4 className="text-xl mb-4">Insalud Frontend</h4>
        <h2 className="text-xl mb-4">Iniciar Sesión</h2>

        {error && <p className="text-red-500">{error}</p>}

        <input
          type="email"
          placeholder="Correo"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="border p-2 w-full mb-3"
          required
        />
        <input
          type="password"
          placeholder="Contraseña"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="border p-2 w-full mb-3"
          required
        />

        <button
          type="submit"
          className="bg-blue-500 text-white px-4 py-2 rounded w-full"
        >
          Entrar
        </button>
      </form>
    </div>
  );
}
