package Erica;

import Erica.PlayViewer.ApplicationFrame;
import javax.xml.parsers.ParserConfigurationException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.util.ArrayList;
import java.io.*;

/**
  * The DigitalMediaPlayerGUI is an ADT that represents the main window and has all of the
  * buttons and panels and combo boxes for the program.
  */
public class DigitalMediaPlayerGUI extends JFrame{

    int screenHeight;
    int screenWidth;
    
    Playlist awesome=new Playlist("DMP Playlist");

    private JPanel displayPanel, leftPanel, rightPanel, bottom;	//panels that are manipulated when fillDisplay is called

	/**
	  * The following four components are used for sorting and relate to the header buttons at the top of each column,
	  * as well as keeping track of how the list is sorted and displaying that at the bottom of the window.
	  */
	protected String[]headers={"Title","Artist","Album","Date Added","Rating"};
	protected JButton[] sortBy=new JButton[headers.length];
	protected int[] ordered=new int[headers.length];		//0=false, 1=true, 2=true(reversed)
	protected String orderedBy="Not sorted.";	//manipulated when we sort, displayed at bottom of window
	
	protected JComboBox userMenu;

	/**
	  * The following four components are used for searching and relate to inputting a search String and keeping track of
	  * whether or not we are searching, what we are searching for, and the search results.
	  */
	protected JTextField search;		//to input a search query
	protected String stringToSearch="";	//the String we are searching for
	//protected Library searchLib;		//to fill with search results during a search
	protected boolean searchBool=false;	//false: not searching, true: searching

