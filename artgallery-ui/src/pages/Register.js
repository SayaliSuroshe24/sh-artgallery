import React, { useState } from 'react';
import axios from 'axios';

const Register = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [phoneNo, setPhoneNo] = useState(''); // Updated to match the backend
    const [role, setRole] = useState('ADMIN'); // Default role
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const handleRegister = async (e) => {
        e.preventDefault();
        setMessage('');
        setError('');
        try {
            // Prepare the user data according to the expected structure
            const userData = {
                userId: 0, // Assuming the backend will handle this
                name,
                email,
                password,
                phoneNo, // Updated to match the backend
                role,
                dateCreated: new Date().toISOString() // Optional, if the backend handles this
            };

            // Make the API call to register the user
            const response = await axios.post('http://localhost:8080/api/users/register', userData);
            setMessage(response.data); // Assuming the response has a message
        } catch (err) {
            console.error(err); // Log the entire error object
            if (err.response && err.response.data) {
                setError(err.response.data.message || 'An error occurred during registration.');
            } else {
                setError('An unexpected error occurred.');
            }
        }
    };

    return (
        <div style={{ maxWidth: '400px', margin: '50px auto', padding: '20px', border: '1px solid #ccc', borderRadius: '8px', backgroundColor: '#fff' }}>
            <h2>Register</h2>
            <form onSubmit={handleRegister}>
                <input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)} required style={{ width: '100%', padding: '10px', margin: '10px 0', border: '1px solid #ccc', borderRadius: '5px' }} />
                <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required style={{ width: '100%', padding: '10px', margin: '10px 0', border: '1px solid #ccc', borderRadius: '5px' }} />
                <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required style={{ width: '100%', padding: '10px', margin: '10px 0', border: '1px solid #ccc', borderRadius: '5px' }} />
                <input type="text" placeholder="Phone Number" value={phoneNo} onChange={(e) => setPhoneNo(e.target.value)} required style={{ width: '100%', padding: '10px', margin: '10px 0', border: '1px solid #ccc', borderRadius: '5px' }} />
                <select value={role} onChange={(e) => setRole(e.target.value)} style={{ width: '100%', padding: '10px', margin: '10px 0', border: '1px solid #ccc', borderRadius: '5px' }}>
                    <option value="ADMIN">Admin</option>
                    <option value="CUSTOMER">Customer</option>
                </select>
                <button type="submit" style={{ width: '100%', padding: '10px', backgroundColor: '#3857F1', color: 'white', border: 'none', borderRadius: '5px' }}>Register</button>
            </form>
            {message && <p style={{ color: 'green' }}>{message}</p>}
            {error && <p style={{ color: 'red' }}>{error}</p>} {/* Display error message */}
        </div>
    );
};

export default Register;