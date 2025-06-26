import React from 'react';
import { Navigate } from 'react-router-dom';

export default function ProtectedRoute({ children }) {
  const providerId = localStorage.getItem('providerId');
  return providerId ? children : <Navigate to="/" />;
}