	/**
	  * The constructor for the DigitalMediaPlayerGUI creates all of the necessary panels and adds all necessary buttons,
	  * combo boxes, text fields, and labels.
	  */
	public DigitalMediaPlayerGUI(){
            super("Digital Media Library");

            //screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
            //screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
            setLayout(new BorderLayout());

            //super.setLocation(screenWidth/4, screenHeight/4);

            setExtendedState((int) Frame.CENTER_ALIGNMENT);	//to fill the screen

		/**
		  * This Component code was taken from <http://www.javalobby.org/java/forums/t15453.html>
		  * It ensures that the main window cannot be resized smaller than a specified dimension.
		  */
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(ComponentEvent event) {
				int[] widths;
				if (searchBool){
					widths=stringWidths(awesome.getEntries());
				}
				else widths=stringWidths(awesome.getEntries());
				int totalWidth=0;
				for (int i=0; i<widths.length; i++){
					totalWidth+=widths[i];
				}
				setSize( Math.max(totalWidth+45, getWidth()), Math.max(400, getHeight()) );
			}
		});


		//KeyHandler kh=new KeyHandler();
		//addKeyListener(kh);

		userMenu=new JComboBox();			//menu used to display all, add a user, or switch between users
		userMenu.addItem("All Users");
		userMenu.addItem("Add User");
		userMenu.setSelectedIndex(0);			//set selected to "All Users"
		//UserMenuHandler umh=new UserMenuHandler(this);
		//userMenu.addActionListener(umh);		//UserMenuHandler tells it what to do when the combo box is changed

		JComboBox userOptions=new JComboBox();		//menu used to display all of the user options: password and remove
		userOptions.addItem("User Options");
		userOptions.addItem("Password");
		userOptions.addItem("Rename");
		userOptions.addItem("Remove User");
		userOptions.setSelectedIndex(0);		//set selected to "User Options"
		//UserOptionsHandler uoh=new UserOptionsHandler(this);
		//userOptions.addActionListener(uoh);		//UserOptionsHandler tells it what to do when the combo box is changed

		JButton addEntry=new JButton("Add Media File");	//button to add media files
		AddHandler ah=new AddHandler(this);
		addEntry.addActionListener(ah);			//AddHander tells it what to do when the button is pressed
                
		JLabel searchLabel=new JLabel("  Search:");
		search=new JTextField(15);			//to input a search String
		search.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e){
				method();
			}
			public void removeUpdate(DocumentEvent e){
				method();
			}
			public void insertUpdate(DocumentEvent e){
				method();
			}

			public void method(){
				stringToSearch=search.getText();
				if (stringToSearch.length()==0){	//we are not searching
					searchBool=false;
					setOrderedFalse();
					fillDisplay();
				}
				if (stringToSearch.length()>0){		//we are searching
					//searchLib=new Library(userLibraries.get(currentLibrary).search(stringToSearch));	//fill search Library
					searchBool=true;
					setOrderedFalse();
					//fillDisplay(searchLib.getEntries(), stringToSearch);	//fill display with search results
				}
			} //end method
		}); //end search documentListener

		JButton shuffle=new JButton("Shuffle");
		shuffle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				/*setOrderedFalse();
				if(!searchBool){					//we are not searching
					awesome.shuffle();	//shuffle currentLibrary
					fillDisplay();
				}
				if(searchBool){						//we are searching
					awesome.shuffle();				//shuffle search results
					//fillDisplay(searchLib.getEntries(),stringToSearch);	//fill display with search results
				}*/

                            try {
                                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                            } catch (ClassNotFoundException ex) { ex.printStackTrace(); }
                            catch (IllegalAccessException ex) { ex.printStackTrace(); }
                            catch (InstantiationException ex) { ex.printStackTrace(); }
                            catch (UnsupportedLookAndFeelException ex) { ex.printStackTrace(); }

                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    ApplicationFrame tester = new ApplicationFrame();
                                    tester.setVisible(true);
                                }
                            });
                        }
                });

		JButton help=new JButton("Help");
		HelpHandler hh=new HelpHandler();
		help.addActionListener(hh);

		JPanel userPanel=new JPanel();		//create the panel for the two combo boxes: userMenu and userOptions
		userPanel.add(userMenu);
		userPanel.add(userOptions);

		JPanel searchPanel=new JPanel();	//create the panel for the search components and the shuffle button
		searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
		searchPanel.add(searchLabel);
		searchPanel.add(search);
		searchPanel.add(shuffle);
		searchPanel.add(help);

		JPanel top=new JPanel();		//place all header components in one panel
		top.setLayout(new BorderLayout());
		top.add(userPanel,BorderLayout.WEST);
		top.add(addEntry,BorderLayout.CENTER);
		top.add(searchPanel,BorderLayout.EAST);

		add(top,BorderLayout.NORTH);		//place header panel at the top of the window
		bottom=new JPanel();			//the bottom panel will hold a String containing information about the Digita Music Library
		add(bottom,BorderLayout.SOUTH);		//place bottom panel at the bottom of the window

		displayPanel=new JPanel();
		displayPanel.setLayout(new GridLayout(0,1));	//undefined number of rows
		displayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
		leftPanel=new JPanel();
		leftPanel.setLayout(new GridLayout(0,1));	//to be placed to the left of displayPanel; undefined number of rows
		rightPanel=new JPanel();
		rightPanel.setLayout(new GridLayout(0,1));	//to be placed to the right of displayPanel; undefined number of rows

		//ButtonHandler bh=new ButtonHandler(this);	//instantiate ButtonHandler object for the buttons at the top of each display column
		for (int i=0; i<headers.length; i++){
			sortBy[i]=new JButton(headers[i]);
			//sortBy[i].addActionListener(bh);
			ordered[i]=0;				//set all ordered to 0; list is currently not sorted
		}

		JPanel center=new JPanel();					//create panel for everything in the center of the window
		center.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));	//no space horizontally between components
		JScrollPane scroller=new JScrollPane(center);			//so that when there are more Entries than the vertical space allows, the panel will scroll
		scroller.getVerticalScrollBar().setUnitIncrement(10);		//sets scrolling speed
		add(scroller,BorderLayout.CENTER);				//add scroller to the center of the window
		center.add(leftPanel);
		center.add(displayPanel);
		center.add(rightPanel);
		fillDisplay();

		setVisible(true);
	}//end constructor

	protected void removeAllListeners(JTextField[] fields, FocusHandler fHandler){
		for (int i=0; i<fields.length; i++){
			fields[i].removeHierarchyListener(fHandler);	//all hierarchy listeners must be removed before we can place new ones
		}
	}//end removeAllListeners

	public void setOrderedFalse(){		//to be called when the list is no longer sorted
		for (int i=0; i<ordered.length; i++){
			ordered[i]=0;
		}
		orderedBy="Not sorted.";	//update the information String
	}//end setOrderedFalse
   
	public void setOrderedBy(String givenOrderedBy){	//called in ButtonHandler
		orderedBy=givenOrderedBy;
	}//end setOrderedBy

	/**
	  * The method stringWidths inputs an array of integers that represent the default
	  * column widths for the dispay, and it inputs the Entries that are about to be
	  * displayed.  It then checks to make sure that the column widths are wide enough
	  * to display the information in each Entry.  If the column widths are too small, it
	  * increases the widths of the column.  The title and artist columns have restricted
	  * widths, so they can only be increased to a size of 320.  This method returns an
	  * array of ints, updated with the correct column widths.
	  */
	private int[] stringWidths(ArrayList<Entry> toDisplay){	//called by the overloaded toDisplay method
		int[]result={150,250,220,80,140,70,70,75};
		if (toDisplay.size()==0){
			return result;
		}
		FontMetrics fm=getFontMetrics(getFont());
		for (int i=0; i<toDisplay.size(); i++){				//for each Entry that is to be displayed
			for (int j=0; j<result.length; j++){			//for each component of the Entry
				String string=toDisplay.get(i).getString(j);	//get the String to be displayed
				int size=fm.stringWidth(string)+6;		//determine how wide the display panel needs to be
				if (size>result[j]){				//if that size is larger than the current size
					result[j]=size;				//increase the size of the display panel to fit the width of the String
				}
			}
		}
		int[] max={250,350,350,90,145,80,110,75};
		for (int i=0; i<result.length; i++){	//to restrict the lengths of the columns; we don't want them too large
			if (result[i]>max[i]){
				result[i]=max[i];	//set to max size
			}
		}
		int totalWidth=0;
		for (int i=0; i<result.length; i++){
			totalWidth+=result[i];
		}
		setSize( Math.max(totalWidth+45, getWidth()), Math.max(400, getHeight()) );
		return result;			//return the new, updated widths for the columns
	}//end stringWidths

	private String properString(int currentLib, String sString){	//called by the overloaded toDisplay method
		return "";
		}//end properString

	public void fillDisplay(){
		fillDisplay(awesome.getEntries(), "");	//the empty String indicates that there is no search String; we are not searching
	}//end fillDisplay

	/**
	  * The fillDisplay method accepts an ArrayList of Entries to display, and a String.
	  * If the String is empty, it indicates that we are not currently in a search.  The 
	  * fillDisplay method first updates the information String at the bottom of the screen,
	  * and then creates the Entry table that is displayed in the center of the window. If
	  * we are inside a user's account, each media file has a ButtonPlus object to the left
	  * of it.  If we are inside the first Library, containing all the files, the display
	  * also shows a User column.
	  */
	public void fillDisplay(ArrayList<Entry> toDisplay, String sString){
		bottom.removeAll();	//to update the information String at the bottom
		validate();
		
		JLabel about=new JLabel("ABOUT");
		bottom.add(about);	//add the information String to the bottom panel of the window

		displayPanel.removeAll();	//now we will update the center panel, remove everything
		leftPanel.removeAll();		//remove everything
		rightPanel.removeAll();		//remove everything
		int[]sizes={200,200,200,200,200};
		//int[]sizes=stringWidths(toDisplay);	//update the column sizes to reflect the data we are about to display
		JPanel buttonPanel=new JPanel();		//for the buttoms at the top of each column
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));	//no spaces between the buttons

			int k=0; //if we are in someone's library, do not display the User column
			JPanel RSDpanel=new JPanel();	//panel on the top row so that the spacing stays consistent, since panels below this one will contain a ButtonPlus object
			RSDpanel.setPreferredSize(new Dimension(40,24));
			RSDpanel.setLayout(new BorderLayout());
			if (toDisplay.size()>0){
				JLabel RSDlabel=new JLabel("R,S,D ");	//R stands for Rate, S stands for Share, D stands for Delete
				RSDlabel.setHorizontalAlignment(JLabel.RIGHT);
				RSDlabel.setToolTipText("Rate, Share, or Delete");
				ToolTipManager.sharedInstance().setInitialDelay(0);
				RSDpanel.add(RSDlabel, BorderLayout.CENTER);

			leftPanel.add(RSDpanel);		//add it to the panel on the left
		}
		for (; k<sizes.length; k++){		//for each component of the Entry (except possibly for User, if k==1)
			JPanel panel=new JPanel();	//a new panel for EACH label
			panel.setPreferredSize(new Dimension(sizes[k],24));			//set the correct dimensions based on our array of widths
			panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));		//outline to create the grid
			panel.setLayout(new BorderLayout());
			panel.add(sortBy[k],BorderLayout.CENTER);				//add the button to the panel
			buttonPanel.add(panel);		//add the panel to the larger panel
		}

			JPanel panell=new JPanel();
			panell.setPreferredSize(new Dimension(40,24));
			rightPanel.add(panell);
		
		displayPanel.add(buttonPanel);	//add the buttons to the top of the display

		//now we must add each Entry to the display
		for (int i=0; i<toDisplay.size(); i++){	
			JPanel entryPanel=new JPanel(); 					//for each entry, make a new panel to place the components
			entryPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0)); 		//remove any space set between components
		//	EntryClicked ec=new EntryClicked(this, toDisplay, sString, i);
		//	entryPanel.addMouseListener(ec);

		/*	if (currentLibrary>0){		//if we are inside someone's account
				ButtonPlus button=new ButtonPlus(toDisplay.get(i),this,currentLibrary,stringToSearch, i);	//create the ButtonPlus object
				leftPanel.add(button.getButton());	//place that object in the leftPanel, which is to the left of the main display

			}*/
			int j=0;
			for (; j<sizes.length; j++){
				JLabel label=new JLabel(toDisplay.get(i).getString(j));		//create a label for each component's String
				JPanel panel=new JPanel();					//create a panel for each component
				panel.setBackground(new Color(205,220,255));			//set the background color to dark blue
				if (i%2==1){
					panel.setBackground(new Color(230,235,255));		//set every other Entry's background color to a lighter blue
				}
				if (toDisplay.get(i).getSelected()){				//if that Entry has been clicked
					panel.setBackground(new Color(255, 255, 150));		//set the Entry's background color to yellow
				}
				panel.setPreferredSize(new Dimension(sizes[j],24));		//set the correct size for the component's panel
				panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));	//add a border around each component's panel
			/*	if (j==0||j==1||j==2||j==7){
					panel.setLayout(new FlowLayout(FlowLayout.LEADING));	//left-aligned text for User, Title, Artist, Rating
				}*/
				panel.add(label);						//each component has its own panel
				entryPanel.add(panel);						//each panel is added to the entryPanel
			}

			displayPanel.add(entryPanel);	//add the Entry's panel to the main display, then repeat again for all Entries in the list
		}//end for
		validate();				//update the Container
	}//end overloaded fillDisplay
		
		
		
		public void save(){
			try {
            	awesome.writeToXML("Awesome Playlist!","awesomeplaylist.xml");
        	}
        	catch(ParserConfigurationException e) { System.err.println(e.getMessage()); }
       		catch(FileNotFoundException e) { System.err.println(e.getMessage()); }
        	catch(IOException e) { System.err.println(e.getMessage()); }
		}

}//end DigitalMediaPlayerGUI