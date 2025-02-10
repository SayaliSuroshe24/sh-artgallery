
import React, { useState } from 'react';
import './ContactUs.css'; // Import your CSS file for styling

const ContactUs = () => {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        phone: '',
        message: '',
    });

    const [successMessage, setSuccessMessage] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        // Here you would typically send the form data to your backend
        try {
            // Example API call (replace with your actual API endpoint)
            // await axios.post('/api/contact', formData);
            setSuccessMessage('Your message has been sent successfully!');
            setErrorMessage('');
            setFormData({ name: '', email: '', phone: '', message: '' }); // Reset form
        } catch (error) {
            setErrorMessage('There was an error sending your message. Please try again.');
            setSuccessMessage('');
        }
    };

    return (
        <div className="contact-us-page">
            <div className="contact-us-header1">
                <h1><b>Contact Us</b></h1>
            
            </div>
            <div className="contact-us-header">
      
                <h2>We'd love to hear from you! Please fill out the form below.</h2>
            </div>
            <div className="contact-us-content">
                <div className="contact-info">
                    <h2>Contact Information</h2>
                    <p><strong>Address:</strong> Pune, Maharastra</p>
                    <p><strong>Phone:</strong> +91 9819187697</p>
                    <p><strong>Email:</strong> <a href="mailto:Himanshi@gmail.com">Himanshi@gmail.com</a></p>
                    <p><strong>Gallery Hours:</strong> Monday to Saturday 10:30 am - 8:00 pm, Sunday: By Appointment</p>
                </div>
                <form onSubmit={handleSubmit} className="contact-form">
                    <input
                        type="text"
                        name="name"
                        placeholder="Name*"
                        value={formData.name}
                        onChange={handleChange}
                        required
                    />
                    <input
                        type="email"
                        name="email"
                        placeholder="Email*"
                        value={formData.email}
                        onChange={handleChange}
                        required
                    />
                    <input
                        type="text"
                        name="phone"
                        placeholder="Phone"
                        value={formData.phone}
                        onChange={handleChange}
                    />
                    <textarea
                        name="message"
                        placeholder="Message"
                        value={formData.message}
                        onChange={handleChange}
                        required
                    />
                    <button type="submit">Send Message</button>
                    {successMessage && <p className="success-message">{successMessage}</p>}
                    {errorMessage && <p className="error-message">{errorMessage}</p>}
                </form>
            </div>
            <div className="map-container" >
                <h2><b>Our Location</b></h2>
                <img src="/images/map.png" width="60%" height="50%" alt="Map" className="map-image"  />
            </div>
        </div>
    );
};

export default ContactUs;
