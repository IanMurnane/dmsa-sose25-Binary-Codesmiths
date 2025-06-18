import React, { useState } from "react";
import {createUser, login} from "../api/apis"; // adjust path if needed

const LoginForm = ({ onLoginComplete }) => {
    const [isCreating, setIsCreating] = useState(false);
    const [formData, setFormData] = useState({
        name: "",
        email: "",
        password: "",
    });
    const [message, setMessage] = useState("");

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (isCreating) {
            try {
                const res = await createUser(formData.name, formData.email, formData.password);

                const userId = res.data?.id;
                if (userId) {
                    setMessage("✅ Account created and logged in!");
                    if (onLoginComplete) onLoginComplete(userId);
                } else {
                    setMessage("❌ Failed to retrieve user ID");
                }
            } catch (err) {
                setMessage("❌ " + (err.response?.data || "Failed to create user"));
            }
        } else {
            try {
                const res = await login(formData.email, formData.password); // <== make sure this function exists
                const userId = res.data?.user?.id;

                if (userId) {
                    setMessage("✅ Logged in!");
                    if (onLoginComplete) onLoginComplete(userId);
                } else {
                    setMessage("❌ Failed to retrieve user ID");
                }
            } catch (err) {
                setMessage("❌ " + (err.response?.data || "Login failed"));
            }
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            {isCreating && (
                <div>
                    <label>Name:</label>
                    <input type="text" name="name" required value={formData.name} onChange={handleChange} />
                </div>
            )}
            <div>
                <label>Email:</label>
                <input type="email" name="email" required value={formData.email} onChange={handleChange} />
            </div>
            <div>
                <label>Password:</label>
                <input type="password" name="password" required value={formData.password} onChange={handleChange} />
            </div>

            <button type="submit" style={{ marginTop: "10px" }}>
                {isCreating ? "Create Account" : "Login"}
            </button>

            {message && (
                <p style={{ marginTop: "10px", fontSize: "0.9rem", color: message.startsWith("✅") ? "green" : "red" }}>
                    {message}
                </p>
            )}

            <p
                style={{
                    marginTop: "10px",
                    fontSize: "0.9rem",
                    cursor: "pointer",
                    color: "#007bff",
                }}
                onClick={() => {
                    setIsCreating(!isCreating);
                    setMessage("");
                }}
            >
                {isCreating ? "Already have an account? Log in" : "New here? Create an account"}
            </p>
        </form>
    );
};

export default LoginForm;
