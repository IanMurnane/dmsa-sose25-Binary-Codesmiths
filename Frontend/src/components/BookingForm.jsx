import React, {useEffect, useState} from "react";
import {createBooking, deleteBooking, getBookings} from "../api/apis";

const BookingForm = ({ vehicleId, userId, handlePayment, ratingCallback }) => {
    const [bookings, setBookings] = useState([]);
    const [bookingDate, setBookingDate] = useState("");

    useEffect(() => {
        loadBookings();
    }, []);

    const loadBookings = async () => {
        try {
            const res = await getBookings();
            setBookings(res.data);
        } catch (err) {
            console.error("Error loading bookings", err);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const bookingDateTime = bookingDate + "T00:00:00"; // send as local datetime string

        const booking = {
            userId,
            vehicleId,
            bookingTime: bookingDateTime,
            status: "REQUIRES PAYMENT",
        };

        try {
            await createBooking(booking);
            setBookingDate("");
            loadBookings();
        } catch (err) {
            console.error("Error submitting booking", err);
        }
    };

    const handleCancel = async (id) => {
        if (!window.confirm("Cancel this booking?")) return;
        try {
            await deleteBooking(id);
            loadBookings();
        } catch (err) {
            console.error("Error deleting booking", err);
        }
    };

    const formatDate = (isoString) => {
        const date = new Date(isoString);
        // Format as "19 Jun 25"
        const options = { day: "2-digit", month: "short", year: "2-digit" };
        return date.toLocaleDateString("en-GB", options);
    };

    return (
        <div className="container">
            <h1>Booking Service</h1>
            <form onSubmit={handleSubmit}>
                <input
                    type="date"
                    value={bookingDate}
                    onChange={(e) => setBookingDate(e.target.value)}
                    required
                />
                <button type="submit">Submit Booking</button>
            </form>

            <h2>All Bookings</h2>
            <table style={{ width: "100%", borderCollapse: "collapse", textAlign: "left" }}>
            <thead>
                <tr>
                    <th>User</th>
                    <th>Vehicle</th>
                    <th>Booking Date</th>
                    <th>Status</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                {bookings.map((b) => (
                    <tr key={b.id}>
                        <td>{b.userId}</td>
                        <td>{b.vehicleId}</td>
                        <td>{formatDate(b.bookingTime)}</td>
                        <td>{b.status}</td>
                        <td>
                            {b.status === "REQUIRES PAYMENT" && (
                                <button onClick={() => handlePayment(b.id)}>Pay</button>
                            )}
                            {b.status === "CONFIRMED" && (
                                <button onClick={() => ratingCallback()}>Rate</button>
                            )}
                        </td>
                        <td>
                            <button onClick={() => handleCancel(b.id)}>Cancel</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default BookingForm;
