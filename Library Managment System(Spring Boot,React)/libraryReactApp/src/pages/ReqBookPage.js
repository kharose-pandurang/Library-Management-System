import Navbar from "./Navbar";
import "./style/ShowData.css";
import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";

export default function ReqBookPage(){
        const [books, setBooks] = useState([]);
        const [bname, setBname] = useState();
        const [sid, setSid] = useState();
        const [sname, setSname] = useState();
        const location = useLocation();
        let student=location.state;
        console.log(student)
        useEffect(() => {
          fetch("http://localhost:8080/admin/getAllBooks")
            .then((res) => res.json())
            .then((result) => {
              setBooks(result);
            });
        }, []);
      
        const Req = (event) => {
            event.preventDefault();
           
          let r1 = {
            "sid": student.sid,
            "sname": student.sname,
            "bname": bname
          };
          console.log(r1)
          if (bname.trim() !== "") {
            if (sid !=="") {
              if (sname!=="") {
               
                  fetch("http://localhost:8080/user/reqabook", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(r1),
                  }).then((res)=>res.json()).then((resp) => {
                    alert(resp.msg);
                    });

              } else {
                alert("Error Getting Your Details");
              }
            } else {
                alert("Error Getting Your Details");
            }
          } else {
            alert("book name is empty!");
          }
        };
        let count = 0;
        return (
          <div>
            <Navbar />
            <div className="Body-Row">
              <div className="sidebar ">
                <form className="mt-3 bg-light rounded m-2">
                  <label className="form-label">Request Book</label>
                  <div className="shadow-none  ">
                    <label className="form-label">Book Name</label>
                    <input
                      type="text"
                      className="form-control"
                      id="exampleInputEmail1"
                      aria-describedby="emailHelp"
                      placeholder="Enter Book Name"
                      onChange={(e) => {
                        e.preventDefault();
                        setBname(e.target.value);
                      }}
                    />
                  </div>
                 
                  <div className="shadow-none  ">
                    <button className="btn btn-primary mybtn" onClick={Req}>
                      Request
                    </button>
                  </div>
                </form>
              </div>
              <div className="sidebox bg-light rounded">
                <table class="table">
                  <thead>
                    <tr>
                      <th scope="col">sr.no</th>
                      <th scope="col">Book Name</th>
                      <th scope="col">Author Name</th>
                      <th scope="col">Subject/Jonor</th>
                      <th scope="col">Book Count</th>
                    </tr>
                  </thead>
                  <tbody>
                    {books.map((row) => (
                      <tr>
                        <th scope="row">{++count}</th>
                        <td>{row.bname}</td>
                        <td>{row.aname}</td>
                        <td>{row.sub}</td>
                        <td>{row.count}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
    )
}