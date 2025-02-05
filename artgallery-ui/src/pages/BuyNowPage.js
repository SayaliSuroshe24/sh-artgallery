// import React from "react";
// import { useLocation, useNavigate } from "react-router-dom";

// const BuyNowPage = () => {
//   const location = useLocation();
//   const navigate = useNavigate();
//   // console.log("painting :"+painting);
//   console.log("value of location state :" )
//   console.log(location.state);
//   const firstKey = Object.keys(location.state)[0]; 
//   const painting = location.state[firstKey];
//   console.log("painting after : ")
//   console.log(painting);
//   if (!painting) {
//     return <p>Invalid selection. Go back to paintings.</p>;
//   }



//   console.log("Selected item:", item);

//   const handleCheckout = () => {
//     alert(`Order placed for "${painting.title}"!`);
//     navigate("/bill-details", { state: { item } });
//   };

//   // const handleCheckout = () => {
//   //   alert(`Order placed for "${painting.title}"!`);
//   //   navigate("/BillDetails");
//   // };

//   return (
//     <div className="container">
//       <h2 className="my-4">Buy Now</h2>
//       <div className="card">
//         <img
//           src={`data:image/png;base64,${painting.image}`}
//           alt={painting.title}
//           className="card-img-top"
//           style={{ height: "300px", objectFit: "cover" }}
//         />
//         <div className="card-body">
//           <h5 className="card-title">{painting.title}</h5>
//           <p className="card-text">
//             <strong>Price:</strong> ₹{painting.price.toFixed(2)}
//           </p>
//           <button className="btn btn-success" onClick={handleCheckout}>
//             Confirm & Pay
//           </button>
//         </div>
//       </div>
//     </div>
//   );
// };

// export default BuyNowPage;

import { useLocation, useNavigate } from "react-router-dom";

const BuyNowPage = () => {
  const location = useLocation();
  const navigate = useNavigate();

  console.log("Location state:", location.state);

  if (!location.state) {
    return <p>Invalid selection. Go back to items.</p>;
  }

  const firstKey = Object.keys(location.state)[0];
  const item = location.state[firstKey];

  console.log("Selected item:", item);

  const handleCheckout = () => {
    navigate("/BillDetailsPage", { state: { item } });
  };

  return (
    <div className="container">
      <h2 className="my-4">Buy Now</h2>
      <div className="card">
        <img
          src={`data:image/png;base64,${item.image}`}
          alt={item.title}
          className="card-img-top"
          style={{ height: "300px", objectFit: "cover" }}
        />
        <div className="card-body">
          <h5 className="card-title">{item.title}</h5>
          <p className="card-text">
            <strong>Price:</strong> ₹{item.price.toFixed(2)}
          </p>
          <button className="btn btn-success" onClick={handleCheckout}>
            Confirm & Pay
          </button>
        </div>
      </div>
    </div>
  );
};

export default BuyNowPage;

