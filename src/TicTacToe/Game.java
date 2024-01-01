package TicTacToe;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.awt.Graphics2D;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/* Game                                                            */
/* author:  Edward Walsh                                           */
/* Aug 5 2010                                                      */
/*                                                                 */
/* Framework for a sprite game                                     */
/*                                                                 */
/* AGREEMENT:                                                      */
/* THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED  */
/* WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED          */
/* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR      */
/* PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE CONTRIBUTORS BE   */
/* LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,           */
/* EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED */
/* TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,   */
/* DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND  */
/* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT         */
/* LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING  */
/* IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF  */
/* THE POSSIBILITY OF SUCH DAMAGE.                                 */
/* When the Program is made available in source code form:         */
/* a) it must be made available under this Agreement; and          */
/* b) a copy of this Agreement must be included with each copy of  */
/* the Program.                                                    */
/* The Modifications that You create or to which You contribute    */
/* are governed by the terms of this Agreement. You may not remove */
/* or alter this Agreement.                                        */
/*                                                                 */


/**
 * Framework for a sprite game.
 * <p>
 * <pre>
 * This framework includes:
 * A method that is invoked every time the screen is painted.
 * Clock that invokes repaint 15 times every second
 * Methods to query the pressed state of the following keyboard keys:
 *     up arrow key
 *     down arrow key
 *     left arrow key
 *     right arrow key
 *     space bar
 * Method to create random numbers
 *
 * Instructions:
 * Use the Graphics context g, in paintFrame(),
 * to paint the current frame in your game.
 *
 * EXAMPLE:
 * //Type your variable declarations here
 * Image image = new ImageIcon("image.gif").getImage();
 * int x = 0;
 * int y = 0;
 * public void initializeSprites(){
 *     //type your sprite initialization code here
 * }
 * public void paintFrame(Graphics g) {
 *       if (isRightArrowPressed()) x=x+10;
 *       if (isLeftArrowPressed()) x=x-10;
 *       if (isUpArrowPressed()) y=y-10;
 *       if (isDownArrowPressed()) y=y+10;
 *       g.drawImage(image,x,y,this);
 *       if (isMouseClicked()) {
 *           int x = getMouseX();
 *           int y = getMouseY();
 *       }
 *   }
 * </pre>
 * <p>
 *
 */
public class Game extends JFrame {

    /* Type your variable declarations here */
		TicTacToeBoard board;
		int timeDelay;
		String message;
		boolean gameOver;
		
		
		
		
		
	/**
	 * Called when the program is launched.
	 */
	public void initializeSprites(){
		//type your sprite initialization code here
		board = new TicTacToeBoard();
		timeDelay = 0;
		message = "";
		gameOver = false;
		
		
		
	}
	


	/**
     * paint the current frame of the game
     * @param g handle to the screen
     */
    public void paintFrame(Graphics g) {
	 /* Type your code here */
    	
    	Font myFont = new Font("Verdana", Font.BOLD, 20);
    	g.setFont(myFont);
    	
    	
    	
    	board.paintBoardToConsole(g);
    	
    	
    	
    	
    	
    	
    	if(board.numberOfCellsThatContainEmpty() == 0) {
        	message = "It's a tie! Try again!!";
        	g.drawString(message, 10, 290);
        	return;
        }
    	
    if(board.didXWin()) {
    	message = "X won the game!!";
    	g.drawString(message, 10, 290);
    	return;
    }
    if(board.didOWin()) {
    	message = "O won the game!!";
    	g.drawString(message, 10, 290);
    	return;
    } 
    if(timeDelay > 0) {
    	timeDelay--;
    	timeDelay -= 16;
    	if(timeDelay <= 0) {
    		timeDelay = 0;
    		message = "";
    	}
    	return;
    }
    if(board.numberOfCellsThatContainX() == board.numberOfCellsThatContainO()) {
    	board.changeFirstEmptyToX();
    	return;
    }
    if(board.numberOfCellsThatContainO() < board.numberOfCellsThatContainX()) {
    	if(isMouseClicked()) {
    		int mouseX = getMouseX();
    		int mouseY = getMouseY();
    		board.ifPointInCellAndIfEmptyThenSetContentsTo0(mouseX, mouseY);
    		timeDelay = 2000;
    		return;
    	}
    } 
    }
    
// }
    
    	

    
    public class GlidingSprite extends Sprite {
    	   protected int xSpeed;
    	   protected int ySpeed;
    	   protected String doWhatWhenAtEdgeOfGameBoard;
    	   protected String doWhatWhenPastEdgeOfGameBoard;
    	   public static final String STOP = "STOP";
    	   public static final String BOUNCE = "BOUNCE";
    	   public static final String DISAPPEAR = "DISAPPEAR";
    	   public static final String REAPPEAR_ON_OPPOSITE_EDGE = "REAPPEAR ON OPPOSITE EDGE";
    	   public static final String DO_NOTHING = "DO NOTHING";

