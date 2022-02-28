package ourAssigment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ourAssigment.socket;

public class MainProgram {
	
	public static List<String> divide(String text) {
		int x=0;
		List<String> us=new ArrayList<>();
		text = text.substring(text.indexOf("//")+2);
		String[] arrOfStr = text.split("/", 2);
	    for (String a : arrOfStr) {
	    	  us.add(a);
	    }
		return us;
		
	}
	
	public static void main(String[] args) {
		String domain=null;
	    String subdomain = null;
	    socket Socket;
	    
	      
		System.out.println("Choose command hahah:");
		System.out.println("1.Open a web page given uri and show the text");
		System.out.println("2.Show a list clickable link");
		System.out.println("3.Download File regardless a size");
		
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		int x=0;
	      System.out.print("Link:");
//	      Link:https://monta.if.its.ac.id/index.html
	      String yourUri = myObj.nextLine();
	      domain=divide(yourUri).get(0);
	      subdomain=divide(yourUri).get(1);
	      
		
		for(;;) {
//open	
			System.out.print("your choice:");
		    String choice = myObj.nextLine();  // Read user input
		    switch(choice) {
		    case "1":
		      Socket=new socket(domain,subdomain);
		      String haha=Socket.command1(subdomain);
		      System.out.println(haha);
		      
		      
		      break;
		    case "2":
		      Socket=new socket(domain,subdomain);
		      String baru=Socket.command1(subdomain);
		      List<String> list4=Socket.command2(baru,1);
//		      for(int i=0;i<list4.size();i++){
//		    	    System.out.println(list4.get(i));
//		      } 
		      // code block
		      break;
		    case "3":
			      Socket=new socket(domain,subdomain);
			      String baru1=Socket.command1(subdomain);
			      List<String> list3=Socket.command2(baru1,0);
			      List<String> tmp=new ArrayList<>();
			      
			      for(int i=0;i<list3.size();i++){
			    	    if(list3.get(i).length()<domain.length())continue;
			    	    String tmp1=divide(list3.get(i)).get(1);
			    	    tmp.add(tmp1);
			    	    
			      } 
			      try {
			    	  for(int i=0;i<tmp.size();i++){
//				    	    System.out.println(tmp.get(i));
				      }   
					Socket.command3(tmp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      // code block
			      break;
		    default:
		      // code block
		  }
//end
			
		}
	    		
	}
}
