package DTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sound.midi.Soundbank;

import DAO.Book;
import DAO.Pendingissue;
import DAO.Student;

public class Database {
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
		return connection;
	}

	public static boolean isUserValid(String sid, String pass) throws Exception {
		Connection con = getConnection();
		PreparedStatement ps1 = con.prepareStatement("Select sid,smail from students");
		ResultSet rs1 = ps1.executeQuery();
		while (rs1.next()) {
			if (rs1.getString(1).equals(sid) && rs1.getString(2).equals(pass)) {
				return true;
			}
		}
		return false;
	}

	public static Book getBook(String bname) throws Exception {
		Connection con1 = getConnection();
		PreparedStatement ps1 = con1.prepareStatement("Select *from books where bname=?");
		ps1.setString(1, bname);
		ResultSet rs1 = ps1.executeQuery();
		Book b1 = new Book();
		if (rs1 == null) {
			return null;
		}
		while (rs1.next()) {
			b1.setBname(rs1.getString(1));
			b1.setAname(rs1.getString(2));
			b1.setBsub(rs1.getString(3));
			b1.setCount(rs1.getInt(4));
		}
		return b1;
	}

	public static Student getStudent(String sid) throws Exception {
		Connection con1 = getConnection();
		PreparedStatement ps1 = con1.prepareStatement("Select *from students where sid=?");
		ps1.setString(1, sid);
		ResultSet rs1 = ps1.executeQuery();
		Student b1 = new Student();
		if (rs1 == null) {
			return null;
		}
		while (rs1.next()) {
			System.out.println("entered");
			b1.setSid(rs1.getString(1));
			b1.setSname(rs1.getString(2));
			b1.setSmail(rs1.getString(3));
			b1.setSclass(rs1.getString(4));
		}
		return b1;
	}

	public static Pendingissue getPendingBook(String sid, String bname) throws Exception {
		Connection con1 = getConnection();
		Pendingissue p = new Pendingissue();
		PreparedStatement ps1 = con1.prepareStatement("Select *from pendingissue where sid=?");
		ps1.setString(1, sid);
		ResultSet rs1 = ps1.executeQuery();
		while (rs1.next()) {
			p.setSid(rs1.getString(2));
			p.setBname(rs1.getString(3));
		}
		return p;
	}

	///////////////////////////////////////////////////////////////////////////////////////////

	public static boolean AddBook(Book b) throws Exception {
		Connection con1 = getConnection();
		Book book = getBook(b.getBname());
		System.out.println(book.toString());
		if (book.getBname() == null) {
			PreparedStatement ps1 = con1.prepareStatement("Insert into books (bname,aname,bsub,count) values(?,?,?,?)");
			ps1.setString(1, b.getBname());
			ps1.setString(2, b.getAname());
			ps1.setString(3, b.getBsub());
			ps1.setInt(4, b.getCount());
			ps1.executeUpdate();
			System.out.println("Book Added");
		} else {
			book.toString();
			PreparedStatement ps1 = con1.prepareStatement("UPDATE books SET count=? Where bname=?");
			ps1.setInt(1, book.getCount() + b.getCount());
			ps1.setString(2, b.getBname());
			ps1.executeUpdate();
			System.out.println("Books updated");
		}

		return true;
	}

	///////////////////////////////////////////////////////////////////////////////////////////

	public static boolean AddStudent(Student s) throws Exception {
		Connection con1 = getConnection();
		Student s1 = getStudent(s.getSid());
		System.out.println(s1.toString());
		if (s1.getSid() == null) {
			PreparedStatement ps1 = con1
					.prepareStatement("Insert into students (sid,sname,smail,sclass) values(?,?,?,?)");
			ps1.setString(1, s.getSid());
			ps1.setString(2, s.getSname());
			ps1.setString(3, s.getSmail());
			ps1.setString(4, s.getSclass());
			ps1.executeUpdate();
			System.out.println("student Added");
			return true;
		} else {
			System.out.println("Student Already Present");
			return false;
		}

	}

	public static boolean requestIssueBook(String sid, String bname) throws Exception {
		Connection con1 = getConnection();
		PreparedStatement ps1 = con1.prepareStatement("Insert into pendingissue(sid,bname) values(?,?)");
		ps1.setString(1, sid);
		ps1.setString(2, bname);
		ps1.executeUpdate();
		System.out.println("approval Pending");
		return true;
	}

	public static boolean verifyIssueBook(String sid, String bname) throws Exception {
		Connection con1 = getConnection();
		PreparedStatement ps1 = con1.prepareStatement("Insert into issuedbooks(sid,bname) values(?,?)");
		ps1.setString(1, sid);
		ps1.setString(2, bname);
		ps1.executeUpdate();

		PreparedStatement ps3 = con1.prepareStatement("Delete from pendingissue where bname=? AND sid=?");
		ps3.setString(1, bname);
		ps3.setString(2, sid);
		ps3.executeUpdate();
		System.out.println("Aproved And Isuue succesfully");

		PreparedStatement ps2 = con1.prepareStatement("Update books SET count=?");
		Book newB = getBook(bname);
		ps2.setInt(1, newB.getCount() - 1);
		ps2.executeUpdate();

		return true;
	}

	public static boolean verifyReturn(String sid, String bname) throws Exception {
		Connection con1 = getConnection();
		Book book = getBook(bname);
		PreparedStatement ps1 = con1.prepareStatement("UPDATE books SET count=? Where bname=?");

		ps1.setInt(1, book.getCount() + 1);
		ps1.setString(2, bname);
		ps1.executeUpdate();

		PreparedStatement ps3 = con1.prepareStatement("Delete from issuedbooks where bname=? AND sid=?");
		ps3.setString(1, bname);
		ps3.setString(2, sid);
		ps3.executeUpdate();
		
		PreparedStatement ps4 = con1.prepareStatement("Delete from pendingreturns where bname=? AND sid=?");
		ps4.setString(1, bname);
		ps4.setString(2, sid);
		ps4.executeUpdate();
		System.out.println("Return succesfully");

		return true;
	}

	public static boolean StudentRequestReturn(String sid, String bname) throws Exception {
		Connection con1 = getConnection();
		PreparedStatement ps1 = con1.prepareStatement("Insert into pendingreturns(sid,bname) values(?,?)");
		ps1.setString(1, sid);
		ps1.setString(2, bname);
		ps1.executeUpdate();
		System.out.println("approval Pending");
		return true;
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////

	public static ResultSet deleteBook(String bname) throws Exception {
		Connection con1 = getConnection();
		Book book = getBook(bname);
		if (book.getBname() != null) {
			PreparedStatement ps1 = con1.prepareStatement("Delete from books where bname=?");
			ps1.setString(1, book.getBname());
			ps1.executeUpdate();
			System.out.println("Book Deleted");
			return getAllBooks();
		} else {
			System.out.println("No book found to delete");
			return null;
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////

	public static ResultSet getAllBooks() throws Exception {
		Connection con1 = getConnection();
		PreparedStatement ps1 = con1.prepareStatement("Select * from books");
		ResultSet rs1 = ps1.executeQuery();
		return rs1;
	}

	public static ResultSet getAllRequests() throws Exception {
		Connection con1 = getConnection();
		PreparedStatement ps1 = con1.prepareStatement("Select * from pendingissue");
		ResultSet rs1 = ps1.executeQuery();
		return rs1;
	}

	public static ResultSet getAllReturnRequests() throws Exception {
		Connection con1 = getConnection();
		PreparedStatement ps1 = con1.prepareStatement("Select * from pendingreturns");
		ResultSet rs1 = ps1.executeQuery();
		return rs1;
	}

	public static ResultSet getStudentIssues(String sid) throws Exception {
		Connection con1 = getConnection();
		PreparedStatement ps1 = con1.prepareStatement("Select * from issuedbooks where sid=?");
		ps1.setString(1, sid);
		ResultSet rs1 = ps1.executeQuery();
		return rs1;
	}
	
	public static ResultSet getAllIssuedBooks() throws Exception {
		Connection con1 = getConnection();
		PreparedStatement ps1 = con1.prepareStatement("Select * from issuedbooks");
		ResultSet rs1 = ps1.executeQuery();
		return rs1;
	}


}
