import axios from 'axios';

const BookingAPI = axios.create({ baseURL: "http://localhost:8081/api" });

export const getProviderBookings = async () => {
  return BookingAPI.get(`/bookings`);
};

export const getBookings = async (userId) => {
  return BookingAPI.get(`/bookings/${userId}`);
};

export const createBooking = async (bookingData) => {
  return BookingAPI.post(`/bookings`, bookingData);
};

export const endBooking = async (bookingId) => {
  return BookingAPI.put(`/bookings/${bookingId}/end`);
};

export const deleteBooking = async (bookingId) => {
  return BookingAPI.delete(`/bookings/${bookingId}`);
};
