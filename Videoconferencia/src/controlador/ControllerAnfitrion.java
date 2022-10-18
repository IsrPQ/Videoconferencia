/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.github.sarxos.webcam.Webcam;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import videoconferencia.IniciarServidor;
import videoconferencia.interfazAnfitrion;
import videoconferencia.Usuario;

/**
 *
 * @author Israel
 */
public class ControllerAnfitrion implements ActionListener, Runnable {
    interfazAnfitrion vista = new interfazAnfitrion();
    private ServerSocket servidor;
    public final int PUERTO=9999;
    public ControllerAnfitrion(interfazAnfitrion vista){
        this.vista= vista;
        
        this.vista.botonComenzar.addActionListener(this);
        this.vista.botonDetener.addActionListener(this);
        this.vista.btnCamara.addActionListener(this);
    }
    public void iniciarServidor() throws ClassNotFoundException{
        try {
            servidor = new ServerSocket(PUERTO);
            this.vista.textServidor.setBackground(Color.GREEN);
            this.vista.textServidor.setText("Servidor Iniciado, PORT:"+PUERTO);
            this.vista.botonComenzar.setEnabled(false);
            
            //Creamos un String, un cliente que llega desde el Socket
            String mensaje;
            Usuario clienteRecibido;
            
            
            
            while(true){
                Socket miSocket = servidor.accept();
                //Llega el objeto
                ObjectInputStream clienteDatos = new ObjectInputStream(miSocket.getInputStream());
                //Extraemos el mensaje
                clienteRecibido = (Usuario)clienteDatos.readObject();
                mensaje = clienteRecibido.getMensajeTexto();
                this.vista.txtAreaMonitorCliente.append("\n Se ha conectado: "+mensaje);
                
                
                
        
                
                //DataInputStream flujo_entrada = new DataInputStream(miSocket.getInputStream());

                //String mensaje_texto = flujo_entrada.readUTF();

                //this.vista.txtAreaMonitorCliente.append("\n Se ha conectado: "+ mensaje_texto);

                miSocket.close();
            }

        } catch (SocketException ex) {
            Logger.getLogger(IniciarServidor.class.getName()).log(Level.INFO, ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(IniciarServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void iniciarCamara( ) throws IOException, ClassNotFoundException{
        
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
        if(e.getSource()==vista.botonComenzar){
            Thread hilo = new Thread(this);
            hilo.start();
        } else if(e.getSource()==vista.botonDetener){
            try {
                servidor.close();
            } catch (Exception ex) {
                Logger.getLogger(IniciarServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.vista.textServidor.setBackground(new Color(204,204,255));
            this.vista.textServidor.setText(" ");
            this.vista.botonComenzar.setEnabled(true);
        }
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
            iniciarServidor();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControllerAnfitrion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControllerAnfitrion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
