/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package urbani_udp_ese1_oop;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicou
 */
public class Server {
    private int port;
    private DatagramSocket  socket;
    
    public Server(int port){
        port = this.port;
        try {
            // può generare eccezioni l'istanziazione
            socket = new DatagramSocket(port);
            System.out.println(socket.getInetAddress());
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onListener(){
        while(true)
        {
            byte[] buffer = new byte[256]; 
            DatagramPacket inputPacket = new DatagramPacket(buffer, buffer.length);
            try {
                System.out.println("Mi metto in ascolto");
                socket.receive(inputPacket);
                onClientRequest(inputPacket, inputPacket.getAddress(), inputPacket.getPort());
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void onClientRequest(DatagramPacket inPacket, InetAddress clientAddress, int clientPort){
        String messageIn;
        System.out.println(clientAddress + "" + clientPort);
        messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
				System.out.println("SONO IL CLIENT " + clientAddress + 
						":" + clientPort + "> " + messageIn);
        sendDate(clientAddress, clientPort);
    }
    
    public void sendDate(InetAddress clientAddress, int clientPort){
        Date d = new Date();
        String date = d.toString();
        DatagramPacket dP= new DatagramPacket(date.getBytes(),date.length(), clientAddress, clientPort);
        try {
            socket.send(dP);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
