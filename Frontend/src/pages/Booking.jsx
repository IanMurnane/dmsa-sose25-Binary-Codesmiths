import React, { useEffect, useState } from "react";


function App() {
  const API_BASE_URL = "http://localhost:8080/api/bookings";

  const [bookings, setBookings] = useState([]);
  const [userName, setUserName] = useState("");
  const [vehicleId, setVehicleId] = useState("");
  const [bookingDate, setBookingDate] = useState("");
  

  useEffect(() => {
    loadBookings();
  }, []);

  const loadBookings = async () => {
    try {
      const res = await fetch(API_BASE_URL);
      const data = await res.json();
      setBookings(data);
    } catch (err) {
      console.error("Error loading bookings", err);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const booking = {
      userName,
      vehicleId,
      bookingDate,
      status: "CONFIRMED",
    };

    try {
      const res = await fetch(`${API_BASE_URL}/create`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(booking),
      });

      if (res.ok) {
        setUserName("");
        setVehicleId("");
        setBookingDate("");
        loadBookings();
      } else {
        throw new Error("Booking failed");
      }
    } catch (err) {
      console.error("Error submitting booking", err);
    }
  };

  const cancelBooking = async (id) => {
    if (!window.confirm("Cancel this booking?")) return;

    try {
      await fetch(`${API_BASE_URL}/delete/${id}`, { method: "DELETE" });
      loadBookings();
    } catch (err) {
      console.error("Error deleting booking", err);
    }
  };

  return (
    <div className="container">
      <h1>Booking Service</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="User Name"
          value={userName}
          onChange={(e) => setUserName(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Vehicle ID"
          value={vehicleId}
          onChange={(e) => setVehicleId(e.target.value)}
          required
        />
        <input
          type="date"
          value={bookingDate}
          onChange={(e) => setBookingDate(e.target.value)}
          required
        />
        <button type="submit">Submit Booking</button>
      </form>

      <h2>All Bookings</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>User Name</th>
            <th>Vehicle ID</th>
            <th>Booking Date</th>
            <th>Status</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {bookings.map((b) => (
            <tr key={b.id}>
              <td>{b.id}</td>
              <td>{b.userName}</td>
              <td>{b.vehicleId}</td>
              <td>{b.bookingDate}</td>
              <td>{b.status}</td>
              <td>
                <button onClick={() => cancelBooking(b.id)}>Cancel</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;
