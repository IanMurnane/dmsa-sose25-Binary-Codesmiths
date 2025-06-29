import React, { useEffect, useState } from 'react';
import { getProviderBookings } from '../api/bookingApi';

export default function Bookings() {
  const [bookings, setBookings] = useState([]);

  useEffect(() => {
    getProviderBookings()
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
