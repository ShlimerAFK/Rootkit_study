package Device_conf;

public class Port_conf extends General_conf{

    public void open_port(){

        command = "New-NetFirewallRule -Name java_server -DisplayName \"java_server\" -Enabled True -Direction Inbound -Protocol TCP -Action Allow -LocalPort 60123";
        message = "Firewall: ";
        file_name = "port_info.txt";

        this.save_to_file();

    };
}


