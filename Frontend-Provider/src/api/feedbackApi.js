import axios from 'axios';

const BookingAPI = axios.create({ baseURL: "http://localhost:8081/api" });

export const getFeedbackByProvider = async (providerId) => {
  return BookingAPI.get(`/feedback/${providerId}`);
};
