package xml.domain.question;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import xml.domain.root.Question;

public class QuestionTest {

//	private final static String JSON_STRING = "{\"id\":\"Q1\",\"type\":\"text\"}";

	private final static String JSON_STRING = "{\r\n" + 
			"    \"id\": \"admin_member_written_enquiries\",\r\n" + 
			"    \"type\": \"text\",\r\n" + 
			"    \"content\": \"Please approximate the total number of written enquiries received for the past 12 months to 30 June year;.\"\r\n" + 
			"}";
	private ObjectMapper mapper;

	@Before
	public void setup() {
		mapper = new ObjectMapper();
	}

	@Test
	public void shouldSerialize() throws JsonProcessingException {
		 Question question  = new Question();
		 question.setId("Q1");
	//	 question.setType("text");
		 String json = this.mapper.writeValueAsString(question);
	//	 assertEquals(JSON_STRING, json);
	}

	@Test
	public void shouldDeserialize() throws JSONException, JsonParseException, JsonMappingException, IOException {
		  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		  Question question= mapper.readValue(JSON_STRING,  Question.class);
		  
		 assertEquals("admin_member_written_enquiries", question.getId());
	}

}
