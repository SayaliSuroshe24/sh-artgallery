import React, { useEffect, useState } from 'react';
import ArtWorkService from '../Service/ArtWorkService.js';
import { Link, Outlet } from 'react-router-dom';

export default function ArtWorkAdd() {
  // State management
  const [artworks, setArtworks] = useState([]);
  const [filteredArtworks, setFilteredArtworks] = useState([]);
  const [searchText, setSearchText] = useState("");

  // Fetch data when component mounts
  useEffect(() => {
    fetchData();
  }, []);

  // Update filtered artworks whenever artworks or search text changes
  useEffect(() => {
    if (searchText === "") {
      setFilteredArtworks([...artworks]);
    } else {
      const newFilteredArtworks = artworks.filter(art =>
        art.title.toLowerCase().includes(searchText.toLowerCase())
      );
      setFilteredArtworks(newFilteredArtworks);
    }
  }, [artworks, searchText]);

  // Fetch artworks from API
  const fetchData = () => {
    ArtWorkService.getAllArtWork()
      .then((response) => {
        setArtworks(response.data);
      })
      .catch((err) => {
        console.error("Error fetching artworks:", err);
      });
  };

  // Handle search text change
  const handleSearchChange = (event) => {
    setSearchText(event.target.value);
  };

  // Handle artwork deletion
  const deleteArt = (artId) => {
    const confirmDelete = window.confirm("Are you sure you want to delete this artwork?");
    if (confirmDelete) {
      ArtWorkService.deleteById(artId)
        .then(() => {
          fetchData(); // Refresh the data after deleting
        })
        .catch((err) => {
          console.error("Error deleting artwork:", err);
        });
    }
  };

  return (
    <div>
      <Link to="/form">
        <button type="button" name="add" id="add" className="btn btn-info">
          Add New ArtWork
        </button>
      </Link>
      &nbsp; &nbsp; &nbsp; &nbsp;
      <label>
        Search:{" "}
        <input
          type="text"
          name="search"
          id="search"
          onChange={handleSearchChange}
          value={searchText}
        />
      </label>

      <table className="table table-striped">
        <thead>
          <tr>
            <th scope="col">ArtId</th>
            <th scope="col">Title</th>
            <th scope="col">ArtistId</th>
            <th scope="col">CategoryId</th>
            <th scope="col">Price</th>
            <th scope="col">Availability</th>
            <th scope="col">Image</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filteredArtworks.map((art) => (
            <tr key={art.artId}>
              <td scope="row">{art.artId}</td>
              <td>{art.title}</td>
              <td>{art.artistId}</td>
              <td>{art.categoryId}</td>
              <td>{art.price}</td>
              <td>{art.availability.toString()}</td>
              {/* <td>{art.image}</td> */}
              <td>
                <img src={`data:image/png;base64,${art.image}`} alt="Byte Image" width="100" height="100" /></td>
              <td>
                <Link to={`/edit/${art.artId}`} state={{ editob: art }}>
                  <button type="button" name="edit" className="btn btn-primary">
                    Edit
                  </button>
                </Link>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <button
                  type="button"
                  name="delete"
                  className="btn btn-warning"
                  onClick={() => deleteArt(art.artId)}
                >
                  Delete
                </button>
                &nbsp;&nbsp;&nbsp;&nbsp;
               
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <div>
        <Outlet></Outlet>
      </div>
    </div>
  );
}
