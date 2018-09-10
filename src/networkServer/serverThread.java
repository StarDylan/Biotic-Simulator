package networkServer;

public class serverThread extends Thread {

    public void run() {
        JavaHTTPServer.runServer();
    }

}
