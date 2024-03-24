/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.theroadto700mp2022.versions;
import hr.theroadto700mp2022.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.awt.Point;
import java.awt.Color;
//new libraries
import java.awt.Font;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author emeka
 */
public class TheRoadTo700M_v2 {
 public static void main(String[] args){

		/*myPossibilities is the window where everything happens,
		it is an object of the class JFrame*/

		/*create myPossibilities from a JFrame using the constructor
		JFrame("****") and set the title to "Possibilities"*/

		JFrame myPossibilities = new JFrame("Possiblilities");

		/*make myPossibilities to close when you press the close
		 button at the top right corner by calling the setDefaultCloseOperation(Integer Constant)*/
		myPossibilities.setDefaultCloseOperation(myPossibilities.EXIT_ON_CLOSE);

		//set the screen size of myPossibilities
		myPossibilities.setSize(700, 500);

		//set layout, this facilitates random movement on myPossibilities
		myPossibilities.setLayout(null);
		
		//create me as a face from a JLabel by calling the constructor method JLabel()
		JLabel me = new JLabel();
		//add a picture to me
		me.setIcon(new ImageIcon(TheRoadTo700M_v2.class.getResource("/myhead.jpg")));
		//set random position of me on myPossibilities
		int x = ThreadLocalRandom.current().nextInt(0,myPossibilities.getWidth()-100);
		int y = ThreadLocalRandom.current().nextInt(0,myPossibilities.getHeight()-50);
        	me.setLocation(x, y);
		//set size of me
		me.setSize(50,50);
		//add a border to me using the createLineBorder(color,thickness) method of the BorderFactory class
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
        	me.setBorder(border);
		//place me in myPossibilities
		myPossibilities.getContentPane().add(me);

		//create a contract offer from a JLabel by calling the constructor JLabel()
		JLabel contractOffer= new JLabel();
		//create an icon using an image, and the icon to the contractOffer
                contractOffer.setIcon(new ImageIcon(TheRoadTo700M_v2.class.getResource("/dollarstash.jpg")));
 	        //get us dollars number format
        	NumberFormat nfUS=NumberFormat.getCurrencyInstance(Locale.US);
		//generate a random amount of dollars within the boundary -$1M and $1M
        	String amount = nfUS.format(ThreadLocalRandom.current().nextInt(-1000000,1000000)); 
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
		contractOffer.setSize(100,100);

		//add the contractOffer to myPossibilities
		myPossibilities.getContentPane().add(contractOffer);
		
		//let the window appear on the screen
		myPossibilities.setVisible(true);
		
		//create a ContractGenerator which will keep placing contracts on the screen
		(new ContractOfferGenerator_v2(myPossibilities,contractOffer)).start();
		
	}
}
class ContractOfferGenerator_v2 extends Thread{
	//ContractOfferGenerator has 2 member references/objects
	JFrame anyJFrame1;
	JLabel contractOffer1;
	/*definition of the constructor of the ContractOfferGenerator_v2 which
	takes two parameters, a JFrame object or reference, and a JLabel object
	or reference*/
	ContractOfferGenerator_v2(JFrame anyJFrame2, JLabel contractOffer2){
		anyJFrame1=anyJFrame2;
		contractOffer1=contractOffer2;
	}
	public void run(){
 	        //get us dollars format
		NumberFormat nfUS=NumberFormat.getCurrencyInstance(Locale.US);
		//generate a random amount of dollars within the boundary -$1M and $1M
        	String amount = nfUS.format(ThreadLocalRandom.current().nextInt(-1000000,1000000)); 
		//append the randomly generated value to the contractOffer
		contractOffer1.setText(amount);
		//create a new Point object
		int x = ThreadLocalRandom.current().nextInt(0,anyJFrame1.getWidth()-100);
		int y = ThreadLocalRandom.current().nextInt(0,anyJFrame1.getHeight()-50);
		Point newLocation = new Point(x,y);
		//position contractOffer1 with the new Point object
		contractOffer1.setLocation(newLocation);
		//anyJFrame1.getContentPane().add(contractOffer1);
		//System.out.println(newLocation.toString());
		try{
			sleep(ThreadLocalRandom.current().nextInt(1000,3000));
		}catch(Exception e){}
		(new ContractOfferGenerator_v2(anyJFrame1, contractOffer1)).start();
	}
}
