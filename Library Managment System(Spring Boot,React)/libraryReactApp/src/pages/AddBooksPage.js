import Navbar from "./Navbar";
import "./style/style.css";
import { useState } from "react";
import axios from "axios";
export default function AddBook() {
  const [bname, setBname] = useState("");
  const [bauthor, setBauthor] = useState("");
  const [bsub, setBsub] = useState("");
  const [bquntity, setBquntity] = useState();
  function Add(event) {
    event.preventDefault();
    let book = {
      "bname": bname,
      "aname": bauthor,
      "sub": bsub,
      "count": bquntity
    };
    console.log(book);
    if (bname.trim() !== "") {
      if (bauthor.trim() !== "") {
        if (bsub.trim() !== "") {
          if (bquntity !== 0) {
            fetch("http://localhost:8080/admin/addBook", {
              method: "POST",
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify(book),
            }).then(() =>{
                alert("Added Successfully");
            } );
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
  }
  return (
    <div>
      <Navbar />
      <div className="Body-home">
        <h3 className="mt-3">Add Books Section</h3>
        <form className="mt-2">
          <div className="shadow-none p-3 mb-2 bg-light rounded">
            <label className="form-label">Book Name</label>
            <input
              type="text"
              className="form-control"
              id="exampleInputEmail1"
              aria-describedby="emailHelp"
              onChange={(e) => {
                e.preventDefault();
                setBname(e.target.value);
              }}
            />
          </div>
          <div className="shadow-none p-3 mb-2 bg-light rounded">
            <label className="form-label">Author Name</label>
            <input
              type="text"
              className="form-control"
              id="exampleInputPassword1"
              onChange={(e) => {
                e.preventDefault();
                setBauthor(e.target.value);
              }}
            />
          </div>
          <div className="shadow-none p-3 mb-2 bg-light rounded">
            <label className="form-label">Subject/Jonor</label>
            <input
              type="text"
              className="form-control"
              id="exampleInputPassword1"
              onChange={(e) => {
                e.preventDefault();
                setBsub(e.target.value);
              }}
            />
          </div>
          <div className="shadow-none p-3 mb-2 bg-light rounded">
            <label className="form-label">No Of Books</label>
            <input
              type="number"
              className="form-control"
              id="exampleInputPassword1"
              onChange={(e) => {
                e.preventDefault();
                setBquntity(e.target.value);
              }}
            />
          </div>
          <div className="shadow-none p-3 mb-2 bg-light rounded">
            <button className="btn btn-primary mybtn" onClick={Add}>
              Submit
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
