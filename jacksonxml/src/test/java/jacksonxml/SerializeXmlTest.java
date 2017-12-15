package jacksonxml;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class SerializeXmlTest {

	@Test
	public void whenJavaSerializedToXmlStr_thenCorrect() throws JsonProcessingException {
	    XmlMapper xmlMapper = new XmlMapper();
	    String xml = xmlMapper.writeValueAsString(new SimpleBean());
	    assertNotNull(xml);
	}

	
}