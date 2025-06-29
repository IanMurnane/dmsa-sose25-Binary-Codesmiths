import React, { useEffect, useState } from 'react';
import { getUserProfile } from '../api/userApi';

export default function Profile() {
  const [provider, setProvider] = useState(null);

  useEffect(() => {
    getUserProfile()
      .then(res => setProvider(res.data))
      .catch(err => console.error('Error fetching provider profile:', err));
  }, []);

  if (!provider) return <p>Loading profile...</p>;

  return (
    <div>
      <h2>Provider Profile</h2>
      <p><strong>ID:</strong> {provider.id}</p>
      <p><strong>Name:</strong> {provider.name}</p>
      <p><strong>Email:</strong> {provider.email}</p>
      <p><strong>Role:</strong> {provider.role}</p>
    </div>
  );
}
