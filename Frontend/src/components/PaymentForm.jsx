import React, { useState } from "react";
import { getPayment, processPayment } from "../api/apis";

const PaymentForm = ({ bookingId, userId, closeCallback }) => {
    const [amount, setAmount] = useState("");
    const [paymentId, setPaymentId] = useState("");
    const [paymentMethod, setPaymentMethod] = useState("credit_card");
    const [paymentResult, setPaymentResult] = useState(null);
    const [paymentError, setPaymentError] = useState("");
    const [statusResult, setStatusResult] = useState(null);
    const [statusError, setStatusError] = useState("");

    const handlePaymentSubmit = async (e) => {
        e.preventDefault();
        try {
            const paymentData = {
                bookingId,
                userId,
                amount: parseFloat(amount),
                paymentMethod,
                billingUnit: "per_hour",
                billingRate: 1.0,
            };

            const response = await processPayment(paymentData);
            if (response.status === 200) {
                setPaymentResult(response.data);
                setPaymentError("");
            }
            else {
                setPaymentResult(null);
                setPaymentError(response.statusText || "Payment failed.");
            }
        }
        catch (err) {
            setPaymentResult(null);
            setPaymentError(err.message);
        }
    };

    const handleStatusSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await getPayment(paymentId);
            if (response.status === 200) {
                setStatusResult(response.data);
                setStatusError("");
            } else {
                setPaymentResult(null);
                setPaymentError(response.statusText || "Payment failed.");
            }
        }
        catch (err) {
            setStatusResult(null);
            setStatusError("Payment unavailable.");
        }
    };

    return (
        <div style={{ padding: "20px" }}>
            <h2>Process New Payment</h2>
            <form onSubmit={handlePaymentSubmit}>
                <input
                    type="text"
                    value={bookingId}
                    placeholder="Booking ID"
                    required
                    disabled
                /><br />
                <input
                    type="text"
                    value={userId}
                    placeholder="User ID"
                    required
                    disabled
                /><br />
                <input
                    type="number"
                    step="0.01"
                    value={amount}
                    onChange={(e) => setAmount(e.target.value)}
                    placeholder="Amount"
                    required
                /><br />
                <select
                    value={paymentMethod}
                    onChange={(e) => setPaymentMethod(e.target.value)}
                >
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
                <input
                    type="text"
                    value={paymentId}
                    onChange={(e) => setPaymentId(e.target.value)}
                    placeholder="Payment ID"
                    required
                /><br />
                <button type="submit">Get Payment Status</button>
            </form>
            {statusResult && <pre>{JSON.stringify(statusResult, null, 2)}</pre>}
            {statusError && <p style={{ color: "red" }}>{statusError}</p>}

            <p>
                <button style={{ marginTop: "40px" }} onClick={() => closeCallback()}>Close and return to Booking</button>
            </p>
        </div>
    );
};

export default PaymentForm;
