import React, { useEffect,useState } from 'react'
import ArtWorkService from '../Service/ArtWorkService.js';
import {Link,Outlet} from 'react-router-dom'

export default function ArtWorkAdd() {
    
    const[artarr,setartarr]=useState([]);
    //this array for search functionality
    const[searcharr,setsearcharr]=useState([]);
    const[searchtxt,setsearch]=useState("");
    //initialisation useEffect
    useEffect(()=>{
        //initialize emparr
        fetchdata();
       
    },[])


//if emparr change hua to search arr alos update + to update searcharr when searchtxt or emparr changes
        useEffect(()=>{
          if(searchtxt===""){
            setsearcharr([...artarr]);
          }
          else{
            let newarr=artarr.filter(art=>art.title.includes(searchtxt));
            setsearcharr(newarr);
          }
        //add code for searching
        //copy emparr into searcharr
      //  setsearcharr([...emparr])

    },[artarr,searchtxt])

    const fetchdata=()=>{
        ArtWorkService.getAllArtWork()
        .then((response)=>{
            setartarr([...response.data]);
        })
        .catch((err)=>{
            console.log(err);
        })

    
    }

    const changesearchtxt=(event)=>{
            setsearch(event.target.value);
    }
    const deleteArt=(artId)=>{
      ArtWorkService.deleteById(artId)
      .then(()=>{
        fetchdata();
      })
      .catch((err)=>{
         console.log(err);
      });
   }

  return (
    <div>
        <Link to="/form">
         <button type="button" name="add" id="add" value="add" className="btn btn-info">Add New ArtWork</button> 
         &nbsp; &nbsp; &nbsp; &nbsp; </Link>
         Search: <input type="text" name="search" id="search"
         onChange={changesearchtxt}
         value={searchtxt}></input>
        
    <table className="table table-striped">
    <thead>
      <tr>
        <th scope="col">artId</th>
        <th scope="col">Title</th>
        <th scope="col">ArtistId</th>
        <th scope="col">CategoryId</th>
        <th scope="col">Price</th>
        <th scope="col">Availability</th>
        <th scope="col">ImageUrl</th>
        {/* <th scope="col">Department No</th> */}
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
       
        {searcharr.map(art=><tr key={art.artId}>
        <td scope="row">{art.artId}</td>
        <td>{art.Title}</td>
        <td>{art.ArtistId}</td>
        <td>{art.CategoryId}</td>
        <td>{art.Price}</td>
        <td>{art.Availability}</td>
        <td>{art.ImageUrl}</td>
        {/* <td>{emp.DEPTNO}</td> */}
        <td>
        
           <Link to={`/edit/${art.artId}`} state={{editob:art}}>
            <button type="button" name="edit" id="edit" value="edit"  className="btn btn-primary">Edit</button> &nbsp;&nbsp;&nbsp;&nbsp;
            </Link>
                                                            
            <button type="button" name="delete" id="delete" value="delete" className="btn btn-warning" 
            onClick={()=>{deleteArt(art.artId)}}>Delete</button> &nbsp;&nbsp;&nbsp;&nbsp;           
            <Link to={`/artwork/view/${art.artId}`}>
            <button type="button" name="view" id="view" value="view" className="btn btn-danger">View</button>      
            </Link>
        </td>
        
      </tr>)}
      
      
    </tbody>
  </table>
  <div>
  <Outlet></Outlet>
</div>
  </div>
  )
}
