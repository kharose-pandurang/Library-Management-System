import Navbar from "./Navbar";
import "./style/ShowData.css";
import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";

export default function StudentIssedBooks() {
  const [issues, setIssues] = useState([]);
  const [issueid, setIssueId] = useState();
  const location = useLocation();
  let student = location.state;
  console.log(student);
  useEffect(() => {
    fetch("http://localhost:8080/user/getmyissues", {
      method: "POST",  
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(student),
    })
      .then((res) => res.json())
      .then((result) => {
        setIssues(result);
      });
  }, []);

  const Req = (event) => {
    event.preventDefault();
    let r1 = {
      "srno": issueid,
      "sid":"",
      "sname":"",
      "bname":""
    };
    console.log(r1);
    if (issueid !== 0) {
      if (window.confirm("Confirm Return?")) {
        fetch("http://localhost:8080/user/return", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(r1),
        })
          .then((res) => res.json())
          .then((resp) => {
            alert(resp.msg);
          });
      } 
    } else {
      alert("Issued id is empty!");
    }
  };
  let count = 0;
  return (
    <div>
      <Navbar />
      <div className="Body-Row">
        <div className="sidebar ">
          <form className="mt-3 bg-light rounded m-2">
            <label className="form-label">Return Book</label>
            <div className="shadow-none  ">
              <label className="form-label">Enter Issue Id</label>
              <input
                type="number"
                className="form-control"
                id="exampleInputEmail1"
                aria-describedby="emailHelp"
                placeholder="Issued Id"
                onChange={(e) => {
                  e.preventDefault();
                  setIssueId(e.target.value);
                }}
              />
            </div>

            <div className="shadow-none  ">
              <button className="btn btn-primary mybtn" onClick={Req}>
                Return Book
              </button>
            </div>
          </form>
        </div>
        <div className="sidebox bg-light rounded">
            <h3>My Issued Books</h3>
          <table class="table">
            <thead>
              <tr>
                <th scope="col">sr.no</th>
                <th scope="col">Issue Id</th>
                <th scope="col">Book Name</th>
              </tr>
            </thead>
            <tbody>
              {issues.map((row) => (
                <tr>
                  <th scope="row">{++count}</th>
                  <td>{row.srno}</td>
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
