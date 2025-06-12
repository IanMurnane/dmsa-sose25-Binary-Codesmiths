import React, { useState } from "react";

const Payment = () => {
  const [bookingId, setBookingId] = useState("");
  const [userId, setUserId] = useState("");
  const [amount, setAmount] = useState("");
  const [paymentMethod, setPaymentMethod] = useState("credit_card");
  const [lookupBookingId, setLookupBookingId] = useState("");
  const [paymentResult, setPaymentResult] = useState(null);
  const [paymentError, setPaymentError] = useState("");
  const [statusResult, setStatusResult] = useState(null);
  const [statusError, setStatusError] = useState("");

  const BASE_URL = "###";

  const handlePaymentSubmit = async (e) => {
    e.preventDefault();
    try {
      const payload = {
        bookingId,
        userId,
        amount: parseFloat(amount),
        paymentMethod,
        billingUnit: "per_hour",
        billingRate: 1.0
      };

      const res = await fetch(BASE_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
      });

      const data = await res.json();
      if (res.ok) {
        setPaymentResult(data);
        setPaymentError("");
      } else {
        throw new Error(data.message || "Payment failed.");
      }
    } catch (err) {
      setPaymentResult(null);
      setPaymentError(err.message);
    }
  };

  const handleStatusSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await fetch(`${BASE_URL}/booking/${lookupBookingId}`);
      const data = await res.json();
      if (res.ok) {
        setStatusResult(data);
        setStatusError("");
      } else {
        throw new Error(data.message || "Not found.");
      }
    } catch (err) {
      setStatusResult(null);
      setStatusError(err.message);
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Process New Payment</h2>
      <form onSubmit={handlePaymentSubmit}>
        <input type="text" value={bookingId} onChange={(e) => setBookingId(e.target.value)} placeholder="Booking ID" required /><br />
        <input type="text" value={userId} onChange={(e) => setUserId(e.target.value)} placeholder="User ID" required /><br />
        <input type="number" step="0.01" value={amount} onChange={(e) => setAmount(e.target.value)} placeholder="Amount" required /><br />
        <select value={paymentMethod} onChange={(e) => setPaymentMethod(e.target.value)}>
          <option value="credit_card">Credit Card</option>
          <option value="paypal">PayPal</option>
        </select><br />
        <button type="submit">Submit Payment</button>
      </form>
      {paymentResult && <pre>{JSON.stringify(paymentResult, null, 2)}</pre>}
      {paymentError && <p style={{ color: "red" }}>{paymentError}</p>}

      <hr />

      <h2>Check Payment Status</h2>
      <form onSubmit={handleStatusSubmit}>
        <input type="text" value={lookupBookingId} onChange={(e) => setLookupBookingId(e.target.value)} placeholder="Booking ID" required /><br />
        <button type="submit">Get Payment Status</button>
      </form>
      {statusResult && <pre>{JSON.stringify(statusResult, null, 2)}</pre>}
      {statusError && <p style={{ color: "red" }}>{statusError}</p>}
    </div>
  );
};

export default Payment;
