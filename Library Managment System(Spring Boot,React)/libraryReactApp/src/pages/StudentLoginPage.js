import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import Navbar from "./Navbar";
import './style/style.css'

import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";

export default function StudentLoginPage() {
  const [sname, setName] = useState();
  const [pass, setPass] = useState();
  const [sid, setSid] = useState();
  const [users, setUsers] = useState([]);
  let userdata;
  const navigate = useNavigate();

  useEffect((e) => {
    fetch("http://localhost:8080/user/allstudents")
      .then((res) => res.json())
      .then((result) => {
        setUsers(result);
      });
  }, []);

  function ValidateUser(event) {
    event.preventDefault();
    console.log(users);
    let sts;
    for (let i = 0; i < users.length; i++) {
      if (
        users[i].sid.localeCompare(sid) === 0 &&
        users[i].smail.localeCompare(pass) === 0
      ) {
        sts = "val";
        console.log(sts);
        userdata = {
          smail: users[i].smail,
          sname: users[i].sname,
          sid: users[i].sid,
        };
        break;
      } else if (
        users[i].sid.localeCompare(sid) === 0 &&
        users[i].smail.localeCompare(pass) === -1
      ) {
        sts = "wp";
      } else {
        sts = "error";
      }
    }
    if (sts === "val") {
      alert("login successfull");
      navigate("/student", { state: userdata });
    } else if (sts === "wp") {
      alert("Wrong Password");
    } else {
      alert("user not found !kindly signup");
    }
  }

  function Signup(event) {
    const user = {
      sname: sname,
      sid: sid,
      smail: pass,
    };
    fetch("http://localhost:8080/user/signup", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(user),
    })
      .then((res) => res.json())
      .then((resp) => {
        if (resp.sname === "done") {
          alert("SignUp successfull!");
        } else {
          alert("Student Already Exist!");
        }
      });
  }
  return (
    <div>
      <Navbar />
      <div className="Body-home">
        <h3 className="mt-5">USER LOGIN</h3>
        <div className="box2">
          <Form>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Student Name</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="User Name"
                  value={sname}
                  onChange={(e) => {
                    e.preventDefault();
                    setName(e.target.value);
                  }}
                />
              </Form.Group>
              <Form.Label>Student Id</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter email"
                value={sid}
                onChange={(e) => {
                  e.preventDefault();
                  setSid(e.target.value);
                }}
              />
              <Form.Text className="text-muted">
                We'll never share your email with anyone else.
              </Form.Text>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicPassword">
              <Form.Label>Your Mail as Password</Form.Label>
              <Form.Control
                type="email"
                placeholder="Password"
                value={pass}
                onChange={(e) => {
                  e.preventDefault();
                  setPass(e.target.value);
                }}
              />
            </Form.Group>
            <Button className="m-2" variant="primary" onClick={Signup}>
              SignUp
            </Button>
            <Button className="m-2" variant="primary" onClick={ValidateUser}>
              logIn
            </Button>
          </Form>
        </div>
      </div>
    </div>
  );
}
