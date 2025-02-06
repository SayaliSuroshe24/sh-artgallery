import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useCart } from "./CartContext";

const BillDetailsPage = () => {
  const location = useLocation();
  const { clearCart } = useCart();
  const navigate = useNavigate();
  const items = location.state?.items || [];
  console.log(location.state);
  console.log(items);

  const [paymentMethod, setPaymentMethod] = useState("");

  if (!items.length) {
    return <p>Invalid bill details. Please go back.</p>;
  }

  const totalAmount = items.reduce((sum, item) => sum + item.price, 0);

  const saveOrderDetails = async () => {
    const orderData = {
      custId: 1, // Replace with actual customer ID
      orderDate: new Date().toISOString(),
      totalAmount,
      orderStatus: "Confirmed",
      orderItemDto: items.map(item => ({
        price: item.price,
        artworkId: item.artId
      }))
    };

    try {
      const response = await fetch("http://localhost:8080/api/orders", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(orderData),
      });
      const result = await response.text();
      console.log("Order saved successfully:", result);
      clearCart();
      navigate("/OrderDetailsPage", { state: { items, paymentMethod } });
    } catch (error) {
      console.error("Error saving order:", error);
    }
  };

  const handlePayment = () => {
    if (!paymentMethod) {
      alert("Please select a payment method.");
      return;
    }
    alert(`Payment Successful for selected items via ${paymentMethod}!`);
    saveOrderDetails();
  };

  return (
    <div className="container">
      <h2 className="my-4">Bill Details</h2>
      <div className="card p-4 shadow-sm">
        <h5>Items:</h5>
        <ul>
          {items.map((item, index) => (
            <li key={index}><strong>{item.title}</strong> - ₹{item.price.toFixed(2)}</li>
          ))}
        </ul>
        <p><strong>Total Price:</strong> ₹{totalAmount.toFixed(2)}</p>

        <h5>Select Payment Method:</h5>
        <div className="form-check">
          <input
            type="radio"
            id="creditCard"
            name="payment"
            value="Credit Card"
            className="form-check-input"
            onChange={(e) => setPaymentMethod(e.target.value)}
          />
          <label className="form-check-label" htmlFor="creditCard">
            Credit Card
          </label>
        </div>

        <div className="form-check">
          <input
            type="radio"
            id="upi"
            name="payment"
            value="UPI"
            className="form-check-input"
            onChange={(e) => setPaymentMethod(e.target.value)}
          />
          <label className="form-check-label" htmlFor="upi">
            UPI (Google Pay, PhonePe, Paytm)
          </label>
        </div>

        <div className="form-check">
          <input
            type="radio"
            id="cod"
            name="payment"
            value="Cash on Delivery"
            className="form-check-input"
            onChange={(e) => setPaymentMethod(e.target.value)}
          />
          <label className="form-check-label" htmlFor="cod">
            Cash on Delivery
          </label>
        </div>

        <button className="btn btn-success w-100 mt-3" onClick={handlePayment}>
          Confirm & Pay
        </button>
      </div>
    </div>
  );
};

export default BillDetailsPage;
