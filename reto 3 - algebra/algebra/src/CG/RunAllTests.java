/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CG;

/**
 *
 * @author luisponcedeleon
 */
public class RunAllTests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("2D:");
        Punto2.main(args);
        System.out.println("");
        Vector2.main(args);
        System.out.println("");
        Matriz2.main(args);
        System.out.println("");
        
        System.out.println("3D:");
        Punto3.main(args);
        System.out.println("");
        Vector3.main(args);
        System.out.println("");
        Matriz3.main(args);
    }
    
}
