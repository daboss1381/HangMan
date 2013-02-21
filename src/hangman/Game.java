/*
 * File:                Game.java
 * Author:              Andre Smink
 * Date:                November 2012
 * Operating System:    Windows 7 64-bit
 * Description:         Creates the user interface to play the game
 *                      Handels the graphics
 *                      Handels the mouse and keyboard input
 *                      Has methods to process selected letters by matching it to
 *                      the word in play
 *                      Methods for showing the hint, removing unused letters and
 *                      add letters to the word.
 *                      Contains the Thread that refreshes the graphics
 */
package hangman;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Sminky
 */
public class Game extends JPanel {

    //Classes
    CategorySelect categorySelect = null;
    StartMultiplayer startMultiplayer = null;
    DataRetrieval dataRetrieval = null;
    Menu menu = null;
    //GUI
    Dimension dim = null;
    JFrame frame = null;
    Painter paint = null;
    //Images
    ImageIcon jumpGifImageIcon = null;
    ImageIcon swingGifImageIcon = null;
    static BufferedImage images[] = null;
    Image jumpGifImage = null;
    Image swingGifImage = null;
    //Strings
    String currentLetter = "";
    static String[][] wordArr = null;
    static String word = "";
    static String msg = "";
    static String hint = "Show Hint";
    //Booleans
    boolean loading = false;
    static boolean gameOver = false;
    static boolean wordLoaded;
    static boolean gameStatus;
    static boolean singlePlayerMode = false;
    //Integers
    static int cursorPosX = 0;
    static int cursorPosY = 0;
    static int misses = 0;
    static int spaces = 0;
    static int letterCounter = 0;
    //Array
    //create alfabet array with Letter, Usable Status, X Coor, Y Coor
    static String[][] alfabet = {
        {"A", "true", "0", "0"},
        {"B", "true", "0", "0"},
        {"C", "true", "0", "0"},
        {"D", "true", "0", "0"},
        {"E", "true", "0", "0"},
        {"F", "true", "0", "0"},
        {"G", "true", "0", "0"},
        {"H", "true", "0", "0"},
        {"I", "true", "0", "0"},
        {"J", "true", "0", "0"},
        {"K", "true", "0", "0"},
        {"L", "true", "0", "0"},
        {"M", "true", "0", "0"},
        {"N", "true", "0", "0"},
        {"O", "true", "0", "0"},
        {"P", "true", "0", "0"},
        {"Q", "true", "0", "0"},
        {"R", "true", "0", "0"},
        {"S", "true", "0", "0"},
        {"T", "true", "0", "0"},
        {"U", "true", "0", "0"},
        {"V", "true", "0", "0"},
        {"W", "true", "0", "0"},
        {"X", "true", "0", "0"},
        {"Y", "true", "0", "0"},
        {"Z", "true", "0", "0"},
        {"1", "true", "0", "0"},
        {"2", "true", "0", "0"},
        {"3", "true", "0", "0"},
        {"4", "true", "0", "0"},
        {"5", "true", "0", "0"},
        {"6", "true", "0", "0"},
        {"7", "true", "0", "0"},
        {"8", "true", "0", "0"},
        {"9", "true", "0", "0"},
        {"0", "true", "0", "0"}};

