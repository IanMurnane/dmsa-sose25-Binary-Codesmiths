import axios from 'axios';

const BookingAPI = axios.create({ baseURL: "http://localhost:8081/api" });

export const processPayment = async (paymentData) => {
  return BookingAPI.post(`/payments`, paymentData);
};

export const getPaymentDetails = async (paymentId) => {
  return BookingAPI.get(`/payments/id/${paymentId}`);
};