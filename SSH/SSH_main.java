package SSH;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;

public class SSH_main {
    


    public static void main(String[] args) {
        
        System.out.println("Starting SSH-setup");
        // final String command = "powershell.exe Add-WindowsCapability -Online -Name OpenSSH.Server~~~~0.0.1.0 ; Start-Service sshd ; Set-Service -Name sshd -StartupType Automatic ; New-NetFirewallRule -Name sshd -DisplayName \"OpenSSH Server\" -Enabled True -Direction Inbound -Protocol TCP -Action Allow -LocalPort 22 ; New-NetFirewallRule -Name sshd -DisplayName \"Java Host\" -Enabled True -Direction Inbound -Protocol TCP -Action Allow -LocalPort 60123 ; ‘ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIM3Z7TiVBJRMV2JTQU3cLj5wssQ2d60IX+dlLUifghnj' | Out-File -Append -Encoding ascii $env:USERPROFILE\\.ssh\\authorized_keys ; ‘ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIGJ03YWb/HRKYz5qlgywd5nZ+QB7ohQou4adkg16aynP' | Out-File -Append -Encoding ascii $env:USERPROFILE\\.ssh\\authorized_keys ; 'ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIM3Z7TiVBJRMV2JTQU3cLj5wssQ2d60IX+dlLUifghnj' | Out-File -Append -Encoding ascii \"C:\\ProgramData\\ssh\\administrators_authorized_keys\" ; 'ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIGJ03YWb/HRKYz5qlgywd5nZ+QB7ohQou4adkg16aynP' | Out-File -Append -Encoding ascii \"C:\\ProgramData\\ssh\\administrators_authorized_keys\" ";

        final String
        command_1 = "Add-WindowsCapability -Online -Name OpenSSH.Server~~~~0.0.1.0",
        command_2 = "Start-Service sshd",
        command_3 = "Set-Service -Name sshd -StartupType Automatic",
        command_4 = "New-NetFirewallRule -Name sshd -DisplayName \"OpenSSH Server\" -Enabled True -Direction Inbound -Protocol TCP -Action Allow -LocalPort 22",
        command_5 = "New-NetFirewallRule -Name sshd -DisplayName \"Java Host\" -Enabled True -Direction Inbound -Protocol TCP -Action Allow -LocalPort 60123",
        command_6 = "'ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIM3Z7TiVBJRMV2JTQU3cLj5wssQ2d60IX+dlLUifghnj' | Out-File -Append -Encoding ascii $env:USERPROFILE\\.ssh\\authorized_keys",
        command_7 = "'ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIGJ03YWb/HRKYz5qlgywd5nZ+QB7ohQou4adkg16aynP' | Out-File -Append -Encoding ascii $env:USERPROFILE\\.ssh\\authorized_keys",
        command_8 = "'ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIM3Z7TiVBJRMV2JTQU3cLj5wssQ2d60IX+dlLUifghnj' | Out-File -Append -Encoding ascii \"C:\\ProgramData\\ssh\\administrators_authorized_keys\"",
        command_9 = "'ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIGJ03YWb/HRKYz5qlgywd5nZ+QB7ohQou4adkg16aynP' | Out-File -Append -Encoding ascii \"C:\\ProgramData\\ssh\\administrators_authorized_keys\"";

        final String commands_unformatted = "powershell.exe %s ; %s ; %s ; %s ; %s ; %s ; %s ; %s ; %s";
        final String commands = String.format(commands_unformatted, command_1, command_2, command_3, command_4, command_5, command_6, command_7, command_8, command_9);

        try {

            System.out.println(commands);

            Runtime.getRuntime().exec(commands);

        } catch (Exception e) {

            System.out.println("Error: " + e);

        }
            

    }
}
