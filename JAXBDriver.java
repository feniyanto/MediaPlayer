import java.io.File;
import java.util.Iterator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import mediaplayer.jaxb.*;

/**
 * This is a driver class to show examples on how to create play lists using
 * the XSPF and JAXB systems
 * @author Justin Sechrist
 */

public class JAXBDriver {
    public static void main(String...args) throws JAXBException {
        new JAXBDriver();
    }

    public JAXBDriver() throws JAXBException {
        createPlaylist();
        loadPlaylist();
        //savePlaylist();
    }
    
    public void createPlaylist() {
        // all JAXB objects are created using ObjectFactory
        final ObjectFactory factory = new ObjectFactory();

        // create the new blank play list
        final PlaylistType playlist = factory.createPlaylistType();

        playlist.setTitle("Justin's playlist");
        playlist.setCreator("Justin Sechrist");
        playlist.setVersion("1");
        playlist.setIdentifier(Integer.toString(super.hashCode()));
        playlist.setLicense("http://www.apache.org/licenses/LICENSE-2.0.txt");

        // make a JAXB element out of the final playlist
        final JAXBElement element = factory.createPlaylist(playlist);
    }

    public void loadPlaylist() throws JAXBException {
        // create the file
        final File file = new File("playlist-valid-ver0.xml");

        // need to tell JAXB where the JAXB classes are located
        final JAXBContext jc = JAXBContext.newInstance("mediaplayer.jaxb");

        // unmarshal the XML document into the playlist class hierarchy
        final Unmarshaller u = jc.createUnmarshaller();
        final JAXBElement element = (JAXBElement)u.unmarshal(file);
        final PlaylistType playlist = (PlaylistType)element.getValue();

        // get the playlist title
        System.out.println(playlist.getTitle());

        // loop through the tracks printing out each track title
        for(Iterator i = playlist.getTrackList().getTrack().iterator(); i.hasNext();) {
            TrackType track = (TrackType)i.next();
            System.out.println(track.getTitle());
        }
    }
}