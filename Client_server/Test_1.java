package Client_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Test_1 {

    public static void main(String[] args) {
        System.out.println("Starting...");
        String host = "5CD3147J5Z";

        try{
        
            Socket socket = new Socket(host, 445);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            Scanner scanner = new Scanner(System.in);

            while(true){

                String message = scanner.nextLine();
                out.write(message);
                out.newLine();
                out.flush();

                System.out.println("Server: " + in.readLine());

                if(message.equalsIgnoreCase("BYE")){
                    
                    scanner.close();
                    socket.close();
                    break;
                }   
        }

        } catch(Exception error){

            System.out.println("Error: " + error);

        }

    }
}
