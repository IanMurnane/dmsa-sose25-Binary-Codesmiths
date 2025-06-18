import axios from "axios";

const UserAPI = axios.create({baseURL: "http://localhost:8080"});
const VehicleAPI = axios.create({baseURL: "http://localhost:8082"});

export const createUser = (name, email, password) => {
  return UserAPI.post("/users/register", {
    fullName: name,
    email,
    password,
    role: "User"
  }, {
    headers: {
      "Content-Type": "application/json"
    }
  });
};
export const bookVehicle = (name, email, password) => {
  return UserAPI.post("/users/register", {
    fullName: name,
    email,
    password,
    role: "User"
  }, {
    headers: {
      "Content-Type": "application/json"
    }
  });
};
// export const getUsers = () => UserAPI.get("/users");
// export const loginUser = (email, password) => {
//   const params = new URLSearchParams();
//   params.append("email", email);
//   params.append("password", password);
//
//   return UserAPI.post("/auth/login", params, {
//     headers: {
//       "Content-Type": "application/x-www-form-urlencoded"
//     }
//   });
// };

export const getVehicles = () => VehicleAPI.get("/vehicles");
export const getVehicle = (id) => VehicleAPI.get(`/vehicles/${id}`);
