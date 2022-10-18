/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
package videoconferencia;
import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;




public class VideoSocket{
    static Socket socket;
    public static void main(String args[]) throws IOException{
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        socket = new Socket("localhost",9999);
        
        BufferedImage bm = webcam.getImage();
        
        ObjectOutputStream dout = new ObjectOutputStream(socket.getOutputStream());
        ImageIcon im = new ImageIcon(bm);
        
        JFrame frame = new JFrame("Pc 1");
        frame.setSize(640,360);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        
        JLabel l = new JLabel();
        l.setVisible(true);
        frame.add(l);
        frame.setVisible(true);
        while(true){
           bm = webcam.getImage();
           im = new ImageIcon(bm);
           dout.writeObject(im);
           l.setIcon(im);
           dout.flush();
        }
    } 
}*/