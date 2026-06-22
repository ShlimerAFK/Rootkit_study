package Host_server;

import java.io.BufferedWriter;

public class Messenger {

    BufferedWriter out = null;
    
    Messenger(BufferedWriter input_out){

        out = input_out;
        
    }

    void send_line(String line){
        try{


            out.write(line);
            out.newLine();

            if(line.equalsIgnoreCase("Done")){
                end_messege();
            }

        }catch(Exception error){

            System.out.println(error);
        }
    }

    void end_messege(){
        
        try{
            out.flush();
        }catch(Exception error){
            System.out.println(error);
        }
    }

}
