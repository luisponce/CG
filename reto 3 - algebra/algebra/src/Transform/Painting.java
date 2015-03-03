/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Transform;

import CG.Punto2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 */
public class Painting extends JPanel{
    
    ArrayList<Punto2> vertices = new ArrayList<>();
    ArrayList<ArrayList<int>> edges = new  
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        // size es el tamaño de la ventana.
        Dimension size = getSize();
        // Insets son los bordes y los títulos de la ventana.
        Insets insets = getInsets();

        int screenW =  size.width - insets.left - insets.right;
        int screenH =  size.height - insets.top - insets.bottom;

        int x = screenW/2;
        int y = screenH/2;
        
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(Color.blue);   
    } 
    
    
    public static void main(String[] args) {
 
      // Crear un nuevo Frame
      JFrame frame = new JFrame("Transforming");
      // Al cerrar el frame, termina la ejecución de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      Painting c = new Painting();
      
      frame.add(c);
      // Asignarle tamaño
      frame.setSize(500, 500);
      
      // Poner el frame en el centro de la pantalla
      frame.setLocationRelativeTo(null);
      // Mostrar el frame
      frame.setVisible(true);
      //c.fillWithRandom();
      //no se puede cambiar el tamaño
      frame.setResizable(false);
      
      
    }
        
        
        
}
