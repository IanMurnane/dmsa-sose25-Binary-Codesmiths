import React, { useEffect, useState } from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import { getVehicles, getVehicle } from "../api/apis";
import L from "leaflet";
import "@fortawesome/fontawesome-free/css/all.min.css";
import scooterPng from "../assets/scooter.png";
import VehicleInfoPanel from "../components/VehicleInfoPanel";
import LoginForm from "../components/LoginForm";
import BookingForm from "../components/BookingForm";
import PaymentForm from "../components/PaymentForm";

// Fix default marker behavior
delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
    iconRetinaUrl: require("leaflet/dist/images/marker-icon-2x.png"),
    iconUrl: require("leaflet/dist/images/marker-icon.png"),
    shadowUrl: require("leaflet/dist/images/marker-shadow.png"),
});

// Icon switcher
const getVehicleIcon = (type) => {
    switch (type) {
        case "bike":
            return L.divIcon({
                html: `<i class="fas fa-bicycle" style="font-size: 24px; color: #2c3e50;"></i>`,
                iconSize: [24, 24],
                className: "custom-fa-icon",
            });
        case "car":
            return L.divIcon({
                html: `<i class="fas fa-car" style="font-size: 24px; color: #2c3e50;"></i>`,
                iconSize: [24, 24],
                className: "custom-fa-icon",
            });
        case "scooter":
        default:
            return L.icon({
                iconUrl: scooterPng,
                iconSize: [32, 32],
                iconAnchor: [16, 32],
                popupAnchor: [0, -32],
            });
    }
};

const Home = () => {
    const [vehicles, setVehicles] = useState([]);
    const [selectedVehicle, setSelectedVehicle] = useState(null);
    const [tab, setTab] = useState("info");
    const [userId, setUserId] = useState(0);
    const [bookingId, setBookingId] = useState(0);

    useEffect(() => {
        setUserId(parseInt(localStorage.getItem("userId") || 0));

        getVehicles()
            .then((res) => {
                const parsed = res.data.map((v) => {
                    const [lat, lng] = v.location.split(" ").map(Number);
                    return { id: v.id, lat, lng, type: v.type || "car" };
                });
                setVehicles(parsed);
            })
            .catch((err) => console.error("API Error:", err));
    }, []);

    const handleVehicleClick = (id) => {
        getVehicle(id)
            .then((res) => {
                setSelectedVehicle(res.data);
                setTab("info");
            })
            .catch((err) => console.error("Vehicle fetch error:", err));
    };

    const handleHireClick = () => {
        setTab("hire");
    };

    const handleLoginComplete = (userId) => {
        localStorage.setItem("userId", userId);
        setUserId(userId);
    };

    const handleLogout = () => {
        localStorage.removeItem("userId");
        setUserId(0);
        setSelectedVehicle(null);
        setTab("info");
    };

    const handlePayment = (id) => {
        setBookingId(id);
        setTab("payment");
    };

    return (
        <div style={{ position: "relative", height: "100vh", width: "100vw" }}>
            <MapContainer
                center={[51.491444, 7.414111]}
                zoom={13}
                style={{ height: "100%", width: "100%" }}
            >
                <TileLayer
                    attribution='&copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />
                {vehicles.map((v, idx) => (
                    <Marker
                        key={idx}
                        position={[v.lat, v.lng]}
                        icon={getVehicleIcon(v.type)}
                        eventHandlers={{
                            click: () => handleVehicleClick(v.id),
                        }}
                    >
                        <Popup>{v.type}</Popup>
                    </Marker>
                ))}
            </MapContainer>

            {/* Sidebar */}
            <div
                style={{
                    position: "absolute",
                    top: "3%",
                    right: "3%",
                    height: "85%",
                    width: "30%",
                    backgroundColor: "white",
                    borderRadius: "12px",
                    boxShadow: "0 4px 12px rgba(0, 0, 0, 0.2)",
                    padding: "16px",
                    overflowY: "auto",
                    zIndex: 1000,
                }}
            >
                <h2 style={{
                    display: "flex",
                    alignItems: "baseline",
                    justifyContent: "space-between"
                }}>
                    <div>
                        Miet Mich
                        <span style={{
                            fontSize: "0.8rem",
                            color: "#666",
                            marginLeft: "8px",
                            fontStyle: "italic"
                        }}>
                            by Binary-Codesmiths
                        </span>
                    </div>

                    {userId !== 0 && (
                        <button
                            onClick={handleLogout}
                            style={{
                                fontSize: "0.8rem",
                                backgroundColor: "#eee",
                                border: "1px solid #ccc",
                                borderRadius: "6px",
                                padding: "4px 8px",
                                cursor: "pointer"
                            }}
                        >
                            Logout
                        </button>
                    )}
                </h2>


                {tab === "info" && (
                    <VehicleInfoPanel
                        vehicle={selectedVehicle}
                        onHireClick={handleHireClick}
                    />
                )}

                {tab === "hire" && userId === 0 && (
                    <LoginForm onLoginComplete={handleLoginComplete} />
                )}
                {tab === "hire" && userId !== 0 && (
                    <BookingForm
                        vehicleId={selectedVehicle.id}
                        userId={userId}
                        handlePayment={handlePayment}
                    />
                )}

                {tab === "payment" && userId !== 0 && (
                    <PaymentForm
                        bookingId={bookingId}
                        userId={userId}
                        closeCallback={() => setTab("hire")}
                    />
                )}
            </div>
        </div>
    );
};

export default Home;
