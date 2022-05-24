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
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicou
 */
public class Client {
    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int port;
    
    public Client(int port){
        try {
            socket = new DatagramSocket();
            serverAddress = InetAddress.getLocalHost();
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.port = port;
    }
    
    public void sendRequest(String message){
        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), serverAddress, port);
        System.out.println(serverAddress + "" + port);
        try {
            System.out.println("prima della send");
            socket.send(packet);
            System.out.println("dopo la send");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String receiveDate(){
        byte[] buffer = new byte[256];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        String date = new String();
        try {
            socket.receive(packet);
            date = extractMessage(packet);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
    
    public String extractMessage(DatagramPacket packet){
        String message = new String(packet.getData(), 0, packet.getLength());
        return message;
    }
    
    public void closeConnection(){
        socket.close();
    }
}
