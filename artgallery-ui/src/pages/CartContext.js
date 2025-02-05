// import React, { createContext, useContext, useState } from "react";

// const CartContext = createContext();

// export const CartProvider = ({ children }) => {
//   const [cart, setCart] = useState([]);


//   const addToCart = (painting) => {
//     setCart((prevCart) => [...prevCart, painting]);
//   };

//   const removeFromCart = (artId) => {
//     setCart((prevCart) => prevCart.filter((item) => item.artId !== artId));
//   };

//   const clearCart = () => {
//     setCart([]);
//   };

//   return (
//     <CartContext.Provider value={{ cart, addToCart, removeFromCart, clearCart }}>
//       {children}
//     </CartContext.Provider>
//   );
// };

// export const useCart = () => useContext(CartContext);


import React, { createContext, useContext, useEffect, useState } from "react";
import axios from "axios";

const CartContext = createContext();

export const CartProvider = ({ children }) => {
  const [cart, setCart] = useState([]);
  const baseUrl = "http://localhost:8080/"; // Backend base URL
  const userId = 1; // Replace with actual user ID from authentication

  // Fetch cart items on load
  useEffect(() => {
    console.log("useEffect called");
    const fetchCartItems = async () => {
      try {
        const response = await axios.get(`${baseUrl}api/cart/user/1`);
        console.log(response.data);
        console.log(response.data.map(item => (item.artwork)));
        setCart(response.data.map(item => (item.artwork))); // Set cart with fetched items
      } catch (error) {
        console.error("Error fetching cart items:", error);
      }
    };

    fetchCartItems();
  }, []);

  const isPresentInCart = (painting) => {
    const index = cart.findIndex(item => item.artId == painting.artId);
    return index != -1;
  };

  const addToCart = (painting) => {
    console.log(painting);
    setCart((prevCart) => [...prevCart, painting]);
  };

  const removeFirstMatch = (arr, value) => {
    const index = arr.findIndex(item => item.artId == value);
    if (index !== -1) {
      arr.splice(index, 1); // Mutates the original array
    }
    return [...arr]; // Return a new array to avoid mutation in React state
  };

  const removeFromCart = (artId) => {
    setCart(removeFirstMatch(cart, artId));
  };

  const clearCart = () => {
    setCart([]);
  };

  return (
    <CartContext.Provider value={{ cart, addToCart, removeFromCart, clearCart, isPresentInCart }}>
      {children}
    </CartContext.Provider>
  );
};

export const useCart = () => useContext(CartContext);
