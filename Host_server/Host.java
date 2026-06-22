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

                    Messenger messenger = new Messenger(out);

                    while (socket.isConnected()){

                        message_from_client = in.readLine();

                        System.out.println("Client: " + message_from_client);
                        
                        if(message_from_client.equalsIgnoreCase("COMMAND")){

                            messenger.send_line(" Command pannel: \n Existing commands:Ip, Kill, Advanced ");
                            messenger.send_line("Done");

                            message_from_client = in.readLine();


                            if (message_from_client.equalsIgnoreCase("IP")){

                                System.out.println(" getting ip");

                                Process get_ip = Runtime.getRuntime().exec("powershell.exe ipconfig");

                                terminal_out = new BufferedReader(new InputStreamReader(get_ip.getInputStream()));

                                String temp = terminal_out.readLine();

                                while (temp != null) {

                                    System.out.println(temp);

                                    messenger.send_line(temp);
                                    
                                    temp = terminal_out.readLine();

                                    if (temp == null) {
                                        messenger.send_line("Done");
                                    }
                                }

                            }else if(message_from_client.equalsIgnoreCase("KILL")){

                                System.out.println(" getting processes");

                                Process get_processes = Runtime.getRuntime().exec("powershell.exe Get-Process | Sort-Object WorkingSet -Descending | Select -First 40");

                                terminal_out = new BufferedReader(new InputStreamReader(get_processes.getInputStream()));

                                String temp = terminal_out.readLine();

                                while (temp != null) {

                                    System.out.println(temp);

                                    messenger.send_line(temp);
                                    
                                    temp = terminal_out.readLine();

                                    if (temp == null) {

                                        messenger.send_line( "Process name");

                                        messenger.send_line("Done");

                                    }
                                }

                                
                                message_from_client = in.readLine();

                                Process kill_Process = Runtime.getRuntime().exec("Powershell.exe taskkill /f /t /im " + message_from_client + ".exe");

                                terminal_out = new BufferedReader(new InputStreamReader(kill_Process.getInputStream()));
                                
                                temp = terminal_out.readLine();

                                if(temp != null){

                                    while (temp != null) {

                                        System.out.println(temp);

                                        messenger.send_line(temp);
                                        
                                        temp = terminal_out.readLine();

                                        if (temp == null) {

                                            messenger.send_line("Done");
                                        }
                                    }
                                } else {

                                    messenger.send_line("Invalid process id");
                                    messenger.send_line("Done");
                                }

                            }else if(message_from_client.equalsIgnoreCase("ADVANCED")){

                                messenger.send_line(" You are in advanced mode, type leave to exit advanced mode");
                                messenger.send_line(" Wright commands below: ");
                                messenger.send_line("Done");
                                

                                while (!message_from_client.equalsIgnoreCase("LEAVE")) {

                                    message_from_client = in.readLine();

                                    if (message_from_client.equalsIgnoreCase("LEAVE")) {

                                        messenger.send_line(" Exiting advanced mode");
                                        messenger.send_line("Done");

                                        break;

                                    }

                                    Process custom_command = Runtime.getRuntime().exec("powershell.exe " + message_from_client);

                                    terminal_out = new BufferedReader(new InputStreamReader(custom_command.getInputStream()));

                                    String temp = terminal_out.readLine();

                                    if (temp != null) {

                                        while (temp != null){

                                            System.out.println(temp);

                                            messenger.send_line(temp);
                                            
                                            temp = terminal_out.readLine();

                                            if (temp == null) {

                                                messenger.send_line("Done");

                                            }
                                        }   
                                    }else if(temp == null){

                                        messenger.send_line("Return was null or invalid command ");
                                        messenger.send_line("Done");
                                    }
                                    
                                }

                            }else{
                                messenger.send_line("Not a valid command, start over");
                                
                                messenger.send_line("Done");

                            }
                            
                            

                        } else if (message_from_client.equalsIgnoreCase("CONNECTED")){

                            messenger.send_line("You are connected to the server");
                            messenger.end_messege();


                        } else if(message_from_client.equalsIgnoreCase("EXIT")){
                            
                            socket.close();
                            in.close();
                            out.close();

                            break;

                        }else{
                            messenger.send_line(" msg receved");

                            messenger.send_line("Done");

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
