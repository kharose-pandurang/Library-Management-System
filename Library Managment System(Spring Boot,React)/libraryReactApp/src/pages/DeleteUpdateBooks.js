import Navbar from "./Navbar";
import "./style/ShowData.css";
import { useState, useEffect } from "react";

export default function DeleteUpadate() {
  const [books, setBooks] = useState([]);
  const [deleteid, setDeletId] = useState("");

  const [bname, setBname] = useState("");
  const [bauthor, setBauthor] = useState("");
  const [bsub, setBsub] = useState("");
  const [bquntity, setBquntity] = useState();

  useEffect(() => {
    fetch("http://localhost:8080/admin/getAllBooks")
      .then((res) => res.json())
      .then((result) => {
        setBooks(result);
      });
  }, []);

  const Del = (event) => {
    let b = {
      bname: deleteid,
      aname: "",
      sub: "",
      count: "",
    };
    if(window.confirm("Want To Delete "+deleteid)){
      fetch("http://localhost:8080/admin/deletebook", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(b),
      })
        .then((resp) => {
          alert("Book Deleted!");
        });
    }
  
  };
  const Up = (event) => {
    let b2 = {
      "bname": bname,
      "aname": bauthor,
      "sub": bsub,
      "count": bquntity,
    };
    if (bname.trim() !== "") {
      if (bauthor.trim() !== "") {
        if (bsub.trim() !== "") {
          if (bquntity !== 0) {
            fetch("http://localhost:8080/admin/updatebook", {
              method: "POST",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify(b2),
            }).then((resp) => {
                alert("Book Updated Successfully");
              });
          } else {
            alert("Books Quantity Should Not be 0!");
          }
        } else {
          alert("Subject is empty!");
        }
      } else {
        alert("Author name is empty!");
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
          <form className="mt-1 bg-light rounded m-2">
            <div className="shadow-none  ">
              <label className="form-label"><h5>Delete Book</h5></label>
              <input
                type="text"
                className="form-control"
                id="exampleInputEmail1"
                aria-describedby="emailHelp"
                placeholder="Enter Book Name"
                onChange={(e) => {
                  e.preventDefault();
                  setDeletId(e.target.value);
                }}
              />
            </div>
            <div className="shadow-none  ">
              <button className="btn btn-primary mybtn" onClick={Del}>
                Delete
              </button>
            </div>
          </form>
          <form className="mt-3 bg-light rounded m-2">
            <label className="form-label"><h5>Update Book</h5></label>
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
              <label className="form-label">Author Name</label>
              <input
                type="text"
                className="form-control"
                id="exampleInputEmail1"
                aria-describedby="emailHelp"
                placeholder="Enter Book Name"
                onChange={(e) => {
                  e.preventDefault();
                  setBauthor(e.target.value);
                }}
              />
            </div>
            <div className="shadow-none  ">
              <label className="form-label">Subject/jonor</label>
              <input
                type="text"
                className="form-control"
                id="exampleInputEmail1"
                aria-describedby="emailHelp"
                placeholder="Enter Book Name"
                onChange={(e) => {
                  e.preventDefault();
                  setBsub(e.target.value);
                }}
              />
            </div>
            <div className="shadow-none  ">
              <label className="form-label">Book Count</label>
              <input
                type="text"
                className="form-control"
                id="exampleInputEmail1"
                aria-describedby="emailHelp"
                placeholder="Enter Book Name"
                onChange={(e) => {
                  e.preventDefault();
                  setBquntity(e.target.value);
                }}
              />
            </div>
            <div className="shadow-none  ">
              <button className="btn btn-primary mybtn" onClick={Up}>
                Update
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
  );
}
