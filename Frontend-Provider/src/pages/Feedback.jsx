import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function Feedback() {
  const [feedbackList, setFeedbackList] = useState([]);
  const providerId = localStorage.getItem('providerId');

  useEffect(() => {
    if (!providerId) return;

    axios.get(`http://localhost:8080/feedback/${providerId}`)
      .then(res => setFeedbackList(res.data))
      .catch(err => console.error('Error fetching feedback:', err));
  }, [providerId]);

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
