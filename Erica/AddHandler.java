package Erica;

/**************
Erica Olavarria
December 2011
Data Structures
Final Project
***************
AddHandler
Class 1 of 16
**************/

import java.awt.event.*;

import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
  * The class AddHandler is used to govern the adding of media files. 
  * AddHandler is placed as the ActionListener for the addEntry button
  * in the DitialMediaPlayerGUI class.
  */
public class AddHandler implements ActionListener{
	/**
	  * Components that will be taken from the DigitaMediaPlayerGUI object each time
	  * the actionPerformed method is called.
	  */
	private DigitalMediaPlayerGUI window;
	private JTextField search;

	public AddHandler(DigitalMediaPlayerGUI givenwindow){
		window=givenwindow;
	}//end constructor

	/**
	  * This method is called every time the button is pressed.
	  */
	public void actionPerformed(ActionEvent event){
		//set the component values to their current values from the window:
		search=window.search;

		search.setText(""); //cease any current searches

			window.fillDisplay();
			
			/*
			FocusHandler fh=new FocusHandler();
			JPanel bigPanel=new JPanel();
			bigPanel.setLayout(new BorderLayout());
			JPanel topPanel=new JPanel();
			topPanel.setLayout(new GridLayout(2,1));
			JLabel label=new JLabel("Please fill all text fields, or select \"Import\" to import from a text file.");
			JLabel label2=new JLabel("");
			label.setHorizontalAlignment(JLabel.CENTER);
			topPanel.add(label);
			topPanel.add(label2);
			bigPanel.add(topPanel,BorderLayout.NORTH);
			JPanel panel=new JPanel();
			panel.setLayout(new GridLayout(5,2)); 		//for the input labels and text fields
			bigPanel.add(panel,BorderLayout.CENTER);
			String[]labels={"Title","Artist","Length","Size (KB)","Type"};
			JLabel[]inputLabels=new JLabel[labels.length];
			JTextField[]inputTextFields=new JTextField[labels.length];
			for (int i=0; i<labels.length; i++){
				inputLabels[i]=new JLabel(labels[i]);
				inputTextFields[i]=new JTextField(15);
				panel.add(inputLabels[i]);
				panel.add(inputTextFields[i]);
			}

			boolean filled=false; 				//to keep track of if the user has entered text in all fields
			inputTextFields[0].addHierarchyListener(fh); 	//set cursor on Title text field
			int a=0; 					//to keep track of if the user has pressed OK or Cancel
			String[]inputs=new String[labels.length];
			while (!filled&&a==0){				//will catch again if user left a field empty and pressed OK
				boolean boolLength=false;
				boolean boolSize=false;
				String[] options={"OK","Import"};
				a=JOptionPane.showOptionDialog(null,bigPanel,"Add Media File", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				filled=true;
				int k=-1;
				for (int i=0; i<labels.length; i++){
					inputs[i]=inputTextFields[i].getText();
					while(inputs[i].length()>0&&inputs[i].charAt(0)==' '){
						inputs[i]=inputs[i].substring(1,inputs[i].length());		//remove any spaces at the front
					}
					while(inputs[i].length()>0&&inputs[i].charAt(inputs[i].length()-1)==' '){
						inputs[i]=inputs[i].substring(0,inputs[i].length()-1);		//remove any spaces at the back
					}
					if (inputs[i].length()==0){
						if(k==-1){
							k=i;
						}
						filled=false;
						inputTextFields[i].setText("");
					}
				}
				if(!filled){
					window.removeAllListeners(inputTextFields,fh);
					inputTextFields[k].addHierarchyListener(fh); 		//place cursor on the first empty field
				}
				if (filled){
					String length="";
					int count=0;
					for (int i=0; i<inputs[2].length(); i++){
						if (inputs[2].charAt(i)!=':'){		//copy the String, ignoring ':'
							length+=inputs[2].charAt(i);
						}
						else{					//count the number of ':' in the String
							count++;
						}
					}
					if (count>2){
						length+='A'; //there are too many ":", so we add a letter character so the exception will catch
					}
					String messageLength="";
					String messageSize="";
					try{
						int lengthInt=Integer.parseInt(length);
						if (lengthInt<0){	//cannot have negative length
							messageLength="Length must be a positive value.";
							boolLength=true;
						}
					}catch(Exception e){
						messageLength="Please enter a Length of one of the following example formats:\n\n\"5829\" to represent 5829 seconds, OR\n\"2:53:36\" to represent 2 hours, 53 minutes, and 36 seconds";
						boolLength=true;
					}
					if(boolLength&&a==0){		//there was a problem with the length
						JOptionPane.showMessageDialog(null, messageLength, "Incorrect Length Format", JOptionPane.PLAIN_MESSAGE);
						inputTextFields[2].setText("");			//clear Length field
						window.removeAllListeners(inputTextFields,fh);
						inputTextFields[2].addHierarchyListener(fh);	//set cursor to Length
						filled=false;				//catch the while loop
					}
					try{
						int sizeInt=Integer.parseInt(inputs[3]);
						if (sizeInt<0){		//cannot have negative size
							messageSize="Size must be a positive value.";
							boolSize=true;
						}
					}catch(Exception e){
						messageSize="Please enter a Size of the following example format:\n\n\"1234\" to represent 1234 KB";
						boolSize=true;
					}
					if(boolSize&&a==0){
						JOptionPane.showMessageDialog(null, messageSize, "Incorrect Size Format", JOptionPane.PLAIN_MESSAGE);
						inputTextFields[3].setText("");		//clear Size field
						if (!boolLength){			//only set cursor if there was not also a Length format problem
							window.removeAllListeners(inputTextFields,fh);
							inputTextFields[3].addHierarchyListener(fh);
						}
						filled=false;				//catch the while loop
					}
				}//end if filled
			}//end while

			if (a==0){		//user pressed OK, not Import
				Entry newEntry=new Entry(inputs);
				int b=userLibraries.get(currentLibrary).getEntries().size();	//position where the Entry will be added
				boolean added=userLibraries.get(currentLibrary).add(newEntry);
				if (added){	//add was successful, so add Entry to first Library also
					int key=userLibraries.get(currentLibrary).getEntries().get(b).getKey();
					userLibraries.get(0).add(newEntry, key);
					window.setOrderedFalse();	//the list may no longer be sorted
					window.save();
					window.fillDisplay();
				}
			}
			if (a==1){		//user pressed Import; show Import option
				boolean fileFound=false;
				while(!fileFound){
					String fileName=JOptionPane.showInputDialog(null,"Please enter the filename to import:","Import Media Files",JOptionPane.PLAIN_MESSAGE);
					if (fileName!=null){
						fileFound=window.getTextFile(fileName);	//will return false if no file was found
					}
					if (fileName==null){
						fileFound=true;		//exit the while loop
					}
				}
			}
			*/
			
			String filepath=open();
			System.out.println(filepath);
			
	}//end actionPerformed
	
	
	public String open(){
        /** path name of the file chosen */
        String fileChosen = "nothing";
        String curDir = System.getProperty("user.dir") + File.separator;
        /** Set default view to current directory */
        JFileChooser chooser = new JFileChooser(curDir);
        /** Allow only 1 file selection at a time */
        chooser.setMultiSelectionEnabled(false);
        /** Allow only the selection of FILES */
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        /** Java 6 can use FileNameExtensionFilter class */
        /** Only text files are valid file input */
        FileNameExtensionFilter filter=new FileNameExtensionFilter("Text file","txt");
        chooser.addChoosableFileFilter(filter);
        /** remove the possibility of viewing or accepting other file types*/
        chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());


        int returnVal = chooser.showOpenDialog(chooser);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = chooser.getSelectedFile();
            try{
                fileChosen = selectedFile.getCanonicalPath();
            } catch(IOException e) { System.out.println("Error " + e); }
        }

        return fileChosen;
    }
	
	
}//end AddHandler