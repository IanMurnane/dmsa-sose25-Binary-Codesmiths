import React from "react";
import LoginForm from "./LoginForm";
import { FaCar } from "react-icons/fa";

const VehicleInfoPanel = ({ vehicle, onHireClick, tab }) => {
    if (tab === "hire") {
        return (
            <div>
                <h3>Login to Hire</h3>
                <LoginForm />
            </div>
        );
    }

    if (!vehicle) {
        return <p>Select a vehicle for further info.</p>;
    }

    const {
        type,
        model,
        pricePerHour,
        billingModel,
        status,
        location,
        lastUpdated,
    } = vehicle;

    const formattedDate = new Date(
        ...lastUpdated,
        0,
        0
    ).toLocaleString("en-GB");

    return (
        <div>
            <h3>{model}</h3>
            <p><strong>Type:</strong> {type}</p>
            <p><strong>Status:</strong> {status}</p>
            <p><strong>Billing:</strong> {billingModel}</p>
            <p><strong>Price/Hour:</strong> €{pricePerHour.toFixed(2)}</p>
            <p><strong>Last updated:</strong> {formattedDate}</p>
            <p><strong>Location:</strong> {location}</p>
            <button onClick={onHireClick} style={{ marginTop: "16px" }}>
                <FaCar style={{ marginRight: "8px" }} />
                Hire this vehicle
            </button>
        </div>
    );
};

export default VehicleInfoPanel;
