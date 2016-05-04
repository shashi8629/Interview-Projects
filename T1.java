import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class T1 {
    public static void main(String[] args) throws Exception {

        final String host = "localhost";
        final InetAddress inetAddress = InetAddress.getByName(host);
        final String hostName = inetAddress.getHostName();
        final List<Integer> openPorts = new ArrayList<Integer>();

        // we begin at port 1 because port 0 is never used
        Socket socket=null;
        for (int port = 1; port <= 200; port++) {
            try {
                 socket = new Socket(hostName, port);
                openPorts.add(port);
            } catch (IOException e) {
            } finally {
            	
                socket.close();
            }
        }
    }
}
