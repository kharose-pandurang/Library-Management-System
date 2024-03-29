import "./style/style.css";
import Navbar from "./Navbar";
import { useNavigate,useLocation } from "react-router-dom";

export default function StudentPage(){
    const navigate=useNavigate();
    const location = useLocation();
    let student=location.state;
    console.log(student)
    return(
        <div>
        <Navbar />
        <div className="Body-home">
          <div className="Card">
            <div className="shadow-none p-3 mb-3 bg-light rounded">
              <button className="btn btn-primary mybtn" onClick={()=>{
                  navigate("/requestbookpage",{state:student});
              }} >Request Books</button>
              <button className="btn btn-primary mybtn" onClick={()=>{
                   navigate("/issuedbookpage", {state:student});
              } }>My Issued Books</button>
            </div>
          </div>
        </div>
      </div>
    )
}