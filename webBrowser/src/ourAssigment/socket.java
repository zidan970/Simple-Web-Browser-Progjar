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
import java.util.concurrent.TimeUnit;

public class socket {
	String MyUrl,MyUrn;
	String htmlContain=null;
	
	public socket(String myUrl,String myUrn) {
		MyUrl=myUrl;
		MyUrn=myUrn;
	}
	
	
	
	public String command1(String myUrn) {
		try {
			MyUrn=myUrn;
//			System.out.println(MyUrn);

			Socket socket = new Socket (MyUrl, 80);
			BufferedInputStream bis = new BufferedInputStream (socket.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream (socket.getOutputStream());
			System.out.println("loading...");
			String URUR="GET /"+MyUrn+" HTTP/1.1\r\nHost: "+MyUrl+"\r\n\r\n";
			bos.write(URUR.getBytes());
			bos.flush();
			int bufferSize = 100;
			byte[] bResp = new byte[bufferSize];
			int c = bis.read(bResp);
			String resp="";

			while(c!=-1)
			{
			    resp += (new String(bResp));
			    c = bis.read(bResp);
			}
			System.out.println("end...");
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
		return list1;

	}

	public void command3(List<String> list1,List<String> list) throws IOException {
		
		String newlist;
        for(int i=0;i<list.size();i++){
        	if(list.get(i).indexOf(".pdf")>=1|list.get(i).indexOf(".jpg")>=1) ;
        	else continue;
        	
        	newlist=list.get(i);
        	String newlist1=newlist;
        	newlist1 =newlist1.substring(newlist1.indexOf('.'));
        	File f = new File("src/hasil/file"+i+newlist1);
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
      
            newlist =newlist.substring(0, newlist.indexOf(' '));
            String text=command1(newlist);
            if(text==null)continue;
//            text = text.substring(text.indexOf("%")+2);
            bw.write(text);
            bw.close();
            
 
        } 
        
        
	}
	public void command5(String list1,String list) throws IOException {
        	MyUrl=list1;
        	try {
				TimeUnit.SECONDS.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String text=command1(list);
            System.out.println(text);
         
	}
	
	public void command6(List<String> list1,List<String> list) throws IOException {
        for(int i=0;i<list.size();i++){
        	MyUrl=list1.get(i);
            String text=command1(list.get(i));
            if(text==null)continue;
            if(text.indexOf("Date")<0)continue;
            text = text.substring(text.indexOf(" ")+1);
            text = text.substring(0, text.indexOf("Date"));
            System.out.println("HTTP Message:"+text+"URL="+MyUrl+"/"+MyUrn);
        } 
	}
	public void command7(List<String> list1,List<String> list) throws IOException {
        for(int i=0;i<list.size();i++){
        	MyUrl=list1.get(i);
            String text=command1(list.get(i));
            if(text==null)continue;
            if(text.indexOf("Date")<0)continue;
            text = text.substring(text.indexOf(" ")+1);
            text = text.substring(0, text.indexOf("Date"));
            
            if(text.indexOf("401")>=0) {
            	System.out.println("HTTP Message:"+text+"URL="+MyUrl+"/"+MyUrn);
            	System.out.println(command1(list.get(i)));
            }
            	
        } 
	}
	

}