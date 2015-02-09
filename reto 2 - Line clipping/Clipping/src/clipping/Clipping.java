/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clipping;

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
public class Clipping extends JPanel{
    
    

      int screenW;
      int screenH;
      
      int x;
      int y;
    
    //200 lineas con x1, y1, x2, y2
    int[][] coords = new int[200][4];
    //coordenadas de los vertices del rectangulo
    int []vertices = new int[4];
    
    public void fillWithRandom(){
        for (int[] coord : coords) {
              for (int j = 0; j < coord.length; j++) {//posiciones random
                  double rng = Math.random();
                  rng -= 0.5;
                  
                  if (j%2==0) {
                      //si es x
                      coord[j] = (int) (rng*screenW);
                  } else {
                      //si es y
                      coord[j] = (int) (rng*screenH);
                  }
              }
          }
    }
    
    public void createRectangle(){
        
        //vertices[0][0] = (int) (Math.random() * screenW);
           for (int j = 0; j < vertices.length; j++){
               if(j%2 == 0){
                   vertices[j] = (int) (Math.random() * screenW);
               }else{
                   vertices[j] = (int) (Math.random() * screenH);
               }
           } 
         
    }
    
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
        
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(Color.blue);
        fillWithRandom();
        for(int[] coord : coords){
            int x1, x2, y1, y2;
            
            x1 = coord[0];
            y1 = coord[1];
            x2 = coord[2];
            y2 = coord[3];
            System.out.println(x1+", "+y1+" - "+x2+", "+y2);
            g2d.drawLine(x+x1, y-y1, x+x2, y-y2);
        }
        g2d.setColor(Color.orange);
        createRectangle();
        int x1, y1, x2, y2;
        x1 = vertices[0];
        x2 = vertices[2];
        y1 = vertices[1];
        y2 = vertices[3];
        
        //trazar el rectangulo
        
        g2d.drawLine(x1, y1, x2, y1);
        g2d.drawLine(x1, y1, x1, y2);
        g2d.drawLine(x2, y1, x2, y2);
        g2d.drawLine(x1, y2, x2, y2);
    }

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        
        
      // Crear un nuevo Frame
      JFrame frame = new JFrame("Clipping");
      // Al cerrar el frame, termina la ejecución de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      Clipping c = new Clipping();
      
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
