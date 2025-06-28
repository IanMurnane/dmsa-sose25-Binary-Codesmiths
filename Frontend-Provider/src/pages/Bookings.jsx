import React, { useEffect, useState } from 'react';
import { getProviderBookings } from '../api/bookingApi';

export default function Bookings() {
  const [bookings, setBookings] = useState([]);
  const providerId = 3;

  useEffect(() => {
      if (!providerId) return;

      getProviderBookings(providerId)
        .then(res => setBookings(res.data))
        .catch(err => console.error(err));
    }, [providerId]);

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
