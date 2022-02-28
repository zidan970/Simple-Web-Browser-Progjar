package aplikasi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class socket {
	public static void main(String[] args) {
		try {

			Socket socket = new Socket ("monta.if.its.ac.id", 80);

			// step 2: obtain the input and output streams
			BufferedInputStream bis = new BufferedInputStream (socket.getInputStream());
/*2*/			//DataInputStream dis = new DataInputStream (socket.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream (socket.getOutputStream());

			// step 3: exchange messages
			System.out.println("l");
			bos.write("GET /index.php/berita/lihatberita HTTP/1.1\r\nHost: monta.if.its.ac.id\r\n\r\n".getBytes());
			bos.flush();
			System.out.println("2");

			int bufferSize = 100;
			byte[] bResp = new byte[bufferSize];
			int c = bis.read(bResp);
/*2*/			//String resp = dis.readLine();
//			resp.split(" ");
			String resp="";

			while(c!=-1)
			{
			    resp += (new String(bResp));
			    c = bis.read(bResp);
			}

			System.out.println("3");
///*1*/		System.out.println(resp);
			

			String[] arrOfStr = resp.split("a>");
			String tmp="";
			String tmp1="";

            // link
			List<String> list1 = new ArrayList<>();
            // judul
			List<String> list2 = new ArrayList<>();
			
			int x=0;
			
		    /*2*/
		    for (String a: arrOfStr) {
		    	tmp1 = a.substring(a.indexOf("<a")+2);
		    	tmp1 = tmp1.substring(tmp1.indexOf("href") + 6);
			    tmp1 = tmp1.substring(0, tmp1.indexOf("<"));
//			    System.out.println(tmp1+"."+x++);

			   			    
			    if(tmp1.charAt(tmp1.length()-1)=='>' | tmp1.charAt(tmp1.length()-1)==' ') {
			    	tmp1 = tmp1.substring(tmp1.indexOf("<a")+1);
			    	tmp1 = tmp1.substring(0, tmp1.indexOf(">")+1);
			    	tmp1=tmp1.replace('"', ' ');
			    	tmp1=tmp1.replace('>', ' ');
			    	list1.add(tmp1);
				    list2.add("empty....");
//				    System.out.println("list0="+tmp1+"list1="+"empty....");
			    }
			    else {
			    	String[] parts = tmp1.split(">", 2);
			    	if(parts[0].indexOf( ' ' )<0) {
//			    		true
			    		parts[0] = parts[0].substring(0, parts[0].indexOf('"'));
			    		list1.add(parts[0]);
					    list2.add(parts[1]);
//					    System.out.println("list0="+parts[0]+"list1="+parts[1]);
			    	}else {
//			    		false
			    		parts[0] = parts[0].substring(0, parts[0].indexOf('"'));
			    		list1.add(parts[0]);
					    list2.add(parts[1]);
//					    System.out.println("list0="+parts[0]+"list1="+parts[1]);
			    	}
				    
			    }
			    	
//			    System.out.println(tmp1+".");
		    }
			System.out.println("4");
			System.out.println("5");
			
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
