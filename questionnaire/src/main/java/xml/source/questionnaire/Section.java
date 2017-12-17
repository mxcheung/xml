package xml.source.questionnaire;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Section {

	private String label;
	private List<RFIQuestion> question;
	private Group group;


	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<RFIQuestion> getQuestion() {
		return question;
	}

	public void setQuestion(List<RFIQuestion> question) {
		this.question = question;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
	

}
