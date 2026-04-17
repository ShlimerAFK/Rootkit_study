package Device_conf;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class General_conf {

    String command;
    String file_name;
    String message;
    
    
    void save_to_file(){

        try{
                Process custom_command = Runtime.getRuntime().exec("powershell.exe " + command);
                
                BufferedReader terminal_out = new BufferedReader(new InputStreamReader(custom_command.getInputStream()));

                FileWriter fileWriter = new FileWriter(file_name);
                
                String temp = terminal_out.readLine();

                fileWriter.write(message);

                while (temp != null) {

                    System.out.println(temp);
                    fileWriter.write(temp + "\n");
                      
                    temp = terminal_out.readLine();
                }
                
                fileWriter.close();

            }catch(IOException e){

                System.out.println("Error: " + e);
            }
    }
}
