import React, { useState } from "react";
import { saveRating } from "../api/apis";

const RatingForm = ({ userId, vehicleId, bookingId, providerId, closeCallback }) => {
    const [ratingValue, setRatingValue] = useState(5);
    const [comments, setComments] = useState("");
    const [message, setMessage] = useState("");

    const stars = [1, 2, 3, 4, 5];

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await saveRating(userId, vehicleId, bookingId, providerId, ratingValue, comments);
            setMessage("✅ Rating submitted successfully!");
        } catch (err) {
            setMessage("❌ " + (err.response?.data || "Error submitting rating"));
        }
    };

    return (
        <form onSubmit={handleSubmit} style={{ maxWidth: "400px", padding: "16px" }}>
            <h3>Leave a Rating</h3>

            <label>Rating:</label>
            <div style={{ marginBottom: "16px" }}>
                {stars.map((star) => (
                    <span
                        key={star}
                        style={{
                            cursor: "pointer",
                            fontSize: "32px",
                            color: ratingValue >= star ? "#FFD700" : "#ccc",
                            marginRight: "6px"
                        }}
                        onClick={() => setRatingValue(star)}
                    >
                        ★
                    </span>
                ))}
            </div>

            <div style={{ marginBottom: "16px" }}>
                <label style={{ display: "block", marginBottom: "6px" }}>Comments:</label>
                <textarea
                    value={comments}
                    onChange={(e) => setComments(e.target.value)}
                    rows={4}
                    style={{ width: "100%" }}
                    placeholder="Write your feedback..."
                />
            </div>

            <button type="submit" style={{ marginRight: "10px" }}>Submit</button>
            <button type="button" onClick={closeCallback}>Close</button>

            {message && (
                <p style={{ marginTop: "10px", color: message.startsWith("✅") ? "green" : "red" }}>
                    {message}
                </p>
            )}
        </form>
    );
};

export default RatingForm;
