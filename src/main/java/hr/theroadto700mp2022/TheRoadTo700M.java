/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.theroadto700mp2022;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Point;
import java.awt.Color;
//new libraries
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

//new code
import java.awt.event.MouseMotionListener;
import java.awt.Dimension;
import java.text.ParseException;

/**
 *
 * @author emeka
 */
public class TheRoadTo700M {

    public static void main(String[] args) {

        /*
         * myPossibilities is the window where everything happens, it is an
         * object of the class JFrame
         */

 /*
         * create myPossibilities from a JFrame using the constructor
         * JFrame("****") and set the title to "Possibilities"
         */
        JFrame myPossibilities = new JFrame("Possiblilities");
        //new code declare scoreBoard
        JFrame scoreBoard = new JFrame("Score Board");
        //new code declare and design screen for scoreBoard
        JLabel screen = new JLabel("$0.0");
        screen.setOpaque(true);
        screen.setBackground(Color.lightGray);
        screen.setForeground(Color.black);
        screen.setHorizontalAlignment(JLabel.RIGHT);
        //new code create a font and add it to the screen
        Font font1 = new Font("Serif", Font.BOLD, 50);
        screen.setFont(font1);
        Border border2 = BorderFactory.createLoweredBevelBorder();
        screen.setBorder(border2);
        //new code create a Dimension object and set the screen size
        int screenW= 400;
        int screenH = 100;
        Dimension dim1 = new Dimension(screenW,screenH);
        scoreBoard.setMinimumSize(dim1);
        //new code: set what happens when the close button is clicked.
        scoreBoard.setDefaultCloseOperation(myPossibilities.EXIT_ON_CLOSE);
        //new code add screen to scoreBoard
        scoreBoard.getContentPane().add(screen);

        /*
         * make myPossibilities to close when you press the close button at the
         * top right corner by calling the setDefaultCloseOperation(Integer
         * Constant)
         */
        myPossibilities.setDefaultCloseOperation(myPossibilities.EXIT_ON_CLOSE);

        //set the screen size of myPossibilities
        myPossibilities.setSize(700, 500);

        //set layout, this facilitates random movement on myPossibilities
        myPossibilities.setLayout(null);

        //create me as a face from a JLabel by calling the constructor method JLabel()
        JLabel me = new JLabel();
        //add a picture to me
        me.setIcon(new ImageIcon(TheRoadTo700M.class.getResource("/myhead.jpg")));
        //set random position of me on myPossibilities
        int x = ThreadLocalRandom.current().nextInt(0, myPossibilities.getWidth() - 100);
        int y = ThreadLocalRandom.current().nextInt(0, myPossibilities.getHeight() - 50);
        me.setLocation(x, y);
        //set size of me
        me.setSize(50, 50);
        //add a border to me using the createLineBorder(color,thickness) method of the BorderFactory class
        Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
        me.setBorder(border);
        //new: code moved 72-24
        //place me in myPossibilities
        myPossibilities.getContentPane().add(me);
        //create a contract offer from a JLabel by calling the constructor JLabel()
        JLabel contractOffer = new JLabel();
        //create an icon using an image, and the icon to the contractOffer
        contractOffer.setIcon(new ImageIcon(TheRoadTo700M.class.getResource("/dollarstash.jpg")));
        //get us dollars number format
        NumberFormat nfUS = NumberFormat.getCurrencyInstance(Locale.US);
        //generate a random amount of dollars within the boundary -$1M and $1M
        String amount = nfUS.format(ThreadLocalRandom.current().nextInt(-1000000, 1000000));
        //append the randomly generated value to the contractOffer
        contractOffer.setText(amount);
        //set a font
        contractOffer.setFont(new Font(Font.DIALOG, Font.BOLD, 14));
        //position the amount in the middle of the contractOffer
        contractOffer.setHorizontalTextPosition(JLabel.CENTER);
        contractOffer.setVerticalTextPosition(JLabel.CENTER);
        //color the amount red
        contractOffer.setForeground(Color.RED);
        //set display size of contractOffer
        contractOffer.setSize(100, 100);
        //add the contractOffer to myPossibilities
        myPossibilities.getContentPane().add(contractOffer);
        //moved from 72-74
        //add an input device listener to me
        me.addMouseListener(new MeMouseListener(/*new code*/screen,me,contractOffer));
        /*new code*/ me.addMouseMotionListener(new MeMouseListener(/*new code*/screen,me,contractOffer));
        //let the window appear on the screen
        myPossibilities.setVisible(true);
        //new code to position scoreBoard beside my possibilities
        y=myPossibilities.getY();
        x=myPossibilities.getWidth();
        scoreBoard.setLocation(x,y);
        scoreBoard.setVisible(true);
        scoreBoard.setResizable(false);
        myPossibilities.setResizable(false);
        
        /*
         adding background image, idea from OG,the bg image should be placed in 
         * the other sources folder. In addition, objects added to a JFrame, are
         * stacked in order of addition. Great idea from OG.
         */
        JLabel bgImage = new JLabel();
        bgImage.setIcon(new ImageIcon(TheRoadTo700M.class.getResource("/ek.jpeg")));
        bgImage.setSize(myPossibilities.getWidth(),myPossibilities.getHeight());
        myPossibilities.add(bgImage);
        //create a ContractGenerator which will keep placing contracts on the screen
        (new ContractOfferGenerator(myPossibilities, contractOffer)).start();

    }
}

