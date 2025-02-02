import React from "react";
import { useCart } from "./CartContext";
import { useNavigate } from "react-router-dom";

const CartPage = () => {
  const { cart, removeFromCart, clearCart } = useCart();
  const navigate = useNavigate();

  const handleCheckout = () => {
    alert("Proceeding to checkout!");
    clearCart();
    navigate("/");
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
                      className="btn btn-danger"
                      onClick={() => removeFromCart(item.artId)}
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
