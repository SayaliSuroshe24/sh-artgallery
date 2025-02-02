import React from 'react';
import { NavLink } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'; // Ensure Bootstrap CSS is imported
import './mainNavStyle.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faShoppingCart } from '@fortawesome/free-solid-svg-icons';
import { useCart } from '../pages/CartContext';

export default function MainNavBar() {
  const { cart } = useCart();
  const cartCount = cart.length;

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <NavLink className="navbar-brand" to="/home">Art Gallery</NavLink>
      <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarNav">
        <ul className="navbar-nav mr-auto">
          <li className="nav-item">
            <NavLink className="nav-link" to="/home" activeClassName="active">Home</NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/category" activeClassName="active">Category</NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/artists" activeClassName="active">Artist</NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/artworks" activeClassName="active">Artwork</NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/aboutus" activeClassName="active">About Us</NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/contactus" activeClassName="active">Contact Us</NavLink>
          </li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/login" activeClassName="active">Login</NavLink>
          </li>
        </ul>
        <NavLink to="/cart" className="nav-link cart-icon" style={{ marginLeft: "auto" }}>
          <FontAwesomeIcon icon={faShoppingCart} size="lg" />
          <span className="badge badge-danger ml-1" style={{ color: "black" , fontSize: "1.2rem", padding: "5px 10px"}}>{cartCount}</span>
        </NavLink>
      </div>
    </nav>
  );
}