    	  public GlidingSprite(Image image, int x, int y){
    	      super(image, x, y);
    	  }

    	  public void setSpeed(int xSpeed, int ySpeed){
    	      this.xSpeed = xSpeed;
    	  }

    	  public int getXSpeed( ){
    	      return(this.xSpeed);
    	  }

    	  public int getYSpeed( ){
    	      return(this.ySpeed);
    	  }

    	  public void setDoWhatWhenAtEdgeOfGameBoard(String doWhat){
    	      this.doWhatWhenAtEdgeOfGameBoard = doWhat;
    	  }

    	  public String getDoWhatWhenAtEdgeOfGameBoard( ){
    	      return(this.doWhatWhenAtEdgeOfGameBoard);
    	  }

    	  public void setDoWhatWhenPastEdgeOfGameBoard(String doWhat){
    	      this.doWhatWhenPastEdgeOfGameBoard = doWhat;
    	  }

    	  public String getDoWhatWhenPastEdgeOfGameBoard( ) {
    	      return(this.doWhatWhenPastEdgeOfGameBoard);
    	  }

    	  public void paintComponent(Graphics g) {
    	      if (this.visible==false) return;
    	      g.drawImage(this.image, this.x, this.y, null);
    	  }
    	}//GlidingSprite

    
    /**
     * Simple Sprite class
     * that contains the properties
     * image, x, y, width, height
     * and that contains the method
     * boolean intersects(Sprite sprite)
     * <p>
     * <pre>
     * EXAMPLE CODE:
     * Sprite sprite = new Sprite(new ImageIcon("image.gif").getImage(), 111, 222);
     * g.drawImage(sprite.image, sprite.x, sprite.y, null);
     * </pre>
     * <p>
     *
     */
    public class Sprite extends Rectangle {
        public Image image = null;        
        public boolean visible  = true;
        public Sprite(Image image, int x, int y) {
            super(x,y,image.getWidth(null),image.getHeight(null));
            this.image = image;        
        }
        public void paintComponent(Graphics g) {
            if (this.visible==false) return;
            g.drawImage(this.image, this.x, this.y, null);
        }
    }//Sprite  

	
    

