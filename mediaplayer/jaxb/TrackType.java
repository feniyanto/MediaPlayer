package mediaplayer.jaxb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for TrackType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * 		<complexType name="TrackType">
 * 		  <complexContent>
 * 			<restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * 			  <sequence>
 * 				<element name="location" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/>
 *         		<element name="identifier" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/>
 *         		<element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         		<element name="creator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         		<element name="annotation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         		<element name="info" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         		<element name="image" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         		<element name="album" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         		<element name="trackNum" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         		<element name="duration" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         		<element name="link" type="{http://www.w3.org/2001/XMLSchema}LinkType" maxOccurs="unbounded" minOccurs="0"/>
 *         		<element name="meta" type="{http://www.w3.org/2001/XMLSchema}MetaType" maxOccurs="unbounded" minOccurs="0"/>
 *         		<element name="extension" type="{http://www.w3.org/2001/XMLSchema}ExtensionType" maxOccurs="unbounded" minOccurs="0"/>
 * 			  </sequence>
 * 			</restriction>
 * 		  </complexContent>
 * 		</complexType>
 * </pre>
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrackType", propOrder = {
	    "location",
	    "identifier",
	    "title",
	    "creator",
	    "annotation",
	    "info",
	    "image",
	    "album",
	    "trackNum",
	    "duration",
	    "link",
	    "meta",
	    "extension"
})

public class TrackType 
{
	protected List<String> location;
	protected List<String> identifier;
	protected String title;
	protected String creator;
	protected String annotation;
	protected String info;
	protected String image;
	protected String album;
	protected BigInteger trackNum;
	protected BigInteger duration;
	protected List<LinkType> link;
	protected List<MetaType> meta;
	protected List<ExtensionType> extension;
	
	public List<String> getLocation() {
		if(location == null) {
			location = new ArrayList<String>();
		}
		
		return this.location;
	}
	
	public List<String> getIdentifier() {
		if(identifier == null) {
			identifier = new ArrayList<String>();
		}
		
		return this.identifier;
	}
	
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
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String value) {
		this.image = value;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public void setAlbum(String value) {
		this.album = value;
	}
	
	public BigInteger getTrackNum() {
		return trackNum;
	}
	
	public void setTrackNum(BigInteger value) {
		this.trackNum = value;
	}
	
	public BigInteger getDuration() {
		return duration;
	}
	
	public void setDuration(BigInteger value) {
		this.duration = value;
	}
	
	public List<LinkType> getLink() {
		if(link == null) {
			link = new ArrayList<LinkType>();
		}
		
		return this.link;
	}
	
	public List<MetaType> getMeta() {
		if(meta == null) {
			meta = new ArrayList<MetaType>();
		}
		
		return this.meta;
	}
	
	public List<ExtensionType> getExtension() {
		if(extension == null) {
			extension = new ArrayList<ExtensionType>();
		}
		
		return this.extension;
	}
	
}
