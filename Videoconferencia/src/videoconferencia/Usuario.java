/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoconferencia;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Usuario implements Serializable{
   private String mensajeTexto;
   public Usuario(){
   
   }

    public Usuario(String mensajeTexto) {
        this.mensajeTexto = mensajeTexto;
    }

    public String getMensajeTexto() {
        return mensajeTexto;
    }

    public void setMensajeTexto(String mensajeTexto) {
        this.mensajeTexto = mensajeTexto;
    }
   
   
    
}