    /**
     * Update the sprite.
     * <p>
     * <pre>
     * If the sprite is moving,then move the sprite to its next position.
     * If the sprite is at the edge of the game board
     *    then examine sprite's doWhatWhenAtEdgeOfGameBoard property:
     *    if DO_NOTHING then we do nothing
     *    if STOP then we stop the sprite
     *    if BOUNCE then we bounce the sprite
     *    if DISAPPEAR then the sprite disappears
     *    if REAPPEAR_ON_OPPOSITE_EDGE then make the sprite appear on the opposite edge
     * If the sprite is past the edge of the game board
     *    then examine sprite's doWhatWhenPastEdgeOfGameBoard property:
     *    if DO_NOTHING then we do nothing
     *    if STOP then we stop the sprite
     *    if BOUNCE then we bounce the sprite
     *    if DISAPPEAR then the sprite disappears
     *    if REAPPEAR_ON_OPPOSITE_EDGE then make the sprite appear on the opposite edge
     * </pre>
     * <p>
     * @param g The Graphics context
     * @param sprite The sprite we are updating
     */
    public void paintGlidingSprite(Graphics g, GlidingSprite sprite) {

    	/* move the sprite to its next position */
    	sprite.x = sprite.x + sprite.xSpeed;
    	sprite.y = sprite.y + sprite.ySpeed;

    	/* if the sprite is past the edge of the game board */
    	if (isSpritePastEdgeOfGameBoard(sprite)) {

    		/* if the sprite's doWhatWhenPastEdgeOfGameBoard is DO_NOTHING */
    		/* then we do nothing                                          */
    		if (sprite.doWhatWhenPastEdgeOfGameBoard.equals(sprite.DO_NOTHING)) {

    		}

    		/* if the sprite's doWhatWhenPastEdgeOfGameBoard is STOP       */
    		/* then stop moving the sprite                                 */
    		else if (sprite.doWhatWhenPastEdgeOfGameBoard.equals(sprite.STOP)) {
    			sprite.xSpeed = 0;
    			sprite.ySpeed = 0;
    		}

    		/* if the sprite's doWhatWhenPastEdgeOfGameBoard is BOUNCE     */
    		/* then bounce the sprite off the edge                         */
    		else if (sprite.doWhatWhenPastEdgeOfGameBoard.equals(sprite.BOUNCE)) {

    			/* reverse direction */
    			if (sprite.x < 0 - sprite.width) sprite.xSpeed = Math.abs(sprite.xSpeed);
    			else if (sprite.x >= gameboard.getWidth()) sprite.xSpeed = 0 - Math.abs(sprite.xSpeed);
    			if (sprite.y <= 0 - sprite.height) sprite.ySpeed = Math.abs(sprite.ySpeed);
    			else if (sprite.y >= gameboard.getHeight()) sprite.ySpeed = 0 - Math.abs(sprite.ySpeed);
    			
    		}

    		/* if the sprite's doWhatWhenPastEdgeOfGameBoard is DISAPPEAR */
    		/* then make the sprite invisible                             */
    		else if (sprite.doWhatWhenPastEdgeOfGameBoard.equals(sprite.DISAPPEAR)) {
    			sprite.visible = false;
    		}

    		/* if the sprite's doWhatWhenPastEdgeOfGameBoard is REAPPEAR_ON_OPPOSITE_EDGE */
    		/* then move the sprite to the opposite end of the game board                 */
    		else if (sprite.doWhatWhenPastEdgeOfGameBoard.equals(sprite.REAPPEAR_ON_OPPOSITE_EDGE)) {
    			if (sprite.x < 0 - sprite.width) sprite.x = gameboard.getWidth();
    			else if (sprite.x >= gameboard.getWidth()) sprite.x = 0 - sprite.width;
    			if (sprite.y <= 0 - sprite.height) sprite.y = gameboard.getHeight();
    			else if (sprite.y >= gameboard.getHeight()) sprite.y = 0 - sprite.height;
    		}
    	}


    	/* if the sprite is past the edge of the game board */
    	if (isSpriteAtEdgeOfGameBoard(sprite)) {

    		/* if the sprite's doWhatWhenAtEdgeOfGameBoard is DO_NOTHING */
    		/* then we do nothing                                          */
    		if (sprite.doWhatWhenAtEdgeOfGameBoard.equals(sprite.DO_NOTHING)) {

    		}

    		/* if the sprite's doWhatWhenAtEdgeOfGameBoard is STOP       */
    		/* then stop moving the sprite                                 */
    		else if (sprite.doWhatWhenAtEdgeOfGameBoard.equals(sprite.STOP)) {
    			sprite.xSpeed = 0;
    			sprite.ySpeed = 0;
    		}
    		    		
    		
    		/* if the sprite's doWhatWhenAtEdgeOfGameBoard is BOUNCE     */
    		/* then bounce the sprite off the edge                       */    		
    		else if (sprite.doWhatWhenAtEdgeOfGameBoard.equals(sprite.BOUNCE)) {
                /* reverse direction */
    			if (sprite.x <= 0) sprite.xSpeed = Math.abs(sprite.xSpeed);
    			else if (sprite.x >= gameboard.getWidth()-sprite.width) sprite.xSpeed = 0 - Math.abs(sprite.xSpeed);
    			if (sprite.y <= 0) sprite.ySpeed = Math.abs(sprite.ySpeed);
    			else if (sprite.y >= gameboard.getHeight()-sprite.height) sprite.ySpeed = 0 - Math.abs(sprite.ySpeed);
    			
    			/* don't let sprite go past edge */
    			if (sprite.x <= 0) sprite.x = 0;
    			else if (sprite.x >= gameboard.getWidth()-sprite.width) sprite.x = gameboard.getWidth() - sprite.width; 
    			if (sprite.y <= 0) sprite.y = 0;
    			else if (sprite.y >= gameboard.getHeight()-sprite.height) sprite.y = gameboard.getHeight() - sprite.height; 
    		}

    		/* if the sprite's doWhatWhenAtEdgeOfGameBoard is DISAPPEAR */
    		/* then make the sprite invisible                             */
    		else if (sprite.doWhatWhenAtEdgeOfGameBoard.equals(sprite.DISAPPEAR)) {
    			sprite.visible = false;
    		}

    		/* if the sprite's doWhatWhenPastEdgeOfGameBoard is REAPPEAR_ON_OPPOSITE_EDGE */
    		/* then move the sprite to the opposite end of the game board                 */
    		else if (sprite.doWhatWhenAtEdgeOfGameBoard.equals(sprite.REAPPEAR_ON_OPPOSITE_EDGE)) {
    			if (sprite.x <= 0) sprite.x = gameboard.getWidth() - sprite.width;
    			else if (sprite.x >= gameboard.getWidth()-sprite.width) sprite.x = 0;
    			if (sprite.y <= 0) sprite.y = gameboard.getHeight() - sprite.height;
    			else if (sprite.y >= gameboard.getHeight()-sprite.height) sprite.y = 0;
    		}
    	}

    	/* render the sprite   */
    	sprite.paintComponent(g);
    }

