package Host_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Host {
    
    public static void main(String[] args) {


        try{
            Socket socket = null;
            BufferedReader in = null;
            BufferedWriter out = null;
            ServerSocket serverSocket = new ServerSocket(60123);
            BufferedReader terminal_out = null;
            String message_from_client;

            System.out.println("Searching for connection...");

            try{
                while (true) {

                    
                    socket = serverSocket.accept();
                    
                    
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    // if(socket.isConnected()){
                    //     out.write("Connected");
                    //     out.newLine();
                    //     out.flush();
                    // }

                    while (socket.isConnected()){
                        message_from_client = in.readLine();

                        System.out.println("Client: " + message_from_client);
                        

                        if (message_from_client.equalsIgnoreCase("IP")){

                            System.out.println("getting ip");

                            Process get_ip = Runtime.getRuntime().exec("powershell.exe ipconfig");

                            terminal_out = new BufferedReader(new InputStreamReader(get_ip.getInputStream()));

                            String temp = terminal_out.readLine();

                            while (temp != null) {

                                System.out.println(temp);

                                out.write(temp);
                                out.newLine();
                                
                                temp = terminal_out.readLine();

                                if (temp == null) {
                                    out.write("Done");
                                    out.newLine();
                                    out.flush();
                                }
                            }   

                        } else if (message_from_client.equalsIgnoreCase("CONNECTED")){

                            out.write("You are connected to the server");
                            out.newLine();
                            out.flush();

                        } else{
                            out.write("msg receved");
                            out.newLine();
                            out.flush();

                        }

                        if(message_from_client.equalsIgnoreCase("EXIT")){
                            
                            socket.close();
                            in.close();
                            out.close();

                            break;
                        }
                    }
            
            }

            

            }catch(Exception error){
                System.out.println("\nError 1: " + error + "\n");
                socket.close();
                in.close();
                out.close();
                serverSocket.close();

                System.out.println("Client disconnected, restarting search");

                main(args);

            }

        }catch(Exception error){
            System.out.println("Error 2:" + error);
            

        }
    }
    
}
