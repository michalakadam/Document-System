package blueenergy;

import blueenergy.document.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgrammerService {
	private final List<Questionnaire> questionnaireList = new ArrayList<>();
	private final List<ApplicationForHolidays> applicationForHolidaysList = new ArrayList<>();



	public void execute(DocumentDao documentDao) {
		splitDocumentsIntoListsOfSpecificSubclasses(documentDao);
	}

	void splitDocumentsIntoListsOfSpecificSubclasses(DocumentDao documentDao) {
		for(Document document : documentDao.getAllDocumentsInDatabase()){
			if (document instanceof Questionnaire) {
				this.questionnaireList.add((Questionnaire)document);
			}
			else if (document instanceof ApplicationForHolidays) {
				this.applicationForHolidaysList.add((ApplicationForHolidays)document);
			}
		}
	}
}
