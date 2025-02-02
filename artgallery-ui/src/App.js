import React from 'react';
import { BrowserRouter as Router, Route, Routes ,Switch} from 'react-router-dom';
import MainNavBar from './components/MainNavBar';
import Home from './pages/Home';
import Categories from './pages/Categories';
import Artists from './pages/Artists';
import AboutUs from './pages/AboutUs';
import ContactUs from './pages/ContactUs';
import Login from './pages/Login';
import Payment from './pages/Payment';
import Header from './components/Header';
import Footer from './components/Footer';
import ArtWorkGet from './pages/ArtWorkGet';
import ArtWorkFormComponent1 from './pages/ArtWorkFormComponent1';
import ArtWorkEditFormComponent from './pages/ArtWorkEditFormComponent';
import Category from './pages/Category'; // Adjust path as needed
import PaintingPage from './pages/PaintingPage'; // Create your Painting Page component
import Sculpture from './pages/Sculpture'; 
import Decor from './pages/Decor'; // Similarly for Decor

//import MovingImages from "./components/Mo
// vingImages"; // Import the MovingImages component
// import "./components/style.css"; // Import global CSS
import ImageSlider from "./components/ImageSlider";
import "./styles1.css"; // Import global CSS
import PaintingsList from './pages/PaintingsList';
import CartPage from './pages/CartPage';
import BuyNowPage from './pages/BuyNowPage';
import { CartProvider } from './pages/CartContext';


function App() {
  return (
    <div>
      <CartProvider>
    <Router>
     <Header></Header>
      <MainNavBar />
      {/* //<MovingImages /> */}
      {/* <ImageSlider /> */}
      <Routes>
        <Route path="/home" element={<Home />} />
        
        {/* <Route exact path="/" component={Category} />
        <Route path="/painting" component={PaintingPage} />
        <Route path="/sculpture" component={Sculpture} />
        <Route path="/decor" component={Decor} /> */}
        {/* Define other routes as needed */}
        <Route path="/" element={<Category />} />
        <Route path="/painting" element={<PaintingsList />} />
        <Route path="/cart" element={<CartPage />} />
        <Route path="/buy/:id" element={<BuyNowPage />} />
        <Route path="/sculpture" element={<Sculpture />} />
        <Route path="/decor" element={<Decor />} />


        {/* <Route path="/categories" element={<Categories />} /> */}
        <Route path="/artists" element={<Artists />} />
        {/* <Route path="/products" element={<ProductTabComponent></ProductTabComponent> }>
            <Route path="view/:prodid" element={<ViewProductDetails></ViewProductDetails>}></Route>
        </Route> */}
        <Route path="/form" element={<ArtWorkFormComponent1></ArtWorkFormComponent1> }></Route>
        <Route path="/edit/:artId" element={<ArtWorkEditFormComponent></ArtWorkEditFormComponent> }></Route>
        {/* <Route path="/edit/:id" element={<ProductEditForm></ProductEditForm> }></Route> */}
        
        <Route path="/artworks" element={<ArtWorkGet></ArtWorkGet>} />
        <Route path="/category" element={<Category/>} />

        <Route path="/aboutus" element={<AboutUs />} />
        <Route path="/contactus" element={<ContactUs />} />
        <Route path="/login" element={<Login />} />
        <Route path="/payment" element={<Payment />} />
      </Routes>
    </Router>
    </CartProvider>
    <Footer></Footer>
    
    </div>
  );
}

export default App;