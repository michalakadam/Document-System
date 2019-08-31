import pl.blueenergy.document.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgrammerService {



	public void execute(DocumentDao documentDao) {
		List<Questionnaire> questionnaireList = new ArrayList<>();
		List<ApplicationForHolidays> applicationForHolidaysList = new ArrayList<>();
		for(Document document : documentDao.getAllDocumentsInDatabase()){
			if (document instanceof Questionnaire) {
				questionnaireList.add((Questionnaire)document);
			}
			else if (document instanceof ApplicationForHolidays) {
				applicationForHolidaysList.add((ApplicationForHolidays)document);
			}
		}
	}
}
