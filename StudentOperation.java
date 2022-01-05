package com.coursemgmt.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.coursemgmt.DTA.Student;

public class StudentOperation {
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    int row=0;
    Scanner sc=new Scanner(System.in);
	 public StudentOperation()
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
	 
	 public void addStudent()
	    {
		
		    System.out.println("Enter Student mailid: ");
	  		 String mailid=sc.next();
	  		System.out.println("Enter Passoword: ");
	  		String password=sc.next();
	  		System.out.println("Enter First Name: ");
	  		String fname=sc.next();
	  		System.out.println("Enter Last Name: ");
	  		String lname=sc.next();
	  		System.out.println("Enter phone: ");
	  		long phone=sc.nextLong();
	  		System.out.println("Enter qualification: ");
	  		String qualification=sc.next();
	  		
	  		
	            
	  		Student stud=new Student();
	  		stud.setEmail(mailid);
	  		stud.setPassword(password);
	  		stud.setFirstName(fname);
	  		stud.setLastName(lname);
	  		stud.setPhone(phone);
	  		stud.setQualification(qualification);
          int row=0;
	    	try
	    	{
	    		pstmt=con.prepareStatement("insert into student values(?,?,?,?,?,?)");
	    	    pstmt.setString(1, stud.getEmail());
	    	    pstmt.setString(2, stud.getPassword());
	    	    pstmt.setString(3, stud.getFirstName());
	    	    pstmt.setString(4, stud.getLastName());
	    	    pstmt.setLong(5, stud.getPhone());
	    	    pstmt.setString(6, stud.getQualification());
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
			
				int flag=0;
				pstmt=con.prepareStatement("select * from student where mailid='"+mailid+"'");
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					if(mailid.equals(rs.getString("mailid")) && password.equals(rs.getString("password")))
					{
				                 System.out.println(" Student Login Successfull.....!!!");
					              flag=1;
					}
					else
						System.out.println("Mailid and password Invalid......!!!");
					          
				}
				if(flag==1)
					return true;
				return false;
		}//login
 
		public void downloadassignment() throws SQLException, IOException {
			// TODO Auto-generated method stub
			System.out.println("Enter Assignment name which u want to download::");
			String name=sc.next();
			
			pstmt=con.prepareStatement("select file_data from assignmenttbl where ass_name=?");
			pstmt.setString(1,name );
		    rs=pstmt.executeQuery();
			//int id=0;
			rs.next();
			
				Blob c=rs.getBlob("file_data"); 
				InputStream r=c.getBinaryStream();
				  
				  FileWriter fw=new FileWriter("E:\\retrivefile.txt");
				  
				  int i; while((i=r.read())!=-1) fw.write((char)i);
				  fw.close();  
				   
				                
				  System.out.println("file downloaded successfully..........!!!");  
		}//download Assignment
		
		
//		void viewModuleDetails() throws SQLException {
//			
//			pstmt=con.prepareStatement("select * from module");
//			ResultSet rs=pstmt.executeQuery();
//			System.out.println("---------------------------------View Module Details--------------------------");
//			System.out.println("Id\tName");
//			while(rs.next()) {
//				System.out.println(rs.getInt(1)+"\t"+rs.getString(2));
//				
//			}
//		}
		public void submitAssignment() throws SQLException, FileNotFoundException{
			System.out.println("Enter module name whose assignment u want to submit::");
			String mod_name=sc.next();
			
			pstmt=con.prepareStatement("select * from module where module_name=?");
			pstmt.setString(1,mod_name);
		    rs=pstmt.executeQuery();
			
			if(rs.next());
				
			System.out.println("Enter Assignment name::");
			String assName=sc.next();
			
			pstmt=con.prepareStatement("select * from assignmenttbl where ass_name=? and module_name=?");
			pstmt.setString(1, assName);
			pstmt.setString(2, mod_name);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				System.out.println("Enter U r Email-Id::");
				String email=sc.next();
				
				pstmt=con.prepareStatement("insert into submitAssTab(module_name,ass_name,email,file_data,checked) values(?,?,?,?,?)");
				pstmt.setString(1, mod_name);
				pstmt.setString(2, rs.getString("ass_name"));
				pstmt.setString(3, email);
				
				File  file=new File("E:\\retrivefile.txt");
				FileReader fr=new FileReader(file);
				pstmt.setCharacterStream(4,fr,(int)file.length());
				pstmt.setBoolean(5, false);
				row=pstmt.executeUpdate();
				if(row>=1)
				    System.out.println("File submited succefully...........!!!");
				else 
					System.out.println("Something went Wrong...............!!!");
			}
			
		}//submitAssignment
		
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
