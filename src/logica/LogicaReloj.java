/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import ventanaEmergente.MensajeTempo;

/**
 *
 * @author caroR
 */
public class LogicaReloj {
    
    private Timer timer;
    private int min;
    private int seg;
    private JLabel etiquetaReloj;
    
    private ArrayList<String> botonesTDcDl = new ArrayList<>();
    private String modoActual = "trabajo";
    
    private JFrame ventanaPrincipal;
    
    public LogicaReloj(JLabel etiquetaReloj, JFrame ventanaPrincipal){
        this.etiquetaReloj = etiquetaReloj;
        setTiempo(25, 00); //Tiempo inicial por defecto  
        
        //para mostrar el JDialog con el mensaje de que se termino el tiempo del pomodoro
        this.ventanaPrincipal = ventanaPrincipal;
        

    }
    
    public void setTiempo(int min , int seg){
        this.min = min;
        this.seg = seg;
        actualizarEtiqueta();
    }
    
    private void actualizarEtiqueta(){
        String tiempo = String.format("%02d:%02d" , min, seg);
        etiquetaReloj.setText(tiempo);
    }
    
    public void detener() {
        if (timer != null) {
            timer.stop();
        }
    }    
    
    public void iniciar(){
        
        if(timer != null && timer.isRunning()){ //si el Timer esta creado y el temporizador esta en marcha
            return;
        }
        
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (seg == 0) {
                    if (min == 0) {
                        detener();
                        MensajeTempo ventanaMensaje = new MensajeTempo(ventanaPrincipal, true);
                        ventanaMensaje.setVisible(true);
                        return;
                    } else {
                        min--;
                        seg = 59;
                    }
                } else {
                    seg--;
                }
                actualizarEtiqueta();
            }
        });
        timer.start();
    }
    
    public void trabajo(){
        detener();
        modoActual = "Trabajo";
        setTiempo(25,0);
    }
    
    public void descansoLargo(){
        detener();
        modoActual = "Descanso Largo";
        setTiempo(10,0);
    }
    
    public void descansoCorto(){
        detener();
        modoActual = "Descanso Corto";
        setTiempo(1,0);
    }
    
     public void reinicio(){
        detener();
        
        switch(modoActual){
            case "Trabajo":
                setTiempo(25, 0);
                break;
            case "Descanso Corto":
                setTiempo(1, 0);
                break;
            case "Descanso Largo":
                setTiempo(10,0);
                break;
            default:
                setTiempo(25,0);                  
        }
    }
    
}
