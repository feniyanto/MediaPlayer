package Erica;

import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.Comment;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Playlist{
	private ArrayList<Entry> library=new ArrayList<Entry>();

	public Playlist(String filePath){
		try {
            readXML(filePath);
        } catch(ParserConfigurationException e) { System.err.println(e.getMessage()); }
        catch(SAXException e) { System.err.println(e.getMessage()); }
        catch(IOException e) { System.err.println(e.getMessage()); }
	}//end constructor
	
	/*
     * READXML() and getTAGVALUE() are methods for readXML()
     */
    private void readXML(String filePath) throws ParserConfigurationException, SAXException, IOException
    {
        File xmlFile = new File(filePath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);

        doc.getDocumentElement().normalize();
        
        //System.out.println("Root element: <" + doc.getDocumentElement().getTagName() + ">");
        NodeList list = doc.getElementsByTagName("track");

        for(int i=0; i<list.getLength(); ++i) {
            Node n = list.item(i);

            if(n.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element)n;
                library.add(new Entry(getTagValue("title",e),
                                      getTagValue("artist",e),
                                      getTagValue("album",e),
                                      getTagValue("added",e),
                                      getTagValue("rating",e),
                                      getTagValue("art",e),
                                      getTagValue("location",e)
                                      ));
            }
        }
    }

    private static String getTagValue(String tag, Element e)
    {
        NodeList list = e.getElementsByTagName(tag).item(0).getChildNodes();
        Node value = (Node)list.item(0);

        return value.getNodeValue();
    }
    
    
    /*
     * WRITEXML() and setTRACKATTRIBUTE() are methods for writeXML()
     */
   public void writeToXML(String playlistTitle, String fileName) throws 
            ParserConfigurationException, FileNotFoundException, IOException
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document xmlDoc = db.newDocument();

        // Build XML Elements and Text Nodes
        /* <?xml version="1.0" encoding="UTF-8"?>
           <playlist playlist name="Justin's Playlist">
               <!--Test comment-->
               <track>
                   <title>Song for Clay</title>
                   <artist>Bloc Party</artist>
                   <album>A Weekend in the City</album>
                   <added>2/4/2012</added>
                   <rating>5</rating>
                   <location>J:/Music/Bloc Party/A Weekend In the City/01 Song for Clay.aac</location>
               </track>
           </playlist>
         */

        // Create the root element of the document
        Element rootElement = xmlDoc.createElement("playlist");
        rootElement.setAttribute("name", playlistTitle);
        // Create a comment
        Comment comment = (Comment) xmlDoc.createComment("Test comment");
        rootElement.appendChild((Node) comment);

        /*
         * There are two ways to add tracks now!
         *
         * Method 1:
         *  EXAMPLE:
         *      Element newTrack = createTrack( "SongTitle",
         *                                      "ArtistName",
         *                                      "AlbumName",
         *                                      "DateAdded",
         *                                      (int)rating,
         *                                      "filePath",
         *                                      (document)xmlDoc);
         *      rootElement.appendChild(newTrack);
         * Method 2:
         *  EXAMPLE:
         *      Element newTrack = xmlDoc.createElement("track");
         *      newTrack.appendChild(this.setTrackAttribute("title","SongTitle",xmlDoc);
         *      newTrack.appendChild(this.setTrackAttribute("artist","ArtistName",xmlDoc);
         *      mainElement.appendChild(this.setTrackAttribute("album", "AlbumName", xmlDoc));
         *      mainElement.appendChild(this.setTrackAttribute("added", "DateAdded", xmlDoc));
         *      mainElement.appendChild(this.setTrackAttribute("rating", "5", xmlDoc));
         *      mainElement.appendChild(this.setTrackAttribute("location", "c:setfilepath/here.aac", xmlDoc));
         *      rootElement.appendChild(newTrack);
         */

        /*
         * EXAMPLES OF METHOD 1
         */
         
         for(int i=0; i<library.size(); ++i) {
         	rootElement.appendChild(createTrack(
         			library.get(i).getTitle(),
         			library.get(i).getArtist(),
         			library.get(i).getAlbum(),
         			library.get(i).getDate().toString(),
         			library.get(i).getRating(),
         			library.get(i).getFilePath(),
         			xmlDoc));
         }
         
        // add a <playlist> to an XMLDocument
        xmlDoc.appendChild(rootElement);

        //Set outputformat
        OutputFormat outFormat = new OutputFormat(xmlDoc);
        outFormat.setIndenting(true);

        // Declare the file
        File xmlFile = new File(fileName);
        // Declare Fileoutputstream
        FileOutputStream outStream = new FileOutputStream(xmlFile);

        //XMLSerializer - Write the document, finally!
        XMLSerializer serializer = new XMLSerializer(outStream, outFormat);
        serializer.serialize(xmlDoc);
    }

    private Element createTrack(String title, String artist, String album, String dateAdded, int rating,
                String filePath, Document xmlDoc)
    {
        Element mainElement = xmlDoc.createElement("track");
        mainElement.appendChild(this.setTrackAttribute("title", title, xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("artist", artist, xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("album", album, xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("added", dateAdded, xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("rating", Integer.toString(rating), xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("location", filePath, xmlDoc));

        return mainElement;
    }
    private Element setTrackAttribute(String name, String value, Document xmlDoc) 
    {
        Text attributeText = xmlDoc.createTextNode(value);
        Element attributeName = xmlDoc.createElement(name);

        attributeName.appendChild(attributeText);

        return attributeName;
    }
    

	public boolean add(Entry newEntry){
		boolean found=false;
		for (int i=0; i<library.size(); i++){
			if (library.get(i).equals(newEntry)){
				found=true;	//newEntry IS in the user's library
				int a=JOptionPane.showConfirmDialog(null,"This media file already exists in the library. Add anyway?","Duplicate Media File",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (a==0){
					found=false;	//user wants to add the file anyway
				}
				break;
			}
		}
		if (!found){	//add the file
			library.add(newEntry);
			return true;	//successful add
		}
		return false;		//unsuccessful add
	}//end add

	public int addFromFile(Entry newEntry, int duplicate){//1 means add duplicates, 0 means don't, -1 means we don't know yet
		boolean found=false;		//file not alread in user's library
		if (duplicate==0||duplicate==-1){	//we don't/don't know about adding duplicates
			for (int i=0; i<library.size(); i++){
				if (library.get(i).equals(newEntry)){
					found=true;	//we found the file
					if (duplicate==-1){	//if we don't know about adding duplicates yet
						int a=JOptionPane.showConfirmDialog(null,"This input file contains media files that already exist in the library. Do you want to add the duplicate files?","Duplicate Media File",JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
						if (a==0){ //yes add
							duplicate=1;	//add the duplicate
						}
						if (a==1){ //no don't add
							duplicate=0;
						}
					}
					break;
				}
			}
		}
		if (duplicate==1||!found){ //if we add duplicates or if we didn't find the file in the library already, add it
			library.add(newEntry);
		}
		return duplicate;	//if duplicate is now 1 or 0, its value will not be changed again for the given text file
	}//end addFromFile

	public void shuffle(){
		Random randomGenerator=new Random();
		for (int i=0; i<library.size(); i++){
			int j=randomGenerator.nextInt(library.size());
			swap(i,j);
		}
		for (int i=0; i<library.size(); i++){
			int j=randomGenerator.nextInt(library.size());
			swap(i,j);
		}
	}//end shuffle

	public void sortString(int k){//0=title, 1=artist, 2=album
		for (int i=0; i<library.size(); i++){
			int min=i;
			for (int j=i+1; j<library.size(); j++){
				if (library.get(j).getString(k).toLowerCase().compareTo(library.get(min).getString(k).toLowerCase())<0){
					min=j;
				}
			}
			swap(i,min);
		}
	}//end sortString

	public void sortDateAdded(){
		for (int i=0; i<library.size(); i++){
			int min=i;
			for (int j=i+1; j<library.size(); j++){
				if (library.get(j).getDate().compareTo(library.get(min).getDate())<0){
					min=j;
				}
			}
			swap(i,min);
		}
	}//end sortDateAdded

	public void sortRating(){
		for (int i=0; i<library.size(); i++){
			int min=i;
			for (int j=i+1; j<library.size(); j++){
				if (library.get(j).getRating()<library.get(min).getRating()){
					min=j;
				}
			}
			swap(i,min);
		}
	}//end sortRating

	public ArrayList<Entry> search(String searchString){
		ArrayList<Entry> result=new ArrayList<Entry>();
		for (int i=0; i<library.size(); i++){
			if (library.get(i).contains(searchString)){
				result.add(library.get(i));
			}
		}
		return result;
	}//end search

	public void searchPrint(String searchString){
		for (int i=0; i<library.size(); i++){
			if (library.get(i).contains(searchString)){
				System.out.println(library.get(i));
			}
		}
	}//end searchPrint

	public ArrayList<Entry> getEntries(){
		return library;
	}//end getEntries

	public void swap(int i, int j){
		if (i!=j){
			Entry temp=library.get(i);
			library.set(i, library.get(j));
			library.set(j, temp);
		}
	}//end swap

	public void reverse(){
		for (int i=0; i<library.size()/2; i++){
			swap(i,library.size()-1-i);
		}
	}//end reverse

	public void noneSelected(){
		for (int i=0; i<library.size(); i++){
			library.get(i).setSelected(false);
		}
	}//end noneSelected

	public void print(){
		System.out.println("LIBRARY:------------------");
		for (int i=0; i<library.size(); i++){
			System.out.println(library.get(i));
		}
		System.out.println("__________________________");
	}//end print

        public String[] getStringArray() {
            String[] playlistEntry = new String[library.size()];

            for(int i=0; i<library.size(); ++i) {
                playlistEntry[i] = this.getEntries().get(i).getTitle();
            }

            return playlistEntry;
        }

}//end Library