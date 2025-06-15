import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Home from "./pages/Home";
import About from "./pages/About";
import Users from "./pages/Users";
import Login from "./pages/Login";
import Payment from "./pages/Payment";
import Booking from "./pages/Booking";
import RequireAuth from "./Components/RequireAuth";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(() => {
    return localStorage.getItem("isLoggedIn") === "true";
  });

  const handleLogout = () => {
    localStorage.removeItem("isLoggedIn");
    setIsLoggedIn(false);
  };

  return (
    <Router>
      {isLoggedIn && (
        <nav>
          <Link to="/">Home</Link> | 
          <Link to="/about">About</Link> | 
          <Link to="/users">Users</Link> |
          <Link to="/payment">Payment</Link> |
           <Link to="/booking">Booking</Link> |
          <button onClick={handleLogout}>Logout</button>
        </nav>
      )}

      <Routes>
        <Route path="/login" element={<Login onLogin={() => setIsLoggedIn(true)} />} />
        <Route path="/" element={<Home />} />
        <Route path="/payment" element={<Payment />} />
        <Route path="/booking" element={<Booking />} />
        <Route
          path="/about"
          element={
            <RequireAuth isLoggedIn={isLoggedIn}>
              <About />
            </RequireAuth>
          }
        />
        <Route
          path="/users"
          element={
            <RequireAuth isLoggedIn={isLoggedIn}>
              <Users />
            </RequireAuth>
          }
        />
      </Routes>
    </Router>
  );
}

export default App;
