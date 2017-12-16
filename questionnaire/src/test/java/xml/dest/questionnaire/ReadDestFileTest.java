package xml.dest.questionnaire;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.baeldung.dozer.Person;
import com.baeldung.dozer.Personne;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import xml.source.questionnaire.Root;
import xml.source.questionnaire.RootService;

public class ReadDestFileTest {

	  private static final Logger LOGGER = LoggerFactory.getLogger(ReadDestFileTest.class);

	public static int PRETTY_PRINT_INDENT_FACTOR = 4;
	DozerBeanMapper mapper;

	@Before
	public void setup() {
		 mapper = new DozerBeanMapper();
	}


	public void configureMapper(String... mappingFileUrls) {
	     mapper.setMappingFiles(Arrays.asList(mappingFileUrls));
	}
	



	private Root getRoot()			throws ParserConfigurationException, SAXException, IOException, JsonParseException, JsonMappingException {
		
		RootService rootService = new RootService();
		Root root = rootService.getRoot();
		return root;
	}
	
	@Test
	public void shouldMaptoRootToSection() throws JsonParseException, JsonMappingException, ParserConfigurationException, SAXException, IOException {
	    configureMapper("dozer_mapping.xml");
	    Root root = getRoot();
	    Section section = mapper.map(root.getXml(), Section.class);
	    assertEquals(section.getLabel(), "Member Communications");
	    SectionHelper sectionHelper = new SectionHelper();
	    String sectionJson = sectionHelper.sectionToString(section);
		System.out.println("sectionJson: " + sectionJson);

	}
	
	@Test
	public void givenSrcAndDestWithDifferentFieldNamesWithCustomMapperwhenMaps_thenCorrect() {
	    configureMapper("dozer_mapping.xml");
	    Personne frenchAppPerson = new Personne("Sylvester Stallone", "Rambo", 70);
	    Person englishAppPerson = mapper.map(frenchAppPerson, Person.class);
	 
	    assertEquals(englishAppPerson.getName(), frenchAppPerson.getNom());
	    assertEquals(englishAppPerson.getNickname(), frenchAppPerson.getSurnom());
	    assertEquals(englishAppPerson.getAge(), frenchAppPerson.getAge());
	}

}