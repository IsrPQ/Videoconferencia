/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import videoconferencia.interfazAnfitrion;

/**
 *
 * @author Jesus
 */
public class ControllerCamara implements ActionListener, Runnable{

    interfazAnfitrion vista = null;
    public ControllerCamara(interfazAnfitrion vista){
        this.vista = vista;
        this.vista.btnCamara.addActionListener(this);
    }
    
    public void iniciarCamara() throws IOException, ClassNotFoundException{
        ServerSocket server = new ServerSocket(8888);
        System.out.println("Esperando");
        
        Socket socket = server.accept();
        System.out.println("Conectado");
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
         
        while(true){
            this.vista.lbCamara.setIcon((ImageIcon)in.readObject());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.btnCamara){
            Thread hilo = new Thread(this);
            hilo.start();
            System.out.println("Iniciando Camara");
        }
    }

    @Override
    public void run() {
        try {
            iniciarCamara();
        } catch (IOException ex) {
            Logger.getLogger(ControllerCamara.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControllerCamara.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
