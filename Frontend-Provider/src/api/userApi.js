import axios from 'axios';

const USER_SERVICE_URL = 'http://localhost:8080';

export const registerUser = async (userData) => {
  return axios.post(`${USER_SERVICE_URL}/auth/register`, userData);
};

export const loginUser = async (credentials) => {
  return axios.post(`${USER_SERVICE_URL}/auth/login`, credentials);
};

export const getUserProfile = async (userId) => {
  return axios.get(`${USER_SERVICE_URL}/users/${userId}`);
};

export const updateUserProfile = async (userId, userData) => {
  return axios.put(`${USER_SERVICE_URL}/users/${userId}`, userData);
};

export const deleteUser = async (userId) => {
  return axios.delete(`${USER_SERVICE_URL}/users/${userId}`);
};
