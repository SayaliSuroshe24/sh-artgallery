import React, { useState } from 'react';
import ArtWorkService from '../Service/ArtWorkService';
import { useNavigate } from 'react-router-dom';

export default function ArtWorkFormComponent() {
    const [formdetails, setformdetails] = useState({
        artId: '',
        title: '',
        artistId: '',
        price: '',
        availability: '',
        catId: '',
        image: null // This will hold the file data
    });

    const [formerrors, setformerrors] = useState({
        artId: '',
        title: '',
        artistId: '',
        price: '',
        availability: '',
        catId: '',
        image: ''
    });

    const navigate = useNavigate();

    const submitform = (event) => {
        event.preventDefault(); // Prevent page refresh

        // Validate form details
        if ( formdetails.title === "" || formdetails.artistId === "" || formdetails.price === "" || formdetails.availability === "" || !formdetails.image) {
            alert("Please fill all details, including the image!");
            if (!formdetails.image) {
                setformerrors({ ...formerrors, image: "Please upload an image" });
            }
        } else {
            // Prepare the FormData for image and artwork details
            const formData = new FormData();

            formData.append('image', formdetails.image); // Append the image file

            // Create a Blob from the JSON string
            const jsonBlob = new Blob([JSON.stringify({
                title: formdetails.title,
                artistId: parseInt(formdetails.artistId),
                price: parseFloat(formdetails.price),
                availability: formdetails.availability,
                categoryId: parseInt(formdetails.catId)
            })], { type: "application/json" });


            formData.append("artworkDto", jsonBlob, "artworkDto.json");

            // Call the service to add artwork
            ArtWorkService.addArtWork(formData)
                .then((result) => {
                    console.log(result);
                    navigate("/artworks"); // Redirect to artwork list after submission
                })
                .catch((err) => {
                    console.log(err);
                });
        }
    };

    const handlechange = (event) => {
        let name = event.target.name;
        let value = event.target.value;

        // Handle image file change separately
        if (name === "image") {
            setformdetails({ ...formdetails, image: event.target.files[0] }); // Get the selected file
        } else {
            setformdetails({ ...formdetails, [name]: value });
        }
    };

    return (
        <div>
            <form onSubmit={submitform}>

                <div className="form-group">
                    <label htmlFor="title">ArtWork Title</label>
                    <input type="text" className="form-control" id="title" name='title'
                        onChange={handlechange}
                        value={formdetails.title} />
                </div>

                <div className="form-group">
                    <label htmlFor="artistId">ArtistId</label>
                    <input type="text" className="form-control" id="artistId" name='artistId'
                        onChange={handlechange}
                        value={formdetails.artistId} />
                </div>

                <div className="form-group">
                    <label htmlFor="price">Price</label>
                    <input type="text" className="form-control" id="price" name='price'
                        onChange={handlechange}
                        value={formdetails.price} />
                </div>

                <div className="form-group">
                    <label htmlFor="availability">Availability</label>
                    <input type="text" className="form-control" id="availability" name='availability'
                        onChange={handlechange}
                        value={formdetails.availability} />
                </div>

                <div className="form-group">
                    <label htmlFor="catId">Category Id</label>
                    <input type="text" className="form-control" id="catId" name='catId'
                        onChange={handlechange}
                        value={formdetails.catId} />
                </div>

                <div className="form-group">
                    <label htmlFor="image">Image</label>
                    <input type="file" className="form-control" id="image" name='image' accept="image/*"
                        onChange={handlechange} />
                    <p>{formerrors.image}</p>
                </div>

                <button type="submit" className="btn btn-primary">Submit</button>
            </form>
        </div>
    );
}
