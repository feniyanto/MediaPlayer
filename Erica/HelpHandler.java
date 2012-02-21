package Erica;

/**************
Erica Olavarria
December 2011
Data Structures
Final Project
***************
HelpHandler
Class 9 of 16
**************/

import javax.swing.*;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
  * The class HelpHandler is used when a user wants to view the User Manual.
  * HelpHandler is placed as the ActionListener for the "Help" button in the
  * DitialMediaPlayerGUI class.
  */
public class HelpHandler implements ActionListener{
	/**
	  * Components that will be taken from the DigitaMediaPlayerGUI object each time
	  * the actionPerformed method is called.
	  */
	JLabel[] labels;
	JPanel[] labelPanels;
	JPanel[] infoPanels;
	JPanel panel=new JPanel();	//for the main content of the window
	JScrollPane scroll;
	boolean[] open;

	public HelpHandler(){
		countHeaders();		//this method will initialize all of the ADT's arrays
		for (int i=0; i<labels.length; i++){
			labelPanels[i]=new JPanel();		//one for each item in the user manual
			labelPanels[i].setLayout(new BorderLayout());
			infoPanels[i]=new JPanel();			//where the informative text will be displayed
			infoPanels[i].setLayout(new GridLayout(0,1));
			open[i]=false;
		}
		getHelpFile();		//fills panels with proper labels, parsing UserManual.txt
	}//end constructor

	/**
	  * This method is called every time the button is pressed.
	  */
	public void actionPerformed(ActionEvent event){
		closeAll();
		scroll=new JScrollPane(panel);
		scroll.setPreferredSize(new Dimension(585,529));		//width, height of new window
		scroll.getVerticalScrollBar().setUnitIncrement(10);		//sets scrolling speed
		JOptionPane.showMessageDialog(null,scroll,"User Manual", JOptionPane.PLAIN_MESSAGE);	//displays the new window
	}//end actionPerformed

	/**
	  * This method counts the number of headers inside the user manual
	  * and initializes all of the ADT's arrays with the number of headers.
	  * This method makes it easy to update the user manual without breaking
	  * the code.
	  */
	public void countHeaders(){
		int countHead=0;
		try{
			Scanner fileScanner=new Scanner(new File("UserManual.txt"));
			while(fileScanner.hasNextLine()){
				String textLine=fileScanner.nextLine();
				if (textLine.contains("<u>")){	//only the headers are underlined
					countHead++;				//count the number of headers in the file
				}
			}
		}
		catch (FileNotFoundException efile){}
		catch (IOException ioe){}

		//initialize all arrays:
		labels=new JLabel[countHead];
		labelPanels=new JPanel[countHead];
		infoPanels=new JPanel[countHead];
		open=new boolean[countHead];
	}//end countHeaders

	/**
	  * The method getHelpFile parses UserManual.txt and places the
	  * text in an appropriate JLabel, which it then places on the
	  * appropriate panel.
	  */
	public void getHelpFile(){
		int count=-1;			//counts the headings, keeps track of where we are (count=0 will be the first heading)
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));	//place components vertically on the panel
		try{
			Scanner fileScanner=new Scanner(new File("UserManual.txt"));
			boolean initial=true;	//keeps track of if we are in the initial two lines or if we are in the informational part of the file
			while(fileScanner.hasNextLine()){
				boolean centered=false, heading=false;
				String textLine=fileScanner.nextLine();
				if (textLine.length()>1){
					if (textLine.substring(0,2).equals("!T")){	//title
						centered=true;
					}
					if (textLine.substring(0,2).equals("!H")){	//header
						heading=true;
						initial=false;							//once we have reached a header, we are no longer in the initial lines
					}
				}
				if (textLine.length()>0&&textLine.charAt(0)=='!'){	//these are the lines with html code
					textLine="<html>"+textLine.substring(2,textLine.length())+"</html>";
				}
				if (heading){
					count++;		//we found a new heading
					labels[count]=new JLabel(textLine);
					Headings head=new Headings(count);
					labels[count].addMouseListener(head);	//so the user can click on each heading
					labels[count].setCursor(new Cursor(Cursor.HAND_CURSOR));	//cursor changes over each heading
					panel.add(labelPanels[count]);
				}
				else{
					JLabel label=new JLabel(textLine);
					if (initial){
						JPanel tempPanel=new JPanel();
						tempPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
						if (centered){
							tempPanel.add(new JLabel("                                        "));	//so that the title appears centered
						}
						tempPanel.add(label);
						panel.add(tempPanel);
					}
					else{
						infoPanels[count].add(label);	//add the text under each heading to the respective infoPanel
					}
				}
			}//end while
			infoPanels[count].add(new JLabel(""));		//for spacing
		}
		catch(FileNotFoundException efile){}
		catch(IOException ioe){}
	}//end getHelpFile

	public void closeAll(){			//called when the Help button is pressed, so that all headings start off closed
		for (int k=0; k<labels.length; k++){
			labelPanels[k].removeAll();
			JPanel tempPanel=new JPanel();
			tempPanel.add(labels[k]);
			tempPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
			labelPanels[k].add(tempPanel,BorderLayout.NORTH);
			open[k]=false;
		}
	}//end closeAll

	/**
	  * Headings is the mouseListener placed on each heading label
	  * so that the user can click on the label to make the text
	  * appear or disappear.
	  */
	public class Headings implements MouseListener{
		int k;		//so we know which label was clicked
		
		public Headings(int i){
			k=i;
		}//end constructor

		public void mouseClicked(MouseEvent e){
			if (open[k]){		//remove text from display
				labelPanels[k].removeAll();
				JPanel tempPanel=new JPanel();
				tempPanel.add(labels[k]);
				tempPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
				labelPanels[k].add(tempPanel,BorderLayout.NORTH);
				open[k]=false;
			}
			else{
				labelPanels[k].add(infoPanels[k],BorderLayout.CENTER);	//add text to display
				labelPanels[k].add(new JLabel(" "),BorderLayout.WEST);	//for spacing purposes
				open[k]=true;
			}
			scroll.validate();
		}
		//these methods are necessary to include, but we don't need them
		public void mouseExited(MouseEvent e){}
		public void mouseEntered(MouseEvent e){}
		public void mouseReleased(MouseEvent e){}
		public void mousePressed(MouseEvent e){}
	}//end Headings

}//end HelpHandler