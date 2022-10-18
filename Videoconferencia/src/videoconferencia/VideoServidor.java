/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoconferencia;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *
 * @author Usuario
 */
public class VideoServidor {
    public static void main(String args[]) throws IOException, ClassNotFoundException{
        ServerSocket server = new ServerSocket(9999);
        System.out.println("Esperando");
        
        Socket socket = server.accept();
        System.out.println("Conectado");
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        JLabel label = new JLabel();
        JFrame frame = new JFrame();
        frame.setSize(640,360);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        label.setSize(640, 360);
        label.setVisible(true);
    
        frame.add(label);
        frame.setVisible(true);
        while(true){
            label.setIcon((ImageIcon)in.readObject());
        }
    }
}
