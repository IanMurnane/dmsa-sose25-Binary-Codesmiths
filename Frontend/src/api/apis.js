import axios from "axios";

const UserAPI = axios.create({baseURL: "http://localhost:8080/api"});
const VehicleAPI = axios.create({baseURL: "http://localhost:8082"});

export const getUsers = () => UserAPI.get("/users");
export const loginUser = (email, password) => {
  const params = new URLSearchParams();
  params.append("email", email);
  params.append("password", password);

  return UserAPI.post("/auth/login", params, {
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    }
  });
};

export const getVehicles = () => VehicleAPI.get("/vehicles");
