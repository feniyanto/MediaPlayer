import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import mediaplayer.jaxb.*;

/**
 * Private constructor to ensure this class is never instantiated.
 * ... unless you use reflection, hacker! DONT DO THAT!
 * @author Justin Sechrist
 */

@SuppressWarnings("")
public class Main
{
	private Main()
	{
		// private constructor to make a singleton
	}

	/** Main method that does what the class level java doc states. */
	public static void main(String[] arg) throws FileNotFoundException {
		System.out.println("Media Player \"" + getProjectVersion() + "\"");
		System.out.println("Running on java version \"" +
                        System.getProperty("java.version") + "\"" + " (build "
                        + System.getProperty("java.runtime.version") + ")" +
                        " from " + System.getProperty("java.vendor"));

		System.out.println("Operating environment \"" +
                        System.getProperty("os.name") + "\"" + " version " +
                        System.getProperty("os.version") + " on " +
                        System.getProperty("os.arch"));

                testPlaylist();
                testTracks();
                testValidPlaylistFileVersion0();
                //setPlaylist();
                //testPlaylistVersion();
	}
        /**
         * Tests a valid playlist file version 0.
         */
        private static void testValidPlaylistFileVersion0() {
            try {
                // create the file
                final InputStream stream = InputStream.class.getResourceAsStream("/test.xml");

                JAXBContext jc = JAXBContext.newInstance("mediaplayer.jaxb");
                Unmarshaller u = jc.createUnmarshaller();
                JAXBElement element = (JAXBElement)u.unmarshal(stream);
                PlaylistType playlist = (PlaylistType)element.getValue();

                // correct playlist title
                System.err.println(playlist.getVersion());
                System.err.println(playlist.getAnnotation());
                System.err.println(playlist.getImage());
                System.err.println(playlist.getCreator());
                System.err.println(playlist.getDate());
                System.err.println(playlist.getIdentifier());
                if(playlist.getVersion().equalsIgnoreCase("1")) {
                    for(Iterator iter = playlist.getTrackList().getTrack().iterator(); iter.hasNext();) {
                        TrackType track = (TrackType)iter.next();
                        if(track.getTitle() == null) {
                            System.err.println("testValidPlaylistFileVer0 track title null");
                        }
                        if(track.getMeta().size() == 1) {
                            System.err.println("VPFileVers0 Meta == 1");
                        }
                    }

                    if(playlist.getAttribution().getLocation().size() == 1) {
                        System.err.println("Playlist Attributionsize location == 1");
                    }
                    if(playlist.getTrackList().getTrack().size() == 3) {
                        System.err.println("Playlist Tracklist Size == 3");
                    }

                    final Marshaller m = jc.createMarshaller();
                    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    m.marshal(element,System.out); 
                }
            } catch(PropertyException e) { System.err.println(e.getMessage()); }
            catch(JAXBException e) { System.err.println(e.getMessage()); }
        }

        private static void setPlaylist() throws FileNotFoundException
        {
            try {
                final ObjectFactory factory = new ObjectFactory();
                final PlaylistType playlist = factory.createPlaylistType();
                
                playlist.setTitle("Justin's Playlist");
                playlist.setCreator("Justin Sechrist");
                //playlist.setDate(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(2012, 1, 25, 12,25,28,1,22));
                playlist.setInfo("THIS IS SOME INFO ABOUT THE PLAYLIST");
                playlist.setVersion("1");
                playlist.setImage("http://pictures.com/header.jpg");

                playlist.setIdentifier(Integer.toString(Class.class.hashCode()));
                playlist.setLicense("http://www.apache.org/licenses/LICENSE-2.0.txt");

                final JAXBElement element = factory.createPlaylist(playlist);

                // make an xml document out of it
                final JAXBContext jc = JAXBContext.newInstance("mediaplayer.jaxb");
                final Marshaller m = jc.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                m.marshal(element, new FileOutputStream("pl.xml"));
            } catch (PropertyException ex) {
                System.err.println(ex.getMessage());
            } //catch (DatatypeConfigurationException ex) {
                //System.err.println(ex.getMessage());
            //}
            catch (JAXBException ex) {
                System.err.println(ex.getMessage());
            }
        }

