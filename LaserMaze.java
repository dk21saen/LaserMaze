import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
////////////////////////////////////////////////////////////////////////////////
/**
 * Write a description of class LaserMaze here.
 *
 * @author Justyn McHarg, Collin Adams, Devante Saenger
 *         Dylan Lane, Ashley Hughes
 * @version 3/20/2018
 */
public class LaserMaze extends JPanel implements MouseListener
{
    protected int BoardChoice;
    protected String[][] LaserGrid;
    protected Image target;
    protected Image tMirror0;
    protected Image tMirror90;
    protected Image tMirror270;
    protected Image tMirror180;
    protected Image dMirror;
    protected Image laser;
    protected Image Checkpoint;
    protected int dMirrorDX1;
    protected int dMirrorDY1;
    protected int dMirrorDX2;
    protected int dMirrorDY2;
    protected int laserDX1;
    protected int laserDY1;
    protected int laserDX2;
    protected int laserDY2;
    protected boolean solved;
    /**
     * Contructor for LaserMaze class
     */
    public LaserMaze(){
        super();
        Toolkit k = Toolkit.getDefaultToolkit();
        setPreferredSize(new Dimension(700,700));
        setBackground( Color.WHITE );
        BoardChoice = 0;
        LaserGrid = new String[6][5];
        target = k.getImage("Target.jpg");
        tMirror0 = k.getImage("MirrorBlock.jpg");
        tMirror90 = k.getImage("MirrorBlockTurn90.jpg");
        tMirror180 = k.getImage("MirrorBlockTurn180.jpg");
        tMirror270 = k.getImage("MirrorBlockTurn270.jpg");
        Checkpoint = k.getImage("Checkpoint.jpg");
        dMirror = k.getImage("DoubleMirror.jpg");
        laser = k.getImage("LaserBlockRight.jpg");
        solved = false;

        dMirrorDX1 = 0;
        dMirrorDY1 = 0;
        dMirrorDX2 = 100;
        dMirrorDY2 = 100;

        laserDX1 = 0;
        laserDY1 = 0;
        laserDX2 = 100;
        laserDY2 = 100;
        addMouseListener(this);
    }

    @Override
    public void mouseEntered( MouseEvent e ) { }

    @Override 
    public void mouseExited( MouseEvent e ) { }

