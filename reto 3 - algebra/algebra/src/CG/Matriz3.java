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
public class Matriz3 {
        double[][] matrix = new double [4][4];
    
    public void setValue(double value, int i, int j){
        matrix[i][j] = value;
    }
    
    public double[] getRow(int row) throws Exception{
        if(row > 4){
           throw new Exception("La fila a devolver no existe");
        }
        return matrix[row];
    }
    
    public double[] getCol(int col) throws Exception{
       if(col > 4){
           throw new Exception("La columna a devolver no existe");
        } 
       double[] res = new double[4];
       for(int i = 0; i < 4; i++){
           res[i] = matrix[i][col];
       }
       return res;
    }
    public static double multiplyArrays(double[] a1, double[] a2){
        double res = 0.0;
        for(int i = 0; i < 4; i++){
            res += a1[i] * a2[i];
        }
        return res;
    }
    public static double[][] times(Matriz3 m1, Matriz3 m2){
        double[][] res = new double[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                try {
                    res[i][j] = multiplyArrays(m1.getRow(i), m2.getCol(j));
                } catch (Exception ex) {
                    Logger.getLogger(Matriz2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return res;
    }
    
    public static double[][] preTimes(Vector3 v3, Matriz3 m2){
        double[][] res = new double [1][3];
        Matriz3 aux = new Matriz3();
        aux.setValue(v3.getX(), 0, 0);
        aux.setValue(v3.getY(), 0, 1);
        aux.setValue(v3.getZ(), 0, 2);
        aux.setValue(v3.comps[3], 0, 3);
        for(int i = 0; i < 4; i++){
            try {
                res[0][i] = multiplyArrays(aux.getRow(0), m2.getCol(i));
            } catch (Exception ex) {
                Logger.getLogger(Matriz2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return res;
    }
}