class ContractOfferGenerator extends Thread {
    //ContractOfferGenerator has 2 member references/objects

    JFrame anyJFrame1;
    JLabel contractOffer1;

    /*
     * definition of the constructor of the ContractOfferGenerator_v2 which takes
     * two parameters, a JFrame object or reference, and a JLabel object or
     * reference
     */
    ContractOfferGenerator(JFrame anyJFrame2, JLabel contractOffer2) {
        anyJFrame1 = anyJFrame2;
        contractOffer1 = contractOffer2;
    }

    public void run() {
        //get us dollars format
        NumberFormat nfUS = NumberFormat.getCurrencyInstance(Locale.US);
        //generate a random amount of dollars within the boundary -$1M and $1M
        String amount = nfUS.format(ThreadLocalRandom.current().nextInt(-1000000, 1000000));
        //append the randomly generated value to the contractOffer
        contractOffer1.setText(amount);
        //create a new Point object
        int x = ThreadLocalRandom.current().nextInt(0, anyJFrame1.getWidth() - 100);
        int y = ThreadLocalRandom.current().nextInt(0, anyJFrame1.getHeight() - 50);
        Point newLocation = new Point(x, y);
        //position contractOffer1 with the new Point object
        contractOffer1.setLocation(newLocation);
        //anyJFrame1.getContentPane().add(contractOffer1);
        System.out.println(/*new code*/"dollar location ="+newLocation.toString());
        try {
            sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
        } catch (Exception e) {
        }
        (new ContractOfferGenerator(anyJFrame1, contractOffer1)).start();
    }

}

class MeMouseListener implements MouseListener/*new code*/,MouseMotionListener{
    Border former;
    
    //new code
    JLabel screen1;
    JLabel me1;
    JLabel contractOffer1;
    //get US dollars format object
    NumberFormat nfUS2 = NumberFormat.getCurrencyInstance(Locale.US);
    
    MeMouseListener(JLabel screen2, JLabel me2, JLabel contractOffer2){
        screen1 = screen2;
        me1 = me2;
        contractOffer1=contractOffer2;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        former = ((JLabel) e.getComponent()).getBorder();
        Border border = BorderFactory.createLineBorder(Color.YELLOW, 2);
        ((JLabel) e.getComponent()).setBorder(border);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        ((JLabel) e.getComponent()).setBorder(former);
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    //new code to make the me JLabel draggable
    @Override
    public void mouseMoved(MouseEvent e){
    }
    @Override
    public void mouseDragged(MouseEvent e){
        //get Mouse location on screen
        Point p = new Point(e.getXOnScreen()+0,e.getYOnScreen()-30);
        System.out.println(/*new code*/"mouse location ="+p.toString());
        //update me1 position to that of mouse
        me1.setLocation(p);
        //compare if contractOffer1 screen location is same as Point p
        int x1 = contractOffer1.getLocationOnScreen().x;
        int x2 = p.x;
        int y1 = contractOffer1.getLocationOnScreen().y;
        int y2 = p.y;
        if((Math.abs(x1-x2)<2)&&(Math.abs(y1-y2)<2)){
            /*
             * use try{}catch(){} structure whenever a code-excerpt that has 
             * a high possiblity of throwing errors is executed
            */
            try{
                //get the current dollar amount displayed by the moving JLabel
                String amount2 = contractOffer1.getText();
                //get the current dollar amount displayed in the screen of scoreboard
                String amount3 = screen1.getText();
                /*
                 * convert both amount2 and 3 to number of type double and add
                 * their sum is an object that would be referenced with "sum" an 
                 * object of the class Number.
                 */
                Number sum = nfUS2.parse(amount2).doubleValue()
                        +nfUS2.parse(amount3).doubleValue();
                /*convert sum into dollar format using the Dollar NumberFormat
                 * object nfUS2 and reference it with String amount4
                 */
                String amount4 = nfUS2.format(sum);
                //set screen1's text to amount4
                screen1.setText(amount4);
            }catch(NumberFormatException nfe){
                /*
                 * do this if the nfUS2.format(sum) generated errors.
                 * Print the error message to screen using println() method of the
                 * err object of the System class with the error message as 
                 * String parameter.
                 * The error message is made up of the part written by the 
                 * developer to indicate the exact place the error occured plus
                 * the computer generated message accessible via the 
                 * getLocalizedMessage() method of the error event object nfe
                 */
                System.err.println("error from TheRoadTo700M:MeMouseListener:"
                        + "mouseDragged method NumberFormatException = "
                        +nfe.getLocalizedMessage());
            }catch(ParseException pe){
                /*
                 * do this if the nfUS2.parse(amount3) & ...(4) generated errors.
                 * Print the error message to the user screen using the println() 
                 * method of the err object of the System class with the error 
                 * message as String parameter.
                 * The error message is made up of the part written by the 
                 * developer to indicate the exact place the error occured, plus
                 * the computer generated message accessible via the 
                 * getLocalizedMessage() method of the error event object pe
                 */
                System.err.println("error from TheRoadTo700M:MeMouseListener:"
                        + "mouseDragged method ParseException = "
                        +pe.getLocalizedMessage());
            }
        }
    }
}