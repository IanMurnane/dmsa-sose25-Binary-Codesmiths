import axios from 'axios';

const PAYMENT_SERVICE_URL = 'http://localhost:8083';

export const processPayment = async (paymentData) => {
  return axios.post(`${PAYMENT_SERVICE_URL}/payments`, paymentData);
};

export const getPaymentDetails = async (bookingId) => {
  return axios.get(`${PAYMENT_SERVICE_URL}/payments/${bookingId}`);
};
