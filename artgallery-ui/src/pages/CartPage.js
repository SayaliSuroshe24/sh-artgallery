import React, { useState } from "react";
import axios from "axios";
import { useCart } from "./CartContext";
import { useNavigate } from "react-router-dom";

const CartPage = () => {
  const { cart,removeFromCart,clearCart } = useCart();   //removefromcart
  const navigate = useNavigate();
  const [userId] = useState(1); // Replace with actual user ID from context or authentication

  const updateCartQuantity = async (artworkId, quantity) => {
    try {
      const response = await axios.put(
        `http://localhost:8080/api/cart/user/${userId}/artwork/${artworkId}/quantity/1`
      );
      console.log("Cart updated:", response.data);
    } catch (error) {
      console.error("Error updating cart:", error);
    }
  };


  const removeFromCartAPI = async (artworkId) => {
    const userId = 1; // Replace with actual user ID from context/authentication
  
    try {
      const response = await axios.delete(
        `http://localhost:8080/api/cart/user/${userId}/artwork/${artworkId}`
      );
      console.log("Item removed from cart:", response.data);
      
      // Optionally, update the cart state after successful removal
      removeFromCart(artworkId); // Assuming response.data contains the updated cart list
  
      //alert("Item removed from cart!");
    } catch (error) {
      console.error("Error removing item from cart:", error);
      alert("Failed to remove item from cart. Please try again.");
    }
  };
  

  
  const handleCheckout = () => {
    alert("Proceeding to checkout!");
    navigate("/BillDetailsPage", { state: { items: cart, isCart: true }  });
  };

  return (
    <div className="container">
      <h2 className="my-4">Your Cart</h2>
      {cart.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <>
          {cart.map((item) => (
            <div key={item.artId} className="card mb-3">
              <div className="row g-0">
                <div className="col-md-4">
                  <img
                    src={`data:image/png;base64,${item.image}`}
                    alt={item.title}
                    className="img-fluid"
                  />
                </div>
                <div className="col-md-8">
                  <div className="card-body">
                    <h5 className="card-title">{item.title}</h5>
                    <p className="card-text">Price: ₹{item.price.toFixed(2)}</p>

                    <button
                      className="btn btn-danger mt-2"
                      onClick={() => removeFromCartAPI(item.artId)}
                    >
                      Remove
                    </button>
                  </div>
                </div>
              </div>
            </div>
          ))}
          <button className="btn btn-primary mt-3" onClick={handleCheckout}>
            Proceed to Checkout
          </button>
        </>
      )}
    </div>
  );
};

export default CartPage;