        public static void testTracks() throws FileNotFoundException {
        try {
            final ObjectFactory factory = new ObjectFactory();
            final PlaylistType playlist = factory.createPlaylistType();
            playlist.setTitle("Justin's Playlist 2");
            playlist.setCreator("Justin's Playlist");
            playlist.setDate(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(
                    2011, 1, 30, 6, 1,1, 1, 1));
            playlist.setInfo("playlist setInfo");
            playlist.setVersion("1");
            playlist.setImage("playlist setImage");
            playlist.setIdentifier("playlist setID");
            playlist.setLicense("playlist setLicense");

            // create track list first
            final TrackListType tracks = factory.createTrackListType();

            // now create track 1 and add to list
            TrackType track = factory.createTrackType();
            track.getIdentifier().add("135c3af5-526f-4d87-9757-1b404b51e31d");
            track.getLocation().add("C:\\music directory\\01 - Oops I did it again.mp3");
            track.setCreator("Britney Spears");
            track.setAlbum("Some album name here");
            track.setTitle("Oops I did it again");
            track.setAnnotation("This is a weird song");
            track.setTrackNum(BigInteger.valueOf(1));
            track.setDuration(BigInteger.valueOf(314));
            tracks.getTrack().add(track);

            // now create track 2 and add to list
            track = factory.createTrackType();
            track.getIdentifier().add("e113c56f-c4d4-4bfb-b9f0-6f90a172b5a9");
            track.getLocation().add("C:\\music\\02 - Come Together.mp3");
            track.setCreator("Beatles");
            track.setAlbum("Abbey Road");
            track.setTitle("Come Together");
            track.setAnnotation("Love the Beatles");
            track.setTrackNum(BigInteger.valueOf(2));
            track.setDuration(BigInteger.valueOf(226));
            tracks.getTrack().add(track);

            // add track to playlist
            playlist.setTrackList(tracks);

            final JAXBElement element = factory.createPlaylist(playlist);
            // make an xml document out of it
            final JAXBContext jc = JAXBContext.newInstance("mediaplayer.jaxb");
            final Marshaller m = jc.createMarshaller();
            if(element != null) {
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                m.marshal(element, System.out);
            }
        } catch (PropertyException ex) {
            System.err.println(ex.getMessage());
        } catch (DatatypeConfigurationException ex) {
            System.err.println(ex.getMessage());
        } catch (JAXBException ex) {
            System.err.println(ex.getMessage());
        }
    }

        /**
         * Tests the create of a MediaPlayer Playlist object.
         */
        public static void testPlaylist() {
            try {
                final ObjectFactory factory = new ObjectFactory();
                final PlaylistType playlist = factory.createPlaylistType();

                playlist.setTitle("Justin's MediaPlayer Playlist");
                playlist.setCreator("Mediaplayer user");
                playlist.setDate(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(
                        2012, 1, 30, 6, 1, 1, 1, 1));
                playlist.setInfo("playlist.setInfo here");
                playlist.setVersion("1");
                playlist.setImage("playlist setImage here");
                playlist.setIdentifier("playlist setID here");
                playlist.setLicense("playlist license here");
                
                final JAXBElement element = factory.createPlaylist(playlist);
                
                // make an XML document out of it
                final JAXBContext jc = JAXBContext.newInstance("mediaplayer.jaxb");
                final Marshaller m = jc.createMarshaller();
                if(element != null) {
                    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    m.marshal(element, System.out);
                }
            } catch(PropertyException e) {
                System.out.println("PropertyException");
                System.err.println(e.getMessage());
            } catch(DatatypeConfigurationException e) {
                System.out.println("DatatypeConfigurationException");
                System.err.println(e.getMessage());
            } catch(JAXBException e) {
                System.out.println("JAXBException");
                System.err.println(e.getMessage());
            }
              
        }
        /**
         * Tests a valid play list file version 0.
         */
    public static void testPlaylistVersion() {
        try {
            // create the file
            final InputStream stream = InputStream.class.getResourceAsStream("/playlist-valid-ver0.xml");

            if(stream == null) { throw new FileNotFoundException(); }

            final JAXBContext jc = JAXBContext.newInstance("mediaplayer.jaxb");
            final Unmarshaller u = jc.createUnmarshaller();
            final JAXBElement element = (JAXBElement)u.unmarshal(stream);
            final PlaylistType playlist = (PlaylistType)element.getValue();

            // make an xml document out of it
            final Marshaller m = jc.createMarshaller();
            
            // THIS WILL ECHO THE XML DOCUMENT IN SYSTEM.OUT
            // setproperty will give the normal settings, KEEP IT.
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(element, System.out);
            // THIS WILL CREATE THE XML DOCUMENT
            m.marshal(element, new FileOutputStream("pl3.xml"));

        } catch(FileNotFoundException ex) {
            System.err.println("NO FILE FOUND...");
        } catch (PropertyException ex) {
            System.err.println("PropertyException..." + ex.getMessage());
            System.err.println(ex.getMessage());
        } catch (JAXBException ex) {
            System.err.println("JAXBException..." + ex.getMessage());
        }
    }
	/**
	 * Attempt to read the version number of the pom.properties.  
         * If not found then RUNNING.IN.IDE.FULL is returned as the version.
	 */
	private static String getProjectVersion() {
		String version;

		try {
			final Properties pomProperties = new Properties();
			pomProperties.load(Main.class.getResourceAsStream(
                                "/META-INF/MediaPlayer/pom.properties"));
			version = pomProperties.getProperty("version");
		} catch(Exception e) {
			version = "RUNNING.IN.IDE.FULL";
		}

		return version;
	}
}