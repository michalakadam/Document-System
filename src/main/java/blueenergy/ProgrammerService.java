package blueenergy;

import blueenergy.document.*;

import java.util.List;
import java.util.stream.Collectors;

public class ProgrammerService {


    public void execute(DocumentDao documentDao) {
        @SuppressWarnings("unchecked")
        List<Questionnaire> questionnaireList = (List<Questionnaire>) filterDocuments(documentDao, Questionnaire.class);
        @SuppressWarnings("unchecked")
        List<ApplicationForHolidays> applicationForHolidaysList = (List<ApplicationForHolidays>) filterDocuments(documentDao, ApplicationForHolidays.class);
        int averageNumberOfAnswersForAllQuestionsInAllQuestionnaires = calculateAverageNumberOfAnswers(questionnaireList);
        System.out.println(averageNumberOfAnswersForAllQuestionsInAllQuestionnaires);
    }

    private List<? extends Document> filterDocuments(DocumentDao documentDao, Class<? extends Document> classType) {
        return documentDao
                .getAllDocumentsInDatabase()
                .stream()
                .filter(document -> document.getClass().equals(classType))
                .collect(Collectors.toList());
    }

    private int calculateAverageNumberOfAnswers(List<Questionnaire> questionnaireList) {
        int numberOfQuestions = 0;
        int numberOfPossibleAnswers = 0;
        for (Questionnaire questionnaire : questionnaireList) {
            numberOfQuestions += questionnaire.getQuestions().size();
            numberOfPossibleAnswers += sumAllPossibleAnswers(questionnaire);
        }
        return calculateAverage(numberOfPossibleAnswers, numberOfQuestions);
    }

    private int sumAllPossibleAnswers(Questionnaire questionnaire) {
        int amountOfPossibleAnswers = 0;
        for (Question question : questionnaire.getQuestions()) {
            amountOfPossibleAnswers += question.getPossibleAnswers().size();
        }
        return amountOfPossibleAnswers;
    }


    private int calculateAverage(int numberOfPossibleAnswers, int numberOfQuestions) {
        return numberOfPossibleAnswers/numberOfQuestions;
    }
}