    /**
     *
     */
    public Game() {
        try {
            if (!wordLoaded) {
                wordLoaded = false;
            }
            dim = Toolkit.getDefaultToolkit().getScreenSize();

            //load images into images array if images array is empty
            if (images == null) {
                images = new BufferedImage[7];
                images[0] = ImageIO.read(getClass().getResource("/hangman/images/backImageBlack1.png"));
                images[1] = ImageIO.read(getClass().getResource("/hangman/images/backImageBlack2.png"));
                images[2] = ImageIO.read(getClass().getResource("/hangman/images/backImageBlack3.png"));
                images[3] = ImageIO.read(getClass().getResource("/hangman/images/backImageBlack4.png"));
                images[4] = ImageIO.read(getClass().getResource("/hangman/images/backImageBlack5.png"));
                images[5] = ImageIO.read(getClass().getResource("/hangman/images/backImageBlack6.png"));
                images[6] = ImageIO.read(getClass().getResource("/hangman/images/backImageBlack7.png"));
            }

            //retrieve gif image
            if (jumpGifImageIcon == null) {
                jumpGifImageIcon = new ImageIcon(getClass().getResource("/hangman/images/jump.gif"));
            }
            if (swingGifImageIcon == null) {
                swingGifImageIcon = new ImageIcon(getClass().getResource("/hangman/images/swing.gif"));
            }

            //get image from image icon
            if (jumpGifImage == null) {
                jumpGifImage = jumpGifImageIcon.getImage();
            }
            if (swingGifImage == null) {
                swingGifImage = swingGifImageIcon.getImage();
            }            

            //if paint is not set create it
            if (paint == null) {
                paint = new Painter();
            }
            //start thread that paints the graphics
            paint.start();
        } catch (HeadlessException | IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //method that is used to start the game
    /**
     *
     * @param tempWord
     * @param sp
     */
    public void startGame(String tempWord, boolean sp) {
        //create array that wil contain all the letter of the word and if it has been used
        wordArr = new String[tempWord.length()][2];
        //add all the letters to the array and set all letters not used
        for (int i = 0; i < wordArr.length; i++) {
            wordArr[i][0] = tempWord.substring(i, i + 1);
            wordArr[i][1] = "true";
        }
        //set word to word var
        word = tempWord;
        //set singlePlayerMode true if single player else set it false
        singlePlayerMode = sp;
        //becomes true when word is loaded into array
        wordLoaded = true;
        if (frame == null) {
            //create frame
            setFrame();
        } else {
            //set frame visible
            frame.setVisible(true);
        }

    }

    /**
     *
     */
    public void setFrame() {
        //create frame
        frame = new JFrame();
        //add panel to frame
        frame.getContentPane().add(new Game());
        //set frame to the size of the screen
        frame.setSize(dim);
        //remove frame decorations
        frame.setUndecorated(true);
        //make frame non resizable
        frame.setResizable(false);
        //set frame visible
        frame.setVisible(true);
        //add key Listener for keyboard input
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //not used
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //not used
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //if game is not over
                if (!gameOver) {
                    //get letter pressed and check it against the alfadet array to check if it is stil usable
                    for (int i = 0; i < alfabet.length; i++) {
                        if (alfabet[i][0].equalsIgnoreCase(e.getKeyChar() + "")) {
                            currentLetter = e.getKeyChar() + "";
                            //set loading true to stop input while input is being processed
                            loading = true;
                            //if the letter entered is usable call processLetter method
                            if (isUsable()) {
                                processLetter();
                            } else {
                                //else set loading false to allow user to input another letter
                                loading = false;
                            }
                            //set usability of letter chosen to false
                            alfabet[i][1] = "false";
                        }
                    }
                    //if escape key is pressed
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        //all static vars are reset to default
                        reset();
                        //sets game invisible and menu visible
                        if (menu == null) {
                            menu = new Menu();
                        }
                        menu.setVisible(true);
                        frame.setVisible(false);
                    }

                    //check for key combinations
                    //control + H shows the hint
                    if ((e.getModifiers() & KeyEvent.CTRL_MASK) == KeyEvent.CTRL_MASK && e.getKeyCode() == KeyEvent.VK_H) {
                        showHint();
                    }
                    //control + T shows a missing letter in the word
                    if ((e.getModifiers() & KeyEvent.CTRL_MASK) == KeyEvent.CTRL_MASK && e.getKeyCode() == KeyEvent.VK_T) {
                        loading = true;
                        showLetter();
                    }
                    //control + R removes a letter not in the word from the alfabet
                    if ((e.getModifiers() & KeyEvent.CTRL_MASK) == KeyEvent.CTRL_MASK && e.getKeyCode() == KeyEvent.VK_R) {
                        loading = true;
                        removeDud();
                    }
                } else {
                    //if game over press [SPACEBAR]
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        //resets all static values
                        reset();
                        //if game was single player return to category selection
                        if (singlePlayerMode) {
                            if (categorySelect == null) {
                                categorySelect = new CategorySelect();
                            }
                            categorySelect.setVisible(true);
                            //if game was multiplayer return to startMultiplayer
                        } else {
                            if (startMultiplayer == null) {
                                startMultiplayer = new StartMultiplayer();
                            }
                            startMultiplayer.setVisible(true);
                        }
                        frame.setVisible(false);
                    }
                }
            }
        });
        //add Mouse Listeners for user input from the mouse
        frame.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //if game is not over
                if (!gameOver) {
                    //Position of the X Coordinate of the cursor on the screen
                    cursorPosX = e.getXOnScreen();
                    //Position of the Y Coordinate of the cursor on the screen
                    cursorPosY = e.getYOnScreen() + 40;
                    //Loop through alfabet array and check in cursor match  the letter coordinate
                    for (int i = 0; i < alfabet.length; i++) {
                        if ((cursorPosX > Integer.parseInt(alfabet[i][2]) && cursorPosX < Integer.parseInt(alfabet[i][2]) + 40 && cursorPosY > Integer.parseInt(alfabet[i][3]) && cursorPosY < Integer.parseInt(alfabet[i][3]) + 40) && alfabet[i][1].equals("true") && loading == false) {
                            //set current letter to letter clicked on
                            currentLetter = alfabet[i][0];
                            //set loading true to stop user input while processing the letter
                            loading = true;
                            if (isUsable()) {
                                //call processLetter method
                                processLetter();
                            } else {
                                //stop loading to allow user input
                                loading = false;
                            }
                            //set used letter false
                            alfabet[i][1] = "false";
                        }
                    }
                    System.out.println(cursorPosX + ", " + cursorPosY);
                    //check if user clicked on show hint button
                    if (singlePlayerMode && cursorPosX > 80 && cursorPosX < 90 && cursorPosY < 120 && cursorPosY > 110) {
                        System.out.println("showing hint!");
                        //call showHint method
                        showHint();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //not used
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //not used
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //not used
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //not used
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                //not used
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //not used
            }
        });
    }

    //paint graphics to the panel
    @Override
    public void paintComponent(Graphics tempG) {
        //create Graphics2D
        Graphics2D g = (Graphics2D) tempG;

        //paint a black background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, (int) dim.getWidth(), (int) dim.getHeight());

        //paint chalk board
        if (misses == 0) {
            g.drawImage(images[0], 0, 0, (int) dim.getWidth(), (int) dim.getHeight(), this);
        } else if (misses == 1) {
            g.drawImage(images[1], 0, 0, (int) dim.getWidth(), (int) dim.getHeight(), this);
        } else if (misses == 2) {
            g.drawImage(images[2], 0, 0, (int) dim.getWidth(), (int) dim.getHeight(), this);
        } else if (misses == 3) {
            g.drawImage(images[3], 0, 0, (int) dim.getWidth(), (int) dim.getHeight(), this);
        } else if (misses == 4) {
            g.drawImage(images[4], 0, 0, (int) dim.getWidth(), (int) dim.getHeight(), this);
        } else if (misses == 5) {
            g.drawImage(images[5], 0, 0, (int) dim.getWidth(), (int) dim.getHeight(), this);
        } else if (misses == 6) {
            g.drawImage(images[6], 0, 0, (int) dim.getWidth(), (int) dim.getHeight(), this);
        }

        //set font
        g.setFont(new Font("ERASER", Font.BOLD, 40));
        //if game is over
        if (gameOver) {
            //if user lost
            if (!gameStatus) {
                //draw dead stickman
                g.drawImage(swingGifImage, 0, 0, (int) dim.getWidth(), (int) dim.getHeight(), this);
                g.setColor(Color.RED);
                g.drawString("GAME OVER", (int) dim.getWidth() / 2 - 180, (int) dim.getHeight() / 2);
                g.drawString("PRESS 'SPACE' TO CONTINUE", (int) dim.getWidth() / 2 - 350, (int) dim.getHeight() / 2 + 40);
                g.setColor(Color.WHITE);
                //draw phrase to screen
                drawString(g, word, 80, (int) dim.getHeight() - 200, (int) dim.getWidth() - 160);
                //if user won
            } else {
                //draw happy stickman
                g.drawImage(jumpGifImage, 0, 0, (int) dim.getWidth(), (int) dim.getHeight(), this);
                g.setColor(Color.GREEN);
                g.drawString("YOU WON!", (int) dim.getWidth() / 2 - 350, (int) dim.getHeight() / 2);
                g.drawString("PRESS 'SPACE' TO CONTINUE", (int) dim.getWidth() / 2 - 550, (int) dim.getHeight() / 2 + 40);
                g.setColor(Color.WHITE);
                //draw phrase to screen
                drawString(g, word, 80, (int) dim.getHeight() - 200, (int) dim.getWidth() - 160);
            }
            //if game is stil running
        } else {
            int wordX = 80;
            int wordY = 200;
            //printing the word to the screen
            //if word is loaded into the word array
            if (wordLoaded) {
                letterCounter = 0;
                spaces = 0;
                //loop through array and print all the  to the screen
                for (int i = 0; i < wordArr.length; i++) {
                    //if char is not whitespace
                    if (!wordArr[i][0].equals(" ")) {
                        //if the letter is not used print a lyne to the screen
                        if (wordArr[i][1].equals("true")) {
                            g.setColor(Color.WHITE);
                            g.drawLine(wordX - 10, wordY + 20, wordX + 10, wordY + 20);
                            //else draw the letter
                        } else {
                            g.setColor(Color.WHITE);
                            g.drawString(wordArr[i][0], wordX, wordY);
                            //add to letterCounter when the letter is printed
                            letterCounter++;
                        }
                        //if char is whitespace leave a empty spot
                    } else {
                        spaces++;
                    }
                    //set newline when word reaches the end of the screen
                    if (wordX < dim.getWidth() - 700) {
                        wordX += 60;
                    } else {
                        wordY += 60;
                        wordX = 80;
                    }
                }
                //if the amount of letter printed equals the length of the word the game is over and the user won
                if ((letterCounter + spaces) == wordArr.length) {
                    gameOver = true;
                    gameStatus = true;
                }
            }

            //printing the alfabet to the screen
            int spacerX = 80;
            int spacerY = (int) dim.getHeight() - 200;
            for (int i = 0; i < alfabet.length; i++) {
                //if the letter was not used [rint it in white
                if (alfabet[i][1].equals("true")) {
                    g.setColor(Color.WHITE);
                    g.drawString(alfabet[i][0], spacerX, spacerY);
                    alfabet[i][2] = Integer.toString(spacerX);
                    alfabet[i][3] = Integer.toString(spacerY);
                    //else print it in black
                } else {
                    g.setColor(Color.BLACK);
                    g.drawString(alfabet[i][0], spacerX, spacerY);
                    alfabet[i][2] = Integer.toString(spacerX);
                    alfabet[i][3] = Integer.toString(spacerY);
                }
                //set newline when letters reach en of the screen
                if (spacerX < dim.getWidth() - 180) {
                    spacerX += 60;
                } else {
                    spacerY += 60;
                    spacerX = 80;
                }
            }
            //if in singleplayer mode paint the hint option on the screen
            if (singlePlayerMode) {
                g.setFont(new Font("ERASER", Font.PLAIN, 20));
                g.setColor(Color.WHITE);
                g.fillOval(80, 70, 10, 10);
                //draw hint to screen                
                drawString(g, hint, 95, 80, 700);
            }
        }
    }
    //method to check if a letter in the alfabet array is usable

    /**
     *
     * @return
     */
    public boolean isUsable() {

        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i][0].equalsIgnoreCase(currentLetter) && alfabet[i][1].equals("true")) {
                return true;
            }
        }
        return false;
    }

    //process the letter that the user entered
    /**
     *
     */
    public void processLetter() {
        msg = "";
        //loop through the word
        for (int i = 0; i < wordArr.length; i++) {
            //if letter enetered matches one of the letters in the word set that letter as used en msg equals Match
            if (wordArr[i][0].equalsIgnoreCase(currentLetter)) {
                wordArr[i][1] = "false";
                msg = "Match";
                //else msg equals not a match
            } else {
                if (!msg.equals("Match")) {
                    msg = "Not a Match";
                }
            }
        }
        //if not a match 1 is added to the misses var and if there is 6 misses the game ends and the user loses
        if (!msg.equals("Match")) {
            if (++misses == 6) {
                gameOver = true;
                gameStatus = false;
            }
        }
        //stop loading to allow user input
        loading = false;
    }
    //shows the hint of the current word

    /**
     *
     */
    public void showHint() {
        //create database
        if (dataRetrieval == null) {
            dataRetrieval = new DataRetrieval();
        }
        //retrive hint by using the getHint method from the database class sending the word as argument
        hint = dataRetrieval.getHint();
        //split the hint up where there is ';' to create newlines
        //hint = text.split(";");
        processHint(hint);
    }

    //method to show letter in the word
    /**
     *
     */
    public void showLetter() {
        String lettersLeft = "";
        //make list of unused letters in the word
        for (int i = 0; i < wordArr.length; i++) {
            if (wordArr[i][1].equals("true")) {
                lettersLeft += wordArr[i][0];
            }
        }

        //create a random number
        Random r = new Random();
        int num = r.nextInt(lettersLeft.length());

        //set current letter to a random letter from the letters that is left
        currentLetter = lettersLeft.substring(num, num + 1);
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i][0].equalsIgnoreCase(currentLetter)) {
                alfabet[i][1] = "false";
            }
        }
        //process the random letter selected
        processLetter();
    }

    /**
     *
     */
    public void removeDud() {
        String lettersLeftAlfabet = "";
        //get all alfabet letters not used
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i][1].equals("true")) {
                lettersLeftAlfabet += alfabet[i][0];
            }
        }

        String lettersLeftWord = "";
        //get all the letters in the word not used
        for (int i = 0; i < wordArr.length; i++) {
            if (wordArr[i][1].equals("true")) {
                lettersLeftWord += wordArr[i][0];
            }
        }

        String duds = "";
        //loop through the both lists of letter unused and find matching letters
        for (int i = 0; i < lettersLeftAlfabet.length(); i++) {
            for (int j = 0; j < lettersLeftWord.length(); j++) {

                if (lettersLeftAlfabet.substring(i, i + 1).equalsIgnoreCase(lettersLeftWord.substring(j, j + 1))) {
                    duds += lettersLeftAlfabet.substring(i, i + 1);
                    break;
                }
            }
        }
        //remove all matching letters from the letters left in the alfabet
        duds = lettersLeftAlfabet.replaceAll("[" + duds + "]", "");

        //there is letters left a random one is removed from the alfabet array
        if (duds.length() > 0) {
            Random r = new Random();
            int num = r.nextInt(duds.length());


            String removeDud = duds.substring(num, num + 1);

            for (int i = 0; i < alfabet.length; i++) {
                if (alfabet[i][0].equalsIgnoreCase(removeDud)) {
                    alfabet[i][1] = "false";
                }
            }
        }
        loading = false;
    }

    //reset al static vars to default
    /**
     *
     */
    public void reset() {
        for (int i = 0; i < alfabet.length; i++) {
            alfabet[i][1] = "true";
        }
        letterCounter = 0;
        gameOver = false;
        misses = 0;
        hint = "Show Hint";
        
    }
    
    //process hint new lines
    /**
     * 
     * @param text
     */
    public void processHint(String text){
        if(text.length() > 70){
            
        }
    }
    
    //Create newlines for hint
    /**
     * 
     * @param g
     * @param s
     * @param x
     * @param y
     * @param width
     */
    public void drawString(Graphics g, String s, int x, int y, int width)
{
	// FontMetrics gives us information about the width,
	// height, etc. of the current Graphics object's Font.
	FontMetrics fm = g.getFontMetrics();

	int lineHeight = fm.getHeight();

	int curX = x;
	int curY = y;
        
        //split the text into words
	String[] words = s.split(" ");
        
        //loop through the array
	for (String tempWord : words)
	{
		// Find out thw width of the word.
		int wordWidth = fm.stringWidth(tempWord + " ");

		// If text exceeds the width, then move to next line.
		if (curX + wordWidth >= x + width)
		{
			curY += lineHeight;
			curX = x;
		}
                //draw the string
		g.drawString(tempWord, curX, curY);

		// Move over to the right for next word.
		curX += wordWidth;
	}
}

    //thread that paints the graphics to the screen
    /**
     *
     */
    public class Painter extends Thread {
        //refresh graphics 33 times per second or every 30 miliseconds (33fps)

        @Override
        public void run() {
            while (true) {
                try {
                    repaint();
                    Thread.sleep(30);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}