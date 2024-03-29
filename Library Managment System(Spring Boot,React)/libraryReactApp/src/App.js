import Home from "./pages/home";
import "./App.css";
import { Routes, Route } from "react-router-dom";
import LibrarianLogin from "./pages/LibrarianLoginPage";
import AdminPage from "./pages/adminPage";
import AddBook from "./pages/AddBooksPage";
import DeleteUpadate from "./pages/DeleteUpdateBooks";
import StudentLoginPage from "./pages/StudentLoginPage";
import StudentPage from "./pages/studentpage";
import ReqBookPage from "./pages/ReqBookPage";
import StudentIssedBooks from "./pages/StudentIssuedBooks";
import AllIssuedBookPage from "./pages/AllIssuedBooks";
import AllRequests from "./pages/AllRequests";
function App() {
  return (
    <Routes>
      <Route path=""
        element={
          <div>
          <Home />
        </div>
        }
      />
      <Route path="admin"
        element={
          <div>
          <LibrarianLogin/>
        </div>
        }
      />
      <Route path="studentlogin"
        element={
          <div>
          <StudentLoginPage/>
        </div>
        }
      />
       <Route path="adminpage"
        element={
          <div>
          <AdminPage/>
        </div>
        }
      />
       <Route path="addbookpage"
        element={
          <div>
          <AddBook/>
        </div>
        }
      />
       <Route path="deleteupdatebooks"
        element={
          <div>
          <DeleteUpadate/>
        </div>
        }
      />
       <Route path="student"
        element={
          <div>
          <StudentPage/>
        </div>
        }
      />
       <Route path="requestbookpage"
        element={
          <div>
          <ReqBookPage/>
        </div>
        }
      />
      <Route path="issuedbookpage"
        element={
          <div>
         <StudentIssedBooks/>
        </div>
        }
      />
        <Route path="allissuedbooks"
        element={
          <div>
          <AllIssuedBookPage/>
        </div>
        }
      />
       <Route path="allreq"
        element={
          <div>
          <AllRequests/>
        </div>
        }
      />
    </Routes>
  );
}

export default App;
