package com.coursemgmt.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.coursemgmt.DTA.Course;
import com.coursemgmt.DTA.Faculty;
import com.coursemgmt.DTA.ModuleClass;
import com.coursemgmt.DTA.Student;

public class AdminOperation {
	    Connection con;
	    PreparedStatement pstmt;
	    Statement stmt;
	    ResultSet rs;
	    Scanner sc=new Scanner(System.in);
	public AdminOperation()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/coursemgmt","root","root");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public void listOfStudent(){
		   
	    	List<Student> studlist=new ArrayList<>();
	    	try {
	    		stmt=con.createStatement();
	    		rs=stmt.executeQuery("select * from student");
				while(rs.next())
				{
					studlist.add(new Student(rs.getString("mailid"),rs.getString("password"),rs.getString("firstName"),rs.getString("lastName"),rs.getLong("phone"),rs.getString("qualification")));
				}
	    	}catch(Exception e)
			{
				e.printStackTrace();
			}		
	    	System.out.println("-------------------------------------------------------------List of Student---------------------------------------------------");
	    	System.out.println("\tMailID\t\t\tPassword\t\tFirst Name\tLast Name\tPhone\t\tQualification\t\t");
	    	System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
	    	for(Student s:studlist)
			{
				System.out.println(s.getEmail()+"\t\t"+s.getPassword()+"\t\t"+s.getFirstName()+"\t\t"+s.getLastName()+"\t\t"+s.getPhone()+"\t\t"+s.getQualification());
				
			}
	    }//listOfStudent
	 
	 public void listOfFaculty(){
		   
	    	List<Faculty> facultylist=new ArrayList<>();
	    	try {
	    		stmt=con.createStatement();
	    		rs=stmt.executeQuery("select * from Faculty");
				while(rs.next())
				{
					facultylist.add(new Faculty(rs.getString("id"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("email"),rs.getLong("phone"),rs.getString("password")));
					
				}
	    	}catch(Exception e)
			{
				e.printStackTrace();
			}		
	    	System.out.println("--------------------------------------------------------List of Faculty----------------------------------------------------------------------");
	    	System.out.println("Faculty ID\t\tFirst Name\tLast Name\t\tMail ID\t\t\tPhone\t\t\tPassword");
	    	System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------");
	    	for(Faculty f:facultylist)
			{
				System.out.println(f.getFaculty_id()+"\t\t"+f.getFirstName()+"\t\t"+f.getLastName()+"\t\t"+f.getEmail()+"\t\t"+f.getPhone()+"\t\t"+f.getPassword());
				
			}

	    }//listoffaculty
	 
	 public void listOfCourse(){
		   
	    	List<Course> courselist=new ArrayList<>();
	    	try {
	    		stmt=con.createStatement();
	    		rs=stmt.executeQuery("select * from COurse");
				while(rs.next())
				{
					courselist.add(new Course(rs.getInt("id"),rs.getString("course_name"),rs.getDouble("course_fees"),rs.getInt("duration")));
					
				}
	    	}catch(Exception e)
			{
				e.printStackTrace();
			}		
	    	System.out.println("------------------------------------------List of Course-------------------------------------------------------");
	    	System.out.println("Course ID\tCourse Name\tCourse Fees\tCourse Duaration");
	    	System.out.println("----------------------------------------------------------------------------------------------------------------");
	    	for(Course c:courselist)
			{
				System.out.println(c.getCid()+"\t\t"+c.getCname()+"\t\t"+c.getCfees()+"\t\t"+c.getCduration());
				
			}

	    }//listofCourse
	 
	 public void listOfStudentCoursewise() {
			// TODO Auto-generated method stub
		 System.out.println("Enter course name:: ");
		 String cname=sc.next();
		
		 try {
			 stmt=con.createStatement();
			 rs=stmt.executeQuery("select S.mailid, S.password, S.firstName, S.lastName, S.phone, S.qualification, C.course_name "
			 		+ "from student S,course C,student_course CS where S.mailid=CS.mailid and CS.courseid=C.id and C.course_name='"+cname+"'");
			 System.out.println("------------------------------------------------------------------------List of Student Course Wise----------------------------------------\n");
			 System.out.println("\tMailID\t\t\tPassword\t\tFirst Name\tLast Name\tPhone\t\tQualification\t\tCourse Name\n");
			 while(rs.next())
		     {
		    	System.out.println(rs.getString("mailid")+"\t\t"+rs.getString("password")+"\t\t"+rs.getString("firstName")+"\t\t"+rs.getString("lastName")+"\t\t"+rs.getString("phone")+"\t\t"+rs.getString("qualification")+"\t\t"+rs.getString("course_name")); 
		     }
			 
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
		}
	 
	 public void listOfStudentModuleWise() {
			// TODO Auto-generated method stub
			System.out.println("Enter Module Name:: ");
			String mname=sc.next();
			try {
				stmt=con.createStatement();
				rs=stmt.executeQuery("select distinct(S.mailid), S.firstName, S.lastName, S.phone, S.qualification, M.module_name from student S, module M, Faculty F, course C,student_course CS, course_module CM where C.id=CM.courseid and CS.mailid=S.mailid  and CM.moduleid=M.id and M.module_name='"+mname+"'");
								
				System.out.println("------------------------------------------------------------------------List of Student Module Wise----------------------------------------\n");
				 System.out.println("\tMailID\t\t\tFirst Name\tLast Name\tPhone\t\tQualification\t\tModule Name\n");
				 while(rs.next())
			     {
			    	System.out.println(rs.getString("mailid")+"\t\t"+rs.getString("firstName")+"\t\t"+rs.getString("lastName")+"\t\t"+rs.getString("phone")
			    	          +"\t\t"+rs.getString("qualification")+"\t\t"+rs.getString("module_name")); 
			     }
						
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//listOfStudentModuleWise

	 public void StudentDetails() {
			// TODO Auto-generated method stub
		 System.out.println("Enter Student Mailid:: ");
			String mailid=sc.next();
			try {
				stmt=con.createStatement();
				rs=stmt.executeQuery("select distinct(S.mailid), S.firstName, S.lastName, S.phone, S.qualification, M.module_name , C.course_name from student S, module M, Faculty F, course C,student_course CS, course_module CM where C.id=CM.courseid and CS.mailid=S.mailid  and CM.moduleid=M.id and S.mailid='"+mailid+"' ");
								
				System.out.println("------------------------------------------------------------------------List of Student Module Wise----------------------------------------\n");
				 System.out.println("\tMailID\t\t\tFirst Name\tLast Name\tPhone\t\tQualification\t\tModule Name\t\tCourse Name\n");
				 while(rs.next())
			     {
			    	System.out.println(rs.getString("mailid")+"\t\t"+rs.getString("firstName")+"\t\t"+rs.getString("lastName")+"\t\t"+rs.getString("phone")
			    	          +"\t\t"+rs.getString("qualification")+"\t\t"+rs.getString("module_name")+"\t\t\t"+rs.getString("course_name")); 
			     }
						
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//StudentDetails

	 
	 public void addCourse() {
			// TODO Auto-generated method stub
			System.out.println("Enter Course id: ");
	  		int cid=sc.nextInt();	
	  		System.out.println("Enter Course Name: ");
	  		String cname=sc.next();
	  		System.out.println("Enter Course fees: ");
	  		double cfees=sc.nextDouble();
	  		System.out.println("Enter Course duration: ");
	  		int cduration=sc.nextInt();
	  		
	  		Course cobj=new Course();
	  		cobj.setCid(cid);
	  		cobj.setCname(cname);
	  		cobj.setCfees(cfees);
	  		cobj.setCduration(cduration);
	  		
	      int row=0;
	    	try
	    	{
	    		pstmt=con.prepareStatement("insert into course values(?,?,?,?)");
	    	    pstmt.setInt(1, cobj.getCid());  
	    	    pstmt.setString(2, cobj.getCname());
	    	    pstmt.setDouble(3, cobj.getCfees());
	    	    pstmt.setInt(4, cobj.getCduration());
	    	    row=pstmt.executeUpdate();
	    	    if(row>=1)
	    	    {
	    	    	System.out.println(" Course record inserted....!!!");
	    	    }else
	    	    {
	    	    	System.out.println("Something Went Wrong....!!!");
	    	    }
	    	}catch(SQLException e)
	    	{
	    	e.printStackTrace();	
	    	}
		}//addCourse

	 public void updateCourse()
	    {
		    System.out.println("Enter Course ID For Updation: ");
	  		int cid=sc.nextInt();
	  		System.out.println("Enter new deatils:  ");
	  		System.out.println("Enter Course Name: ");
	  		String cname=sc.next();
	  		System.out.println("Enter Course fees: ");
	  		double cfees=sc.nextDouble();
	  		System.out.println("Enter Course duration: ");
	  		int cduration=sc.nextInt();
	  		
	  		Course cobj=new Course();
	  		cobj.setCid(cid);
	  		cobj.setCname(cname);
	  		cobj.setCfees(cfees);
	  		cobj.setCduration(cduration);
	  		
      int row=0;
	    	try
	    	{
	    		pstmt=con.prepareStatement("update course set course_name=?,course_fees=?,duration=? where id=?");
	    		   
		    	    pstmt.setString(1, cobj.getCname());
		    	    pstmt.setDouble(2, cobj.getCfees());
		    	    pstmt.setInt(3, cobj.getCduration());
		    	    pstmt.setInt(4, cobj.getCid());  
	    	    row=pstmt.executeUpdate();
	    	    if(row>=1)
	    	    {
	    	    	System.out.println(" Course Updated Succefully....!!!");
	    	    }else
	    	    {
	    	    	System.out.println("Something Went Wrong....!!!");
	    	    }
	    	}catch(SQLException e)
	    	{
	    	e.printStackTrace();	
	    	}	    	
	   }//updateCourse
	
	 public void deleteCourse() {
			// TODO Auto-generated method stub
			    System.out.println("Enter course id which you want to delete: ");
			    int cid=sc.nextInt();
			    
			    Course cobj=new Course();
		  		cobj.setCid(cid);
		    	try
		    	{
		    		pstmt=con.prepareStatement("delete from module where id=?");
		    		pstmt.setInt(1, cobj.getCid());
		    		int row=pstmt.executeUpdate();
		    		System.out.println("Course record deleted....!!!");
		    	}catch(SQLException e)
		    	{
		    		e.printStackTrace();
		    	}	    
		}//deleteCourse
	 
	 public void listOfModule(){
		   
	    	List<ModuleClass> modulelist=new ArrayList<>();
	    	try {
	    		stmt=con.createStatement();
	    		rs=stmt.executeQuery("select * from Module");
				while(rs.next())
				{
					modulelist.add(new ModuleClass(rs.getInt("id"),rs.getString("module_name")));
				}
	    	}catch(Exception e)
			{
				e.printStackTrace();
			}		
	    	System.out.println("------------------------------------------List of Module-------------------------------------------------------");
	    	System.out.println("MOdule ID\tModule Name");
	    	System.out.println("----------------------------------------------------------------------------------------------------------------");
	    	for(ModuleClass m:modulelist)
			{
				System.out.println(m.getModule_id()+"\t\t"+m.getModulename());
				
			}

	    }//listofmodule

	 public void addModule() {
			// TODO Auto-generated method stub
			System.out.println("Enter Module id: ");
	  		int mid=sc.nextInt();	
	  		System.out.println("Enter Module Name: ");
	  		String mname=sc.next();
	  		
	  		ModuleClass module=new ModuleClass();
	  		module.setModule_id(mid);
	  		module.setModulename(mname);
	      int row=0;
	    	try
	    	{
	    		pstmt=con.prepareStatement("insert into module values(?,?)");
	    	    pstmt.setInt(1, module.getModule_id());  
	    	    pstmt.setString(2, module.getModulename());
	    	   
	    	    row=pstmt.executeUpdate();
	    	    if(row>=1)
	    	    {
	    	    	System.out.println(" Module record inserted....!!!");
	    	    }else
	    	    {
	    	    	System.out.println("Something Went Wrong....!!!");
	    	    }
	    	}catch(SQLException e)
	    	{
	    	e.printStackTrace();	
	    	}
		}//addModule

	 public void updateModule()
	    {
		    System.out.println("Enter Module ID For Updation: ");
	  		int mid=sc.nextInt();
	  		System.out.println("Enter new deatils:  ");
	  		System.out.println("Enter Module Name: ");
	  		String mname=sc.next();
	  		
	  		ModuleClass module=new ModuleClass();
	  		module.setModule_id(mid);
	  		module.setModulename(mname);
         int row=0;
	    	try
	    	{
	    		pstmt=con.prepareStatement("update module set module_name=? where id=?");
	    		 pstmt.setInt(2, module.getModule_id());  
		    	    pstmt.setString(1, module.getModulename());
		    	   
	    	    row=pstmt.executeUpdate();
	    	    if(row>=1)
	    	    {
	    	    	System.out.println(" Module Updated Succefully....!!!");
	    	    }else
	    	    {
	    	    	System.out.println("Something Went Wrong....!!!");
	    	    }
	    	}catch(SQLException e)
	    	{
	    	e.printStackTrace();	
	    	}	    	
	   }//updateModule
	
	 
	 public void deleteModule() {
			// TODO Auto-generated method stub
			    System.out.println("Enter Module id which you want to delete: ");
			    int mid=sc.nextInt();
			    
			    ModuleClass module=new ModuleClass();
		  		module.setModule_id(mid);
		    	try
		    	{
		    		pstmt=con.prepareStatement("delete from module where id=?");
		    		pstmt.setInt(1, module.getModule_id());
		    		int row=pstmt.executeUpdate();
		    		System.out.println("Module record deleted....!!!");
		    	}catch(SQLException e)
		    	{
		    		e.printStackTrace();
		    	}	    
		}//deleteModule
	 
		public void deleteStudent() {
		// TODO Auto-generated method stub
		    System.out.println("Enter mailid which you want to delete: ");
		    String mailid=sc.next();
		    
		    Student stud=new Student();
		    stud.setEmail(mailid);
	    	
	    	try
	    	{
	    		pstmt=con.prepareStatement("delete from student where mailid=?");
	    		pstmt.setString(1, stud.getEmail());
	    		int row=pstmt.executeUpdate();
	    		System.out.println("Student record deleted....!!!");
	    	}catch(SQLException e)
	    	{
	    		e.printStackTrace();
	    	}	    
	}//deleteStudent

	public void deleteFaculty() {
		// TODO Auto-generated method stub
		    System.out.println("Enter faculty id which you want to delete: ");
		    String facultyid=sc.next();
		    
		    Faculty faculty=new Faculty();
		    faculty.setFaculty_id(facultyid);
	    	
	    	try
	    	{
	    		pstmt=con.prepareStatement("delete from faculty where id=?");
	    		pstmt.setString(1, faculty.getFaculty_id());
	    		int row=pstmt.executeUpdate();
	    		System.out.println("Faculty record deleted....!!!");
	    	}catch(SQLException e)
	    	{
	    		e.printStackTrace();
	    	}	    	
	}//deleteFaculty
	
	 public void updateFaculty()
	    {
		    System.out.println("Enter faculty ID For Updation: ");
	  		String faculty_id=sc.next();
	  		System.out.println("Enter new deatils:  ");
	  		System.out.println("Enter First Name: ");
	  		String fname=sc.next();
	  		System.out.println("Enter Last Name: ");
	  		String lname=sc.next();
	  		System.out.println("Enter Email: ");
	  		String email=sc.next();
	  		System.out.println("Enter Passoword: ");
	  		String password=sc.next();  		
	  		System.out.println("Enter phone: ");
	  		long phone=sc.nextLong();
	  		
	  		Faculty faculty=new Faculty();
	  		faculty.setFaculty_id(faculty_id);
	  		faculty.setPassword(password);
	  		faculty.setFirstName(fname);
	  		faculty.setLastName(lname);
	  		faculty.setEmail(email);	  		
	  		faculty.setPhone(phone);
	  		faculty.setPassword(password);
            int row=0;
	    	try
	    	{
	    		pstmt=con.prepareStatement("update faculty set firstName=?, lastName=?, email=?, phone=?, password=? where id=?");
	    		 pstmt.setString(1, faculty.getFirstName());
		    	 pstmt.setString(2, faculty.getLastName());
		    	 pstmt.setString(3, faculty.getEmail());
		    	 pstmt.setLong(4, faculty.getPhone());
		    	 pstmt.setString(5, faculty.getPassword());
	    	    pstmt.setString(6, faculty.getFaculty_id());
	    	    row=pstmt.executeUpdate();
	    	    if(row>=1)
	    	    {
	    	    	System.out.println(" Record Updated Succefully....!!!");
	    	    }else
	    	    {
	    	    	System.out.println("Something Went Wrong....!!!");
	    	    }
	    	}catch(SQLException e)
	    	{
	    	e.printStackTrace();	
	    	}	    	
	   }//updateFaculty
	 
	 public void updateStudent()
	    {
		    System.out.println("Enter Student mailid For Updation: ");
	  		String mailid=sc.next();
	  		System.out.println("Enter new deatils:  ");
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
	    		pstmt=con.prepareStatement("update student set  password=?, firstName=?, lastName=?, phone=?, qualification=? where mailid=?");
	    	    pstmt.setString(6, stud.getEmail());
	    	    pstmt.setString(1, stud.getPassword());
	    	    pstmt.setString(2, stud.getFirstName());
	    	    pstmt.setString(3, stud.getLastName());
	    	    pstmt.setLong(4, stud.getPhone());
	    	    pstmt.setString(5, stud.getQualification());
	    	    row=pstmt.executeUpdate();
	    	    if(row>=1)
	    	    {
	    	    	System.out.println(" Record Updated Succefully....!!!");
	    	    }else
	    	    {
	    	    	System.out.println("Something Went Wrong....!!!");
	    	    }
	    	}catch(SQLException e)
	    	{
	    	e.printStackTrace();	
	    	}
	    	
	   }//updateStudent

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

	public void addcoursetoStudent() {
		// TODO Auto-generated method stub
		System.out.println("Enter mailid:: ");
		String mailid=sc.next();
		
		System.out.println("Enter course Id:: ");
		int courseid=sc.nextInt();
		
		Student student=new Student();
		Course course=new Course();
		student.setEmail(mailid);
		course.setCid(courseid);
      int row=0;
    	try
    	{
    		pstmt=con.prepareStatement("insert into student_course values(?,?,?)");
    		pstmt.setInt(1, 0);
    	    pstmt.setString(2, student.getEmail());  
    	    pstmt.setInt(3, course.getCid());
    	   
    	    row=pstmt.executeUpdate();
    	    if(row>=1)
    	    {
    	    	System.out.println("  record inserted....!!!");
    	    }else
    	    {
    	    	System.out.println("Something Went Wrong....!!!");
    	    }
    	}catch(SQLException e)
    	{
    	e.printStackTrace();	
    	}
		
	}

	public void addmoduletoStudent() {
		// TODO Auto-generated method stub
		
	}

	

	
	
}
