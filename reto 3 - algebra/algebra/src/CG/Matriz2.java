/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CG;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathaneidelman
 */
public class Matriz2 {
    double[][] matrix = new double [3][3];
    
    public double[] getRow(int row) throws Exception{
        if(row > 3){
           throw new Exception("La fila a devolver no existe");
        }
        return matrix[row];
    }
    
    public double[] getCol(int col) throws Exception{
       if(col > 3){
           throw new Exception("La columna a devolver no existe");
        } 
       double[] res = new double[3];
       for(int i = 0; i < 3; i++){
           res[i] = matrix[i][col];
       }
       return res;
    }
    public static double multiplyArrays(double[] a1, double[] a2){
        double res = 0.0;
        for(int i = 0; i < 3; i++){
            res += a1[i] * a2[i];
        }
        return res;
    }
    public static double[][] times(Matriz2 m1, Matriz2 m2){
        double[][] res = new double[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                try {
                    res[i][j] = multiplyArrays(m1.getRow(i), m2.getCol(j));
                } catch (Exception ex) {
                    Logger.getLogger(Matriz2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }
}
