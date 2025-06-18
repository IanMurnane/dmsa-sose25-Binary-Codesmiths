import React, { useEffect, useState } from "react";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import { getVehicles } from "../api/apis";
import L from "leaflet";

// Optional: Custom marker icon fix for Leaflet default icons
delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
    iconRetinaUrl: require("leaflet/dist/images/marker-icon-2x.png"),
    iconUrl: require("leaflet/dist/images/marker-icon.png"),
    shadowUrl: require("leaflet/dist/images/marker-shadow.png"),
});

const Home = () => {
    const [vehicles, setVehicles] = useState([]);

    useEffect(() => {
        getVehicles()
            .then((res) => {
                console.log(res.data);
                const parsed = res.data.map((v) => {
                    const [lat, lng] = v.location.split(" ").map(Number);
                    console.log({lat, lng});
                    return { lat, lng };
                });
                setVehicles(parsed);
            })
            .catch((err) => {
                console.error("API Error:", err);
            });
    }, []);

    return (
        <div style={{ height: "100vh", width: "100%" }}>
            <MapContainer center={[51.491444, 7.414111]} zoom={13} style={{ height: "100%", width: "100%" }}>
                <TileLayer
                    attribution='&copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors'
                    url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                />
                {vehicles.map((v, idx) => (
                    <Marker key={idx} position={[v.lat, v.lng]}>
                        <Popup>Vehicle at ({v.lat}, {v.lng})</Popup>
                    </Marker>
                ))}
            </MapContainer>
        </div>
    );
};

export default Home;
