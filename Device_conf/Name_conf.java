package Device_conf;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Name_conf extends General_conf{

        void save_host_name(){


            command = "hostname";
            message = "Hostname: ";
            file_name = "hostname.txt";

            this.save_to_file();
    
    }
}
