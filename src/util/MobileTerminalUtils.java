package util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MobileTerminalUtils {

    public static ServerSocket serverSocket;
    private static InetAddress inetAddress;
    static {
        try {
            serverSocket = new ServerSocket(0); // System will allocate an available port to serverSocket.
            System.out.println("Start server socket: " + "isBound: " + serverSocket.isBound() + ", " +
                    serverSocket.getLocalSocketAddress() + ":" + serverSocket.getLocalPort());
            startListening();
            inetAddress = InetAddress.getLocalHost();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void startListening() throws IOException {
//        while (true) {
//        Socket socket = serverSocket.accept();
//        System.out.println("Client socket connected: " + socket.getInetAddress() + ":" + socket.getPort());
//        }
    }

    public static void main(String[] args) throws IOException {
//        MobileTerminalUtils.serverSocket.isBound();
//        serverSocket.close();
        System.out.println(inetAddress.getHostAddress());
        System.out.println(serverSocket.isBound());
    }
}
