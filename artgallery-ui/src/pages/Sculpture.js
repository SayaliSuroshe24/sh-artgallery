import React, { useEffect, useState } from "react";
import axios from "axios";
import { useCart } from "./CartContext";
import { useNavigate } from "react-router-dom";

const SculpturesList = () => {
  const [sculptures, setSculptures] = useState([]);
  const [loading, setLoading] = useState(true);
  const baseUrl = "http://localhost:8080/";
  const { addToCart, isPresentInCart } = useCart();
  const navigate = useNavigate();

  useEffect(() => {
    fetchSculptures();
  }, []);

  const fetchSculptures = async () => {
    try {
      const response = await axios.get(baseUrl + "api/artworks/category/2"); // Assuming category 2 is for sculptures
      setSculptures(response.data);
      setLoading(false);
    } catch (error) {
      console.error("Error fetching sculptures:", error);
      setLoading(false);
    }
  };

  const handleBuyNow = (sculpture) => {
    navigate(`/buy/${sculpture.artId}`, { state: { sculpture } });
  };

  // const handleAddToCart = (sculpture) => {
  //   addToCart(sculpture);
  //   alert(`Added "${sculpture.title}" to cart!`);
  // };

  const handleAddToCart= async (sculpture) => {
    const userId = 1; // Replace with actual user ID from context/authentication
    const quantity = 1; // Default quantity
    if(!isPresentInCart(sculpture)) {
    try {
      await axios.put(
        `http://localhost:8080/api/cart/user/${userId}/artwork/${sculpture.artId}/quantity/${quantity}`
      );
      addToCart(sculpture);
      alert(`Added "${sculpture.title}" to cart!`);
    } catch (error) {
      console.error("Error adding sculpture to cart:", error);
      alert("Failed to add sculpture to cart. Please try again.");
    }
  } else {
    alert(`Item "${sculpture.title}" is already present in cart!`);
  }
  };
  



  if (loading) {
    return <p>Loading sculptures...</p>;
  }

  return (
    <div className="container">
      <h2 className="my-4">Available Sculptures</h2>
      <div className="row">
        {sculptures.map((sculpture) => (
          <div key={sculpture.artId} className="col-md-4 mb-4">
            <div className="card shadow-sm">
              {sculpture.image && (
                <img
                  src={`data:image/png;base64,${sculpture.image}`}
                  alt={sculpture.title}
                  className="card-img-top"
                  style={{ height: "250px", objectFit: "cover" }}
                />
              )}
              <div className="card-body">
                <h5 className="card-title">{sculpture.title}</h5>
                <p className="card-text">
                  <strong>Price:</strong> ₹{sculpture.price.toFixed(2)}
                </p>
                <div className="d-flex justify-content-between">
                  <button
                    className="btn btn-success w-100 me-2"
                    onClick={() => handleBuyNow(sculpture)}
                  >
                    Buy Now
                  </button>
                  <button
                    className="btn btn-warning w-100"
                    onClick={() => handleAddToCart(sculpture)}
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

export default SculpturesList;
