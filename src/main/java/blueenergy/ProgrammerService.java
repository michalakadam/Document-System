package blueenergy;

import blueenergy.document.ApplicationForHolidays;
import blueenergy.document.Document;
import blueenergy.document.DocumentDao;
import blueenergy.document.Questionnaire;

import java.util.List;
import java.util.stream.Collectors;

public class ProgrammerService {


    public void execute(DocumentDao documentDao) {
        List<? extends Document> questionnaireList = filterDocuments(documentDao, Questionnaire.class);
        List<? extends Document> applicationForHolidaysList = filterDocuments(documentDao, ApplicationForHolidays.class);
    }

    private List<Document> filterDocuments(DocumentDao documentDao, Class classType) {
        return documentDao
                .getAllDocumentsInDatabase()
                .stream()
                .filter(document -> document.getClass().equals(classType))
                .collect(Collectors.toList());
    }

}
