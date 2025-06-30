import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import Vehicles from './pages/Vehicles';
import Bookings from './pages/Bookings';
import Feedback from './pages/Feedback';
import Profile from './pages/Profile';
import Payments from './pages/Payments';
import ProtectedRoute from './components/ProtectedRoute';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/dashboard" element={
          <ProtectedRoute><Dashboard /></ProtectedRoute>
        } />
        <Route path="/vehicles" element={
          <ProtectedRoute><Vehicles /></ProtectedRoute>
        } />
        <Route path="/bookings" element={
          <ProtectedRoute><Bookings /></ProtectedRoute>
        } />
        <Route path="/payments" element={
                  <ProtectedRoute><Payments /></ProtectedRoute>
        } />
        <Route path="/feedback" element={
          <ProtectedRoute><Feedback /></ProtectedRoute>
        } />
        <Route path="/profile" element={
          <ProtectedRoute><Profile /></ProtectedRoute>
        } />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
