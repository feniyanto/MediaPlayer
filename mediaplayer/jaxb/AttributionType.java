package mediaplayer.jaxb;


import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AttributionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * 		<complexType name="AttributionType">
 * 		  <complexContent>
 * 			<restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * 			  <sequence>
 * 				<element name="location" type="{http://www.w3.org/2001/XMLSchema}anyURI>
 * 			  </sequence>
 * 			</restriction>
 * 		  </complexContent>
 * 		</complexType>
 * </pre>
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttributionType",propOrder = { "location" })

public class AttributionType 
{
	protected List<String> location;
	
	/**
	 * Gets the value of the location property
	 * 
	 * To add a new item, do as follows:
	 * <pre>
	 * 	  getLocation().add(newItem);
	 * </pre>
	 */
	public List<String> getLocation() {
		if(location == null) {
			location = new ArrayList<String>();
		}
		
		return this.location;
	}
}
