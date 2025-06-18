import React, {useEffect, useState} from "react";
import {createBooking, deleteBooking, getBookings} from "../api/apis";

const BookingForm = ({ vehicleId, userId }) => {
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
        const booking = {
            userId,
            vehicleId,
            bookingDate,
            status: "CONFIRMED",
        };

        try {
            console.log("booking", booking);
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
            <table style={{ width: "100%", borderCollapse: "collapse" }}>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>User ID</th>
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
                        <td>{b.userId}</td>
                        <td>{b.vehicleId}</td>
                        <td>{formatDate(b.startTime)}</td>
                        <td>{b.status}</td>
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
