import axios from 'axios';

const VEHICLE_SERVICE_URL = 'http://localhost:8082';

export const getVehicles = async () => {
  return axios.get(`${VEHICLE_SERVICE_URL}/vehicles`);
};

export const getVehicleById = async (vehicleId) => {
  return axios.get(`${VEHICLE_SERVICE_URL}/vehicles/${vehicleId}`);
};

export const createVehicle = async (vehicleData) => {
  return axios.post(`${VEHICLE_SERVICE_URL}/vehicles`, vehicleData);
};

export const updateVehicle = async (vehicleId, vehicleData) => {
  return axios.put(`${VEHICLE_SERVICE_URL}/vehicles/${vehicleId}`, vehicleData);
};

export const deleteVehicle = async (vehicleId) => {
  return axios.delete(`${VEHICLE_SERVICE_URL}/vehicles/${vehicleId}`);
};
