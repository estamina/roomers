import java.net.*;
import java.io.*;

public class URLReader {
	public static final String resource="http://hornad.fei.tuke.sk/kpi/index.html";

    public static void main(String[] args) throws Exception {
	URL url = new URL(resource);
	InputStream in=url.openStream();

        int c;
        while((c=in.read())!=-1){
            System.out.print((char)c);
        }

	in.close();
    }
}

