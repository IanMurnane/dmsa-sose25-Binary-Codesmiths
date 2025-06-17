import axios from 'axios';

const BOOKING_SERVICE_BASE_URL = 'http://localhost:8081';

export const getBookings = async (userId) => {
  return axios.get(`${BOOKING_SERVICE_BASE_URL}/bookings/${userId}`);
};

export const createBooking = async (bookingData) => {
  return axios.post(`${BOOKING_SERVICE_BASE_URL}/bookings`, bookingData);
};

export const endBooking = async (bookingId) => {
  return axios.put(`${BOOKING_SERVICE_BASE_URL}/bookings/${bookingId}/end`);
};

export const deleteBooking = async (bookingId) => {
  return axios.delete(`${BOOKING_SERVICE_BASE_URL}/bookings/${bookingId}`);
};
