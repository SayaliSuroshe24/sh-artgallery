import React,{useState} from 'react'
import ArtWorkService from '../Service/ArtWorkService'
import {useNavigate} from 'react-router-dom'
export default function ArtWorkFormComponent() {
    const [formdetails,setformdetails]=useState({artId:'',title:'',artistId:'',price:'',availability:'',image:'',catId:''})
    const navigate=useNavigate();
    const [formerrors,setformerrors]=useState({artId:'',title:'',artistId:'',price:'',availability:'',image:'',catId:''})
    const submitform=(event)=>{
        event.preventDefault(); // stop refresh page action of submit button
        if(formdetails.artId==="" || formdetails.title==="" || formdetails.artistId==="" || formdetails.price==="" || formdetails.availability===""){
           alert("pls fill all details")
           if(formdetails.artId==="" || formdetails.artId.trim().length>0){
            setformerrors({...formerrors,artId:"pls fill artId"})
           }
        }else{
            let art={artId:parseInt(formdetails.artId),title:formdetails.title,
                artistId:parseInt(formdetails.artistId),
                price:parseFloat(formdetails.price),
                availability:(formdetails.availability),catId:parseInt(formdetails.catId)}
            ArtWorkService.addArtWork(art)
            .then((result)=>{
                console.log(result)
                navigate("/artwork")
            }).catch((err)=>{
                console.log(err)
            });
            //change the url to products
           
        }
        
        

    }
    const handlechange=(event)=>{
        let name=event.target.name;
        let value=event.target.value;
        setformdetails({...formdetails,[name]:value})

    }
    /*const changepid=(event)=>{
        setformdetails({...formdetails,pid:event.target.value})
    }
    const changepname=(event)=>{
        setformdetails({...formdetails,pname:event.target.pname})
    }*/
  return (
    <div>
        <form onSubmit={submitform}>
            <div className="form-group">
                <label htmlFor="artId">ArtWork Id</label>
                <input type="text" className="form-control" id="artId" name='artId'
                onChange={handlechange}
                value={formdetails.artId}/>
                <p>{formerrors.artId}</p>
            </div>
            <div className="form-group">
                <label htmlFor="title">ArtWork Title</label>
                <input type="text" className="form-control" id="title" name='title'
                onChange={handlechange}
                value={formdetails.title}/>
            </div>
            <div className="form-group">
                <label htmlFor="artistId">ArtistId</label>
                <input type="text" className="form-control" id="artistId" name='artistId'
                onChange={handlechange}
                value={formdetails.artistId}/>
            </div>
            <div className="form-group">
                <label htmlFor="price">price</label>
                <input type="text" className="form-control" id="price" name='price'
                onChange={handlechange}
                value={formdetails.price}/>
            </div>
            <div className="form-group">
                <label htmlFor="availability">Availability</label>
                <input type="text" className="form-control" id="availability" name='availability'
                onChange={handlechange}
                value={formdetails.availability}/>
            </div>
            <div className="form-group">
                <label htmlFor="catId">Category Id</label>
                <input type="text" className="form-control" id="catId" name='catId'
                onChange={handlechange}
                value={formdetails.catId}/>
            </div>
           
  <button type="submit" className="btn btn-primary">Submit</button>
</form>
    </div>
  )
}
