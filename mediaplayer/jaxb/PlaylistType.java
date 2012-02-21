package mediaplayer.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for PlaylistType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * <complexType name="PlaylistType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="creator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="annotation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="info" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         <element name="location" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         <element name="identifier" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         <element name="image" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         <element name="date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="license" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         <element name="attribution" type="{http://www.w3.org/2001/XMLSchema}AttributionType" minOccurs="0"/>
 *         <element name="link" type="{http://www.w3.org/2001/XMLSchema}LinkType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="meta" type="{http://www.w3.org/2001/XMLSchema}MetaType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="extension" type="{http://www.w3.org/2001/XMLSchema}ExtensionType" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="trackList" type="{http://www.w3.org/2001/XMLSchema}TrackListType"/>
 *       </sequence>
 *       <attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}VersionType" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * </pre>
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlaylistType", propOrder = {
    "title",
    "creator",
    "annotation",
    "info",
    "location",
    "identifier",
    "image",
    "date",
    "license",
    "attribution",
    "link",
    "meta",
    "extension",
    "trackList"
})

public class PlaylistType 
{
    protected String title;
    protected String creator;
    protected String annotation;
    protected String info;
    protected String location;
    protected String identifier;
    protected String image;
    protected XMLGregorianCalendar date;
    protected String license;
    protected AttributionType attribution;
    protected List<LinkType> link;
    protected List<MetaType> meta;
    protected List<ExtensionType> extension;
    
    @XmlElement(required = true)
    protected TrackListType trackList;
    
    @XmlAttribute(required = true)
    protected String version;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String value) {
        this.title = value;
    }
    
    public String getCreator() {
        return creator;
    }
    
    public void setCreator(String value) {
        this.creator = value;
    }
    
    public String getAnnotation() {
        return annotation;
    }
    
    public void setAnnotation(String value) {
        this.annotation = value;
    }
    
    public String getInfo() {
        return info;
    }
    
    public void setInfo(String value) {
        this.info = value;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String value) {
        this.location = value;
    }
    
    public String getIdentifier() {
        return identifier;
    }
    
    public void setIdentifier(String value) {
        this.identifier = value;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String value) {
        this.image = value;
    }
    
    public XMLGregorianCalendar getDate() {
        return date;
    }
    
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }
    
    public String getLicense() {
        return license;
    }
    
    public void setLicense(String value) {
        this.license = value;
    }
    
    public AttributionType getAttribution() {
        return attribution;
    }
    
    public void setAttribution(AttributionType value) {
        this.attribution = value;
    }
    
    public List<LinkType> getLink() {
        if (link == null) {
            link = new ArrayList<LinkType>();
        }
        return this.link;
    }
    
    public List<MetaType> getMeta() {
        if (meta == null) {
            meta = new ArrayList<MetaType>();
        }
        return this.meta;
    }
    
    public List<ExtensionType> getExtension() {
        if (extension == null) {
            extension = new ArrayList<ExtensionType>();
        }
        return this.extension;
    }
    
    public TrackListType getTrackList() {
        return trackList;
    }
    
    public void setTrackList(TrackListType value) {
        this.trackList = value;
    }
    
    public String getVersion() {
        return version;
    }
    
    public void setVersion(String value) {
        this.version = value;
    }
}
