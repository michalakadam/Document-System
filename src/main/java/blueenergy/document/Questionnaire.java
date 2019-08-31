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

	 @Override
	 public boolean equals(Object o) {
		 if (this == o) return true;
		 if (o == null || getClass() != o.getClass()) return false;
		 Questionnaire that = (Questionnaire) o;
		 return Objects.equals(title, that.title) &&
				 Objects.equals(questions, that.questions);
	 }

	 @Override
	 public int hashCode() {
		 return Objects.hash(title, questions);
	 }
 }
