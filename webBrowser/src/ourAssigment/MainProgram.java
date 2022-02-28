package ourAssigment;

import java.util.List;
import java.util.Scanner;

import ourAssigment.socket;

public class MainProgram {
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
//	      Link:monta.if.its.ac.id/index.html
	      String yourUri = myObj.nextLine();
	      String[] arrOfStr = yourUri.split("/", 2);
	      for (String a : arrOfStr) {
	    	  x++;
	      	  if(x==1)
	            domain=a;
	      	  else {
	      		subdomain=a;
	      	  }
	      }
		
		for(;;) {
//open	
			System.out.print("your choice:");
		    String choice = myObj.nextLine();  // Read user input
		    switch(choice) {
		    case "1":
		      Socket=new socket(domain,subdomain);
		      String haha=Socket.command1();
		      System.out.println(haha);
		      
		      
		      break;
		    case "2":
		      Socket=new socket(domain,subdomain);
		      String baru=Socket.command1();
			  Socket.command2(baru);
		      // code block
		      break;
		    case "3":
			      Socket=new socket(domain,subdomain);
			      String baru1=Socket.command1();
			      List<String> list3=Socket.command2(baru1);
			      //tinggal nerusin
			      // code block
			      break;
		    default:
		      // code block
		  }
//end
			
		}
	    		
	}
}
