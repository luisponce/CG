

package Game;

import CG.Matriz2;
import CG.Punto2;
import CG.Transform.Rotate;
import CG.Transform.Scale;
import CG.Transform.Translate;
import CG.Vector2;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


/**
 *
 */
public abstract class GameObject {
    
    Graphics2D g2d;
    
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    int x, y;
    
    Vector2 speed;
    
    Vector2 up, right;
    
    int width, height;
    
    private ArrayList<Punto2> vertices = new ArrayList<>();
    private ArrayList<Integer[]> edges = new ArrayList<>();
    
    /** The rectangle used for this entity during collisions  resolution */
    private Rectangle me = new Rectangle();
    /** The rectangle used for other entities during collision resolution */
    private Rectangle him = new Rectangle();
    
    
    public GameObject(int x, int y, ArrayList<Punto2> v, ArrayList<Integer[]> e,
            int width, int height,
            Graphics2D g2d){
        this.g2d = g2d;
        this.x = x;
        this.y = y;
        this.edges = e;
        this.vertices = v;
        this.width = width;
        this.height = height;
        
        speed = new Vector2(0, 0);
        
        up = new Vector2(0, 1);
        right = new Vector2(1, 0);
    }

    public void setVertices(ArrayList<Punto2> vertices) {
        this.vertices = vertices;
    }

    public void setEdges(ArrayList<Integer[]> edges) {
        this.edges = edges;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
    
    private void paintEdge(int pos){
        Integer[] edge = edges.get(pos);
        
        Punto2 v1 = vertices.get(edge[0]);
        Punto2 v2 = vertices.get(edge[1]);
        
        g2d.drawLine(Math.round((float) (v1.getX())),
                Math.round((float) (v1.getY())),
                Math.round((float) (v2.getX())),
                (int) Math.round(v2.getY()));
    }
    
    public void paintFigure(){
        for(int i = 0; i<edges.size(); i++){
            paintEdge(i);
        }
    }
    
    public void setSpeed(Vector2 spd){
        speed = spd;
    }
    
    public Vector2 getSpeed(){
        return speed;
    }
    
    public void fixedMove(long delta){
        int dx = (int) (speed.getX() * delta / 1000);
        int dy = (int) (speed.getY() * delta / 1000);
        
        if(type == 1){//spaceship
            if((x <= width/2 && dx<0) 
                    || (x >= Game.getInstance().getW()-width/2 && dx>0)){
                dx = 0;
            }
            if((y <= height/2 && dy<0) 
                    || (y >= Game.getInstance().getH()-height/2 && dy>0)){
                dy = 0;
            }
        }
        
        Move(dx, dy);
    }
    
    public void Move(int dx, int dy){
        for(int i = 0; i<vertices.size(); i++) {
            vertices.set(i, Punto2.preTimes(vertices.get(i),
                    Matriz2.transpose((new Translate(dx, dy)))));
        }
        
        x += dx;
        y += dy;
    }
    
    public void Rotate(double angle){
        for(int i = 0; i<vertices.size(); i++) {
            vertices.set(i, Punto2.preTimes(vertices.get(i),
                    Matriz2.transpose((new Translate(-x, -y)))));
        }
        
        for(int i = 0; i<vertices.size(); i++) {
            vertices.set(i, Punto2.preTimes(vertices.get(i),
                    Matriz2.transpose((new Rotate(angle)))));
        }
        
        for(int i = 0; i<vertices.size(); i++) {
            vertices.set(i, Punto2.preTimes(vertices.get(i),
                    Matriz2.transpose((new Translate(x, y)))));
        } 
        
        up = Vector2.rotate(up, angle);
        right = Vector2.rotate(right, angle);
        
    }
    
    public void Scale(double sx, double sy){
        for(int i = 0; i<vertices.size(); i++) {
            vertices.set(i, Punto2.preTimes(vertices.get(i),
                    Matriz2.transpose((new Translate(-x, -y)))));
        }
        
        for(int i = 0; i<vertices.size(); i++) {
            vertices.set(i, Punto2.preTimes(vertices.get(i),
                    Matriz2.transpose((new Scale(sx, sy)))));
        }
        
        for(int i = 0; i<vertices.size(); i++) {
            vertices.set(i, Punto2.preTimes(vertices.get(i),
                    Matriz2.transpose((new Translate(x, y)))));
        } 
        
        width *= sx;
        height *= sy;
   }

    public int getHeight(){
        return height;
    }
    
    public int getWidth(){
        return width;
    }
    
    public boolean collidesWith(GameObject other) {
        me.setBounds((int) x-width/2, (int) y-height/2, width, height);
        him.setBounds((int) other.x-other.width/2, (int) other.y-other.height/2,
                other.getWidth(), other.getHeight());
        
        
        return me.intersects(him);
    }

    public abstract void  collidedWith(GameObject him) ;

    
    
}
