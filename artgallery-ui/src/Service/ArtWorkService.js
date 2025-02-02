import axios from 'axios'
const baseUrl="http://localhost:8080/"
//"/api/artworks"
class ArtWorkService{
    getAllArtWork(){

        return axios.get(baseUrl+"api/artworks");
    }


    // addArtWork(art){
    //    // let myheaders={'content-Type':'application/json'}
    //     let myheaders={'content-Type':'application/json'}
    //     return axios.post(baseUrl+"api/artworks/"+art.artId,art,{headers:myheaders});
    // }


    //  addArtWork = (art, file) => {
    //     // Create a new FormData object to handle file and form data
    //     const formData = new FormData();
    
    //     // Append artwork details to formData (assuming art is an object)
    //     formData.append("artworkDTO", JSON.stringify(art)); // Convert JSON to string for artwork details
    //     formData.append("image", file); // Append the image file
    
    //     // Send the POST request with formData
    //     return axios.post(baseUrl+"api/artworks/upload", formData, {
    //         headers: {
    //             'Content-Type': 'multipart/form-data' // Don't need to specify this manually if using FormData
    //         }
    //     })
    //     .then(response => {
    //         console.log("Artwork added successfully:", response.data);
    //     })
    //     .catch(error => {
    //         console.error("Error adding artwork:", error);
    //     });
    // };




    // addArtWork = (art, file) => {
    //     // Create a new FormData object to handle file and form data
    //     const formData = new FormData();
        
    //     // Append artwork details as form data (key-value pairs)
    //     formData.append("artId", art.artId);
    //     formData.append("title", art.title);
    //     formData.append("artistId", art.artistId);
    //     formData.append("price", art.price);
    //     formData.append("availability", art.availability);
    //     formData.append("catId", art.catId);
    
    //     // Append the image file
    //     formData.append("image", file);
    
    //     // Send the POST request with formData
    //     return axios.post(baseUrl + "api/artworks/upload", formData, {
    //         headers: {
    //             'Content-Type': 'multipart/form-data' // Automatically handled by FormData
    //         }
    //     })
    //     .then(response => {
    //         console.log("Artwork added successfully:", response.data);
    //     })
    //     .catch(error => {
    //         console.error("Error adding artwork:", error);
    //     });
    // };

    addArtWork = (formData) => {
        // Send the POST request with formData
        console.log(formData.get('artworkDto'));
        console.log(formData.get('image'));
        return axios.post(baseUrl + "api/artworks/upload", formData, {
            headers: {
                'Content-Type': 'multipart/form-data' // Automatically handled by FormData
            }
        })
        .then(response => {
            console.log("Artwork added successfully:", response.data);
        })
        .catch(error => {
            console.error("Error adding artwork:", error);
        });
    };









   
    getById(artId){
        return axios.get(baseUrl+"api/artworks/"+artId)
    }
    
    updateArtWork(artId, formData){

        // Send the PUT request with formData
        console.log(artId);
        console.log(formData.get('artworkDto'));
        console.log(typeof(formData.get('image')));
        return axios.put(baseUrl+"api/artworks/"+artId, formData, {
            headers: {
                'Content-Type': 'multipart/form-data' // Automatically handled by FormData
            }
        })
        .then(response => {
            console.log("Artwork edited successfully:", response.data);
        })
        .catch(error => {
            console.error("Error editing artwork:", error);
        });
        //let myheaders={'content-Type':'application/json'}
        //return axios.put(baseUrl+"api/artworks/"+JSON.parse(art.get('artworkDTO')).artId,art.get('artworkDTO'),{headers:myheaders})
    }
    deleteById(artId){
        //let myheaders={'content-Type':'application/json'}
        return axios.delete(baseUrl+"api/artworks/"+artId)
    }









}
export default new ArtWorkService();