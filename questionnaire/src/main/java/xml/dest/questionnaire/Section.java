package xml.dest.questionnaire;

import java.util.List;

public class Section {

	private String label;
	private List<Panel> panel
	;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public List<Panel> getPanel() {
		return panel;
	}
	public void setPanel(List<Panel> panel) {
		this.panel = panel;
	}
	

	
}
