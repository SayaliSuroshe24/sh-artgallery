import React from "react";
import "./style.css"; // Import the CSS file

const MovingImages = () => {
  return (
    <div className="container text-center mt-5">
      <h1>Animated Images with CSS</h1>
      <div className="d-flex justify-content-center align-items-center">
        <img src="./images/painting1.jpg" alt="Move" className="moving-image mx-3" />
        <img src="/images/painting1.jpg" alt="Move" className="moving-image mx-3" />
        <img src="https://via.placeholder.com/150" alt="Bounce" className="bouncing-image mx-3" />
        <img src="https://via.placeholder.com/150" alt="Rotate" className="rotating-image mx-3" />
      </div>
    </div>
  );
};

export default MovingImages;
