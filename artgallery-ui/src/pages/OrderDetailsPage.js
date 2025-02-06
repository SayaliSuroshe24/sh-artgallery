import React from "react";
import { useLocation, useNavigate } from "react-router-dom";

const OrderDetailsPage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { items, paymentMethod } = location.state || {};

  if (!items || items.length === 0 || !paymentMethod) {
    return <p>Invalid order details. Please go back.</p>;
  }

  return (
    <div className="container">
      <h2 className="my-4">Order Confirmation</h2>
      <div className="card p-4 shadow-sm">
        {items.map((item, index) => (
          <div key={index} className="mb-3">
            <p><strong>Item:</strong> {item.title}</p>
            <p><strong>Price:</strong> ₹{item.price.toFixed(2)}</p>
          </div>
        ))}
        <p><strong>Payment Method:</strong> {paymentMethod}</p>
        <p><strong>Status:</strong> Order Confirmed</p>
        <button className="btn btn-primary w-100 mt-3" onClick={() => navigate("/")}>
          Back to Home
        </button>
      </div>
    </div>
  );
};

export default OrderDetailsPage;
