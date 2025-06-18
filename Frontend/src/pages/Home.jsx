import React, { useEffect, useState } from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import { getVehicles } from "../api/apis";
import L from "leaflet";
import "@fortawesome/fontawesome-free/css/all.min.css";
import scooterPng from "../assets/scooter.png"; // 👈 Your custom scooter icon

// Optional: fallback for default Leaflet icons
delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
    iconRetinaUrl: require("leaflet/dist/images/marker-icon-2x.png"),
    iconUrl: require("leaflet/dist/images/marker-icon.png"),
    shadowUrl: require("leaflet/dist/images/marker-shadow.png"),
});

// Function to return the appropriate icon based on vehicle type
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

    useEffect(() => {
        getVehicles()
            .then((res) => {
                const parsed = res.data.map((v) => {
                    const [lat, lng] = v.location.split(" ").map(Number);
                    return { lat, lng, type: v.type || "car" };
                });
                setVehicles(parsed);
            })
            .catch((err) => {
                console.error("API Error:", err);
            });
    }, []);

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
                    >
                        <Popup>
                            {v.type.charAt(0).toUpperCase() + v.type.slice(1)} at ({v.lat.toFixed(4)}, {v.lng.toFixed(4)})
                        </Popup>
                    </Marker>
                ))}
            </MapContainer>

            {/* Sidebar on right */}
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
                <h2 style={{ display: "flex", alignItems: "baseline" }}>
                    Miet Mich
                    <span
                        style={{
                            fontSize: "0.8rem",
                            color: "#666",
                            marginLeft: "8px",
                            fontStyle: "italic",
                        }}
                    >
            by Binary-Codesmiths
          </span>
                </h2>
                <p>Select a vehicle for further info.</p>
                {/* Future tab/routing content goes here */}
            </div>
        </div>
    );
};

export default Home;
