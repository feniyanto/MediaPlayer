package mediaplayer.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the MediaPlayer.jaxb package. 
 * <p>An ObjectFactory allows you to construct new 
 * instances of the Java representation for XML 
 * content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 * @author Emil A. Lefkof III (2005-2006)
 */
@XmlRegistry
public class ObjectFactory {
    private final static QName _Playlist_QNAME = new QName("playlist");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.melloware.jspiff.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MetaType }
     * 
     */
    public MetaType createMetaType() {
        return new MetaType();
    }

    /**
     * Create an instance of {@link PlaylistType }
     * 
     */
    public PlaylistType createPlaylistType() {
        return new PlaylistType();
    }

    /**
     * Create an instance of {@link TrackType }
     * 
     */
    public TrackType createTrackType() {
        return new TrackType();
    }

    /**
     * Create an instance of {@link AttributionType }
     * 
     */
    public AttributionType createAttributionType() {
        return new AttributionType();
    }

    /**
     * Create an instance of {@link LinkType }
     * 
     */
    public LinkType createLinkType() {
        return new LinkType();
    }

    /**
     * Create an instance of {@link TrackListType }
     * 
     */
    public TrackListType createTrackListType() {
        return new TrackListType();
    }

    /**
     * Create an instance of {@link ExtensionType }
     * 
     */
    public ExtensionType createExtensionType() {
        return new ExtensionType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlaylistType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xspf.org/ns/0/", name = "playlist")
    public JAXBElement<PlaylistType> createPlaylist(PlaylistType value) {
        return new JAXBElement<PlaylistType>(_Playlist_QNAME, PlaylistType.class, null, value);
    }

}
