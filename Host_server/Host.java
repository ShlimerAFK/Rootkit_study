package Host_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Host {
    
    public static void main(String[] args) {

        System.out.println("Starting...");

        try{
<<<<<<< HEAD:Host_server/Test_1.java
            Socket socket = null;
            BufferedReader in = null;
            BufferedWriter out = null;
            ServerSocket serverSocket = new ServerSocket(60123);
            BufferedReader terminal_out = null;         
=======
            Socket socket;
            BufferedReader in;
            BufferedWriter out;
            ServerSocket serverSocket = new ServerSocket(445);
            BufferedReader terminal_out;
>>>>>>> 1e1fff04925c7176b6f3ee04f0bad5f82190a118:Host_server/Host.java


            try{
                while (true) {
                    
                    socket = serverSocket.accept();
                    
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    if(socket != null){
                        out.write("Connected");
                        out.newLine();
                        out.flush();
                    }

                    while (socket.isConnected()){
                        String message_from_client = in.readLine();

                        System.out.println("Client: " + message_from_client);
                        

                        if (message_from_client.equalsIgnoreCase("IP")){

                            System.out.println("getting ip");

                            Process get_ip = Runtime.getRuntime().exec("powershell.exe ipconfig");

                            terminal_out = new BufferedReader(new InputStreamReader(get_ip.getInputStream()));

                            String temp = terminal_out.readLine();

                            while (temp != null) {

                                System.out.println(temp);

                                out.write(temp);
                                
                                temp = terminal_out.readLine();

                                if (temp == null) {
                                    out.newLine();
                                    out.flush();
                                }
                            }   
                        } 
                        
                        // else{
                        //     out.write("msg receved");
                        //     out.newLine();
                        //     out.flush();

                        // }

                        if(message_from_client.equalsIgnoreCase("EXIT")){
                            
                            socket.close();
                            in.close();
                            out.close();

                            break;
                        }
                    }
            
            }

            

            }catch(Exception error){
                System.out.println("Error 1: " + error);
            }

        }catch(Exception error){
            System.out.println("Error 2:" + error);

        }
    }
    
}
