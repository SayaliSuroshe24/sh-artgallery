import React, { useState } from "react";
import axios from "axios";
import "./Login.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post("http://localhost:8080/api/users/login", { email, password });

      if (response.data.token) {
        localStorage.setItem("token", response.data.token);
        setMessage("Login successful!");
      } else {
        setMessage("Login failed. Please check your credentials.");
      }
    } catch (error) {
      console.error("Login Error:", error.response?.data || error.message);
      setMessage(error.response?.data?.message || "An error occurred!");
    }
  };

  return (
    <div className="login-container">
      <div className="login-box">
        <h2 className="login-title">Login</h2>
        <form onSubmit={handleLogin}>
          <input type="email" className="login-input" placeholder="Enter Email" value={email} onChange={(e) => setEmail(e.target.value)} required />
          <input type="password" className="login-input" placeholder="Enter Password" value={password} onChange={(e) => setPassword(e.target.value)} required />
          <button type="submit" className="login-button">Login</button>
        </form>
        {message && <p className={`login-message ${message === "Login successful!" ? "success" : "error"}`}>{message}</p>}
      </div>
    </div>
  );
};

export default Login;
