import axios from 'axios';

// const VEHICLE_SERVICE_URL = 'http://localhost:8082';
const BookingAPI = axios.create({ baseURL: "http://localhost:8081/api" });

export const getVehicles = async () => {
  return BookingAPI.get(`/vehicles`);
};

export const getVehicleById = async (vehicleId) => {
  return BookingAPI.get(`/vehicles/${vehicleId}`);
};

export const createVehicle = async (vehicleData) => {
  return BookingAPI.post(`/vehicles`, vehicleData);
};

export const updateVehicle = async (vehicleId, vehicleData) => {
  return BookingAPI.put(`/vehicles/${vehicleId}`, vehicleData);
};

export const deleteVehicle = async (vehicleId) => {
  return BookingAPI.delete(`/vehicles/${vehicleId}`);
};
