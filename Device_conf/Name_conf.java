package Device_conf;


public class Name_conf extends General_conf{

        void save_host_name(){


            command = "hostname";
            message = "Hostname: ";
            file_name = "hostname.txt";

            this.save_to_file();
    
    }
}
