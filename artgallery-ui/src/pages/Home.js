import React from 'react';
import ImageSlider from "../components/ImageSlider";
import "../styles1.css"; // Import global CSS


const Home = () => {
  return (
    <div className="container mt-4">
      <h1>Welcome to the Art Gallery</h1>
      <p>Explore by collections, themes, and more. Discover and purchase stunning paintings.</p>
      <ImageSlider />
      <div className="blocks-container">
        <div className="block">
          <h3>Visit Our Gallery in Pune</h3>
          <p>Come and explore the finest collection of art pieces in Pune.</p>
        </div>
        <div className="block">
          <h3>Art Events & Exhibitions</h3>
          <p>Join us for exciting events and exhibitions hosted regularly.</p>
        </div>
        <div className="block">
          <h3>Affordable for All !!</h3>
          <p>Explore art that fits your budget. Everyone deserves a piece of beauty.</p>
        </div>
      </div>
      <div className="gallery-info">
        <div className="info-item">
          <h3>Established</h3>
          <p>Our gallery was established in 2010 with a vision to make art accessible to everyone.</p>
        </div>
        <div className="info-item">
          <h3>Available Arts</h3>
          <p>Explore a diverse collection of contemporary, traditional, and digital artworks from artists around the world.</p>
        </div>
        <div className="info-item">
          <h3>Years of Experience</h3>
          <p>With over 15 years of experience, we bring you the finest collection of exclusive art pieces.</p>
        </div>
        <div className="info-item">
          <h3>Exclusive Art</h3>
          <p>Our collection features exclusive artworks that you won't find anywhere else, bringing you unique pieces from renowned artists.</p>
        </div>
        <div className="info-item">
          <h3>Our Vision</h3>
          <p>Our vision is to create an art community where everyone can appreciate and engage with beautiful, meaningful art.</p>
        </div>
      </div>
      
    </div>
  );
};

export default Home;