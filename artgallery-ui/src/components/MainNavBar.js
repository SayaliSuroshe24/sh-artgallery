import React from 'react';
import { NavLink } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css'; // Ensure Bootstrap CSS is imported
import './mainNavStyle.css';

// export default function MainNavBar() {
//   return (
//     <nav className="navbar navbar-expand-lg navbar-light bg-light">
//       <NavLink className="navbar-brand" to="/home">Art Gallery</NavLink>
//       <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
//         <span className="navbar-toggler-icon"></span>
//       </button>
//       <div className="collapse navbar-collapse" id="navbarNav">
//         <ul className="navbar-nav mr-auto"> {/* Align items to the left */}
//           <li className="nav-item">
//             <NavLink className="nav-link" to="/home">Home</NavLink>
//           </li>
//           <li className="nav-item">
//             <NavLink className="nav-link" to="/category">Category</NavLink>
//           </li>
//           <li className="nav-item">
//             <NavLink className="nav-link" to="/artists">Artist</NavLink>
//           </li>
//           <li className="nav-item">
//             <NavLink className="nav-link" to="/artworks">Artwork</NavLink>
//           </li>
          
//           <li className="nav-item">
//             <NavLink className="nav-link" to="/aboutus">About Us</NavLink>
//           </li>
//           <li className="nav-item">
//             <NavLink className="nav-link" to="/contactus">Contact Us</NavLink>
//           </li>
//           <li className="nav-item">
//             <NavLink className="nav-link" to="/login">Login</NavLink>
//           </li>
//         </ul>
//       </div>
//     </nav>
//   );
// }
// import React from 'react';
// import { NavLink } from 'react-router-dom';
// import 'bootstrap/dist/css/bootstrap.min.css'; // Ensure Bootstrap CSS is imported
// import './navbarStyle.css'; // Link your custom CSS file here

export default function MainNavBar() {
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
      </div>
    </nav>
  );
}
