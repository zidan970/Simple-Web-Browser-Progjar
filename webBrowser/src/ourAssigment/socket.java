package ourAssigment;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class socket {
	String Domain,Subdomain;
	String htmlContain=null;
	
	public socket(String domain,String subdomain) {
		Domain=domain;
		Subdomain=subdomain;
	}
	
	
	
	public String command1(String subdomain) {
		try {
			Subdomain=subdomain;
			System.out.println(Subdomain);

			Socket socket = new Socket (Domain, 80);

			// step 2: obtain the input and output streams
			BufferedInputStream bis = new BufferedInputStream (socket.getInputStream());
/*2*/			//DataInputStream dis = new DataInputStream (socket.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream (socket.getOutputStream());

			// step 3: exchange messages
			System.out.println("l");
			String URUR="GET /"+Subdomain+" HTTP/1.1\r\nHost: "+Domain+"\r\n\r\n";
			bos.write(URUR.getBytes());
//			bos.write("GET /index.php/berita/lihatberita HTTP/1.1\r\nHost: monta.if.its.ac.id\r\n\r\n".getBytes());
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
/*1*/		//System.out.println(resp);
			return resp;

			

			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	
public List<String> command2(String baru,int command) {
		
		String[] arrOfStr = baru.split("a>");
		String[] tmpSplit;
		String tmp="";
		String tmp1="";
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		
		int x=0;
		
	    /*2*/
	    for (String a: arrOfStr) {
	    	tmp1 = a.substring(a.indexOf("<a")+2);
	    	tmp1 = tmp1.substring(tmp1.indexOf("href") + 6);
		    tmp1 = tmp1.substring(0, tmp1.indexOf("<"));
//		    System.out.println(tmp1+"."+x++);

		   			    
		    if(tmp1.charAt(tmp1.length()-1)=='>' | tmp1.charAt(tmp1.length()-1)==' ') {
		    	tmp1 = tmp1.substring(tmp1.indexOf("<a")+1);
		    	tmp1 = tmp1.substring(0, tmp1.indexOf(">")+1);
		    	
		    	tmp1 = tmp1.substring(0, tmp1.indexOf('"')+1);
//		    	String[] arrOfStr1 = tmp1.split(""+'"', 1);
//		    	tmp1=arrOfStr1[0];
		    	tmp1=tmp1.replace('"', ' ');
//		    	tmp1=tmp1.replace('>', ' ');
		    	list1.add(tmp1);
			    list2.add("empty....");
			    if(command==1)System.out.println("url="+tmp1+" text="+"empty....");
		    }
		    else {
		    	String[] parts = tmp1.split(">", 2);
		    	if(parts[0].indexOf( ' ' )<0) {
//		    		true
		    		parts[0] = parts[0].substring(0, parts[0].indexOf('"'));
		    		list1.add(parts[0]);
				    list2.add(parts[1]);
				    if(command==1)System.out.println("url="+parts[0]+" text="+parts[1]);
		    	}else {
//		    		false
		    		parts[0] = parts[0].substring(0, parts[0].indexOf('"'));
		    		list1.add(parts[0]);
				    list2.add(parts[1]);
				    if(command==1)System.out.println("url="+parts[0]+" text="+parts[1]);
		    	}
			    
		    }
		    	
	    }
		System.out.println("4");
		System.out.println("5");
		return list1;

	}

	public void command3(List<String> list1,List<String> list) throws IOException {
		
        for(int i=0;i<list.size();i++){
        	File f = new File("src/hasil/file"+i+".html");
        	Domain=list1.get(i);
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            String text=command1(list.get(i));
            if(text==null)continue;
            text = text.substring(text.indexOf("<!")+2);
//            System.out.println("<!"+text);
            bw.write("<!"+text);
            bw.close();
 
        } 
        
        
	}
	
	public void command5(List<String> list1,List<String> list) throws IOException {
        for(int i=0;i<list.size();i++){
        	Domain=list1.get(i);
            String text=command1(list.get(i));
            if(text==null)continue;
            if(text.indexOf("Date")<0)continue;
            text = text.substring(text.indexOf(" ")+1);
            text = text.substring(0, text.indexOf("Date"));
            System.out.println("HTTP Message:"+text+"URL="+Domain+"/"+Subdomain);
        } 
	}
	public void command6(List<String> list1,List<String> list) throws IOException {
        for(int i=0;i<list.size();i++){
        	Domain=list1.get(i);
            String text=command1(list.get(i));
            if(text==null)continue;
            if(text.indexOf("Date")<0)continue;
            text = text.substring(text.indexOf(" ")+1);
            text = text.substring(0, text.indexOf("Date"));
            
            if(text.indexOf("401")>=0) {
            	System.out.println("HTTP Message:"+text+"URL="+Domain+"/"+Subdomain);
            	System.out.println(command1(list.get(i)));
            }
            	
        } 
	}
	

}