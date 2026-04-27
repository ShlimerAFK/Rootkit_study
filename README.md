This project is meant for educational purposes ONLY. Do not use on another person without their full consent.

**How to use**

SETUP
1. If you plan to use this program on separate machines, run the program Device_conf with administrative privileges on the computer that will run the host program. This program will open a port in your computers firewall (60123 by default) and save the computers hostname to a txt file (hostname.txt). You dont need to run this program if you plan on using localhost.
2. In the client program, change the host variable to the machine name of the target computer, or keep it as localhost if you plan to run it locally.
3. Run the host program.
4. Run the client program.
5. The client program should print "connected" if everything worked.

USAGE
Now that you are connected, you can start using the server and running commands. Start by writing "command" in your terminal, this will let you run one out of three commands (Ip, Kill, Advanced). 

IP: If you write "ip", the program will run a harcoded version of Ipconfig

KILL: If you write "kill" the program will return the 40 programs that use the most cpu usage (You can change the amout of programs). The program will then ask you for a process Id, all programs have a process id far to the right. Write the process id coresponding to the program you want to close. (Their might be more then one instance of the program running, so you might need to do this multiple times)

If you write "Advanced" you open advanced mode. In advanced mode you write terminal command almost as usual. You might need to run the host program as administrator to run some commands. 
The cd command behaves a bit weird when using this program. To move through the file paths you need to do all cd commands in one line so they execute correctly. Example cd ..; cd desktop; mkdir map_name;

DRAWBACKS
To use this program can only be used within the same network, so keep that in mind.
