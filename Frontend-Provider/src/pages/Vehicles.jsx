import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function Vehicles() {
  const [vehicles, setVehicles] = useState([]);
  const [vehicle, setVehicle] = useState({ type: '', model: '', price: '', billingModel: '' });

  useEffect(() => {
    axios.get('http://localhost:8083/vehicles')
      .then(res => setVehicles(res.data))
      .catch(err => console.error(err));
  }, []);

  const handleChange = e => {
    const { name, value } = e.target;
    setVehicle(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = e => {
    e.preventDefault();
    axios.post('http://localhost:8083/vehicles', vehicle)
      .then(() => {
        alert('Vehicle added!');
        setVehicle({ type: '', model: '', price: '', billingModel: '' });
        return axios.get('http://localhost:8083/vehicles');
      })
      .then(res => setVehicles(res.data))
      .catch(err => console.error(err));
  };

  return (
    <div>
      <h2>Your Vehicles</h2>
      <ul>
        {vehicles.map(vehicle => (
          <li key={vehicle.id}>
            {vehicle.type} - {vehicle.model} - {vehicle.status}
          </li>
        ))}
      </ul>

      <h2>Add New Vehicle</h2>
      <form onSubmit={handleSubmit}>
        <input name="type" placeholder="Type" value={vehicle.type} onChange={handleChange} />
        <input name="model" placeholder="Model" value={vehicle.model} onChange={handleChange} />
        <input name="price" placeholder="Price" value={vehicle.price} onChange={handleChange} />
        <input name="billingModel" placeholder="Billing Model" value={vehicle.billingModel} onChange={handleChange} />
        <button type="submit">Add Vehicle</button>
      </form>
    </div>
  );
}