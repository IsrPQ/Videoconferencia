/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videoconferencia;

import org.opencv.core.Core;

/**
 *
 * @author Usuario
 */
public class PracticaOpenCV {
    
    public static void main(String args[]){
        String a = Core.NATIVE_LIBRARY_NAME;
        System.loadLibrary(a);
    }
   
}
