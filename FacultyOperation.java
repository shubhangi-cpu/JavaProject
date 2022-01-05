package com.coursemgmt.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.coursemgmt.DTA.Faculty;
import com.coursemgmt.DTA.Student;

public class FacultyOperation {

	Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    int row=0;
    Scanner sc=new Scanner(System.in);
	 public FacultyOperation()
	    {
	    	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemgmt","root","root");
				//System.out.println("connection done..!!");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	 
	public void addFaculty() {
		// TODO Auto-generated method stub
		System.out.println("Enter Faculty id: ");
  		String facultyid=sc.next();	
  		System.out.println("Enter First Name: ");
  		String fname=sc.next();
  		System.out.println("Enter Last Name: ");
  		String lname=sc.next();
  		System.out.println("Enter mailid: ");
  		String mailid=sc.next();
  		System.out.println("Enter phone: ");
  		long phone=sc.nextLong();
  		System.out.println("Enter Passoword: ");
  		String password=sc.next();
  		
  		Faculty faculty=new Faculty();
  		faculty.setFaculty_id(facultyid);
  		faculty.setPassword(password);
  		faculty.setFirstName(fname);
  		faculty.setLastName(lname);
  		faculty.setEmail(mailid);
  		faculty.setPhone(phone);
  		faculty.setPassword(password);
      
    	try
    	{
    		pstmt=con.prepareStatement("insert into faculty values(?,?,?,?,?,?)");
    	    pstmt.setString(1, faculty.getFaculty_id());  
    	    pstmt.setString(2, faculty.getFirstName());
    	    pstmt.setString(3, faculty.getLastName());
    	    pstmt.setString(4, faculty.getEmail());
    	    pstmt.setLong(5, faculty.getPhone());
    	    pstmt.setString(6, faculty.getPassword());
    	    row=pstmt.executeUpdate();
    	    if(row>=1)
    	    {
    	    	System.out.println(" Registration Succefull....!!!");
    	    }else
    	    {
    	    	System.out.println("Something Went Wrong....!!!");
    	    }
    	}catch(SQLException e)
    	{
    	e.printStackTrace();	
    	}
	}//addStudent

	

	public boolean login(String mailid, String password) throws SQLException {
		// TODO Auto-generated method stub
	        int flag=1;
			pstmt=con.prepareStatement("select * from faculty where email='"+mailid+"'");
		    rs=pstmt.executeQuery();
			while(rs.next())
			{
				if(mailid.equals(rs.getString("email")) && password.equals(rs.getString("password")))
				{
			       System.out.println(" Faculty Login Successfully.....!!!");		
			               flag=0;
			               
				}
				else
					System.out.println("mailid and password Invalid......!!!");
				      
			}
			if(flag==0)
				return true;
			return false;
		
	}//login

	
	
	public void uploadAssignment()  {
		
		
		try {
			System.out.println("Enter module name: ");
			String modulename=sc.next();
			pstmt=con.prepareStatement("select * from module where module_name=?");
			pstmt.setString(1,modulename );
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next());
				
			
			System.out.println("Enter Assignment name::");
			String assName=sc.next();
			System.out.println("Enter full path of file which you want to upload in this format(E:\\\\Ass01.txt)::");
			String pathname=sc.next();
			//File  file=new File("E:\\shubhangi\\Assignment\\Ass01.txt");
			File file=new File(pathname);
			FileReader fr=new FileReader(file); 
			
			pstmt=con.prepareStatement("insert into assignmenttbl(module_name,ass_name,file_data) values(?,?,?)");
			pstmt.setString(1,modulename );
			pstmt.setString(2, assName);
			pstmt.setCharacterStream(3,fr,(int)file.length()); 
			row=pstmt.executeUpdate();
			if(row>=1)
			      System.out.println(" file Uploaded successfully.........!!!");
			else
				System.out.println("Somthing Went wrong.........!!!");
		    } catch (SQLException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
		
	}//uploadAssignment
	
	
	public void checkAssignments() {
		
		try {
			pstmt=con.prepareStatement("select * from submitasstab where checked=false");
			rs=pstmt.executeQuery();
			System.out.println("ID\tEMAIL_ID\t\tASSIGNMENT_NAME\t");
			while(rs.next()) {
				System.out.println(rs.getInt("id")+"\t"+rs.getString("email")+"\t\t"+rs.getString("ass_name"));
			}
			System.out.println("\nEnter Id to check Assignment::");
			int id=sc.nextInt();
			
			pstmt=con.prepareStatement("update submitasstab set checked=true where id=?");
			pstmt.setInt(1, id);
			row=pstmt.executeUpdate();
			if(row>=1)
			    System.out.println("Assinemnet checked succefully.....!!!");
			else
				System.out.println("Somthing went wrong........!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}//checkAssignment

	
	 public void closeConnection()
	    {
	    	try {
				pstmt.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }//closeConnection
}
