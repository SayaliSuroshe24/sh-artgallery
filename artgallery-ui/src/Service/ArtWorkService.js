import axios from 'axios'
const baseUrl="http://localhost:8080/"
//"/api/artworks"
class ArtWorkService{
    getAllArtWork(){

        return axios.get(baseUrl+"api/artworks");
    }


   

    



   
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