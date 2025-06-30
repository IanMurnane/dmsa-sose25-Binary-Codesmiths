import React, { useState } from 'react';
import { getPaymentDetails } from '../api/paymentApi';

const Payments = () => {
  const [bookingId, setBookingId] = useState('');
  const [payment, setPayment] = useState(null);
  const [error, setError] = useState('');

  const fetchPayment = async () => {
    try {
      const response = await getPaymentDetails(bookingId);
      setPayment(response.data);
      setError('');
    } catch (err) {
      setPayment(null);
      setError('Payment not found or server error.');
    }
  };

  return (
    <div style={{ padding: '2rem' }}>
      <h2>Fetch Payment by Booking ID</h2>

      <input
        type="text"
        placeholder="Enter Booking ID"
        value={bookingId}
        onChange={(e) => setBookingId(e.target.value)}
        style={{ marginRight: '1rem' }}
      />
      <button onClick={fetchPayment}>Check Payment Details</button>

      {error && <p style={{ color: 'red' }}>{error}</p>}

      {payment && (
        <div style={{ marginTop: '2rem' }}>
          <h3>Payment Details</h3>
          <p><strong>Booking ID:</strong> {payment.bookingId}</p>
          <p><strong>Amount:</strong> {payment.amount}</p>
          <p><strong>Payment Method:</strong> {payment.paymentMethod}</p>
          <p><strong>Billing Rate:</strong> {payment.billingRate}</p>
          <p><strong>Billing Unit:</strong> {payment.billingUnit}</p>
        </div>
      )}
    </div>
  );
};

export default Payments;
