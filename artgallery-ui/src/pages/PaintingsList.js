import React, { useEffect, useState } from "react";
import axios from "axios";
import { useCart } from "./CartContext";
import { useNavigate } from "react-router-dom";

const PaintingsList = () => {
  const [paintings, setPaintings] = useState([]);
  const [loading, setLoading] = useState(true);
  const baseUrl = "http://localhost:8080/";
  const { addToCart } = useCart();
  const navigate = useNavigate();

  useEffect(() => {
    fetchPaintings();
  }, []);

  const fetchPaintings = async () => {
    try {
      const response = await axios.get(baseUrl + "api/artworks/category/1");
      setPaintings(response.data);
      setLoading(false);
    } catch (error) {
      console.error("Error fetching paintings:", error);
      setLoading(false);
    }
  };

  const handleBuyNow = (painting) => {
    navigate(`/buy/${painting.artId}`, { state: { painting } });
  };

  const handleAddToCart = (painting) => {
    addToCart(painting);
    alert(`Added "${painting.title}" to cart!`);
  };

  if (loading) {
    return <p>Loading paintings...</p>;
  }

  return (
    <div className="container">
      <h2 className="my-4">Available Paintings</h2>
      <div className="row">
        {paintings.map((painting) => (
          <div key={painting.artId} className="col-md-4 mb-4">
            <div className="card shadow-sm">
              {painting.image && (
                <img
                  src={`data:image/png;base64,${painting.image}`}
                  alt={painting.title}
                  className="card-img-top"
                  style={{ height: "250px", objectFit: "cover" }}
                />
              )}
              <div className="card-body">
                <h5 className="card-title">{painting.title}</h5>
                <p className="card-text">
                  <strong>Price:</strong> ₹{painting.price.toFixed(2)}
                </p>
                <div className="d-flex justify-content-between">
                  <button
                    className="btn btn-success w-100 me-2"
                    onClick={() => handleBuyNow(painting)}
                  >
                    Buy Now
                  </button>
                  <button
                    className="btn btn-warning w-100"
                    onClick={() => handleAddToCart(painting)}
                  >
                    Add to Cart
                  </button>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default PaintingsList;
