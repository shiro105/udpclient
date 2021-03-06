/**
*	UDP Client Program
*	Connects to a UDP Server
*	Receives a line of input from the keyboard and sends it to the server
*	Receives a response from the server and displays it.
*
*	@author: Matthew Shiroma
* @version: 2.1
*/

import java.io.*;
import java.net.*;

class MyUDPClient {
    public static void main(String args[]) throws Exception
    {
      
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
       
        while(true)
        {
          System.out.println("Please enter a message.");
          byte[] sendData = new byte[1024];
          byte[] receiveData = new byte[1024];
          String sentence = inFromUser.readLine();

          sendData = sentence.getBytes();

    	    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
          clientSocket.send(sendPacket);
          DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
          clientSocket.receive(receivePacket);

          String modifiedSentence = new String(receivePacket.getData());
          System.out.println("FROM SERVER:" + modifiedSentence);

          System.out.println("Would you like to send another? Type, 'Goodbye' to leave.");
          String userInput = inFromUser.readLine();
          if(userInput.equals("Goodbye"))
          {
            System.out.println("See you later!");
            break;
          }
        }
        
      clientSocket.close();
    }
}
