import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Artists = () => {
  const [artists, setArtists] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchArtists = async () => {
      try {
        const response = await axios.get('/api/artists'); // Adjust the API endpoint as needed
        setArtists(response.data);
      } catch (err) {
        setError('Error fetching artists');
      } finally {
        setLoading(false);
      }
    };

    fetchArtists();
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div className="container mt-4">
      <h1>Artists</h1>
      <ul className="list-group">
        {artists.map(artist => (
          <li key={artist.id} className="list-group-item">{artist.name}</li>
        ))}
      </ul>
    </div>
  );
};

export default Artists;