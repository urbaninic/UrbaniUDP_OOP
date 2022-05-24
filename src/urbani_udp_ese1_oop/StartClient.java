/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package urbani_udp_ese1_oop;

/**
 *
 * @author nicou
 */
public class StartClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Client c = new Client(2000);
        c.sendRequest("Richiesta data e ora");
        String date = c.receiveDate();
        c.closeConnection();
        System.out.println("Data ricevuta" + date);
    }
    
}
