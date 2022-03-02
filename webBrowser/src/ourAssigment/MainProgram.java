package ourAssigment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ourAssigment.socket;

public class MainProgram {
	public static URL getFinalURL(URL url) {
	    try {
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setInstanceFollowRedirects(false);
	        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
	        con.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
	        con.addRequestProperty("Referer", "https://www.google.com/");
	        con.connect();
	        //con.getInputStream();
	        int resCode = con.getResponseCode();
	        if (resCode == HttpURLConnection.HTTP_SEE_OTHER
	                || resCode == HttpURLConnection.HTTP_MOVED_PERM
	                || resCode == HttpURLConnection.HTTP_MOVED_TEMP) {
	            String Location = con.getHeaderField("Location");
	            if (Location.startsWith("/")) {
	                Location = url.getProtocol() + "://" + url.getHost() + Location;
	            }
	            return getFinalURL(new URL(Location));
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }
	    return url;
	}
	
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
	
	public static void main(String[] args) throws IOException {
		String Myurl=null;
	    String subMyurl = null;
	    socket Socket;
	    
	      
		System.out.println("Choose command hahah:");
		System.out.println("1.Open a web page given uri and show the text");
		System.out.println("2.Show a list clickable link");
		System.out.println("3.Download File regardless a size");
		System.out.println("5.follow redirections");
		System.out.println("6.Show respective HTTP error message");
		System.out.println("7.Open a web page is protected by HTTP Basic authentication");
		System.out.println("8.can access a web page that is protected behind a login page");
		
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		int x=0;
	      System.out.print("Link:");
//	      Link:https://monta.if.its.ac.id/index.php/berita/lihatberita
	      String yourUri = myObj.nextLine();
	      Myurl=divide(yourUri).get(0);
	      subMyurl=divide(yourUri).get(1);
	      
		
		for(;;) {
//open	
			System.out.print("your choice:");
		    String choice = myObj.nextLine();  // Read user input
		    switch(choice) {
		    case "1":
		      Socket=new socket(Myurl,subMyurl);
		      String haha=Socket.command1(subMyurl);
		      System.out.println(haha);
		      break;
		    case "2":
		      Socket=new socket(Myurl,subMyurl);
		      String baru=Socket.command1(subMyurl);
		      List<String> list4=Socket.command2(baru,1);
//		      for(int i=0;i<list4.size();i++){
//		    	    System.out.println(list4.get(i));
//		      } 
		      // code block
		      break;
		    case "3":
			      Socket=new socket(Myurl,subMyurl);
			      String baru1=Socket.command1(subMyurl);
			      List<String> list3=Socket.command2(baru1,0);
			      List<String> tmpNew1=new ArrayList<>();
			      List<String> tmpNew2=new ArrayList<>();
			      
			      for(int i=0;i<list3.size();i++){
			    	    if(list3.get(i).length()<Myurl.length())continue;
			    	    String tmp1=divide(list3.get(i)).get(0);
			    	    String tmp2=divide(list3.get(i)).get(1);
			    	    tmpNew1.add(tmp1);
			    	    tmpNew2.add(tmp2);
			    	    
			      } 
			      try {
			    	  for(int i=0;i<tmpNew2.size();i++){
//				    	    System.out.println(tmpNew2.get(i));
				      }   
					Socket.command3(tmpNew1,tmpNew2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      // code block
			      break;
		    case "5":
		    	  Socket=new socket(Myurl,subMyurl);
			      String redirect=Socket.command1(subMyurl);
			      if(redirect.indexOf("Refresh: 0;url=")>=0) {
			    	  
			      
			      redirect = redirect.substring(redirect.indexOf("Refresh: 0;url=")+15);
			      String[] redirect1 = redirect.split("Content");
			      Myurl=divide(redirect1[0]).get(0);
			      subMyurl=divide(redirect1[0]).get(1);
			      Socket.command5(Myurl, subMyurl);
			      }else  System.out.println("nothing redirect url");
			     
			   
		    	  break;
		    case "6":
		    	  Socket=new socket(Myurl,subMyurl);
			      String case5=Socket.command1(subMyurl);
			      case5 = case5.substring(case5.indexOf(" ")+1);
			      case5 = case5.substring(0,3);
//			      System.out.print(case5);
			      if(case5.indexOf("400")>=0) {
			    	  System.out.print("Bad Request");
			      }
			      else if(case5.indexOf("401")>=0) {
			    	  System.out.print("Unauthorized");
			      }
			      else if(case5.indexOf("402")>=0) {
			    	  System.out.print("Payment Required ");
			      }
			      else if(case5.indexOf("403")>=0) {
			    	  System.out.print("Forbidden");
			      }
			      else if(case5.indexOf("404")>=0) {
			    	  System.out.println("Page Not Found");
			      }
			      else if(case5.indexOf("500")>=0) {
			    	  System.out.print("Not Implemented");
			      }
			      else if(case5.indexOf("501")>=0) {
			    	  System.out.print("Not Implemented");
			      }
			      else if(case5.indexOf("502")>=0) {
			    	  System.out.print("Bad Gateway");
			      }
			      else if(case5.indexOf("503")>=0) {
			    	  System.out.print("Service Unavailable");
			      }
			      else if(case5.indexOf("504")>=0) {
			    	  System.out.print("Gateway Timeout");
			      }
		    	  break;
		      // code block
		    case "7":
		    	 Socket=new socket(Myurl,subMyurl);
			      String case6=Socket.command1(subMyurl);
			      List<String> list6=Socket.command2(case6,0);
			      List<String> tmpNew61=new ArrayList<>();
			      List<String> tmpNew62=new ArrayList<>();
			      
			      for(int i=0;i<list6.size();i++){
			    	    if(list6.get(i).length()<Myurl.length())continue;
			    	    String tmp1=divide(list6.get(i)).get(0);
			    	    String tmp2=divide(list6.get(i)).get(1);
			    	    tmpNew61.add(tmp1);
			    	    tmpNew62.add(tmp2);
			    	    
			      }
			      try {
					Socket.command7(tmpNew61,tmpNew62);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	  break;
		      // code block
		    case "test":
		    	System.out.println(getFinalURL(new URL("http://monta.if.its.ac.id/index.php/berita/detailBerita/257")).toString());
//		    	 Socket=new socket(Myurl,subMyurl);
//			     String hahi=Socket.command1(subMyurl);
//			     hahi = hahi.substring(hahi.indexOf(" ")+1);
//			     hahi = hahi.substring(0, hahi.indexOf("Date"));
//			     System.out.println(hahi);
		    default:
		  }
//end
			
		}
	    		
	}
}
