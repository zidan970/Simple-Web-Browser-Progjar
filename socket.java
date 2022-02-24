Socket socket = new Socket ("monta.if.its.ac.id", 80);

// step 2: obtain the input and output streams
BufferedInputStream bis = new BufferedInputStream (socket.getInputStream());
BufferedOutputStream bos = new BufferedOutputStream (socket.getOutputStream());

// step 3: exchange messages
System.out.println("l");
bos.write("HELLO WORLD\r\h\r\n".getBytes());
bos.flush();
System.out.println("2");

int bufferSize = 100;
byte[] bResp = new byte[buffersize];
int c = bis.red(bRead);
String resp = "";

while(c!=-1)
{
    resp += (new String(bResp));
    c = bis.read(bResp);
}

System.out.println("3");
System.out.println(resp);
System.out.println("4");