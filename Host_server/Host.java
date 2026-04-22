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

                    while (socket.isConnected()){

                        message_from_client = in.readLine();

                        System.out.println("Client: " + message_from_client);
                        
                        if(message_from_client.equalsIgnoreCase("COMMAND")){

                            out.write(" Command pannel: \n Existing commands:Ip, Kill, Advanced ");

                            out.newLine();
                            out.write("Done");

                            out.newLine();
                            out.flush();

                            message_from_client = in.readLine();


                            if (message_from_client.equalsIgnoreCase("IP")){

                                System.out.println(" getting ip");

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

                            }else if(message_from_client.equalsIgnoreCase("KILL")){

                                System.out.println(" getting processes");

                                Process get_processes = Runtime.getRuntime().exec("powershell.exe Get-Process | Sort-Object WorkingSet -Descending | Select -First 40");

                                terminal_out = new BufferedReader(new InputStreamReader(get_processes.getInputStream()));

                                String temp = terminal_out.readLine();

                                while (temp != null) {

                                    System.out.println(temp);

                                    out.write(temp);
                                    out.newLine();
                                    
                                    temp = terminal_out.readLine();

                                    if (temp == null) {

                                        out.write(" Process id: ");
                                        out.newLine();

                                        out.write("Done");
                                        out.newLine();
                                        out.flush();
                                    }
                                }

                                
                                message_from_client = in.readLine();

                                Process kill_Process = Runtime.getRuntime().exec("Powershell.exe taskkill /pid " + message_from_client);

                                terminal_out = new BufferedReader(new InputStreamReader(kill_Process.getInputStream()));
                                
                                temp = terminal_out.readLine();

                                if(temp != null){

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
                                } else {
                                    out.write("Invalid process id");
                                    out.newLine();
                                    out.write("DONE");
                                    out.newLine();
                                    out.flush();
                                }

                            }else if(message_from_client.equalsIgnoreCase("ADVANCED")){

                                out.write(" You are in advanced mode, type leave to exit advanced mode");
                                out.newLine();
                                out.write(" Wright commands below: ");
                                out.newLine();
                                out.write("done");
                                out.newLine();
                                out.flush();
                                

                                while (!message_from_client.equalsIgnoreCase("LEAVE")) {


                                    message_from_client = in.readLine();


                                    if (message_from_client.equalsIgnoreCase("LEAVE")) {
                                        out.write(" Exiting advanced mode");
                                        out.newLine();
                                        out.write("done");
                                        out.newLine();
                                        out.flush();
                                        break;
                                    }

                                    Process custom_command = Runtime.getRuntime().exec("powershell.exe " + message_from_client);

                                    terminal_out = new BufferedReader(new InputStreamReader(custom_command.getInputStream()));

                                    String temp = terminal_out.readLine();

                                    if (temp != null) {

                                        while (temp != null){

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
                                    }else if(temp == null){
                                        out.write("Return was null or invalid command ");
                                        out.newLine();
                                        out.write("done");
                                        out.newLine();
                                        out.flush();

                                    }
                                    
                                }

                            }else{
                                out.write(" Not a valid command, start over");

                                out.newLine();
                                out.write("Done");

                                out.newLine();
                                out.flush();
                            }
                            
                            

                        } else if (message_from_client.equalsIgnoreCase("CONNECTED")){

                            out.write(" You are connected to the server");

                            out.newLine();
                            out.flush();

                        } else if(message_from_client.equalsIgnoreCase("EXIT")){
                            
                            socket.close();
                            in.close();
                            out.close();

                            break;

                        }else{
                            out.write(" msg receved");

                            out.newLine();
                            out.write("Done");

                            out.newLine();
                            out.flush();

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
