/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reto7;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Sistema coordenado con (0,0) en el centro de la pantalla
 * @author luisponcedeleon
 */
public class Reto7 extends JPanel{
    
    

      int screenW;
      int screenH;
      
      int x;
      int y;
      Bezier b;
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        // size es el tamaño de la ventana.
        Dimension size = getSize();
        // Insets son los bordes y los títulos de la ventana.
        Insets insets = getInsets();

        screenW =  size.width - insets.left - insets.right;
        screenH =  size.height - insets.top - insets.bottom;

        x = screenW/2;
        y = screenH/2;
        
        b = new Bezier();
        b.curve();
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(Color.BLACK);
        
        for(int i=0; i<b.paint.size()-1; ++i)
            g2d.drawLine((int) b.paint.get(i).getX()+x, y-(int) b.paint.get(i).getY(), (int) b.paint.get(i).getX()+x, y-(int) b.paint.get(i).getY());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        
        
      // Crear un nuevo Frame
      JFrame frame = new JFrame("Curvas de Bezier");
      // Al cerrar el frame, termina la ejecución de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      Reto7 r = new Reto7();
      
      frame.add(r);
      // Asignarle tamaño
      frame.setSize(500, 500);
      
      // Poner el frame en el centro de la pantalla
      frame.setLocationRelativeTo(null);
      // Mostrar el frame
      frame.setVisible(true);
      //no se puede cambiar el tamaño
      frame.setResizable(false);
      
    }
    
}
