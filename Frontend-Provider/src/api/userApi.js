import axios from 'axios';

const BookingAPI = axios.create({ baseURL: "http://localhost:8081/api" });

export const registerUser = async (userData) => {
  return BookingAPI.post(`/users/register`, userData);
};

export const loginUser = async (credentials) => {
  return BookingAPI.post(`/auth/login`, credentials);
};

export const getUsers= async () => {
  return BookingAPI.get(`/users`);
};

export const getUserProfile = async (userId) => {
  return BookingAPI.get(`/users/${userId}`);
};

export const updateUserProfile = async (userId, userData) => {
  return BookingAPI.put(`/users/${userId}`, userData);
};

export const deleteUser = async (userId) => {
  return BookingAPI.delete(`/users/${userId}`);
};