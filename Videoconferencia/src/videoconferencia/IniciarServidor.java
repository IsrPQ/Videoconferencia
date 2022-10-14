/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package videoconferencia;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Israel
 */
public class IniciarServidor implements Runnable {
    private final int PUERTO=999;
    @Override
    public void run() {
        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            
            Socket miSocket = servidor.accept();
            
            DataInputStream flujo_entrada = new DataInputStream(miSocket.getInputStream());
            
            String mensaje_texto = flujo_entrada.readUTF();
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(IniciarServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
