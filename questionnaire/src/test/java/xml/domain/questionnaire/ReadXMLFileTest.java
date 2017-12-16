package xml.domain.questionnaire;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadXMLFileTest {

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	private ObjectMapper mapper;

	@Before
	public void setup() {
		mapper = new ObjectMapper();
	}

	@Test
	public void shouldConvertXMltoJson() throws IOException, ParserConfigurationException, SAXException {
		File fXmlFile = new File("src\\test\\resources\\root.xml");
		XmlHelper xmlHelper = new XmlHelper();
		String json = xmlHelper.xmltoJson(fXmlFile);
		System.out.println("Root element :" + json);
	}

	@Test
	public void shouldLoadSerialize() throws IOException, ParserConfigurationException, SAXException {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		File fXmlFile = new File("src\\test\\resources\\root.xml");
		XmlHelper xmlHelper = new XmlHelper();
		String json = xmlHelper.xmltoJson(fXmlFile);
		Root root = mapper.readValue(json, Root.class);
		String newJson = this.mapper.writeValueAsString(root);
		System.out.println("newJson:" + newJson);

	}

}