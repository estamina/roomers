import java.net.*;
import java.io.*;

public class EchoServerOneClient {
    public static final int ECHO_PORT=7;

    public static void main(String[] args) throws Exception {
        //Pomocou tohto socketu cakam na pripojenie - na porte - echo 7
        ServerSocket socket = new ServerSocket(ECHO_PORT);

        //Pomocou tohto socketu komunikujem s klientom
        Socket clientSocket;

        try{
            //Ak sa niekto napoji tak to akceptujem
            clientSocket = socket.accept();

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

            //Uzavriem server socket
            socket.close();
        }catch (Exception e){
            System.out.println("Chyba "+e.getMessage());
        }
    }
}
