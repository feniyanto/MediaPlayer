package mediaplayer.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Java class for ExtensionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * 		<complexType name="ExtensionType">
 * 		  <complexContent>
 * 			<restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 * 			  <sequence>
 * 				<any/>
 * 			  </sequence>
 * 			  <attribute name="application" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI>
 * 			</restriction>
 * 		  </complexContent>
 * 		</complexType>
 * </pre>
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtensionType", propOrder = { "content" })

public class ExtensionType 
{
	@XmlMixed
	@XmlAnyElement
	protected List<Object> content;
	@XmlAttribute(required = true)
	protected String application;
	
	/**
	 * Gets the value of the content property.
	 * 
	 * To add a new item, do as follows:
	 * <pre>
	 * 		getContent().add(newItem);
	 * </pre>
	 */
	public List<Object> getContent() {
		if(content == null) {
			content = new ArrayList<Object>();
		}
		
		return this.content;
	}
	
	/**
	 * Gets the value of the application property.
	 */
	public String getApplication() {
		return application;
	}
	
	/**
	 * Sets the value of the application property.
	 */
	public void setApplication(String value) {
		this.application = value;
	}
}
