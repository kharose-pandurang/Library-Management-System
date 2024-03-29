import Navbar from "./Navbar";
import "./style/ShowData.css";
import { useState, useEffect } from "react";

export default function AllRequests(){
    const [req, setReq] = useState([]);
    const [reid,setReqId]=useState();
    useEffect(() => {
      fetch("http://localhost:8080/admin/getallreq")
        .then((res) => res.json())
        .then((result) => {
          setReq(result);
        });
    }, []);
  
    const V = (event) => {
     
      let r1 = {
        "srno":reid,
        "sid":"",
        "sname":"",
        "bname":""
      };
      console.log(r1);
     
          if (r1.srno.trim() !== "" && window.confirm("Confirm Issue?")) {
            fetch("http://localhost:8080/admin/verifybookreq", {
              method: "POST",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify(r1),
            })
              .then((res) => res.json())
              .then((resp) => {
                alert(resp.msg);
              });
          } else {
            alert("Not Verified");
          }
    };
    let count = 0;
    return (
      <div>
        <Navbar />
        <div className="Body-Row">
          <div className="sidebar ">
            <form className="mt-3 bg-light rounded m-2">
              <label className="form-label">Return Request</label>
              <div className="shadow-none  ">
                <label className="form-label">Request Id </label>
                <input
                  type="number"
                  className="form-control"
                  id="exampleInputEmail1"
                  aria-describedby="emailHelp"
                  placeholder="Enter Book Name"
                  onChange={(e) => {
                    e.preventDefault();
                    setReqId(e.target.value);
                  }}
                />
              </div>
  
              <div className="shadow-none  ">
                <button className="btn btn-primary mybtn" onClick={V}>
                  Verify
                </button>
                <button className="btn btn-primary mybtn" >
                  Verify
                </button>
              </div>
            </form>
          </div>
          <div className="sidebox bg-light rounded">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">sr.no</th>
                  <th scope="col">Request Id</th>
                  <th scope="col">Student Id</th>
                  <th scope="col">Student Name</th>
                  <th scope="col">Book Name</th>
                </tr>
              </thead>
              <tbody>
                {req.map((row) => (
                  <tr>
                    <th scope="row">{++count}</th>
                    <td>{row.srno}</td>
                    <td>{row.sid}</td>
                    <td>{row.sname}</td>
                    <td>{row.bname}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    );
}