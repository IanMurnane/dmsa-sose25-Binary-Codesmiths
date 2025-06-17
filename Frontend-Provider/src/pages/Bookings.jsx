import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function Bookings() {
  const [bookings, setBookings] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8083/bookings/provider/1') // Replace with dynamic provider ID
      .then(res => setBookings(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div>
      <h2>Your Bookings</h2>
      <ul>
        {bookings.map(booking => (
          <li key={booking.id}>
            Vehicle: {booking.vehicleId} | Status: {booking.status}
          </li>
        ))}
      </ul>
    </div>
  );
}