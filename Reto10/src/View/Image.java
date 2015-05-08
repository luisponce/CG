/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.MyColor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    protected MyColor [][] originalImage;
    boolean DEBUG = true;
    
    /**
     * Construct the image.
     * Used for an input image
     */
    public Image() {
        
    }
    
    /**
     * Construct the image. For each pixel, a color is stored.
     * @param myImage image, created in Model.Scene
     */
    public Image(MyColor [][] myImage) {
        originalImage = myImage;   
    }
 
    public void escribirImagen(String nombreArchivo) {
        File file = new File(nombreArchivo);
        int r, g, b;
        try {
            PrintStream output = new PrintStream(new FileOutputStream(file));
            output.println("P3");
            output.println("#Imagen a color");
            // int width = Scene.WIDTH_IN_PIXELS + 1;
            // int height = Scene.HEIGHT_IN_PIXELS + 1;
            int height = originalImage.length - 1;
            int width = originalImage[0].length - 1;
            if(DEBUG)
                System.out.println("height: " + height + " width: " + width);
            int realWidth = width + 1;
            int realHeight = height + 1;
            output.println(realWidth + " " + realHeight);
            output.println("255");
            // From top to bottom
            for(int yIndex = height; yIndex >= 0; yIndex--) {
                // From left to right
                for (int xIndex = 0; xIndex <= width; xIndex++) {
                    r = (int)(originalImage[yIndex][xIndex].getR() * 255);
                    g = (int)(originalImage[yIndex][xIndex].getG() * 255);
                    b = (int)(originalImage[yIndex][xIndex].getB() * 255);
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
            System.out.println("Problema al escribir " + nombreArchivo + 
                    e);
        }
    }
    
    public void leerImagen(String fileName) {
        Scanner sc;
        try {
            sc = new Scanner(new File(fileName));
            
            String tipoArchivo = sc.nextLine();
            //String comentario = sc.nextLine();
            int width = sc.nextInt();
            int height = sc.nextInt();
            int maxValue = sc.nextInt();
            if(DEBUG)
                System.out.println("tipo: " + tipoArchivo + " " +
                        "ancho: " + width + " " +
                        "alto: " + height + " " +
                        "max: " + maxValue);
            
            // Allocate space for the image
            originalImage = new MyColor[height][width];
            
            // Read each pixel
            for(int yIndex = height - 1; yIndex >= 0; yIndex-- ) {
                for(int xIndex = 0; xIndex < width; xIndex++) {
                    int r = sc.nextInt();
                    int g = sc.nextInt();
                    int b = sc.nextInt();
                    // Create the color
                    double rNormalized = ((double)r)/maxValue;
                    double gNormalized = ((double)g)/maxValue;
                    double bNormalized = ((double)b)/maxValue;
                    MyColor color = new MyColor(rNormalized, gNormalized, bNormalized);
                    // Assign the color to the matrix
                    originalImage[yIndex][xIndex] = color;
                }
            }
                    
        } catch (FileNotFoundException ex) {
            System.out.println("No existe el archivo de texuturas: " + fileName);
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
    
    public int getHeight() {
        return originalImage.length;
    }
    
    public int getWidth() {
        return originalImage[0].length;
    }
    
    public MyColor getPixel(int yIndex, int xIndex) {
        return originalImage[yIndex][xIndex];
    }
    
    public static void main(String [] args) {
        Image texture = new Image();
        texture.leerImagen("earthmap1k.ppm");
        texture.escribirImagen("salida.ppm");
        
    }
        
}