    /**
     * This method is used for detecting input if the user has either
     * right or left clicked. If the user left clicks the piece will rotate 
     * if that is allowed for the current puzzle trying to be solved.
     * Right click moves the pieces that are displayed at the top of the board
     * at the beginning of the game as long as the space you are trying to move
     * the piece to is a blank space.
     */
    @Override
    public void mouseClicked( MouseEvent e ) { 
        boolean blank = false;
        Toolkit k = Toolkit.getDefaultToolkit();
        if (solved == false) {
            if(BoardChoice == 1){
                if(SwingUtilities.isLeftMouseButton(e)){
                    int x = e.getX();
                    int i = getCol(x);
                    int y = e.getY();
                    int j = getRow(y);
                    if (x > 507 && x < 700 && y > 300 && y < 500) {
                        fireLaser();
                    }
                    else{
                        if(LaserGrid[j][i].equals("DoubleMirror.jpg"))
                        {
                            dMirror = k.getImage("DoubleMirrorTurn.jpg");
                            repaint(); 
                            LaserGrid[j][i] = "DoubleMirrorTurn.jpg";
                            e.consume();
                        }
                        else if(LaserGrid[j][i].equals("DoubleMirrorTurn.jpg"))
                        {
                            dMirror = k.getImage("DoubleMirror.jpg");
                            repaint(); 
                            LaserGrid[j][i] = "DoubleMirror.jpg";
                            e.consume();

                        }
                    }
                }
                if(SwingUtilities.isRightMouseButton(e))
                {
                    int x = e.getX();
                    int i = getCol(x);
                    int y = e.getY();
                    int j = getRow(y);
                    if (x > 507 && x < 700 && y > 300 && y < 500) {
                        fireLaser();
                    }
                    else{
                        if(LaserGrid[j][i].equals(""))
                        {
                            blank = true;
                        }
                        if(blank)
                        {
                            if(dMirror.equals(k.getImage("MirrorQ.jpg"))){
                                dMirror = k.getImage("DoubleMirror.jpg");
                            }
                            int xcoor = getXCoor(x);
                            int ycoor = getYCoor(y);

                            int tempDX1 = dMirrorDX1;
                            int tempDY1 = dMirrorDY1;
                            int tempDX2 = dMirrorDX2;
                            int tempDY2 = dMirrorDY2;
                            int tempI = getCol(tempDX1);
                            int tempJ = getRow(tempDY1);

                            dMirrorDX1 = xcoor;
                            dMirrorDY1 = ycoor;
                            dMirrorDX2 = xcoor + 100;
                            dMirrorDY2 = ycoor + 100;
                            repaint();
                            LaserGrid[j][i] = "DoubleMirror.jpg";
                            LaserGrid[tempJ][tempI] = "";
                            e.consume();
                        }
                    }
                }
            }

            if(BoardChoice == 2){
                if(SwingUtilities.isLeftMouseButton(e)){
                    int x = e.getX();
                    int i = getCol(x);
                    int y = e.getY();
                    int j = getRow(y);
                    if (x > 507 && x < 700 && y > 300 && y < 500) {
                        fireLaser(0);
                    }
                    else
                    {
                        if(LaserGrid[j][i].equals("MirrorBlock.jpg") 
                        || LaserGrid[j][i].equals("MirrorBlockQ.jpg"))
                        {
                            if(i==1 && j == 5){
                                tMirror0 = k.getImage("MirrorBlockTurn90.jpg");
                                repaint(); 
                                LaserGrid[j][i] = "MirrorBlockTurn90.jpg";
                                e.consume();
                            }
                            if(i==1 && j == 4){
                                tMirror90 = k.getImage("MirrorBlockTurn90.jpg");
                                repaint(); 
                                LaserGrid[j][i] = "MirrorBlockTurn90.jpg";
                                e.consume();
                            }
                            if(i==3 && j == 4){
                                tMirror270 = k.getImage("MirrorBlockTurn90.jpg");
                                repaint(); 
                                LaserGrid[j][i] = "MirrorBlockTurn90.jpg";
                                e.consume();
                            }
                            if(i==3 && j == 5){
                                tMirror180 = k.getImage("MirrorBlockTurn90.jpg");
                                repaint(); 
                                LaserGrid[j][i] = "MirrorBlockTurn90.jpg";
                                e.consume();
                            }
                        }
                        else if(LaserGrid[j][i].equals("MirrorBlockTurn90.jpg")
                        || LaserGrid[j][i].equals("MirrorBlockQ.jpg"))
                        {
                            if(i==1 && j == 5){
                                tMirror0 = k.getImage("MirrorBlockTurn180.jpg");
                                repaint(); 
                                LaserGrid[j][i] = "MirrorBlockTurn180.jpg";
                                e.consume();
                            }
                            if(i==1 && j == 4){
                                tMirror90 = k.getImage("MirrorBlockTurn180.jpg");
                                repaint(); 
                                LaserGrid[j][i] = "MirrorBlockTurn180.jpg";
                                e.consume();
                            }
                            if(i==3 && j == 4){
                                tMirror270 = k.getImage("MirrorBlockTurn180.jpg");
                                repaint(); 
                                LaserGrid[j][i] = "MirrorBlockTurn180.jpg";
                                e.consume();
                            }
                            if(i==3 && j == 5){
                                tMirror180 = k.getImage("MirrorBlockTurn180.jpg");
                                repaint(); 
                                LaserGrid[j][i] = "MirrorBlockTurn180.jpg";
                                e.consume();
                            }
                        }
                        else if(LaserGrid[j][i].equals("MirrorBlockTurn180.jpg")
                        || LaserGrid[j][i].equals("MirrorBlockQ.jpg"))
                        {
                            if(i==1 && j == 5){
                                tMirror0 = k.getImage("MirrorBlockTurn270.jpg");
                                repaint(); 
                                LaserGrid[j][i] = "MirrorBlockTurn270.jpg";
                                e.consume();
                            }
                            if(i==1 && j == 4){
                                tMirror90 = k.getImage("MirrorBlockTurn270.jpg");
                                repaint(); 
                                LaserGrid[j][i] = "MirrorBlockTurn270.jpg";
                                e.consume();
                            }
                            if(i==3 && j == 4){
                                tMirror270 = k.getImage("MirrorBlockTurn270.jpg");
                                repaint(); 
                                LaserGrid[j][i] = "MirrorBlockTurn270.jpg";
                                e.consume();
                            }
                            if(i==3 && j == 5){
                                tMirror180 = k.getImage("MirrorBlockTurn270.jpg");
                                repaint(); 
                                LaserGrid[j][i] = "MirrorBlockTurn270.jpg";
                                e.consume();
                            }
                        }
                        else if(LaserGrid[j][i].equals("MirrorBlockTurn270.jpg")
                        || LaserGrid[j][i].equals("MirrorBlockQ.jpg"))
                        {
                            if(i==1 && j == 5){
                                tMirror0 = k.getImage("MirrorBlock.jpg");
                                repaint(); 
                                LaserGrid[j][i] = "MirrorBlock.jpg";
                                e.consume();
                            }
                            if(i==1 && j == 4){
                                tMirror90 = k.getImage("MirrorBlock.jpg");
                                repaint(); 
                                LaserGrid[j][i] = "MirrorBlock.jpg";
                                e.consume();
                            }
                            if(i==3 && j == 4){
                                tMirror270 = k.getImage("MirrorBlock.jpg");
                                repaint(); 
                                LaserGrid[j][i] = "MirrorBlock.jpg";
                                e.consume();
                            }
                            if(i==3 && j == 5){
                                tMirror180 = k.getImage("MirrorBlock.jpg");
                                repaint(); 
                                LaserGrid[j][i] = "MirrorBlock.jpg";
                                e.consume();
                            }
                        }
                        else if(LaserGrid[j][i].equals("LaserBlockRight.jpg")
                        || LaserGrid[j][i].equals("LaserBlockQ.jpg")){
                            laser = k.getImage("LaserBlockDown.jpg");
                            repaint();
                            LaserGrid[j][i] = "LaserBlockDown.jpg";
                            e.consume();
                        }
                        else if(LaserGrid[j][i].equals("LaserBlockDown.jpg")){
                            laser = k.getImage("LaserBlockLeft.jpg");
                            repaint();
                            LaserGrid[j][i] = "LaserBlockLeft.jpg";
                            e.consume();
                        }
                        else if(LaserGrid[j][i].equals("LaserBlockLeft.jpg")){
                            laser = k.getImage("LaserBlockUp.jpg");
                            repaint();
                            LaserGrid[j][i] = "LaserBlockUp.jpg";
                            e.consume();
                        }
                        else if(LaserGrid[j][i].equals("LaserBlockUp.jpg")){
                            laser = k.getImage("LaserBlockRight.jpg");
                            repaint();
                            LaserGrid[j][i] = "LaserBlockRight.jpg";
                            e.consume();
                        }
                        else if(LaserGrid[j][i].equals("Checkpoint.jpg"))
                        {
                            Checkpoint = k.getImage("CheckpointTurn.jpg");
                            repaint(); 
                            LaserGrid[j][i] = "CheckpointTurn.jpg";
                            e.consume();
                        }
                        else if(LaserGrid[j][i].equals("CheckpointTurn.jpg"))
                        {
                            Checkpoint = k.getImage("Checkpoint.jpg");
                            repaint(); 
                            LaserGrid[j][i] = "Checkpoint.jpg";
                            e.consume();
                        }
                        else if(LaserGrid[j][i].equals("Target.jpg")
                        || LaserGrid[j][i].equals("TMirrorQ.jpg")){
                            target = k.getImage("Target90.jpg");
                            repaint();
                            LaserGrid[j][i] = "Target90.jpg";
                            e.consume();
                        }
                        else if(LaserGrid[j][i].equals("Target90.jpg")
                        || LaserGrid[j][i].equals("TMirrorQ.jpg")){
                            target = k.getImage("Target180.jpg");
                            repaint();
                            LaserGrid[j][i] = "Target180.jpg";
                            e.consume();
                        }
                        else if(LaserGrid[j][i].equals("Target180.jpg")
                        || LaserGrid[j][i].equals("TMirrorQ.jpg")){
                            target = k.getImage("Target270.jpg");
                            repaint();
                            LaserGrid[j][i] = "Target270.jpg";
                            e.consume();
                        }
                        else if(LaserGrid[j][i].equals("Target270.jpg")
                        || LaserGrid[j][i].equals("TMirrorQ.jpg")){
                            target = k.getImage("Target.jpg");
                            repaint();
                            LaserGrid[j][i] = "Target.jpg";
                            e.consume();
                        }
                    }
                }
                if(SwingUtilities.isRightMouseButton(e))
                {
                    int x = e.getX();
                    int i = getCol(x);
                    int y = e.getY();
                    int j = getRow(y);
                    if (x > 507 && x < 700 && y > 300 && y < 500) {
                        fireLaser(0);
                    }
                    else{
                        if(LaserGrid[j][i].equals(""))
                        {
                            blank = true;
                        }
                        if(blank)
                        {
                            if(laser.equals(k.getImage("LaserBlockQ.jpg"))){
                                laser = k.getImage("LaserBlockRight.jpg");
                            }
                            int xcoor = getXCoor(x);
                            int ycoor = getYCoor(y);

                            int tempDX1 = laserDX1;
                            int tempDY1 = laserDY1;
                            int tempDX2 = laserDX2;
                            int tempDY2 = laserDY2;
                            int tempI = getCol(tempDX1);
                            int tempJ = getRow(tempDY1);

                            laserDX1 = xcoor;
                            laserDY1 = ycoor;
                            laserDX2 = xcoor + 100;
                            laserDY2 = ycoor + 100;
                            repaint();
                            LaserGrid[j][i] = "LaserBlockRight.jpg";
                            LaserGrid[tempJ][tempI] = "";
                            e.consume();
                        }
                    }
                }
            }
        }
    }

