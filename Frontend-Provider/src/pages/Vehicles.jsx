import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {getVehicles} from "../api/vehicleApi";

export default function Vehicles() {
  const [vehicles, setVehicles] = useState([]);
  const [vehicle, setVehicle] = useState({
    type: '',
    vendorVehicleId: '',
    location: '',
    price: '',
    billingModel: '',
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
    axios.post('http://localhost:8082/vehicles', vehicle)
      .then(() => {
        alert('Vehicle added!');
        setVehicle({
          type: '',
          vendorVehicleId: '',
          location: '',
          price: '',
          billingModel: '',
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
            {v.type} - {v.vendorVehicleId} - {v.status}
          </li>
        ))}
      </ul>

      <h3>Add Vehicle</h3>
      <form onSubmit={handleSubmit}>
        <input name="type" placeholder="Type" value={vehicle.type} onChange={handleChange} />
        <input name="vendorVehicleId" placeholder="ID" value={vehicle.vendorVehicleId} onChange={handleChange} />
        <input name="location" placeholder="Location" value={vehicle.location} onChange={handleChange} />
        <input name="price" placeholder="Price" value={vehicle.price} onChange={handleChange} />
        <input name="billingModel" placeholder="Billing Model" value={vehicle.billingModel} onChange={handleChange} />
        <button type="submit">Add</button>
      </form>
    </div>
  );
}
