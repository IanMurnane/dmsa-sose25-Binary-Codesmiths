import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function Bookings() {
  const [bookings, setBookings] = useState([]);
  const providerId = 3;

  useEffect(() => {
    axios.get(`http://localhost:8081/bookings/provider/${providerId}`)
      .then(res => setBookings(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div>
      <h2>Your Vehicle Bookings</h2>
      <ul>
        {bookings.map(booking => (
          <li key={booking.id}>
            Vehicle: {booking.vehicleId} | Status: {booking.status} | User: {booking.userId}
          </li>
        ))}
      </ul>
    </div>
  );
}
