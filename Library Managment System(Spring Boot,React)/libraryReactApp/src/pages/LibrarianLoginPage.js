import Navbar from "./Navbar";
import "./style/style.css";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
export default function LibrarianLogin() {
  const [mail, setMail] = useState("");
  const [pass, setPass] = useState("");
 
  const nav = useNavigate();
  const ValidateAdmin = () => {

    if (mail === "admin@mail.com") {
      if (pass === "admin") {
        alert("Successfully Log In");
        nav("/adminpage", { state: "Admin" });
      } else {
        alert("invalid password!");
      }
    } else {
      alert("invalid AdminId!");
    }
  };

  return (
    <div>
      <Navbar />
      <div className="Body-home">
        <h3 className="mt-5">LIBRARIAN LOGIN</h3>
        <form className="mt-4">
          <div className="shadow-none p-3 mb-3 bg-light rounded">
            <label  className="form-label">
              Email address
            </label>
            <input
              type="email"
              className="form-control"
              id="exampleInputEmail1"
              aria-describedby="emailHelp"
              onChange={(e) => {
                e.preventDefault();
                setMail(e.target.value);
              }}
            />
            <div id="emailHelp" className="form-text">
              We'll never share your email with anyone else.
            </div>
          </div>
          <div className="shadow-none p-3 mb-3 bg-light rounded">
            <label  className="form-label">
              Password
            </label>
            <input
              type="password"
              className="form-control"
              id="exampleInputPassword1"
              onChange={(e) => {
                e.preventDefault();
                setPass(e.target.value);
              }}
            />
          </div>
          <div className="shadow-none p-3 mb-3 bg-light rounded">
          <button className="btn btn-primary mybtn" onClick={ValidateAdmin}>
            Submit
          </button>
          </div>
        </form>
      </div>
    </div>
  );
}
