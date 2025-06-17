import axios from 'axios';
const FEEDBACK_SERVICE_URL = 'http://localhost:8084';

export const getFeedbackByProvider = async (providerId) => {
  return axios.get(`${FEEDBACK_SERVICE_URL}/feedback/provider/${providerId}`);
};
