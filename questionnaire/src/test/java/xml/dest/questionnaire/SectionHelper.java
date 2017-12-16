package xml.dest.questionnaire;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SectionHelper {

	private ObjectMapper mapper;
	
	public String sectionToString(Section  section) throws JsonProcessingException {
		mapper = new ObjectMapper();
		String newJson = this.mapper.writeValueAsString(section);
		return newJson;
	}
}