    /**
     * Is a sprite touching or past the edge of the game board?
     * <p>
     * EXAMPLE: <br>
     * If the sprite is to the left of the game board's right edge,
     * then return false. <br>
     * If any part of the sprite is touching the game board's right edge,
     * then return true. <br>
     * If the sprite is to the right of the game board's right edge, then return true.
     * @param sprite The sprite we are checking
     * @return True if the sprite is touching or is past the edge of the game board.
     */
    public boolean isSpriteAtEdgeOfGameBoard(Sprite sprite) {
    	if (sprite.x < 0) return(true);
    	if (sprite.x + sprite.width > this.gameboard.getWidth()) return(true);
    	if (sprite.y < 0) return(true);
    	if (sprite.y + sprite.height > this.gameboard.getHeight()) return(true);
    	return(false);
    }

    /**
     *
     * <p>
     * <p>
     * EXAMPLE: <br>
     * If the sprite is to the left of the game board's right edge,
     * then return false. <br>
     * If any part of the sprite is touching the game board's right edge,
     * then return false. <br>
     * If the sprite is to the right of the game board's right edge, then return true.
     * @param sprite The sprite we are checking
     * @return True if the sprite is touching or is past the edge of the game board.
     */
    public boolean isSpritePastEdgeOfGameBoard(Sprite sprite) {
    	if (sprite.x+sprite.width < 0) return(true);
    	if (sprite.x > this.gameboard.getWidth()) return(true);
    	if (sprite.y+sprite.height < 0) return(true);
    	if (sprite.y > this.gameboard.getHeight()) return(true);
    	return(false);
    }


    /**
     * is the up arrow pressed?
     * @return true or false
     */
    public synchronized boolean isUpArrowPressed() {
        return(upArrowPressed);
    }


    /**
     * is the down arrow pressed?
     * @return true or false
     */
    public synchronized boolean isDownArrowPressed() {
        return(downArrowPressed);
    }

    /**
     * is the left arrow pressed?
     * @return true or false
     */
    public synchronized boolean isLeftArrowPressed() {
        return(leftArrowPressed);
    }

    /**
     * is the right arrow pressed?
     * @return true or false
     */
    public synchronized boolean isRightArrowPressed() {
        return(rightArrowPressed);
    }

    /**
     * is the space bar pressed?
     * @return true or false
     */
    public synchronized boolean isSpaceKeyPressed() {
        return(spaceKeyPressed);
    }

    /**
     * Get the x coordinate of the mouse
     * @return x coordinate of the mouse
     */
    public synchronized int getMouseX() {
    	return(this.mouseX);
    }

    /**
     * Get the y coordinate of the mouse
     * @return y coordinate of the mouse
     */
    public synchronized int getMouseY() {
    	return(this.mouseY);
    }


