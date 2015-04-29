/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

/**
 * Holds the information of a System with 3 equations with 3 unknowns.
 * There are two components: M matrix and C vector.
 * The system is Mx = C
 * @author htrefftz
 */
public class System3x3 {
    /** Coefficient matrix */
    protected final Matrix3x3 m;
    /** Constant vector */
    protected final Vector3 c;
    /** Solution */
    protected Vector3 s;
    
    private final boolean DEBUG = false;
    
    /**
     * Construct a 3x3 system of equations with 3 unknowns
     * @param m coefficients matrix
     * @param c c vector
     */
    public System3x3(Matrix3x3 m, Vector3 c) {
        this.m = m;
        this.c = c;
    } 
    
    /**
     * Construct the first matrix, replacing the first column with the C vector
     * @return Mx
     */
    private Matrix3x3 createMx() {
        Matrix3x3 mx = Matrix3x3.injectVector(m, c, 0);
        if(DEBUG) System.out.println("mx: " + mx);
        return mx;
    }
    
    /**
     * Construct the second matrix, replacing the second column with the C vector
     * @return My
     */
    private Matrix3x3 createMy() {
        Matrix3x3 my = Matrix3x3.injectVector(m, c, 1);
        if(DEBUG) System.out.println("my: " + my);
        return my;
    }
    
    /**
     * Construct the third matrix, replacing the third column with the C vector
     * @return Mz
     */
    public Matrix3x3 createMz() {
        Matrix3x3 mz = Matrix3x3.injectVector(m, c, 2);
        if(DEBUG) System.out.println("mz: " + mz);
        return mz;
    }
    
    /**
     * Solve the system
     * @return x1, x2, x3 as a vector. Solution to the system.
     * @throws java.lang.Exception
     */
    public Vector3 solve() throws Exception {
        double d = Matrix3x3.determinant(this.m);
        if(d == 0)
            throw new Exception("No hay solucion");
        double dx = Matrix3x3.determinant(createMx());
        double dy = Matrix3x3.determinant(createMy());
        double dz = Matrix3x3.determinant(createMz());
        if(DEBUG) {
            System.out.println("dx: " + dx);
            System.out.println("dy: " + dy);
            System.out.println("dz: " + dz);
        }
        double x = dx / d;
        double y = dy / d;
        double z = dz / d;
        
        return new Vector3(x, y, z);
    }
    
    /**
     * Main program to test the program
     * @param args Not used
     */
    public static void main(String [] args) {
        Matrix3x3 m = new Matrix3x3(2, 1, 1,  1, -1, -1,  1, 2, 1);
        Vector3 c = new Vector3(3, 0, 0);
        System3x3 system = new System3x3(m, c);
        try {
            Vector3 solution = system.solve();
            System.out.println("Coefficients: " + m);
            System.out.println("Determinant" + Matrix3x3.determinant(m));
            System.out.println("Solution: " + solution);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
