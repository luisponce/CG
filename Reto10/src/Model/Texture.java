/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.Image;

/**
 *
 * @author htrefftz
 */
public class Texture {
    /** Image of the texture */
    protected Image textureImage;
    /** Image height */
    int height;
    /** Image width */
    int width;
    
    public Texture(String fileName) {
        textureImage = new Image();
        textureImage.leerImagen(fileName);
        height = textureImage.getHeight();
        width = textureImage.getWidth();
    }
    /**
     * Retornar el color que se lee de la
     * textura.
     * Usar la función textureImage.getPixel()
     * @param s
     * @param t
     * @return 
     */
    public MyColor getTexelClamp(double s, double t) {
        int px, py;
        
        if(s<1){
            px = (int) (textureImage.getWidth() * s); 
        } else if(s<=0){
            px = 0;
        } else {
            px = textureImage.getWidth();
        }
        
        if(t<1){
            py = (int) (textureImage.getHeight()* t); 
        } else if(t<=0){
            py = 0;
        } else {
            py = textureImage.getHeight();
        }
        
        return textureImage.getPixel(py, px);
    }
    /**
     * Retornar el color que se lee de la
     * textura.
     * Usar la función textureImage.getPixel()

     * @param s
     * @param t
     * @return 
     */
    public MyColor getTexelTile(double s, double t) {
        int px, py;
        
        px = (int) (textureImage.getWidth() * s) % textureImage.getWidth(); 
        
        py = (int) (textureImage.getHeight()* t) % textureImage.getHeight(); 
         
        
        return textureImage.getPixel(py, px);
    }

}
