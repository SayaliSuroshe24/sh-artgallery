import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

const BillDetailsPage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const item = location.state?.item;
  console.log(location.state);
  console.log(item);

  const [paymentMethod, setPaymentMethod] = useState("");

  if (!item) {
    return <p>Invalid bill details. Please go back.</p>;
  }

  const handlePayment = () => {
    if (!paymentMethod) {
      alert("Please select a payment method.");
      return;
    }

    alert(`Payment Successful for "${item.title}" via ${paymentMethod}!`);
    navigate("/");
  };

  return (
    <div className="container">
      <h2 className="my-4">Bill Details</h2>
      <div className="card p-4 shadow-sm">
        <p><strong>Item:</strong> {item.title}</p>
        <p><strong>Price:</strong> ₹{item.price.toFixed(2)}</p>

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
