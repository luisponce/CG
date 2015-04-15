/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reto4;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 */
public class Main extends JFrame {
    private PnlTransform panelControl;
    private Projection panelProjections;
    
    private static Main instance = null;
    
    public static Main getInstance(){
        if(instance == null){
            instance = new Main();
        }
        return instance;
    }
    
   public Projection getPanelProjections(){
       return this.panelProjections;
   } 
    public Main() { 
        setTitle("Projections");
        setSize(540, 450);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#FFFFFF"));
        
        
         
        panelProjections = new Projection();
        panelProjections.setBounds(5, 0, 540, 410);
         
        //add(panelControl);
        add(panelProjections);
        
        addKeyListener(new Main.KeyInputHandler());
        
        panelProjections.init();
        //panelControl.setVisible(true);
        panelProjections.setVisible(true);
    }
    
    public static void main (String args[]) { 
        Main ventana = getInstance();
        
        //ventana.panelProjections.init(ventana.getGraphics());
        ventana.setVisible(true);
        JOptionPane.showMessageDialog(null, "Please use arrows to rotate the camera", "Instructions", JOptionPane.INFORMATION_MESSAGE);
        
        Main.getInstance().startLoop();
        
    } 
    
    public void startLoop(){
        
        while(true){
            
            panelProjections.Repaint();
        }
    }
    
    private class KeyInputHandler extends KeyAdapter {
		/** The number of key presses we've had while waiting for an "any key" press */
		
		/**
		 * Notification from AWT that a key has been pressed. Note that
		 * a key being pressed is equal to being pushed down but *NOT*
		 * released. Thats where keyTyped() comes in.
		 *
		 * @param e The details of the key that was pressed 
		 */
                @Override
		public void keyPressed(KeyEvent e) {
			// if we're waiting for an "any key" typed then we don't 
			// want to do anything with just a "press"
			Projection p = Main.getInstance().getPanelProjections();
                 
            p.update = true;
                        
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            p.leftPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            p.rightPressed = true;
			}
                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            p.upPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            p.downPressed = true;
			}
                        
                        
		} 
		
		/**
		 * Notification from AWT that a key has been released.
		 *
		 * @param e The details of the key that was released 
		 */
		public void keyReleased(KeyEvent e) {
			// if we're waiting for an "any key" typed then we don't 
			// want to do anything with just a "released"
			Projection p = Main.getInstance().getPanelProjections();
			
            p.update = false;
            
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				p.leftPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				p.rightPressed = false;
			}
                        if (e.getKeyCode() == KeyEvent.VK_UP) {
				p.upPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				p.downPressed = false;
			}
		}
    }
}


