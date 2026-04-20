package Device_conf;


public class Main {
    
    public static void main(String[] args) {
    
    Port_conf port_conf = new Port_conf();

    Name_conf name_conf = new Name_conf();

    port_conf.open_port();

    name_conf.save_host_name();

    }
}
