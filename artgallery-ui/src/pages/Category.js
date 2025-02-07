import React from "react";
import { Link } from "react-router-dom"; // Importing Link from react-router-dom
import "./categoryStyle.css";
import "./categoryBackground.css";

// Sample data for categories
const categories = [
  { id: 1, name: "Painting", imageUrl: "/images/paint.jpg", link: "/painting" },
  { id: 2, name: "Sculpture", imageUrl: "/images/radhakrishna.jpg", link: "/sculpture" },
  { id: 3, name: "Decor", imageUrl: "/images/Decor.jpg", link: "/decor" },
];

const CategoryCard = ({ category }) => {
  return (
    <div className="category-card">
      <img src={category.imageUrl} alt={category.name} className="category-image" />
      <h3>{category.name}</h3>
      {/* Add a Link here to redirect to the respective category page */}
      <Link to={category.link} className="category-link">
        <button className="category-button">Explore</button>
      </Link>
    </div>
  );
};

const Categories = () => {
  return (
    <div className="categories-container">
      <h2>Shop by Category</h2>
      <div className="categories-list">
        {categories.map((category) => (
          <CategoryCard key={category.id} category={category} />
        ))}
      </div>
    </div>
  );
};

export default Categories;
