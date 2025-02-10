
import React from "react";
import "./AboutUs.css"; // Import CSS file

const AboutUs = () => {
  return (
    <div className="about-us">
      {/* About Us Section */}
      <section className="about-section">
        <div className="about-text">
          <h1>About Us</h1>
          <p>
            Welcome to our platform! We are dedicated to providing the best services 
            with a focus on quality, customer satisfaction, and innovation. 
            Our mission is to empower users with seamless experiences.
          </p>
          <h1>Our Mission</h1>
    <p>
        Our mission is to bridge the gap between art and the audience, creating 
        a space where ideas, emotions, and stories are shared through visual masterpieces. We strive 
        to support artists by providing them with a platform to express their vision and connect with 
        art lovers worldwide.
    </p>

    <h1>Our Vision</h1>
    <p>
        We envision a world where art is accessible to all, inspiring creativity and cultural exchange. 
        Our goal is to become a globally recognized gallery that fosters talent and appreciation for 
        diverse artistic forms.
    </p>
        </div>
        <div className="about-image">
          <img src="/images/aboutus.jpg" alt="About Us" />
        </div>
      </section>

      {/* Image Gallery */}
      <section className="gallery">
        <img src="/images/aboutus1.jpg" alt="Gallery" className="full-width" />
        <div className="image-row">
          <img src="/images/aboutus2.jpg" alt="Gallery Small 1" />
          <img src="/images/aboutus3.jpg" alt="Gallery Small 2" />
        </div>
      </section>

      {/* Our Team Section */}
      <section className="team">
        <h2>Our Team</h2>
        <div className="team-container">
          <div className="team-member">
            <img src="/images/himanshi1.jpg" alt="Team Member" />
            <h3>Himanshi Sahu</h3>
            <p>Admin</p>
          </div>
          <div className="team-member">
            <img src="/images/sayali.jpg" alt="Team Member" />
            <h3>Sayali Suroshe</h3>
            <p>Admin</p>
          </div>
        </div>
      </section>

      {/* Highlighted Features */}
      <section className="features">
        <h2>Highlighted Features</h2>
        <div className="feature-container">
          <div className="feature-box">✔ Fast & Reliable</div>
          <div className="feature-box">✔ User-Friendly Design</div>
          <div className="feature-box">✔ 24/7 Customer Support</div>
          <div className="feature-box">✔ Secure & Trusted</div>
        </div>
      </section>
    </div>
  );
};

export default AboutUs;
