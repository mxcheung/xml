package jacksonxml;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;



public class SerializeXmlTest {

	@Test
	public void whenJavaSerializedToXmlStr_thenCorrect() throws JsonProcessingException {
	    XmlMapper xmlMapper = new XmlMapper();
	    String xml = xmlMapper.writeValueAsString(new SimpleBean());
	    assertNotNull(xml);
	}
	
	@Test
	public void whenJavaGotFromXmlStr_thenCorrect() throws IOException {
	 
	    JacksonXmlModule xmlModule = new JacksonXmlModule();
	    xmlModule.setDefaultUseWrapper(false);
	    ObjectMapper objectMapper = new XmlMapper(xmlModule);
	    SimpleBean value = 
	    		objectMapper.readValue("<SimpleBean><x>1</x><y>2</y></SimpleBean>", SimpleBean.class);
	    assertTrue(value.getX() == 1 && value.getY() == 2);
	}
	
	
	@Test
	public void whenJavaGotFromXmlFile_thenCorrect() throws IOException {
	    File file = new File("src\\test\\simple_bean.xml");
	    JacksonXmlModule xmlModule = new JacksonXmlModule();
	    xmlModule.setDefaultUseWrapper(false);
	    ObjectMapper objectMapper = new XmlMapper(xmlModule);

	    String xml = inputStreamToString(new FileInputStream(file));
	    SimpleBean value = objectMapper.readValue(xml, SimpleBean.class);
	    assertTrue(value.getX() == 1 && value.getY() == 2);
	}
	
	public static String inputStreamToString(InputStream is) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    String line;
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    while ((line = br.readLine()) != null) {
	        sb.append(line);
	    }
	    br.close();
	    return sb.toString();
	}

	
}