import React from 'react';
import { useNavigate } from 'react-router-dom';

export default function Dashboard() {
  const navigate = useNavigate();

  return (
    <div>
      <h1>Provider Dashboard</h1>
      <p>Welcome! Here you can manage your vehicles and bookings.</p>
      <button onClick={() => navigate('/vehicles')}>Vehicles</button>
      <button onClick={() => navigate('/bookings')}>Bookings</button>
      <button onClick={() => navigate('/payments')}>Payments</button>
      <button onClick={() => navigate('/feedback')}>Feedback</button>
      <button onClick={() => navigate('/profile')}>Profile</button>
    </div>
  );
}