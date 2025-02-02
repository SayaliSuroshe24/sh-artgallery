import React from "react";
import { useLocation, useNavigate } from "react-router-dom";

const BuyNowPage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const painting = location.state?.painting;

  if (!painting) {
    return <p>Invalid selection. Go back to paintings.</p>;
  }

  const handleCheckout = () => {
    alert(`Order placed for "${painting.title}"!`);
    navigate("/");
  };

  return (
    <div className="container">
      <h2 className="my-4">Buy Now</h2>
      <div className="card">
        <img
          src={`data:image/png;base64,${painting.image}`}
          alt={painting.title}
          className="card-img-top"
          style={{ height: "300px", objectFit: "cover" }}
        />
        <div className="card-body">
          <h5 className="card-title">{painting.title}</h5>
          <p className="card-text">
            <strong>Price:</strong> ₹{painting.price.toFixed(2)}
          </p>
          <button className="btn btn-success" onClick={handleCheckout}>
            Confirm & Pay
          </button>
        </div>
      </div>
    </div>
  );
};

export default BuyNowPage;
