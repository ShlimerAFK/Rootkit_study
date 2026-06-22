package Client_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        System.out.println("Starting...");
        System.out.println("Searching for server");
        String host = "localhost";

        try{
        
            Socket socket = new Socket(host, 60123);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            Scanner scanner = new Scanner(System.in);

            String message = "";
            String temp;

            
            if (socket.isBound()) {
                
                out.write("connected");
                out.newLine();
                out.flush();

                System.out.println(in.readLine());
                
            }
            

            while(true){

                message = scanner.nextLine();

                out.write(message);
                out.newLine();
                out.flush();

                if(message.equalsIgnoreCase("EXIT")){
                    
                    scanner.close();
                    socket.close();
                    break;

                }else{

                    temp = in.readLine();

                    while(temp != null){

                        if(!temp.equalsIgnoreCase("DONE")){
                            System.out.println(temp);
                        }

                        if(temp.equalsIgnoreCase("DONE")){
                            break;
                        }

                        temp = in.readLine(); 
                    }
                }

        }

        } catch(Exception error){

            System.out.println("Error: " + error);

        }

    }
}