    /**
     * Is the mouse clicked?
     * @return true if the mouse is clicked.
     */
    public synchronized boolean isMouseClicked() {
    	return(this.bufferedMouseClicked);
    }


    /**
     * Generate a random number between a minimum number and a maximum number.
     * @param minNumber The smallest number that can be generated.
     * @param maxNumber The largest number that can be generated.
     * @return A number between the minimum number and the maximum number.
     */
    public int randomNumber(int minNumber, int maxNumber) {
        if (minNumber==maxNumber) return(minNumber);
        if (minNumber>maxNumber) {
            int temp = minNumber;
            minNumber = maxNumber;
            maxNumber = temp;
        }
        return(minNumber+(int)(Math.floor(Math.random()*(double)(maxNumber-minNumber+1))));
    }//randomNumber


    /** The panel where all of the game's images are rendered */
    GameBoard gameboard = null;


    /** Is the up arrow key pressed? */
    boolean upArrowPressed = false;

    /**
     * Set the state of the up arrow key.
     * @param upArrowPressed Set to true if the up arrow key is pressed.
     * Set to false if the the up arrow key is not pressed.
     */
    public synchronized void setUpArrowPressed(boolean upArrowPressed) {
        this.upArrowPressed = upArrowPressed;
    }//setUpArrowPressed

    /** Is the down arrow key pressed? */
    boolean downArrowPressed = false;

    /**
     * Set the state of the down arrow key.
     * @param upArrowPressed Set to true if the down arrow key is pressed.
     * Set to false if the the down arrow key is not pressed.
     */
    public synchronized void setDownArrowPressed(boolean downArrowPressed) {
        this.downArrowPressed = downArrowPressed;
    }//setDownArrowPressed

    /** Is the left arrow key pressed? */
    boolean leftArrowPressed = false;


    /**
     * Set the state of the left arrow key.
     * @param upArrowPressed Set to true if the left arrow key is pressed.
     * Set to false if the the left arrow key is not pressed.
     */
    public synchronized void setLeftArrowPressed(boolean leftArrowPressed) {
        this.leftArrowPressed = leftArrowPressed;
    }//setLeftArrowPressed

    /** Is the right arrow key pressed? */
    boolean rightArrowPressed = false;

    /**
     * Set the state of the right arrow key.
     * @param upArrowPressed Set to true if the right arrow key is pressed.
     * Set to false if the the right arrow key is not pressed.
     */
    public synchronized void setRightArrowPressed(boolean rightArrowPressed) {
        this.rightArrowPressed = rightArrowPressed;
    }//setRightArrowPressed

    /** Is the space bar pressed? */
    boolean spaceKeyPressed = false;

    /**
     * Set the state of the space bar.
     * @param upArrowPressed Set to true if the space bar is pressed.
     * Set to false if the the space bar is not pressed.
     */
    public synchronized void setSpaceKeyPressed(boolean spaceKeyPressed) {
        this.spaceKeyPressed = spaceKeyPressed;
    }//setSpaceKeyPressed


    /** x coordinate of the mouse */
    int mouseX = 0;


    /**
     * Set the x coordinate of the mouse
     * @param x x coordinate of the mouse
     */
    public synchronized void setMouseX(int x) {
    	this.mouseX = x;
    }

    /** y coordinate of the mouse */
    int mouseY = 0;

    /**
     * Set the y coordinate of the mouse
     * @param y y coordinate of the mouse
     */
    public synchronized void setMouseY(int y) {
    	this.mouseY = y;
    }


    /** Is the mouse clicked? */
    boolean bufferedMouseClicked = false;




    /**
     * Set to true if the mouse is clicked.
     * @param clicked is true if the mouse is clicked.
     */
    public synchronized void setBufferedMouseClicked(boolean clicked) {
    	this.bufferedMouseClicked = clicked;
    }

    /** Is the mouse clicked? */
    boolean rawMouseClicked = false;




    /**
     * Set to true if the mouse is clicked.
     * @param clicked is true if the mouse is clicked.
     */
    public synchronized void setRawMouseClicked(boolean clicked) {
    	this.rawMouseClicked = clicked;
    }


    /**
     * Is the mouse clicked?
     * @return true if the mouse is clicked.
     */
    public synchronized boolean isRawMouseClicked() {
    	return(this.rawMouseClicked);
    }


