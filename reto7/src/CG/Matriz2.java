/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CG;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Matriz2 {
    double[][] matrix;

    public Matriz2() {
        this.matrix = new double[3][3];
    }
    
    public Matriz2(double[][] matrix) {
        this.matrix = matrix;
    }    
    
    public void setValue(double value, int i, int j){
        matrix[i][j] = value;
    }
    
    public void setMatrix(double[][] m){
        this.matrix = m;
    }
    
    public double getValue(int i, int j){
        return matrix[i][j];
    }
    
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
    
    
    public static Matriz2 times(Matriz2 m1, Matriz2 m2){
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
        return new Matriz2(res);
    }
    
    public static Matriz2 transpose(Matriz2 m){
        double[][] res = new double[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                res[i][j] = m.getValue(j, i);
            }
        }
        return new Matriz2(res);
    }
    
    @Override
    public String toString(){
        String str = "";
        for (double[] fila : matrix) {
            for (double num : fila) {
                str+= num + " ";
            }
            str+="\n";
        }
        
        return str;
    }
    
    public static void main(String[] args) {
        System.out.println("pruebas de matriz2:");
        
        double[][] md1 = {{1,2,1}, {0,3,2}, {1,1,1}};
        Matriz2 m1 = new Matriz2(md1);
        System.out.println("M1 = \n"+m1.toString());
        
        double[][] md2 = {{2,3,1}, {1,0,1}, {1,1,2}};
        Matriz2 m2 = new Matriz2(md2);
        System.out.println("M2 = \n"+m2.toString());
        
        Matriz2 mul = Matriz2.times(m1, m2);
        System.out.println("M1*M2 = \n"+mul.toString());
    }
}
