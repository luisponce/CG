/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.MyColor;
import Model.Scene;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Class to keep the image that is drawn.
 * To convert a *.jpg to a *.ppm in MAC:
 *   - dodwnload ImageMagick
 *   - in a term:
 *   - convert ejemplo.jpg -compress none ejemplo.ppm
 * 
 * @author htrefftz
 */
public class Image {
    protected MyColor [][] originalimage;
    
    /**
     * Construct the image. For each pixel, a color is stored.
     * @param myImage image, created in Model.Scene
     */
    public Image(MyColor [][] myImage) {
        originalimage = myImage;   
    }
 
    public void escribirImagen(String nombreArchivo) {
        File file = new File(nombreArchivo);
        int r, g, b;
        try {
            PrintStream output = new PrintStream(new FileOutputStream(file));
            output.println("P3");
            output.println("#Imagen a color");
            int width = Scene.WIDTH_IN_PIXELS + 1;
            int height = Scene.HEIGHT_IN_PIXELS + 1;
            output.println(width + " " + height);
            output.println("255");
            // From top to bottom
            for(int yIndex = Scene.HEIGHT_IN_PIXELS; yIndex >= 0; yIndex--) {
                // From left to right
                for (int xIndex = 0; xIndex <= Scene.WIDTH_IN_PIXELS; xIndex++) {
                    r = (int)(originalimage[yIndex][xIndex].getR() * 255);
                    g = (int)(originalimage[yIndex][xIndex].getG() * 255);
                    b = (int)(originalimage[yIndex][xIndex].getB() * 255);
                    if(r < 0) r = 0; if (r > 255) r = 255;
                    if(g < 0) g = 0; if (g > 255) g = 255;
                    if(b < 0) b = 0; if (b > 255) b = 255;
                    // Salvar en el archivo
                    output.print(r + " " + g + " " + b + " ");
                }
                output.println();
            }
            output.close();
        } catch(Exception e) {
            System.out.println("Problema al escribir " + nombreArchivo);
        }
    }

    public void escribirImagenEnsayo(String nombreArchivo) {
        File file = new File(nombreArchivo);
        try {
            PrintStream output = new PrintStream(new FileOutputStream(file));
            output.println("P3");
            output.println("#Examen Eafit");
            output.println("3 2");
            output.println("255");
            output.println("255   0   0     0 255   0     0   0 255");
            output.println("255 255   0   255 255 255     0   0   0");
            output.close();
        } catch(Exception e) {
            System.out.println("Problema al escribir " + nombreArchivo);
        }
    }
        
}
