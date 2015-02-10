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
    
 
    final byte INSIDE = 0; // 0000
    final byte LEFT = 1;   // 0001
    final byte RIGHT = 2;  // 0010
    final byte BOTTOM = 4; // 0100
    final byte TOP = 8;    // 1000
    
    int xmax, xmin, ymax, ymin;
 
// Compute the bit code for a point (x, y) using the clip rectangle
// bounded diagonally by (xmin, ymin), and (xmax, ymax)
 
// ASSUME THAT xmax, xmin, ymax and ymin are global constants.
 
byte ComputeOutCode(int x, int y)
{
	byte code;
 
	code = INSIDE;          // initialised as being inside of clip window
 
	if (x < xmin)           // to the left of clip window
		code |= LEFT;
	else if (x > xmax)      // to the right of clip window
		code |= RIGHT;
	if (y < ymin)           // below the clip window
		code |= BOTTOM;
	else if (y > ymax)      // above the clip window
		code |= TOP;
 
	return code;
}
 
// Cohen–Sutherland clipping algorithm clips a line from
// P0 = (x0, y0) to P1 = (x1, y1) against a rectangle with 
// diagonal from (xmin, ymin) to (xmax, ymax).
void CohenSutherlandLineClipAndDraw(int x0, int y0, int x1, int y1, Graphics2D g2d)
{
	// compute outcodes for P0, P1, and whatever point lies outside the clip rectangle
	Byte outcode0 = ComputeOutCode(x0, y0); System.out.println(x0+" "+y0+" "+outcode0);
	Byte outcode1 = ComputeOutCode(x1, y1); System.out.println(x1+" "+y1+" "+outcode1);
	boolean accept = false;
 
	while (true) {
		if ((outcode0 | outcode1) == 0b0) { // Bitwise OR is 0. Trivially accept and get out of loop
			accept = true;
			break;
		} else if ((outcode0 & outcode1) != 0b0) { // Bitwise AND is not 0. Trivially reject and get out of loop
			break;
		} else {
			// failed both tests, so calculate the line segment to clip
			// from an outside point to an intersection with clip edge
			int x, y;
            x=-1;
            y=-1;
 
			// At least one endpoint is outside the clip rectangle; pick it.
			Byte outcodeOut = outcode0 != 0b0 ? outcode0 : outcode1;
 
			// Now find the intersection point;
			// use formulas y = y0 + slope * (x - x0), x = x0 + (1 / slope) * (y - y0)
			if ((outcodeOut & TOP) != 0b0) {           // point is above the clip rectangle
				x = x0 + (x1 - x0) * (ymax - y0) / (y1 - y0);
				y = ymax;
			} else if ((outcodeOut & BOTTOM) != 0b0) { // point is below the clip rectangle
				x = x0 + (x1 - x0) * (ymin - y0) / (y1 - y0);
				y = ymin;
			} else if ((outcodeOut & RIGHT) != 0b0) {  // point is to the right of clip rectangle
				y = y0 + (y1 - y0) * (xmax - x0) / (x1 - x0);
				x = xmax;
			} else if ((outcodeOut & LEFT) != 0b0) {   // point is to the left of clip rectangle
				y = y0 + (y1 - y0) * (xmin - x0) / (x1 - x0);
				x = xmin;
			} else {
                System.out.println("ERRORRRRRRRR-----------");
            }
 
			// Now we move outside point to intersection point to clip
			// and get ready for next pass.
			if (outcodeOut.equals(outcode0)) {
				x0 = x;
				y0 = y;
				outcode0 = ComputeOutCode(x0, y0);
			} else {
				x1 = x;
				y1 = y;
				outcode1 = ComputeOutCode(x1, y1);
			}
		}
	}
	if (accept) {
               // Following functions are left for implementation by user based on
               // their platform (OpenGL/graphics.h etc.)
               g2d.setColor(Color.red);
               g2d.drawLine(screenW/2 + x0, screenH/2 - y0, screenW/2 + x1, screenH/2 - y1);
               System.out.println("entreeeeeeeeeeeeee");
	}
}
    
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
        System.out.print("Rectangulo:");
        //vertices[0][0] = (int) (Math.random() * screenW);
           for (int j = 0; j < vertices.length; j++){
               double rng = Math.random();
               rng -= 0.5;
               if(j%2 == 0){
                   vertices[j] = (int) (rng * screenW);
               }else{
                   vertices[j] = (int) (rng * screenH);
               }
               
               System.out.print(" "+vertices[j]);
           } 
         System.out.println("");
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
//            System.out.println(x1+", "+y1+" - "+x2+", "+y2);
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
        
        g2d.drawLine(x1+x, y-y1, x+x2, y-y1);
        g2d.drawLine(x+x1, y-y1, x+x1, y-y2);
        g2d.drawLine(x+x2, y-y1, x+x2, y-y2);
        g2d.drawLine(x+x1, y-y2, x+x2, y-y2);
        
        xmin = Math.min(x1, x2);
        xmax = Math.max(x1, x2);
        ymin = Math.min(y1, y2);
        ymax = Math.max(y1, y2);
        
        for (int[] linea : coords) {
            CohenSutherlandLineClipAndDraw(linea[0], linea[1], linea[2], linea[3], g2d);
            
        }
        
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
