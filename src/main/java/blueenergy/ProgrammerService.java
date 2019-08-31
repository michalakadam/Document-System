package blueenergy;

import blueenergy.document.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProgrammerService {


    public void execute(DocumentDao documentDao) {
        List<? extends Document> questionnaireList = filterDocuments(documentDao, Questionnaire.class);
        List<? extends Document> applicationForHolidaysList = filterDocuments(documentDao, ApplicationForHolidays.class);
        int averageNumberOfAnswersForAllQuestionsInAllQuestionnaires = calculateAverageNumberOfAnswers(questionnaireList);
    }

    private List<Document> filterDocuments(DocumentDao documentDao, Class classType) {
        return documentDao
                .getAllDocumentsInDatabase()
                .stream()
                .filter(document -> document.getClass().equals(classType))
                .collect(Collectors.toList());
    }

    private int calculateAverageNumberOfAnswers(List<? extends Document> questionnaireList) {
        int averageNumberOfAnswers = 0;
        for (Document questionnaire : questionnaireList) {
            averageNumberOfAnswers += computeAverageNumberOfAnswers((Questionnaire)questionnaire);
        }
        return averageNumberOfAnswers;
    }

    private int computeAverageNumberOfAnswers(Questionnaire questionnaire) {
        int sum = 0;
        for (Question question : questionnaire.getQuestions()) {
            sum += question.getPossibleAnswers().size();
        }
        return calculateAverage(sum, questionnaire.getQuestions().size());
    }

    private int calculateAverage(int sum, int numberOfQuestions) {
        return sum/numberOfQuestions;
    }
}
