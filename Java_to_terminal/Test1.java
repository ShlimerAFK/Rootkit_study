package Java_to_terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test1 {

  // clearar arpcache
  static void clear_arp_table(){

    try{
      System.out.println("Clear arp table");
      System.out.println(" ");

      // Runnar comandot som clerar arpcachen (tror jag)
      Process clear_arp = Runtime.getRuntime().exec("netsh interface ip delete arpcache");
      // Skapar ett Buffered Reader objekt som öppnar en inputstream och sparar terminal outputen i objektet? 
      BufferedReader stdout = new BufferedReader(new InputStreamReader(clear_arp.getInputStream()));

      String temp = stdout.readLine();
      // Går igenom bufferd reader objektet med den sparade inputstreamen för att skriva ut den, slutar när stdout = null
      while(temp != null){
        System.out.println(temp);

        temp = stdout.readLine();
      }
      // Fångar ett error i variabeln e
    }catch(Exception e){

      System.out.println("Error: " + e);
    }
  }

  static void ping_network(int ping_amount){
    // skapar en sträng, %d är en placeholder för en Int variabel vid användning av formaterade strängar
    String command_1 = "ping  192.168.0.%d -n 1";   

    try{
      // loopar igenom olika ip nummer som startar på xxx.xxx.xxx.1, loopar mängden gånger = funktionens argument ping:amount
      for(int i = 1; i <= ping_amount; i++){

          // skapar ett Process objekt som runnar command_1, 
          // innom exec är det en formaterad sträng som fört tar emot original strängen 
          // och sedan vad man vill lägga i placeholdern %d som i detta fall är variablen i för att öka ip nummret med 1 varge loop
          Process exec_ping = Runtime.getRuntime().exec(String.format(command_1, i));

          // funktionellt göt inget, typ stänger saker ig
          exec_ping.getOutputStream().close();

          // skapar ett Buffert reader objekt som sparar 
          BufferedReader stdout = new BufferedReader(new InputStreamReader(exec_ping.getInputStream()));

          String temp = stdout.readLine();
          // skriver ut returnsen av komandot ping från terminalen
          while (temp != null) {

            System.out.println(temp);
            temp = stdout.readLine();
          }

          // stänger min inputstream
          stdout.close();
      }
    }catch(Exception e){ 
      
        System.out.println("Error: " + e);

      }
  }

static String[] get_arp_table(){

  String command_2 = "arp -a";

  try{
    // runnar comandot arp -a för att få åtkomst till alla ip och mac adresser
    Process exec_arp_a = Runtime.getRuntime().exec(command_2);

    // skapar ett objekt och sparar inputen från terminalen
    BufferedReader arpstdout = new BufferedReader(new InputStreamReader(exec_arp_a.getInputStream()));

    // skapar en stringbuilder för att kunna konvertera inputstream till en stäng för att kunna spara det
    StringBuilder stringBuilder = new StringBuilder();

    // skapar en arrey som är 20 lång (vet ej hur stor den skall bli) som skall ha strängar i sig
    String[] arp_list = new String[255];

    // skapar en index variabel som skall användas i while loopen senare
    int index = 0;

    System.out.println("");
    System.out.println("arp output: ");

    String temp = arpstdout.readLine();

    while(temp != null){

      // skriver ut (en line i guess) från Buffured reader objektet
      System.err.println(temp);

      // lägger till en likadan linje som den nyss skrevs ut i terminalen i stringbuilder objektet
      stringBuilder.append(temp);

      // gör om inputstreamen till en string och sparar ner det i en variabel
      // om jag direkt använder arpstdout.readline för att spara det i min array så blir det på något konstigt sätt null
      String current_ip_and_mac = stringBuilder.toString();

      // resetar stringbuilders length för att kunna använda det igen
      stringBuilder.setLength(0);


      if(current_ip_and_mac.length() >= 51 && current_ip_and_mac.length() <= 57){
          arp_list[index] = current_ip_and_mac.substring(2,52);
      }

      index ++;
      
      temp = arpstdout.readLine();
    }
    
    System.out.println("");
    System.out.println("Saved arp content:");

    for(int i = 1; i< arp_list.length; i++ ){
      if (arp_list[i] != null){

        System.out.println("ip: " + arp_list[i]);
      }
    }

    return arp_list;

  }catch(Exception e){

    System.out.println("Error: " + e);
    String[] Error = new String[0];

    return Error;
  }
}

static void find_ip(String mac_adress, String[] arp_list){

  String arp_mac_adress = "";
  int not_found_counter = 0;

  for(int i=0; i < arp_list.length; i++){

    if(arp_list[i] != null){
      arp_mac_adress = arp_list[i].substring(21, 38);

      System.out.println("arp_mac_adress: " + arp_mac_adress);
    }

    if(arp_mac_adress.equals(mac_adress) && arp_list[i] != null){
      
      System.out.println("");
      System.out.println("Match found, ip: " + arp_list[i].substring(0, 15));
      break;

    }else{
      not_found_counter ++;
    }
    }

  if(not_found_counter == arp_list.length){
    System.out.println(" ");
    System.out.println("No matches found");
    }
}
 public static void main(String[] args) throws IOException {

  System.out.println("starting...");
  System.out.println(" ");
  
  clear_arp_table();
  ping_network(10);

  find_ip("fe80::f972:ee5c:f609:2b50%15",get_arp_table());

}}

