/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

/**
 * This class handles a 3x3 matrix
 * The matrix is used to store the coefficients of a 3x3 linear system of
 * 3 equations with 3 unknowns
 * @author htrefftz
 */
public class Matrix3x3 {
    /** 3 x 3 matrix */
    protected double [][] matrix;
    
    private final boolean DEBUG = false;
    
    /**
     * Constructs a 3x3 matrix
     */
    public Matrix3x3() {
        matrix = new double[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                matrix[i][j] = 0;
            }
        }
    }
    
    /**
     * Constructs a 3x3 matrix
     * @param matrix2 matrix2 contains the values to be copied into the new
     * Matrix3x3
     */
    public Matrix3x3(Matrix3x3 matrix2) {
        matrix = new double[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                matrix[i][j] = matrix2.matrix[i][j];
            }
        }
        
    }
    
    /**
     * Constructs a 3x3 matrix
     * @param m00 element to be stored at position 00
     * @param m01 element to be stored at position 01
     * @param m02 element to be stored at position 02
     * @param m10 element to be stored at position 10
     * @param m11 element to be stored at position 11
     * @param m12 element to be stored at position 12
     * @param m20 element to be stored at position 20
     * @param m21 element to be stored at position 21
     * @param m22 element to be stored at position 22
     */
    public Matrix3x3(double m00, double m01, double m02,
                    double m10, double m11, double m12,
                    double m20, double m21, double m22) {
        matrix = new double[3][3];
        matrix[0][0] = m00; matrix[0][1] = m01; matrix[0][2] = m02;
        matrix[1][0] = m10; matrix[1][1] = m11; matrix[1][2] = m12;
        matrix[2][0] = m20; matrix[2][1] = m21; matrix[2][2] = m22;
    }
    
    /**
     * Changes the column vecctor in position "column" for the contents
     * of "vector" in the 3x3 "matrix"
     * @param matrix original matrix (remains unchanged)
     * @param vector vector to insert into the matrix
     * @param column position to insert the vector into
     * @return 
     */
    public static Matrix3x3 injectVector(Matrix3x3 matrix, Vector3 vector, int column) {
        Matrix3x3 mNew = new Matrix3x3(matrix);
        for(int row = 0; row < 3; row++) {
            mNew.matrix[row][column] = vector.vector[row];
        }
        return mNew;
    }
    
    /**
     * Computes the determinant of a given matrix.
     * @param matrix matrix to compute the determinant of.
     * @return determinant of the matrix.
     */
    public static double determinant(Matrix3x3 matrix) {
        double acum = 0d;
        acum += matrix.matrix[0][0] * (matrix.matrix[1][1] * matrix.matrix[2][2] - matrix.matrix[1][2] * matrix.matrix[2][1]);
        acum -= matrix.matrix[0][1] * (matrix.matrix[1][0] * matrix.matrix[2][2] - matrix.matrix[1][2] * matrix.matrix[2][0]);
        acum += matrix.matrix[0][2] * (matrix.matrix[1][0] * matrix.matrix[2][1] - matrix.matrix[1][1] * matrix.matrix[2][0]);
        return acum;
    }
    
    @Override
    public String toString() {
        String s = new String();
        for(int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                s += this.matrix[row][col] + " ";
            }
            s += "\n";
        }
        return s;
    }
    
}
