import "./style/style.css";
import Navbar from "./Navbar";
import { useNavigate } from "react-router-dom";
export default function AdminPage() {
    const navigate=useNavigate();
  return (
    <div>
      <Navbar />
      <div className="Body-home">
        <div className="Card">
          <div className="shadow-none p-3 mb-3 bg-light rounded">
            <button className="btn btn-primary mybtn" onClick={()=>{
                navigate("/addbookpage");
            }} >Add Books</button>
            <button className="btn btn-primary mybtn" onClick={()=>{
                 navigate("/deleteupdatebooks");
            } }>Delete/Update Books</button>
            <button className="btn btn-primary mybtn mybtn" onClick={()=>{
                navigate("/allreq");
            }}>All Requests</button>
            <button className="btn btn-primary mybtn mybtn" onClick={()=>{
                navigate("/allissuedbooks");
            }}>Issued Books</button>
          </div>
        </div>
      </div>
    </div>
  );
}
