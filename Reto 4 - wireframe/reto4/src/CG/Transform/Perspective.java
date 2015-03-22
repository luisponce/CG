
package CG.Transform;

import CG.Matriz3;

/**
 *
 */
public class Perspective  extends Matriz3 {

    public Perspective(double d) {
        super();
        
        double[][] r = {{1, 0, 0,   0},
                        {0, 1, 0,   0},
                        {0, 0, 1,   0},
                        {0, 0, 1/d, 0}};
        
        this.setMatrix(r);
    }
    
    
}