    /**
     * Constructor for the game.
     * <p>
     * <pre>
     * The following tasks are performed:
     * a frame is displayed on the screen
     * a clock is created; the clock invoked repaint 15 times per second
     * a keyboard listener listens for presses of the arrow keys & space bar
     * </pre>
     * <p>
     */
    public Game() {

        /* how large is the window? */
        setSize(640,480);

        /* if the end-user clicks on the x in the upper right corner, */
        /* close this app                                             */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* allow the window to receive the focus; */
        /* we want this window to see key presses */
        setFocusable(true);

        /* this window can receive keyboard events */
        addKeyListener(new MyKeyListener());

        /* make the window visible */
        setVisible(true);

        /* add MyPanel to the window */
        setLayout(new GridLayout(1,1));
        gameboard = new GameBoard();
        add(gameboard);

        validate();

    }//Game




    /**
     * Panel that displays all the graphics in the game.
     * <p>
     * Why do I need a panel--why can't I display the graphics in the frame?
     * I want to the top of the graphics area to be at y=0.
     * <p>
     * Offscreen buffers are used to create a rock-solid animation that does not blink.
     * <p>
     * Focus is given to the panel so that the panel can listen for key presses.
     * <p>
     * The clock invokes repaint 15 times per second.
     */
    public class GameBoard extends JPanel {

        /** offscreen buffers are used to create a rock-solid animation that does not blink */
        protected Image offscreenImage = null;
        /** offscreen buffers are used to create a rock-solid animation that does not blink */
        protected Graphics offscreenGraphics = null;

        /**
         * Constructor for the main panel in this game;
         * all of the graphics are displayed on this panel.
         * <p>
         * <pre>
         * Focus is given to the panel so that the panel can listen for key pressed.
         * NOTE:  Focus determines who receives the characters that are typed on the keyboard--
         * the entity that has the focus receives the characters.
         * A keyboard listener is created to listen for key pressed.
         * A clock is created; the clock invokes repaint 15 times per second.
         * </pre>
         * <p>
         */
        public GameBoard() {
            /* allow this panel to get the focus */
            setFocusable(true);
            /* give this panel the focus */
            requestFocus();
            /* Now that this panel has the focus, this panel can receive keyboard events */
            addKeyListener(new MyKeyListener());
            /* this window can receive mouse motion events */
            addMouseMotionListener(new MyMouseMotionListener());
            /* this window can receive mouse events */
            addMouseListener(new MyMouseListener());
            /* start a clock that invokes repaint 15 times per second */
            new ThreadClock().start();
        }//MyPanel




        public TicTacToeCell getCell(int row, int col) {
			// TODO Auto-generated method stub
			return null;
		}




		/**
         * Repaint all the graphics that are on the screen.
         * The graphics are painted onto an off-screen buffer.
         * When the buffer is completely painted, the buffer is displayed on this panel.
         */
        public void paintComponent(Graphics g){

        	/* rawMouseClicked is set WHENEVER the mouse is clicked */
        	/* We will use a buffer so that we notice the click     */
        	/* ONLY at the top of this loop--i.e. we do NOT want    */
        	/* the state of the mouse click to change in the middle */
        	/* of this loop.                                        */
        	setBufferedMouseClicked(isRawMouseClicked());
        	setRawMouseClicked(false);

            /* if the off-screen buffer has not yet been created, create it */
            if (offscreenImage==null) {
                offscreenImage = createImage(getWidth(), getHeight());
                offscreenGraphics = offscreenImage.getGraphics();

                /* allow the developer to init sprites */
    	        initializeSprites();

            }//if

            /* if a human changed the width or the height of the frame          */
            /* then create a new off-buffer that is the same size as the frame. */
            if (  (offscreenImage.getWidth(this)!= getWidth())
               || (offscreenImage.getHeight(this)!= getHeight()) ) {
                offscreenImage = createImage(getWidth(), getHeight());
                offscreenGraphics = offscreenImage.getGraphics();
                requestFocus();
            }//if

            /* if this panel lost the focus, get the focus back.              */
            /* Why?  We want this panel to be able to respond to key presses. */
            /* WARNING:  If you are planning on adding GUI controls           */
            /* to your game, then you will need to comment this out.          */
            if (hasFocus()==false) requestFocus();

            /* clear/erase/delete the old image that is on the off-screen buffer */
            offscreenGraphics.setColor(Color.WHITE);
            offscreenGraphics.fillRect
               (0,
                0,
                offscreenImage.getWidth(this),
                offscreenImage.getHeight(this));
            offscreenGraphics.setColor(Color.BLACK);

            /* paint the new image onto the off-screen buffer */
            paintFrame(offscreenGraphics);

            /* render the off-screen buffer onto the screen */
            g.drawImage(offscreenImage,0,0,this);

            /* We do NOT want future iterations of paintComponent() */
            /* to respond to any mouse clicks that THIS iteration   */
            /* of paintComponent() have already responded to.       */
            setBufferedMouseClicked(false);

        }//paintComponent


