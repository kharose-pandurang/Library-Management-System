import Navbar from "./Navbar";
import "./style/ShowData.css";
import { useState, useEffect } from "react";


export default function AllIssuedBookPage(){
    const [list, setList] = useState([]);
    
    useEffect(() => {
      fetch("http://localhost:8080/admin/getAllIssuedBooks")
        .then((res) => res.json())
        .then((result) => {
          setList(result);
        });
    }, []);
  
    let count = 0;
    return (
      <div>
        <Navbar />
          <div className="sidebox1 bg-light rounded">
            <table class="table1">
              <thead>
                <tr>
                  <th scope="col">sr.no</th>
                  <th scope="col">Book Name</th>
                  <th scope="col">Student Id</th>
                  <th scope="col">Student Name</th>
                </tr>
              </thead>
              <tbody>
                {list.map((row) => (
                  <tr>
                    <th scope="row">{++count}</th>
                    <td>{row.bname}</td>
                    <td>{row.sid}</td>
                    <td>{row.sname}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
)
}