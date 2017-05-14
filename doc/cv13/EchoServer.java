import java.net.*;
import java.io.*;

public class EchoServer extends Thread {
    public static final int ECHO_PORT=7;
    
    //Pomocou tohto socketu komunikujem s klientom
    Socket clientSocket;

    public EchoServer(Socket clientSocket){
        this.clientSocket=clientSocket;
    }
    
    public void run() {
        try{
            //Ziskam streamy - vstupny (Reader) a vystupny (Writer) - pracujem so znakmi
            //Kedze socket pracuje so stream-ami bajtovymi prevediem ich na znakove 
            //pomocou InputStreamReader a OutputStreamWriter - a.
            //Navyse chcem citam po riadkoch tak budem pouzivat buferovany vstup - readLine
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream());
            
            String line;
            //Precitam riadok
            while ((line = in.readLine()) != null) {
                //Zapisem riadok
                out.write(line+"\n");
                //Vyprazdnim buffer a tym prinutim poslat
                out.flush();
                
                //Vypis pre testovanie
                System.out.println("echo: "+line);
            }
            
            //Uzavriem streamy
            in.close();
            out.close();
            
            //Uzavriem klientsky socket
            clientSocket.close();
            
        }catch (Exception e){
            System.out.println("Chyba "+e.getMessage());
        }
    }    
    
    public static void main(String[] args){
        try{
            //Pomocou tohto socketu cakam na pripojenie - na porte - echo 7
            ServerSocket socket = new ServerSocket(ECHO_PORT);

            //Nekonecny cyklus na servery - obsluhy
            while(true){
                Socket acceptedSocket = socket.accept();
                //Vytvaram vlakno obsluhy
                EchoServer es = new EchoServer(acceptedSocket);
                //a zacnem obsluhu
                es.start();
            }
        }catch (Exception e){
            System.out.println("Chyba "+e.getMessage());
        }
    }        
}