    /**
     * This method checks to see if the pieces for the 1st board are in
     * the correct place when the player wants to fire the laser
     */
    public void fireLaser() {
        if (LaserGrid[2][1].equals("DoubleMirrorTurn.jpg")) {
            solved = true;
            repaint();
        }
        else{
            JOptionPane.showMessageDialog(null,"Incorrect Solution");
        }
    } 

    /**
     * This method checks to see if the pieces for the 2nd board are in
     * the correct place when the player wants to fire the laser
     */
    public void fireLaser(int direction) {
        if (LaserGrid[1][2].equals("LaserBlockDown.jpg") 
        && LaserGrid[2][2].equals("Checkpoint.jpg")
        && LaserGrid[4][1].equals("MirrorBlock.jpg") 
        && LaserGrid[4][3].equals("MirrorBlockTurn90.jpg")
        && LaserGrid[5][1].equals("MirrorBlockTurn270.jpg") 
        && LaserGrid[5][2].equals("Target270.jpg")
        && LaserGrid[5][3].equals("MirrorBlockTurn180.jpg")) {
            solved = true;
            repaint();
        } 
        else{
            JOptionPane.showMessageDialog(null,"Incorrect Solution");
        }
    }

    /**
     * This method makes the 2D array that is used as a string 
     * representation of the current board on display
     */
    public void gridMaker(){
        if(BoardChoice==1){
            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 5; j++){ 
                    LaserGrid[i][j] = "";
                } 
            }
            LaserGrid[0][0] = "DoubleMirror.jpg";
            LaserGrid[2][0] = "LaserBlockRight.jpg";
            LaserGrid[2][4] = "MirrorBlockTurn90.jpg";
            LaserGrid[5][1] = "MirrorBlockTurn270.jpg";
            LaserGrid[5][4] = "MirrorBlockTurn180.jpg";
            LaserGrid[1][1] = "Target.jpg";
        }
        else if(BoardChoice == 2){
            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 5; j++){
                    LaserGrid[i][j] = "";
                }
            }
            LaserGrid[5][2] = "TMirrorQ.jpg";
            LaserGrid[2][2] = "Checkpoint.jpg";
            LaserGrid[4][1] = "MirrorBlockQ.jpg";
            LaserGrid[5][1] = "MirrorBlockQ.jpg"; 
            LaserGrid[4][3] = "MirrorBlockQ.jpg";
            LaserGrid[5][3] = "MirrorBlockQ.jpg";
        }
    }

    @Override
    public void mousePressed( MouseEvent e ) { }

    @Override 
    public void mouseReleased( MouseEvent e ) { } 

    /**
     * Graphics method that draws the board itself and displays the images 
     * used for the pieces in the game.
     */
    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        String fire = "Fire Laser";
        g.setFont(new Font("TimesRoman",Font.PLAIN,24));
        Toolkit k = Toolkit.getDefaultToolkit();
        Image b1 = k.getImage("Button.png");
        g.drawImage(b1, 507, 300, 700, 500,0,0,b1.getWidth(null),b1.getHeight(null),null);
        g.drawString(fire, 550 , 400);

        if(BoardChoice == 1){
            // Image board = k.getImage("Beginner.jpg");
            // g.drawImage(board,507,0,700,300,0,0,board.getWidth(null),board.getHeight(null),null);

            g.drawImage(dMirror,dMirrorDX1,dMirrorDY1,dMirrorDX2,dMirrorDY2,0,0,dMirror.getWidth(null),dMirror.getHeight(null),null);

            g.drawImage(laser,0,200,100,300,0,0,laser.getWidth(null),laser.getHeight(null),null);

            g.drawImage(tMirror90,400,200,500,300,0,0,tMirror90.getWidth(null),tMirror90.getHeight(null),null);

            g.drawImage(tMirror270,100,500,200,600,0,0,tMirror270.getWidth(null),tMirror270.getHeight(null),null);

            g.drawImage(tMirror180,400,500,500,600,0,0,tMirror180.getWidth(null),tMirror180.getHeight(null),null);

            g.drawImage(target,100,100,200,200,0,0,target.getWidth(null),target.getHeight(null),null);
            if(solved){
                g.setColor(Color.RED);

                g.drawLine(50,250,150,250);

                g.drawLine(150,250,150,550);

                g.drawLine(150,550,450,550);

                g.drawLine(450,550,450,250);

                g.drawLine(450,250,150,250);

                g.drawLine(150,250,150,150);

                g.drawString("Puzzle Solved!",0,690);
            }
        }
        else if(BoardChoice == 2){
            // Image board = k.getImage("Intermediate.jpg");
            // g.drawImage(board,507,0,700,300,0,0,board.getWidth(null),board.getHeight(null),null);

            g.drawImage(target,200,500,300,600,0,0,target.getWidth(null),target.getHeight(null),null);

            g.drawImage(tMirror0,100,500,200,600,0,0,tMirror0.getWidth(null),tMirror0.getHeight(null),null);

            g.drawImage(tMirror90,100,400,200,500,0,0,tMirror90.getWidth(null),tMirror90.getHeight(null),null);

            g.drawImage(tMirror180,300,500,400,600,0,0,tMirror180.getWidth(null),tMirror180.getHeight(null),null);

            g.drawImage(tMirror270,300,400,400,500,0,0,tMirror270.getWidth(null),tMirror270.getHeight(null),null);

            g.drawImage(laser,laserDX1,laserDY1,laserDX2,laserDY2,0,0,laser.getWidth(null),laser.getHeight(null),null);

            g.drawImage(Checkpoint,200,200,300,300,0,0,Checkpoint.getWidth(null),Checkpoint.getHeight(null),null);

            if (solved) {
                g.setColor(Color.red);

                g.drawLine(250, 150, 250, 550);

                g.drawLine(250, 550, 150, 550);

                g.drawLine(150, 550, 150, 450);

                g.drawLine(150, 450, 350, 450);

                g.drawLine(350, 450, 350, 550);

                g.drawLine(350, 550, 250, 550);

                g.drawString("Puzzle Solved!",0,690);
            }
        }

        g.setColor( Color.blue );
        //Draws outline for board
        g.drawRect( 0, 100, 500, 500 );
        //draws first column
        g.drawRect( 0, 100, 100, 100 );
        g.drawRect( 0, 200, 100, 100 );
        g.drawRect( 0, 300, 100, 100 );
        g.drawRect( 0, 400, 100, 100 );
        g.drawRect( 0, 500, 100, 100 );
        //draws second column
        g.drawRect( 0, 100, 200, 100 );
        g.drawRect( 0, 200, 200, 100 );
        g.drawRect( 0, 300, 200, 100 );
        g.drawRect( 0, 400, 200, 100 );
        g.drawRect( 0, 500, 200, 100 );
        //draws third column
        g.drawRect( 0, 100, 300, 100 );
        g.drawRect( 0, 200, 300, 100 );
        g.drawRect( 0, 300, 300, 100 );
        g.drawRect( 0, 400, 300, 100 );
        g.drawRect( 0, 500, 300, 100 );
        //draws fourth column
        g.drawRect( 0, 100, 400, 100 );
        g.drawRect( 0, 200, 400, 100 );
        g.drawRect( 0, 300, 400, 100 );
        g.drawRect( 0, 400, 400, 100 );
        g.drawRect( 0, 500, 400, 100 );
        //draws fifth column
        g.drawRect( 0, 100, 500, 100 );
        g.drawRect( 0, 200, 500, 100 );
        g.drawRect( 0, 300, 500, 100 );
        g.drawRect( 0, 400, 500, 100 );
        g.drawRect( 0, 500, 500, 100 );
        
        g.setColor(Color.RED);
        g.fillOval(500,0,200,200);
        
        g.setFont(new Font("TimesRoman",Font.PLAIN,132));
        g.setColor(Color.WHITE);
        g.drawString("1", 560, 145);
    }

    /**
     * Creates the JFrame for the project. Asks user which puzzle they would 
     * like to play.
     */
    private static void createAndShowGUI(){ 
        //Create and set up the window.
        Toolkit k = Toolkit.getDefaultToolkit();
        JFrame frame = new JFrame("LaserMaze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String firstStrNumber;
        firstStrNumber = JOptionPane.showInputDialog(null, "Enter 1 for Board " 
            + "1, Enter 2 for Board 2",
            "Board Selection", JOptionPane.QUESTION_MESSAGE);

        JOptionPane.showMessageDialog(null, " Set up mirrors in order to guide" 
            + "the laser to your target \n Left click on a piece to rotate it" 
            + "(if applicable) \n Right click on an empty space to move piece" 
            + "there \n Click on Fire Laser button to test solution \n If "
            + "laser's path pops up, your solution was correct! \n Otherwise," 
            + "keep trying");

        LaserMaze panel = new LaserMaze();
        frame.getContentPane().add(panel);
        panel.BoardChoice = Integer.parseInt(firstStrNumber);
        if(panel.BoardChoice == 1){
            panel.dMirror = k.getImage("MirrorQ.jpg");
        }
        if(panel.BoardChoice == 2){
            panel.laser = k.getImage("LaserBlockQ.jpg");
            panel.target = k.getImage("TMirrorQ.jpg");
            panel.tMirror0 = k.getImage("MirrorBlockQ.jpg");
            panel.tMirror90 = k.getImage("MirrorBlockQ.jpg");
            panel.tMirror180 = k.getImage("MirrorBlockQ.jpg");
            panel.tMirror270 = k.getImage("MirrorBlockQ.jpg");
        }
        panel.gridMaker();
        //Display the window.
        frame.pack();
        frame.setVisible(true); 
    }

    /**
     * Method that returns an int based on the given parameter.
     * 
     * @param   int x   Given input value(Usually a coordinate value)
     * @return  int     Value is based upon given parameter
     */
    private int getCol(int x){ 
        if(x < 100){
            return 0;
        } 
        else if(x >= 100 && x < 200){
            return 1;
        }
        else if(x >= 200 && x < 300){
            return 2;
        }
        else if(x >= 300 && x < 400){
            return 3;
        }
        else if(x >= 400 && x < 500){
            return 4;
        }
        else{
            return 0;
        }
    }

    /**
     * Method that returns an int based on the given parameter.
     * 
     * @param   int y   Given input value(Usually a coordinate value)
     * @return  int     Value is based upon given parameter
     */
    private int getRow(int y){
        if(y >= 100 && y < 200){
            return 1;
        }
        else if(y >= 200 && y < 300){
            return 2;
        }
        else if(y >= 300 && y < 400){
            return 3;
        }
        else if(y >= 400 && y < 500){
            return 4;
        }
        else if(y >= 500 && y < 600){
            return 5;
        }
        else{
            return 0; 
        }
    }

    /**
     * Method that returns an int based on the given parameter.
     * 
     * @param   int x   Given input value(Usually a coordinate value)
     * @return  int     Value is based upon given parameter
     */
    private int getXCoor(int x){
        if(x < 100){
            return 0;
        }
        else if(x >= 100 && x < 200){
            return 100;
        }
        else if(x >= 200 && x < 300){
            return 200;
        }
        else if(x >= 300 && x < 400){
            return 300;
        }
        else if(x >= 400 && x < 500){
            return 400;
        }
        else{
            return 0; 
        }
    }

    /**
     * Method that returns an int based on the given parameter.
     * 
     * @param   int y   Given input value(Usually a coordinate value)
     * @return  int     Value is based upon given parameter
     */
    private int getYCoor(int y){
        if(y >= 100 && y < 200){
            return 100;
        }
        else if(y >= 200 && y < 300){
            return 200;
        }
        else if(y >= 300 && y < 400){
            return 300;
        }
        else if(y >= 400 && y < 500){
            return 400;
        }
        else if(y >= 500 && y < 600){
            return 500;
        }
        else{
            return 0; 
        }
    }

    /**
     * Main method that initiates the program
     */
    public static void main (String args[]){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    createAndShowGUI();
                }
            });
    }
}
