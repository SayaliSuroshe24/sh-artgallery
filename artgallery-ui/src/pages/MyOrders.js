import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

const MyOrders = () => {
  const { userId } = useParams();
  const baseUrl = "http://localhost:8080";
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios.get(baseUrl + `/api/orders/user/${userId}`)
      .then(response => {
        const sortedOrders = response.data.sort((a, b) => new Date(b.orderDate) - new Date(a.orderDate));
        setOrders(sortedOrders);
        setLoading(false);
      })
      .catch(error => {
        setError(error.message);
        setLoading(false);
      });
  }, [userId]);

  if (loading) return <p>Loading orders...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div className="container mt-4">
      <h2>My Orders</h2>
      {orders.length === 0 ? (
        <p>No orders found.</p>
      ) : (
        <div className="list-group">
          {orders.map(order => (
            <div key={order.orderId} className="list-group-item">
              <h5>Order #{order.orderId}</h5>
              <p>Order Date: {new Date(order.orderDate).toLocaleString()}</p>
              <p>Status: {order.orderStatus}</p>
              <p>Total Amount: ${order.totalAmount}</p>
              <h6>Order Items:</h6>
              <ul>
                {order.orderItems.map(item => (
                  <li key={item.orderItemId}>
                    <p><strong>Artwork:</strong> {item.artwork.title} by {item.artwork.artist.name}</p>
                    <p><strong>Category:</strong> {item.artwork.category.categoryName}</p>
                    <p><strong>Price:</strong> ${item.price}</p>
                    <p><strong>Quantity:</strong> {item.quantity}</p>
                  </li>
                ))}
              </ul>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default MyOrders;
