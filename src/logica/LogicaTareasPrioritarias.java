/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import ui.VentanaPrincipal;
import ventanaEmergente.MensajeTareasCom;

/**
 *
 * @author caroR
 */
public class LogicaTareasPrioritarias {
    
    private JTextField jtxFTarea;
    private JCheckBox[] checkboxes;
    private ArrayList<String> tareas = new ArrayList<>();
    private VentanaPrincipal ventanaPrincipal;
    
    
    public LogicaTareasPrioritarias(JTextField jtxFTarea, JCheckBox[] checkboxes, VentanaPrincipal ventanaPrincipal){
        this.jtxFTarea = jtxFTarea;
        this.checkboxes = checkboxes;
        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    //metodo añade tarea
    public void agregarTarea(){
        String texto = jtxFTarea.getText().trim(); //trim para eliminar espacios al inicio y final del texto
        
        //verificamos que el texto no este vacio y que no se añada a la tarea el mensaje inicial
        if(texto.isEmpty() || texto.equalsIgnoreCase("Añade tu tarea prioritaria aquí")){
            return;
        }
        
        if(tareas.size() < 4){
            tareas.add(texto);
            
            for (int i = 0; i < checkboxes.length; i++) {
            if (i < tareas.size()) {
                checkboxes[i].setText(tareas.get(i));
                checkboxes[i].setVisible(true);
                checkboxes[i].setSelected(false);
            } else {
                checkboxes[i].setText("Prioridad " + (i + 1));
                checkboxes[i].setVisible(true);
                checkboxes[i].setSelected(false);
            }
        }
            
            jtxFTarea.setText("Añade tu tarea prioritaria aquí");
            
        }else{
            MensajeTareasCom ventanaMensaje = new MensajeTareasCom(ventanaPrincipal, true);
            ventanaMensaje.setVisible(true);
        }
    }
    
    public void eliminarTareaMarcada() {
        
        for (int i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].isSelected() && i < tareas.size()) {
                tareas.remove(i);
                break;
            }
        }
        
        //actualizamos los textos de los checkBoxes
        for (int i = 0; i < checkboxes.length; i++) {
            if (i < tareas.size()) {
                checkboxes[i].setText(tareas.get(i));
                checkboxes[i].setSelected(false);
            } else {
                checkboxes[i].setText("Prioridad " + (i + 1));
                checkboxes[i].setSelected(false);
            }
        }
    }
}
