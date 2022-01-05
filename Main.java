package com.coursemgmt.Client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.coursemgmt.DAO.AdminOperation;
import com.coursemgmt.DAO.FacultyOperation;
import com.coursemgmt.DAO.StudentOperation;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
    	Scanner sc=new Scanner(System.in);
    	int choice1 = 0;
    	int choice2=0;
    	int choice3=0;
    	
    	do
    	{   		
    	System.out.println("-----------------------------------------------------Course management System------------------------------------------------------------------");
    	System.out.println("\n1. Admin Login \n2. Student Login \n3. Faculty Login \n4. If you are new Student first register "
    			                     + "\n5. If you are new faculty first register \n0. Exit");
    	System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
    	System.out.println("Enter your choice: ");
        choice1=sc.nextInt();
       
        switch(choice1)
        {
        case 1:
            System.out.println("Enter Username:");
         	String username=sc.next();
         	System.out.println("Enter Password: ");
         	String password=sc.next();
         	
        	if(username.equals("Admin") && password.equals("admin123"))
        	{
        		 AdminOperation  adminop=new AdminOperation();
    			do {				     
    				     System.out.println("--------------------------------------------------Welcome Admin----------------------------------------------------------------"); 
    				     System.out.println("\n1. View Student \n2. Update Student \n3. Delete Student ");
    				     
    				     System.out.println("\n4. View Faculty \n5. Update Faculty \n6. Delete Faculty ");
    				    
    				     System.out.println("\n7. View Module \n8. Add Module \n9. Update Module \n10. Delete Module ");
    				    
    				     System.out.println("\n11. View  Course \n12. Add course \n13. Update Course \n14. Delete Course  ");
    				      	
    				     System.out.println("\n15. Display student course wise \n16. Display student module wise \n17. Display student All Detail  ");
    				     System.out.println("\n18. Allocate course to the student  \n0.Exit");
    				     System.out.println("--------------------------------------------------------------------------------------------------------------------------------");

    				     
    				     System.out.println("Enter your choice");
    				     choice2=sc.nextInt();
    				     switch(choice2)
    				     {
    				     case 1:
    				    	 System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------\n");
    				    	 adminop.listOfStudent(); 
    				    	 System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------\n");
    				    	 break;
    				    	    
    				     case 2:adminop.updateStudent();  break;
    				    	  
    				     case 3:adminop.deleteStudent();   break;
    				    	  
    				     case 4:
    				    	 System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------\n");
    				    	 adminop.listOfFaculty();  
    				    	 System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------\n");
    				    	 break;
    				    	  
    				     case 5:adminop.updateFaculty();   break;
    				    	  
    				     case 6:adminop.deleteFaculty();  break;
    				    	   
    				     case 7:
    				    	 System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------\n");
    				    	 adminop.listOfModule(); 
    				    	 System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------\n");
    				    	 break;
    				    	   
    				     case 8:adminop.addModule();    break;
  				    	       
    				     case 9:adminop.updateModule();   break;
  				    	        
    				     case 10:adminop.deleteModule();  break;
  				    	        
    				     case 11:
    				    	 System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------\n");
    				    	 adminop.listOfCourse();  
    				    	 System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------\n");
    				    	 break;
  				    	        
    				     case 12:adminop.addCourse();  break;
  				    	         
    				     case 13:adminop.updateCourse();   break;
                                
    				     case 14:adminop.deleteCourse();  break;
    				     
    				     case 15:
    				    	 //System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------\n");
    				    	 adminop.listOfStudentCoursewise(); 
    				    	 System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------\n");
    				    	 break;
    				    	 
    				     case 16:
    				    	 //System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------\n");
    				    	 adminop.listOfStudentModuleWise(); 
    				    	 System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------\n");
    				    	 break;
    				    	   
    				     case 17:
    				    	 //System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------\n");
    				    	 adminop.StudentDetails(); 
    				    	 System.out.println("\n-----------------------------------------------------------------------------------------------------------------------------------\n");
    				    	 break;
    				    
    				     case 18:
    				    	 adminop.addcoursetoStudent();
  				    	         break;
    				    
    				     case 0: break;
    				 
    				     }//Admin switch
    				   
    			}while(choice2!=0);
        	}// if
            	break;
        case 2:
        	StudentOperation studop=new StudentOperation();
        	System.out.println("Enter Student mailid:");
         	String mailid1=sc.next();
         	System.out.println("Enter Password: ");
         	String password1=sc.next();
         	
        	 if((studop.login(mailid1,password1)))
        	{
        		do {
     	            System.out.println("------------------------------------------------------Welcome Student--------------------------------------------------------------");
    	    	     System.out.println("\n1. download Assignment \n2. Submint Assignment \n0.Exit");
    	    	    
    	    	     System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
    	    	     System.out.println("Enter your choice");
    	    	     choice3=sc.nextInt();
    	    	     switch(choice3)
    	    	     {
    	    	     case 1:studop.downloadassignment();   break;
    	    	           
    	    	     case 2:studop.submitAssignment();   break;
    	    	    	    
    	    	     case 0:  break;
    	    	     }//student switch
    		    }while(choice3!=0);
        	}// if
        	
        	break;
        case 3:
        	FacultyOperation facultyop=new FacultyOperation();
        	System.out.println("Enter faculty mailid:");
         	String mailid=sc.next();
         	System.out.println("Enter Password: ");
         	String password2=sc.next();
         	
        	 if((facultyop.login(mailid,password2)))
        	{
    			int choice4 = 0;
    			do {
    				System.out.println("----------------------------------------------------------Welcome Faculty---------------------------------------------------------------");
    	    	     System.out.println("\n1. Upload Assignment \n2. Check Assignment \n0. Exit \n");
    	    	     System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
    	    	     System.out.println("Enter your choice");
    	    	     choice4=sc.nextInt();
    	    	     switch(choice4)
    	    	     {
    	    	     case 1:facultyop.uploadAssignment();   break;
    	    	           
    	    	     case 2:facultyop.checkAssignments();  break;
    	    	    	   
    	    	     case 0:   break;    
    	    	     }
    			}while(choice4!=0);
        	}//else if
        	
              break;
              
        case 4:StudentOperation studop1=new StudentOperation();
        	   studop1.addStudent();
               break;
               
        case 5:FacultyOperation facultyop1=new FacultyOperation();
        	   facultyop1.addFaculty();
               break;
               
        case 0:System.exit(0);
        	   break;
        }
    }while(choice1!=0);
	}//mainmethod
}//class
