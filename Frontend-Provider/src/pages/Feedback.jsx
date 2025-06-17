import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function Feedback() {
  const [feedbackList, setFeedbackList] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8083/feedback/provider/1') // Replace with provider ID
      .then(res => setFeedbackList(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div>
      <h2>Vehicle Feedback</h2>
      <ul>
        {feedbackList.map(feedback => (
          <li key={feedback.id}>
            Rating: {feedback.rating} - "{feedback.comment}"
          </li>
        ))}
      </ul>
    </div>
  );
}