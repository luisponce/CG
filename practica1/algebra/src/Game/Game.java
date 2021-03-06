/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Asteroids.Asteroid;
import Asteroids.Bullet;
import Asteroids.Ship;
import CG.Punto2;
import CG.Vector2;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 */
public class Game extends Canvas {
	/** The stragey that allows us to use accelerate page flipping */
	private BufferStrategy strategy;
	/** True if the game is currently "running", i.e. the game loop is looping */
	private boolean gameRunning = true;
	/** The list of all the entities that exist in our game */
	private ArrayList entities = new ArrayList();
	/** The list of entities that need to be removed from the game this loop */
	private ArrayList removeList = new ArrayList();
	/** The entity representing the player */
	private GameObject ship;
	/** The speed at which the player's ship should move (pixels/sec) */
	private double moveSpeed = 300;
	/** The time at which last fired a shot */
	private long lastFire = 0;
	/** The interval between our players shot (ms) */
	private long firingInterval = 500;
	/** The number of aliens left on the screen */
	private int alienCount;
        
        private int score = 0;
	
	/** The message to display which waiting for a key press */
	private String message = "";
	/** True if we're holding up game play until a key has been pressed */
	private boolean waitingForKeyPress = true;
	/** True if the left cursor key is currently pressed */
	private boolean leftPressed = false;
	/** True if the right cursor key is currently pressed */
	private boolean rightPressed = false;
    /** True if the up cursor key is currently pressed */
	private boolean upPressed = false;
	/** True if the down cursor key is currently pressed */
	private boolean downPressed = false;
	/** True if we are firing */
	private boolean firePressed = false;
	/** True if game logic needs to be applied this loop, normally as a result of a game event */
	private boolean logicRequiredThisLoop = false;
	
        private int w = 800;

    
        private int h = 600;
        
        private double secsToSpawnAsteroid = 1000;
        private double timerSinceLastAsteroid;
        
        private static Game instance;
        
        public int getW() {
            return w;
        }

        public int getH() {
            return h;
        }
        
        public static Game getInstance(){
            if(instance == null){
                instance = new Game();
            }
            return instance;
        }
        
	/**
	 * Construct our game and set it running.
	 */
	private Game() {
		// create a frame to contain our game

		JFrame container = new JFrame("Asteroids");
		
		// get hold the content of the frame and set up the resolution of the game

		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(w,h));
		panel.setLayout(null);
		
		// setup our canvas size and put it into the content of the frame

		setBounds(0,0,w,h);
		panel.add(this);
		
		// Tell AWT not to bother repainting our canvas since we're

		// going to do that our self in accelerated mode

		setIgnoreRepaint(true);
		
		// finally make the window visible 

		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		// add a listener to respond to the user closing the window. If they

		// do we'd like to exit the game

		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// add a key input system (defined below) to our canvas

		// so we can respond to key pressed

		addKeyListener(new KeyInputHandler());
		
		// request the focus so key events come to us

		requestFocus();

		// create the buffering strategy which will allow AWT

		// to manage our accelerated graphics

		createBufferStrategy(2);
		strategy = getBufferStrategy();
		
		// initialise the entities in our game so there's something

		// to see at startup

