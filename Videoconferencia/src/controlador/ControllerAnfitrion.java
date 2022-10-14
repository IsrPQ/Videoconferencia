/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import videoconferencia.IniciarServidor;
import videoconferencia.interfazAnfitrion;

/**
 *
 * @author Israel
 */
public class ControllerAnfitrion implements ActionListener, Runnable {
    interfazAnfitrion vista = new interfazAnfitrion();
    public final int PUERTO=9999;
    public ControllerAnfitrion(interfazAnfitrion vista){
        this.vista= vista;
        
        this.vista.botonComenzar.addActionListener(this);
    }
    public void iniciarServidor(){
        
        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            this.vista.textServidor.setBackground(Color.GREEN);
            this.vista.textServidor.setText("Servidor Iniciado, PORT:"+PUERTO);
            
            while(true){
                Socket miSocket = servidor.accept();
            
                DataInputStream flujo_entrada = new DataInputStream(miSocket.getInputStream());

                String mensaje_texto = flujo_entrada.readUTF();

                this.vista.txtAreaMonitorCliente.append("\n Se ha conectado: "+mensaje_texto);

                miSocket.close();
                
            }

        } catch (IOException ex) {
            Logger.getLogger(IniciarServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vista.botonComenzar){
            Thread hilo = new Thread(this);
            hilo.start();
        }
    }

    @Override
    public void run() {
        iniciarServidor();
    }
}
