import java.io.*;
import java.net.*;

public class EchoClient {
    public static final int ECHO_PORT=7;
    
    public static void main(String[] args) throws IOException {
        //Pomocou tohto socketu komunikujem so serverom
        Socket socket;

        try {
            socket = new Socket("localhost", ECHO_PORT);
            
            //Ziskam streamy - vstupny (Reader) a vystupny (Writer) - pracujem so znakmi
            //Kedze socket pracuje so stream-ami bajtovymi prevediem ich na znakove 
            //pomocou InputStreamReader a OutputStreamWriter - a.
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
            BufferedReader stdIn=new BufferedReader(new InputStreamReader(System.in));
            
            String line;
            //Precitam riadok zo standardneho vstupu
            while ((line = stdIn.readLine()) != null) {
                                
                //Zapisem riadok
                out.write(line+"\n");
                                
                //Vyprazdnim buffer a tym prinutim poslat
                out.flush();
                
                //Precitam co poslal server
                line=in.readLine();
                
                //Vypis pre testovanie
                System.out.println("echo: "+line);
            }
            
            //Uzavriem streamy
            in.close();
            out.close();
            stdIn.close();
            
            //Uzavriem  socket
            socket.close();
        } catch (Exception e) {
            System.out.println("Chyba "+e.getMessage());
        }
    }
}
