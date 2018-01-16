package util;

import entity.UserEntity;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MobileTerminalUtils implements ServletContextListener {

    public static ServerSocket serverSocket;
    public static boolean hasServerSocketStarted = false;
    public static HashMap<String, String> userEmail2Ip = new HashMap<>();
    public static HashMap<String, Socket> ip2ClientSocket = new HashMap<>();

    public static void startServerSocket() {
        if (!hasServerSocketStarted) {
            try {
                serverSocket = new ServerSocket(0); // System will allocate an available port to serverSocket.
                hasServerSocketStarted = true;
                System.out.println("Start server socket(Push Service): [localhost:" + serverSocket.getLocalPort() + "]");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Server socket has started: [localhost:" + serverSocket.getLocalPort() + "]");
        }
    }

    public static void listenServerPort() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (hasServerSocketStarted == false) {
                        break;
                    }
                    try {
                        Socket clientSocket = serverSocket.accept();
                        String ip = clientSocket.getInetAddress().toString();
                        ip = ip.substring(1, ip.length());
                        ip2ClientSocket.put(ip, clientSocket);
                        System.out.println("Save client socket (Push Service): [" + ip + ":" + clientSocket.getPort() + "]");
                        OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream());
                        out.write("{\"from\":\"Server\",\"msg\":\"Hello1 " + Math.random() * 100 + "\"}" + "\n");
                        out.flush();
                        System.out.println("clientSocket.isClosed(): " + clientSocket.isClosed());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static void stopServerSocket() {
        if (hasServerSocketStarted == true) {
            try {
                System.out.println("Stop server socket: [localhost:" + serverSocket.getLocalPort() + "]");
                serverSocket.close();
                hasServerSocketStarted = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopClientSocket() {
        for (Map.Entry entry : ip2ClientSocket.entrySet()) {
            Socket s = (Socket) entry.getValue();
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean pushMessage(UserEntity userEntity, String message) {
        Socket clientSocket = ip2ClientSocket.get(userEntity.getEmail());
        OutputStreamWriter out = null;
        if (clientSocket != null) {
            try {
                out = new OutputStreamWriter(clientSocket.getOutputStream());
                out.write("{\"from\":\"Server\",\"message\":\"Hello-" + (int) Math.random() * 100 + "\"}");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Initial Push Service: " + getClass());
        startServerSocket();
        listenServerPort();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Destroy Push Service: " + getClass());
        stopServerSocket();
        stopClientSocket();
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.1.103", 49380);
        OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
        out.write("{\"from\":\"Server\",\"msg\":\"Hello: 2" + Math.random() * 100 + "\"}");
        out.flush();
        out.close();
    }
}
