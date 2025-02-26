import React, { useState, useEffect } from "react";
import "../styles1.css";

const images = [
  "/images/painting4.jpg",
  "/images/painting5.jpg",
  "/images/painting3.jpeg",
];

const ImageSlider = () => {
  const [currentIndex, setCurrentIndex] = useState(0);

  const nextSlide = () => {
    setCurrentIndex((prevIndex) => (prevIndex + 1) % images.length);
  };

  const prevSlide = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex === 0 ? images.length - 1 : prevIndex - 1
    );
  };

  // Auto-slide every 3 seconds
  useEffect(() => {
    const interval = setInterval(nextSlide, 3000);
    return () => clearInterval(interval); // Cleanup on unmount
  }, []);

  return (
    <div className="slider-container">
      <button className="prev-btn" onClick={prevSlide}>❮</button>
      <img src={images[currentIndex]} alt={`Slide ${currentIndex}`} className="slider-image" />
      <button className="next-btn" onClick={nextSlide}>❯</button>
    </div>
  );
};

export default ImageSlider;