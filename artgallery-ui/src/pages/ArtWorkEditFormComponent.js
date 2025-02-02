import React, { useState,useEffect } from 'react';
import ArtWorkService from '../Service/ArtWorkService';
import {useLocation, useNavigate } from 'react-router-dom';


export default function ArtWorkFormComponent() {
    const [formdetails, setformdetails] = useState({
        artId: '',
        title: '',
        artistId: '',
        price: '',
        availability: '',
        categoryId: '',
        image: null // This will hold the file data
    });

    const [formerrors, setformerrors] = useState({
        artId: '',
        title: '',
        artistId: '',
        price: '',
        availability: '',
        categoryId: '',
        image: ''
    });

    const navigate = useNavigate();
    const location=useLocation();
    
    const updateArtWork = () => {
        

        // Validate form details
        if ( formdetails.title === "" || formdetails.artistId === "" || formdetails.price === "" || formdetails.availability === "" || !formdetails.image) {
            alert("Please fill all details, including the image!");
            if (!formdetails.image) {
                setformerrors({ ...formerrors, image: "Please upload an image" });
            }
            if (formdetails.artId === "" || formdetails.artId.trim().length > 0) {
                setformerrors({ ...formerrors, artId: "Please fill artId" });
            }
        } else {
            // Prepare the FormData for image and artwork details
            const formData = new FormData();
            // formData.append('artworkDTO', JSON.stringify({
            //     artId: parseInt(formdetails.artId),
            //     title: formdetails.title,
            //     artistId: parseInt(formdetails.artistId),
            //     price: parseFloat(formdetails.price),
            //     availability: formdetails.availability,
            //     categoryId: parseInt(formdetails.categoryId)
            // }));
            // formData.append('image', formdetails.image); // Append the image file

            if(typeof(formdetails.image) == 'string'){
                // Convert Base64 to Blob
                const byteCharacters = atob(formdetails.image);
                const byteNumbers = new Array(byteCharacters.length).fill(null).map((_, i) => byteCharacters.charCodeAt(i));
                const byteArray = new Uint8Array(byteNumbers);
                const imageBlob = new Blob([byteArray], { type: "image/png" });

                // Convert Blob to File
                formdetails.image = new File([imageBlob], "image.png", { type: "image/png" });
            }

            formData.append('image', formdetails.image); // Append the image file

            // Create a Blob from the JSON string
            const jsonBlob = new Blob([JSON.stringify({
                artId: parseInt(formdetails.artId),
                title: formdetails.title,
                artistId: parseInt(formdetails.artistId),
                price: parseFloat(formdetails.price),
                availability: formdetails.availability,
                categoryId: parseInt(formdetails.categoryId)
            })], { type: "application/json" });


            formData.append("artworkDto", jsonBlob, "artworkDto.json");

            // Call the service to add artwork
            ArtWorkService.updateArtWork(formdetails.artId, formData)
                .then((result) => {
                    console.log(result);
                    navigate("/artworks"); // Redirect to artwork list after submission
                })
                .catch((err) => {
                    console.log(err);
                });
        }
    };

    useEffect(()=>{
        setformdetails({...location.state.editob})
    },[])

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
    useEffect(()=>{
        setformdetails({...location.state.editob})
    },[])

    return (
        <div>
            <form>
                <div className="form-group">
                    <label htmlFor="artId">ArtWork Id</label>
                    <input type="text" className="form-control" id="artId" name='artId'
                        onChange={handlechange}
                        value={formdetails.artId} readOnly />
                    <p>{formerrors.artId}</p>
                </div>

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
                    <label htmlFor="categoryId">Category Id</label>
                    <input type="text" className="form-control" id="categoryId" name='categoryId'
                        onChange={handlechange}
                        value={formdetails.categoryId} />
                </div>

                <div className="form-group">
                    <label htmlFor="image">Image</label>
                    <br/>
                    <img src={`data:image/png;base64,${formdetails.image}`} alt="Byte Image" width="100" height="100" />
                    <input type="file" className="form-control" id="image" name='image' accept="image/*"
                        onChange={handlechange} />
                    <p>{formerrors.image}</p>
                </div>
                <button type="button" className="btn btn-primary" onClick={updateArtWork}>update Artwork</button>
            </form>
        </div>
    );
}