		initEntities();
	}
	
	/**
	 * Start a fresh game, this should clear out any old data and
	 * create a new set.
	 */
	private void startGame() {
		// clear out any existing entities and intialise a new set

		entities.clear();
		initEntities();
                score = 0;
		
		// blank out any keyboard settings we might currently have

		leftPressed = false;
		rightPressed = false;
                upPressed = false;
                downPressed = false;
		firePressed = false;
	}
	
        private void spawnAsteroid(){
            Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.WHITE);
            ArrayList<Punto2> v = new ArrayList<>();
            ArrayList<Integer[]> e = new ArrayList<>();
            
            Random seed = new Random();
            
            int type = seed.nextInt(4);
            int x = 0;
            int y = 0;
            
            Vector2 dir = new Vector2(0, 0);
            double spd = seed.nextDouble()*0.6;
            
            if(type==0){
                //up
                x = seed.nextInt(w-100);
                y = seed.nextInt(100) -100;
            } else if(type==1){
                //right
                x = w - seed.nextInt(100) +100;
                y = seed.nextInt(h-100);
            } else if(type==2){
                //down
                x = seed.nextInt(w-100)+100;
                y = h - seed.nextInt(100) +100;
            } else if(type==3){
                //left
                x = (int) seed.nextInt(100) -100;
                y = (int) seed.nextInt(h-100)+100;
            }
            

            Asteroid enemy = new Asteroid(x,y, v, e, 0, 0, g, seed.nextInt(3)+1);
            dir = new Vector2(new Punto2(x,y),
                    new Punto2(w/2 + seed.nextInt(50)-50, h/2 + seed.nextInt(50)-50));
            if(spd<=0.4) spd = 0.4;
            enemy.setSpeed(Vector2.scale(dir, spd));
            
            entities.add(enemy);
        }
        
	/**
	 * Initialise the starting state of the entities (ship and aliens). Each
	 * entitiy will be added to the overall list of entities in the game.
	 */
	private void initEntities() {
		// create the player ship and place it roughly in the center of the screen

            Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.WHITE);
            ArrayList<Punto2> v = new ArrayList<>();
            ArrayList<Integer[]> e = new ArrayList<>();

            //spawn initial asteroids
            for(int i = 0; i<5; i++){
                spawnAsteroid();
            }
            
            timerSinceLastAsteroid = 0;
            
            
            ship = new Ship(w/2,h/2, v, e, 0, 0, g);
            
            entities.add(ship);
            
	}
        
        public void tryToFire() {
            // check that we have waiting long enough to fire
            if (System.currentTimeMillis() - lastFire < firingInterval) {
                    return;
            }

            // if we waited long enough, create the shot entity, and record the time.
            lastFire = System.currentTimeMillis();
            Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.WHITE);
            ArrayList<Punto2> v = new ArrayList<>();
            ArrayList<Integer[]> e = new ArrayList<>();
            GameObject bullet = new Bullet((int)ship.getVertices().get(6).getX(), (int)ship.getVertices().get(6).getY(), v, e, 0, 0, g);
            bullet.Rotate(ship.getAngle());
            bullet.up = ship.up;
            
            if(ship.speed.magnitude() == 0.0) 
                bullet.setSpeed(Vector2.scale(new Vector2(ship.up.getX(),ship.up.getY()), -450));
            else bullet.setSpeed(Vector2.scale(ship.speed, 2.5));
            entities.add(bullet);
        }
	
	/**
	 * Notification from a game entity that the logic of the game
	 * should be run at the next opportunity (normally as a result of some
	 * game event)
	 */
	public void updateLogic() {
		logicRequiredThisLoop = true;
	}
	
	/**
	 * Remove an entity from the game. The entity removed will
	 * no longer move or be drawn.
	 * 
	 * @param entity The entity that should be removed
	 */
	public void removeEntity(GameObject entity) {
		removeList.add(entity);
	}
        
        public void score(){
            score++;
        }
	
	/**
	 * Notification that the player has died. 
	 */
	public void notifyDeath() {
		message = "GAME OVER, try again?";
		waitingForKeyPress = true;
	}
	
	/**
	 * Notification that the player has won since all the aliens
	 * are dead.
	 */
	public void notifyWin() {
		message = "Well done! You Win!";
		waitingForKeyPress = true;
	}
	
	/**
	 * The main game loop. This loop is running during all game
	 * play as is responsible for the following activities:
	 * <p>
	 * - Working out the speed of the game loop to update moves
	 * - Moving the game entities
	 * - Drawing the screen contents (entities, text)
	 * - Updating game events
	 * - Checking Input
	 * <p>
	 */
	public void gameLoop() {
		long lastLoopTime = System.currentTimeMillis();
		
		// keep looping round til the game ends

		while (gameRunning) {
			// work out how long its been since the last update, this

			// will be used to calculate how far the entities should

			// move this loop

			long delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
                        
                        
			
			// Get hold of a graphics context for the accelerated 

			// surface and blank it out

			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0,0,800,600);
			
			// cycle round asking each entity to move itself

			if (!waitingForKeyPress) {
				for (int i=0;i<entities.size();i++) {
					GameObject entity = (GameObject) entities.get(i);
					
					entity.fixedMove(delta);
				}
			}
			
			// cycle round drawing all the entities we have in the game

			for (int i=0;i<entities.size();i++) {
				GameObject entity = (GameObject) entities.get(i);
				
				entity.paintFigure();
			}
			
			// brute force collisions, compare every entity against

			// every other entity. If any of them collide notify 

			// both entities that the collision has occured

			for (int p=0;p<entities.size();p++) {
				for (int s=p+1;s<entities.size();s++) {
					GameObject me = (GameObject) entities.get(p);
					GameObject him = (GameObject) entities.get(s);
					
					if (me.collidesWith(him)) {
                                            
						me.collidedWith(him);
						him.collidedWith(me);
                                                
					}
				}
			}
			
			// remove any entity that has been marked for clear up

			entities.removeAll(removeList);
			removeList.clear();

			
			
			// if we're waiting for an "any key" press then draw the 
			// current message 

			if (waitingForKeyPress) {
				g.setColor(Color.white);
				g.drawString(message,(800-g.getFontMetrics().stringWidth(message))/2,250);
                                g.drawString("SCORE: "+score,(800-g.getFontMetrics().stringWidth("SCORE: "+score))/2,300);
				g.drawString("Press any key",(800-g.getFontMetrics().stringWidth("Press any key"))/2,350);
			}
			
			// finally, we've completed drawing so clear up the graphics

			// and flip the buffer over

			g.dispose();
			strategy.show();
			
			// resolve the movement of the ship. First assume the ship 

			// isn't moving. If either cursor key is pressed then

			// update the movement appropraitely

			
                        if (!waitingForKeyPress) {
                            timerSinceLastAsteroid += delta;

                            if (timerSinceLastAsteroid >= secsToSpawnAsteroid) {
                                spawnAsteroid();
                                timerSinceLastAsteroid = 0;
                            }
                        }
			
			if ((leftPressed) && (!rightPressed)) {
				ship.Rotate(-0.5 * delta);
			} else if ((rightPressed) && (!leftPressed)) {
				ship.Rotate(0.5 * delta);
			}
                        
                        if ((upPressed) && (!downPressed)) {
				ship.speed = Vector2.add(ship.speed, Vector2.scale(ship.up, -1.5*delta));
                                
			} else if ((downPressed) && (!upPressed)) {
				ship.speed = Vector2.add(ship.speed, Vector2.scale(ship.up, 1.5*delta));
                                
                                
			} else {
                                
                                if(ship.speed.magnitude() <= 0){
                                    ship.speed = new Vector2(0, 0);
                                } else {
                                    if(ship.speed.magnitude() >= 3.5)
                                    ship.speed = Vector2.sub(ship.speed, Vector2.scale(ship.speed.normalized(), 1*delta));
                                    else ship.speed = new Vector2(0, 0);
                                }
                        }
//                        System.out.println(ship.speed.toString());
			
			// if we're pressing fire, attempt to fire

			if (firePressed) {
				tryToFire();
			}
			
			// finally pause for a bit. Note: this should run us at about

			// 100 fps but on windows this might vary each loop due to

			// a bad implementation of timer

			try { Thread.sleep(10); } catch (Exception e) {}
		}
	}
	
	/**
	 * A class to handle keyboard input from the user. The class
	 * handles both dynamic input during game play, i.e. left/right 
	 * and shoot, and more static type input (i.e. press any key to
	 * continue)
	 * 
	 * This has been implemented as an inner class more through 
	 * habbit then anything else. Its perfectly normal to implement
	 * this as seperate class if slight less convienient.
	 * 
	 * @author Kevin Glass
	 */
	private class KeyInputHandler extends KeyAdapter {
		/** The number of key presses we've had while waiting for an "any key" press */
		private int pressCount = 1;
		
		/**
		 * Notification from AWT that a key has been pressed. Note that
		 * a key being pressed is equal to being pushed down but *NOT*
		 * released. Thats where keyTyped() comes in.
		 *
		 * @param e The details of the key that was pressed 
		 */
		public void keyPressed(KeyEvent e) {
			// if we're waiting for an "any key" typed then we don't 
			// want to do anything with just a "press"
			if (waitingForKeyPress) {
				return;
			}
			
			
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = true;
			}
                        if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				firePressed = true;
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
			if (waitingForKeyPress) {
				return;
			}
			
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				leftPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				rightPressed = false;
			}
                        if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				firePressed = false;
			}
		}

		/**
		 * Notification from AWT that a key has been typed. Note that
		 * typing a key means to both press and then release it.
		 *
		 * @param e The details of the key that was typed. 
		 */
		public void keyTyped(KeyEvent e) {
			// if we're waiting for a "any key" type then
			// check if we've recieved any recently. We may

			// have had a keyType() event from the user releasing

			// the shoot or move keys, hence the use of the "pressCount"
			// counter.

			if (waitingForKeyPress) {
				if (pressCount == 1) {
					// since we've now recieved our key typed

					// event we can mark it as such and start 

					// our new game

					waitingForKeyPress = false;
					startGame();
					pressCount = 0;
				} else {
					pressCount++;
				}
			}
			
			// if we hit escape, then quit the game

			if (e.getKeyChar() == 27) {
				System.exit(0);
			}
		}
	}
	
	/**
	 * The entry point into the game. We'll simply create an
	 * instance of class which will start the display and game
	 * loop.
	 * 
	 * @param argv The arguments that are passed into our game
	 */
	public static void main(String argv[]) {
		Game g = Game.getInstance();

		// Start the main game loop, note: this method will not

		// return until the game has finished running. Hence we are

		// using the actual main thread to run the game.

		g.gameLoop();
	}
}
