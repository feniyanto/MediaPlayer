/**
 * READ AND WRITE XML files
 * @author Justin Sechrist
 */

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

public class Playlist {
    public Playlist()
    {
        try {
            writeXML();
        }
        catch(ParserConfigurationException e) { System.err.println(e.getMessage()); }
        catch(FileNotFoundException e) { System.err.println(e.getMessage()); }
        catch(IOException e) { System.err.println(e.getMessage()); }


        try {
            readXML();
        } catch(ParserConfigurationException e) { System.err.println(e.getMessage()); }
        catch(SAXException e) { System.err.println(e.getMessage()); }
        catch(IOException e) { System.err.println(e.getMessage()); }
    }

    /*
     * WRITEXML() and setTRACKATTRIBUTE() are methods for writeXML()
     */
    private void writeXML() throws 
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
        // <playlist> </playlist>
        Element rootElement = xmlDoc.createElement("playlist");
        rootElement.setAttribute("name", "Justin's Playlist");
        // Create a comment
        Comment comment = (Comment) xmlDoc.createComment("Test comment");
        rootElement.appendChild((Node) comment);

        /*
         * There are two ways to add tracks now!
         *
         * Method 1:
         *  EXAMPLE:
         *      Element newTrack = createTrack("elementName",
         *                                      "SongTitle",
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
        rootElement.appendChild(createTrack("Song for Clay","Bloc Party",
                "A Weekend in the City", "2/4/2012", 5,
                "J:/Bloc Party/A Weekend In the City/01 Song for Clay.aac", xmlDoc));

        rootElement.appendChild(createTrack("Hunting for Witches","Bloc Party",
                "A Weekend in the City", "2/4/2012", 5,
                "J:/Bloc Party/A Weekend In the City/02 Hunting for Witches.aac", xmlDoc));

        rootElement.appendChild(createTrack("Waiting for the 7.18","Bloc Party",
                "A Weekend in the City", "2/4/2012", 5,
                "J:/Bloc Party/A Weekend In the City/03 Waiting for the 7.18.aac", xmlDoc));

        rootElement.appendChild(createTrack("The Prayer","Bloc Party",
                "A Weekend in the City", "2/4/2012", 5,
                "J:/Bloc Party/A Weekend In the City/04 The Prayer.aac", xmlDoc));

        rootElement.appendChild(createTrack("Uniform","Bloc Party",
                "A Weekend in the City", "2/4/2012", 3,
                "J:/Bloc Party/A Weekend In the City/05 Uniform.aac", xmlDoc));

        rootElement.appendChild(createTrack("On","Bloc Party",
                "A Weekend in the City", "2/4/2012", 4,
                "J:/Bloc Party/A Weekend In the City/06 On.aac", xmlDoc));

        /**
         * EXAMPLES OF METHOD 2:
         */
        Element mainElement = xmlDoc.createElement("track");

        mainElement = xmlDoc.createElement("track");
        mainElement.appendChild(this.setTrackAttribute("title", "Where Is Home?", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("artist", "Bloc Party", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("album", "A Weekend in the City", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("added", "2/4/2012", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("rating", "5", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("location", "J:/Music/Bloc Party/A Weekend In the City/07 Where Is Home?.aac", xmlDoc));
        rootElement.appendChild(mainElement);

        mainElement = xmlDoc.createElement("track");
        mainElement.appendChild(this.setTrackAttribute("title", "Kreuzberg", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("artist", "Bloc Party", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("album", "A Weekend in the City", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("added", "2/4/2012", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("rating", "5", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("location", "J:/Music/Bloc Party/A Weekend In the City/08 Kreuzberg.aac", xmlDoc));
        rootElement.appendChild(mainElement);

        mainElement = xmlDoc.createElement("track");
        mainElement.appendChild(this.setTrackAttribute("title", "I Still Remember", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("artist", "Bloc Party", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("album", "A Weekend in the City", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("added", "2/4/2012", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("rating", "4", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("location", "J:/Music/Bloc Party/A Weekend In the City/09 I Still Remember.aac", xmlDoc));
        rootElement.appendChild(mainElement);

        mainElement = xmlDoc.createElement("track");
        mainElement.appendChild(this.setTrackAttribute("title", "Sunday", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("artist", "Bloc Party", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("album", "A Weekend in the City", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("added", "2/4/2012", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("rating", "5", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("location", "J:/Music/Bloc Party/A Weekend In the City/10 Sunday.aac", xmlDoc));
        rootElement.appendChild(mainElement);

        mainElement = xmlDoc.createElement("track");
        mainElement.appendChild(this.setTrackAttribute("title", "SRXT", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("artist", "Bloc Party", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("album", "A Weekend in the City", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("added", "2/4/2012", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("rating", "4", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("location", "J:/Music/Bloc Party/A Weekend In the City/11 SRXT.aac", xmlDoc));
        rootElement.appendChild(mainElement);

        mainElement = xmlDoc.createElement("track");
        mainElement.appendChild(this.setTrackAttribute("title", "Cain Said to Abel", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("artist", "Bloc Party", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("album", "A Weekend in the City", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("added", "2/4/2012", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("rating", "4", xmlDoc));
        mainElement.appendChild(this.setTrackAttribute("location", "J:/Music/Bloc Party/A Weekend In the City/12 Cain Said to Abel.aac", xmlDoc));
        rootElement.appendChild(mainElement);

        // add a <playlist> to an XMLDocument
        xmlDoc.appendChild(rootElement);

        //Set outputformat
        OutputFormat outFormat = new OutputFormat(xmlDoc);
        outFormat.setIndenting(true);

        // Declare the file
        File xmlFile = new File("playlist.xml");
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

    /*
     * READXML() and getTAGVALUE() are methods for readXML()
     */
    private void readXML() throws ParserConfigurationException, SAXException, IOException
    {
        File xmlFile = new File("playlist.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(xmlFile);

        doc.getDocumentElement().normalize();
        
        System.out.println("Root element: <" + doc.getDocumentElement().getTagName() + ">");
        NodeList list = doc.getElementsByTagName("track");

        for(int i=0; i<list.getLength(); ++i) {
            Node n = list.item(i);

            if(n.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println("-------------------");
                Element e = (Element)n;
                System.out.println("Title: " + getTagValue("title",e));
                System.out.println("Artist: " + getTagValue("artist",e));
                System.out.println("Album: " + getTagValue("album",e));
                System.out.println("Date Added: " + getTagValue("added",e));
                System.out.println("Rating: " + getTagValue("rating",e));
                System.out.println("FilePath: " + getTagValue("location",e));
            }
        }
        System.out.println("-------------------");
    }

    private static String getTagValue(String tag, Element e)
    {
        NodeList list = e.getElementsByTagName(tag).item(0).getChildNodes();
        Node value = (Node)list.item(0);

        return value.getNodeValue();
    }


    public static void main(String...args) {
        new Playlist();
    }
}
