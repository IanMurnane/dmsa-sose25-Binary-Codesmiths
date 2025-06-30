import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { getUsers, registerUser } from "../api/userApi";

export default function Users() {
    
    const [users, setUsers] = useState([]);
    const [formData, setFormData] = useState({
        id: '',
        email: '',
        fullName: '',
        password: '',
        role: ''
    });

    // Fetch provider's vehicles
    useEffect(() => {
        getUsers()
            .then(res => setUsers(res.data))
            .catch(err => console.error(err));
    }, []);

    // Handle input changes
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    // Handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();

        registerUser(formData)
            .then(() => {
            alert('User added!');
            setFormData({
                id: '',
                email: '',
                fullName: '',
                password: '',
                role: ''
            });
        return getUsers();
        })
        .then(res => setUsers(res.data))
        .catch(err => console.error(err));
    };

    return (
        <div>
            <h2>Your Users</h2>
            <table border="1" cellPadding="8" cellSpacing="0">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Full Name</th>
                        <th>Email</th>
                        <th>Role</th>
                    </tr>
                </thead>
                <tbody>
                    {users.map(user => (
                        <tr key={user.id}>
                        <td>{user.id}</td>
                        <td>{user.fullName}</td>
                        <td>{user.email}</td>
                        <td>{user.role}</td>
                    </tr>
                ))}
            </tbody>
        </table>

            <h3>Add User</h3>
            <form onSubmit={handleSubmit}>
                <input name="fullName" placeholder="Full Name" value={formData.fullName} onChange={handleChange} />
                <input name="email" placeholder="Email" value={formData.email} onChange={handleChange} />
                <input name="password" placeholder="Password" value={formData.password} onChange={handleChange} />
                <input name="role" placeholder="Role" value={formData.role} onChange={handleChange} />
                <button type="submit">Add</button>
            </form>
        </div>
    );
}