        /**
         * Clock that invokes repaint 15 times every second.
         */
        public class ThreadClock extends Thread {

            /**
             * Invoke repaint 15 times every second
             */
            public void run() {
                while(true) {
                    javax.swing.SwingUtilities.invokeLater(new Runnable(){
                        public void run() {
                            GameBoard.this.repaint();
                        }
                    });
                    try {sleep(67);} catch(InterruptedException e){}
                }//while
            }//run
        }//ThreadGame

    }//MyPanel


    /**
     * Listen for keyboard presses of the following keys: <br>
     * up arrow key <br>
     * down arrow key <br>
     * left arrow key <br>
     * right arrow key <br>
     * space bar
     */
    protected class MyKeyListener implements KeyListener {

        /**
         * Fires whenever a key is pressed.
         * If any of the arrow keys or the space bar is pressed, notify the game.
         * @param event The event that is fired whenever a key is pressed.
         * We will use this event to determine which key , on the keyboard, is pressed.
         */
        public void keyPressed(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_UP) setUpArrowPressed(true);
            if (event.getKeyCode() == KeyEvent.VK_DOWN) setDownArrowPressed(true);
            if (event.getKeyCode() == KeyEvent.VK_LEFT) setLeftArrowPressed(true);
            if (event.getKeyCode() == KeyEvent.VK_RIGHT) setRightArrowPressed(true);
            if (event.getKeyCode() == KeyEvent.VK_SPACE) setSpaceKeyPressed(true);
        }//keyPressed

        /**
         * Fires whenever a key is released.
         * If any of the arrow keys or the space bar is released, notify the game.
         * @param event The event that is fired whenever a key is released.
         * We will use this event to determine which key , on the keyboard, is released.
         */
        public void keyReleased(KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.VK_UP) setUpArrowPressed(false);
            if (event.getKeyCode() == KeyEvent.VK_DOWN) setDownArrowPressed(false);
            if (event.getKeyCode() == KeyEvent.VK_LEFT) setLeftArrowPressed(false);
            if (event.getKeyCode() == KeyEvent.VK_RIGHT) setRightArrowPressed(false);
            if (event.getKeyCode() == KeyEvent.VK_SPACE) setSpaceKeyPressed(false);
        }//keyReleased
        public void keyTyped(KeyEvent event) {
        }//keyTyped
    }//MyKeyListener


    /**
     * Listen for mouse motion events
     */
    public class MyMouseMotionListener implements MouseMotionListener {

		/**
		 * Fires whenever the mouse is dragged.
		 * We will tell the game where the mouse is.
		 * @event The event that is fired when the mouse is dragged.
		 */
		public void mouseDragged(MouseEvent event) {
		}

		/**
		 * Fires whenever the mouse is moved.
		 * We will tell the game where the mouse is.
		 * @event The event that is fired when the mouse is dragged.
		 */
		public void mouseMoved(MouseEvent event) {
			setMouseX(event.getX());
			setMouseY(event.getY());
		}

    }

    /**
     * Listen for Mouse events
     */
    public class MyMouseListener implements MouseListener {

		/**
		 * Fires whenever the mouse is clicked.
		 * We will notify the game.
		 * @event The event that is fired when the mouse is clicked.
		 */
		public void mouseClicked(MouseEvent event) {
			setRawMouseClicked(true);
		}

		public void mouseEntered(MouseEvent arg0) {
		}

		public void mouseExited(MouseEvent arg0) {
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
		}

    }



    /**
     * Play the game
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                new Game();
            }
            });

    }//main

}//Game