import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { getVehicles } from "../api/vehicleApi";

export default function Vehicles() {
    const [vehicles, setVehicles] = useState([]);
    const [vehicle, setVehicle] = useState({
        providerId: '',
        type: '',
        model: '',
        pricePerHour: '',
        billingModel: '',
        status: '',
        location: '',
        lastUpdated: ''
    });

    // Fetch provider's vehicles
    useEffect(() => {
        getVehicles()
            .then(res => setVehicles(res.data))
            .catch(err => console.error(err));
    }, []);

    // Handle input changes
    const handleChange = (e) => {
        const { name, value } = e.target;
        setVehicle(prev => ({ ...prev, [name]: value }));
    };

    // Handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();

        const payload = {
            ...vehicle,
            providerId: parseInt(vehicle.providerId),
            pricePerHour: parseFloat(vehicle.pricePerHour),
            lastUpdated: new Date().toISOString(), // set on submit
        };

        axios.post('http://localhost:8082/vehicles', payload)
            .then(() => {
                alert('Vehicle added!');
                setVehicle({
                    providerId: '',
                    type: '',
                    model: '',
                    pricePerHour: '',
                    billingModel: '',
                    status: '',
                    location: '',
                    lastUpdated: ''
                });
                return axios.get('http://localhost:8082/vehicles');
            })
            .then(res => setVehicles(res.data))
            .catch(err => console.error(err));
    };

    return (
        <div>
            <h2>Your Vehicles</h2>
            <ul>
                {vehicles.map(v => (
                    <li key={v.id}>
                        {v.type} - {v.model} - {v.status}
                    </li>
                ))}
            </ul>

            <h3>Add Vehicle</h3>
            <form onSubmit={handleSubmit}>
                <input name="providerId" placeholder="Provider ID" value={vehicle.providerId} onChange={handleChange} />
                <input name="type" placeholder="Type" value={vehicle.type} onChange={handleChange} />
                <input name="model" placeholder="Model" value={vehicle.model} onChange={handleChange} />
                <input name="pricePerHour" placeholder="Price Per Hour" value={vehicle.pricePerHour} onChange={handleChange} />
                <input name="billingModel" placeholder="Billing Model" value={vehicle.billingModel} onChange={handleChange} />
                <input name="status" placeholder="Status" value={vehicle.status} onChange={handleChange} />
                <input name="location" placeholder="Location" value={vehicle.location} onChange={handleChange} />
                <button type="submit">Add</button>
            </form>
        </div>
    );
}
