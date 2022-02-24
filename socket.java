package aplikasi;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class socket {
	public static void main(String[] args) {
		try {

			Socket socket = new Socket ("monta.if.its.ac.id", 80);

			// step 2: obtain the input and output streams
			DataInputStream dis = new DataInputStream (socket.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream (socket.getOutputStream());

			// step 3: exchange messages
			System.out.println("l");
			bos.write("GET /index.php/berita/lihatberita HTTP/1.1\r\nHost: monta.if.its.ac.id\r\n\r\n".getBytes());
			bos.flush();
			System.out.println("2");

			int bufferSize = 100;
//			byte[] bResp = new byte[buffersize];
//			int c = bis.red(bRead);
			String resp = dis.readLine();
			resp.split(" ");

//			while(c!=-1)
//			{
//			    resp += (new String(bResp));
//			    c = bis.read(bResp);
//			}

			System.out.println("3");
			System.out.println(resp);
			System.out.println("4");
			
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
