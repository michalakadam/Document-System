package blueenergy.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


 public class Questionnaire extends Document{

	private String title;
	private List<Question> questions = new ArrayList<>();

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
 }
