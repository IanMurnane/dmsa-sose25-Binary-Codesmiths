import axios from "axios";

const UserAPI = axios.create({ baseURL: "http://localhost:8080" });
const BookingAPI = axios.create({ baseURL: "http://localhost:8081" });
const VehicleAPI = axios.create({ baseURL: "http://localhost:8082" });
const PaymentAPI = axios.create({ baseURL: "http://localhost:8083" });
const RatingAPI = axios.create({ baseURL: "http://localhost:8084" });

// Users
export const createUser = (name, email, password) => {
  return UserAPI.post(
      "/users/register",
      {
        fullName: name,
        email,
        password,
        role: "User",
      },
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
  );
};
export const login = (email, password) => {
  return UserAPI.post(
      "/auth/login",
      {
        email,
        password,
      },
      {
        headers: {
          "Content-Type": "application/json",
        },
      }
  );
};

// Vehicles
export const getVehicles = () => VehicleAPI.get("/vehicles");
export const getVehicle = (id) => VehicleAPI.get(`/vehicles/${id}`);

// Bookings
export const getBookings = () => BookingAPI.get("/bookings");
export const createBooking = (bookingData) =>
    BookingAPI.post("/bookings", bookingData, {
      headers: { "Content-Type": "application/json" },
    });
export const deleteBooking = (id) =>
    BookingAPI.delete(`/bookings/${id}`);

// Payments
export const getPayment = (id) => PaymentAPI.get(`/payments/${id}`);
export const processPayment = (paymentData) =>
    PaymentAPI.post("/payments", paymentData, {
        headers: { "Content-Type": "application/json" },
    });

// Ratings
export const saveRating = (userId, vehicleId, bookingId, providerId, ratingValue, comments) => {
    return RatingAPI.post(
        "/ratings",
        {
            userId,
            vehicleId,
            bookingId,
            providerId,
            ratingValue,
            comments,
        },
        {
            headers: {
                "Content-Type": "application/json",
            },
        }
    );
};
