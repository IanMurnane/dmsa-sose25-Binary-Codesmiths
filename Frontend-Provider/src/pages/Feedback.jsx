import React, { useEffect, useState } from 'react';
import { getFeedbackByProvider } from '../api/feedbackApi';

export default function Feedback() {
  const [feedbackList, setFeedbackList] = useState([]);

  useEffect(() => {
    getFeedbackByProvider()
      .then(res => setFeedbackList(res.data))
      .catch(err => console.error('Error fetching feedback:', err));
  }, []);

  return (
      <div>
        <h2>Customer Feedback</h2>
        {feedbackList.length === 0 ? (
            <p>No feedback yet.</p>
        ) : (
            <ul>
              {feedbackList.map(f => (
                  <li key={f.bookingId}>
                    <strong>User ID:</strong> {f.userId} <br />
                    <strong>Vehicle ID:</strong> {f.vehicleId} <br />
                    <strong>Booking ID:</strong> {f.bookingId} <br />
                    <strong>Provider ID:</strong> {f.providerId} <br />
                    <strong>Rating:</strong> {f.ratingValue} / 5 <br />
                    <strong>Comments:</strong> "{f.comments}"
                    <hr />
                  </li>
              ))}
            </ul>
        )}
      </div>
  );
}
