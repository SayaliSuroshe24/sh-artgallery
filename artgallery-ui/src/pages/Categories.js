import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Categories = () => {
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await axios.get('/api/categories'); // Adjust the API endpoint as needed
        setCategories(response.data);
      } catch (err) {
        setError('Error fetching categories');
      } finally {
        setLoading(false);
      }
    };

    fetchCategories();
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div className="container mt-4">
      <h1>Categories</h1>
      <ul className="list-group">
        {categories.map(category => (
          <li key={category.id} className="list-group-item">{category.name}</li>
        ))}
      </ul>
    </div>
  );
};

export default Categories;