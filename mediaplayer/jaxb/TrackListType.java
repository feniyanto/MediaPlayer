package mediaplayer.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for TrackListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * <complexType name="TrackListType">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="track" type="{http://www.w3.org/2001/XMLSchema}TrackType" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrackListType", propOrder = {
    "track"
})

public class TrackListType 
{
	protected List<TrackType> track;
	
	public List<TrackType> getTrack() {
		if(track == null) {
			track = new ArrayList<TrackType>();
		}
		
		return this.track;
	}
}
