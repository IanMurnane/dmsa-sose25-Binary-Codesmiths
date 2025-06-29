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
            <li key={f.id}>
              <strong>Vehicle:</strong> {f.vehicleId} <br />
              <strong>Rating:</strong> {f.rating} / 5<br />
              <strong>Comment:</strong> "{f.comment}"
              <hr />
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}
