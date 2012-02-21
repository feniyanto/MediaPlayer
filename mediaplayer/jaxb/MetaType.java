package mediaplayer.jaxb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LinkType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * <complexType name="LinkType">
 *   <simpleContent>
 *     <extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
 *       <attribute name="rel" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     </extension>
 *   </simpleContent>
 * </complexType>
 * </pre>
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MetaType", propOrder = {
    "value"
})

public class MetaType 
{
	@XmlValue
	protected String value;
	@XmlAttribute(required = true)
	protected String rel;
	
	public String getValue() {
        return value;
    }
	
	public void setValue(String value) {
        this.value = value;
    }
	
	public String getRel() {
        return rel;
    }
	
	public void setRel(String value) {
        this.rel = value;
    }
}
