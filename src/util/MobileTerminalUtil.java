package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.MessageEntity;
import entity.UserEntity;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MobileTerminalUtil implements ServletContextListener {

    public static ServerSocket serverSocket;
    public static boolean hasServerSocketStarted = false;
    public static HashMap<String, String> userEmail2Ip = new HashMap<>();
    public static HashMap<String, Integer> ip2RemotePort = new HashMap<>();
    public static MessageEntity serverIpPortInfo;
    private static final String ip = "192.168.123.217";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        startServerSocket();
        listenServerPort();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        stopServerSocket();
    }

    public void startServerSocket() {
        if (!hasServerSocketStarted) {
            try {
                serverSocket = new ServerSocket(0); // System will allocate an available port to serverSocket.
                hasServerSocketStarted = true;
                System.out.println("Start server socket(Push Service): [localhost:" + serverSocket.getLocalPort() + "]");
                serverIpPortInfo = new MessageEntity();
                serverIpPortInfo.setSourcePort(serverSocket.getLocalPort() + "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Server socket has started: [localhost:" + serverSocket.getLocalPort() + "]");
        }
    }

    public void listenServerPort() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (hasServerSocketStarted == false) {
                        break;
                    }
                    try {
                        Socket clientSocket = serverSocket.accept();
                        String clientIp = clientSocket.getInetAddress().toString();
                        clientIp = clientIp.substring(1, clientIp.length());

                        // Read client message contained client port, then save client port
//                      ExecutorService executor = Executors.newCachedThreadPool();
                        Task task = new Task(clientSocket);
                        task.messageToClient = "Got server IP and port.";
                        FutureTask<String> futureTask = new FutureTask<>(task);
                        new Thread(futureTask).start();
                        String messageFromClient = null;
                        try {
                            messageFromClient = futureTask.get();
                            System.out.println("Client port: " + messageFromClient);
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                        String clientPort = saveClientPort(messageFromClient);
//                ip2ClientPort.put(clientIp, clientSocket);
                        System.out.println("Save client socket (Push Service): [" + clientIp + ":" + clientPort + "]");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private String saveClientPort(String messageFromClient) {
        MessageEntity messageEntity = new Gson().fromJson(messageFromClient, MessageEntity.class);
        System.err.println(messageEntity.toString());
        return messageEntity.getSourcePort();
    }

    public void stopServerSocket() {
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

    public void pushMessage(MessageEntity messageEntity, UserEntity userEntity) {
        String remoteIp = userEmail2Ip.get(userEntity.getEmail());
        int remotePort = ip2RemotePort.get(remoteIp);
        Socket socket = null;
        OutputStreamWriter out = null;
        try {
            socket = new Socket(remoteIp, remotePort);
            Gson gson = new GsonBuilder().disableHtmlEscaping().create();
            out = new OutputStreamWriter(socket.getOutputStream());
            out.write(gson.toJson(messageEntity));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Task implements Callable<String> {
        Socket socketAccepted;
        String messageToClient;

        public Task(Socket socketAccepted) {
            this.socketAccepted = socketAccepted;
        }

        @Override
        public String call() {
            InputStream is = null;
            InputStreamReader isr = null;
            BufferedReader br = null;
            OutputStream os = null;
            PrintWriter out = null;
            String messageFromClient;
            try {
                // Get input stream
                is = socketAccepted.getInputStream();
                isr = new InputStreamReader(is);// byte stream
                br = new BufferedReader(isr);// character stream

                // Read a line from client
                messageFromClient = br.readLine();
                System.out.println("Server Task got message: " + messageFromClient);
                // Close input stream
                socketAccepted.shutdownInput();

                // Get output stream

                os = socketAccepted.getOutputStream();
                out = new PrintWriter(os);
                out.write(messageToClient);
                out.flush();
                return messageFromClient;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                // Close resources
                try {
                    if (out != null)
                        out.close();
                    if (os != null)
                        os.close();
                    if (br != null)
                        br.close();
                    if (isr != null)
                        isr.close();
                    if (is != null)
                        is.close();
                    if (socketAccepted != null)
                        socketAccepted.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
