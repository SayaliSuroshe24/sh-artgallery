import React, { useEffect, useState } from "react";
import axios from "axios";
import { useCart } from "./CartContext";
import { useNavigate } from "react-router-dom";

const DecorList = () => {
  const [decorItems, setDecorItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const baseUrl = "http://localhost:8080/";
  const { addToCart, isPresentInCart } = useCart();
  const navigate = useNavigate();

  useEffect(() => {
    fetchDecorItems();
  }, []);

  const fetchDecorItems = async () => {
    try {
      const response = await axios.get(baseUrl + "api/artworks/category/3"); // Assuming category 3 is for decor
      setDecorItems(response.data);
      setLoading(false);
    } catch (error) {
      console.error("Error fetching decor items:", error);
      setLoading(false);
    }
  };

  const handleBuyNow = (decor) => {
    navigate(`/buy/${decor.artId}`, { state: { decor } });
  };

  // const handleAddToCart = (decor) => {
  //   addToCart(decor);
  //   alert(`Added "${decor.title}" to cart!`);
  // };

  const handleAddToCart = async (decor) => {
    const userId = 1; // Replace with actual user ID from context/authentication
    const quantity = 1; // Default quantity
    if(!isPresentInCart(decor)) {
    try {
      await axios.put(
        `http://localhost:8080/api/cart/user/${userId}/artwork/${decor.artId}/quantity/${quantity}`
      );
      addToCart(decor);
      alert(`Added "${decor.title}" to cart!`);
    } catch (error) {
      console.error("Error adding decor to cart:", error);
      alert("Failed to add decor to cart. Please try again.");
    }
  } else {
    alert(`Item "${decor.title}" is already present in cart!`);
  }
  };
  

  if (loading) {
    return <p>Loading decor items...</p>;
  }

  return (
    <div className="container">
      <h2 className="my-4">Available Decor Items</h2>
      <div className="row">
        {decorItems.map((decor) => (
          <div key={decor.artId} className="col-md-4 mb-4">
            <div className="card shadow-sm">
              {decor.image && (
                <img
                  src={`data:image/png;base64,${decor.image}`}
                  alt={decor.title}
                  className="card-img-top"
                  style={{ height: "250px", objectFit: "cover" }}
                />
              )}
              <div className="card-body">
                <h5 className="card-title">{decor.title}</h5>
                <p className="card-text">
                  <strong>Price:</strong> ₹{decor.price.toFixed(2)}
                </p>
                <div className="d-flex justify-content-between">
                  <button
                    className="btn btn-success w-100 me-2"
                    onClick={() => handleBuyNow(decor)}
                  >
                    Buy Now
                  </button>
                  <button
                    className="btn btn-warning w-100"
                    onClick={() => handleAddToCart(decor)}
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

export default DecorList;
