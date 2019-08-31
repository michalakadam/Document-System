package blueenergy;

import blueenergy.document.*;
import blueenergy.organization.User;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ProgrammerService {


    public void execute(DocumentDao documentDao) {
        @SuppressWarnings("unchecked")
        List<Questionnaire> questionnaireList = (List<Questionnaire>) filterDocuments(documentDao, Questionnaire.class);
        @SuppressWarnings("unchecked")
        List<ApplicationForHolidays> applicationForHolidaysList = (List<ApplicationForHolidays>) filterDocuments(documentDao, ApplicationForHolidays.class);
        double averageNumberOfAnswersForAllQuestionsInAllQuestionnaires = calculateAverageNumberOfAnswers(questionnaireList);
        List<User> usersApplyingForHolidays = extractUsersFromApplications(applicationForHolidaysList);
        List<User> usersWithPolishCharacters = filterUsersWithPolishCharacters(usersApplyingForHolidays);
    }

    private List<? extends Document> filterDocuments(DocumentDao documentDao, Class<? extends Document> classType) {
        return documentDao
                .getAllDocumentsInDatabase()
                .stream()
                .filter(document -> document.getClass().equals(classType))
                .collect(Collectors.toList());
    }

    private double calculateAverageNumberOfAnswers(List<Questionnaire> questionnaireList) {
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


    private double calculateAverage(double numberOfPossibleAnswers, double numberOfQuestions) {
        return numberOfPossibleAnswers/numberOfQuestions;
    }

    private List<User> extractUsersFromApplications(List<ApplicationForHolidays> applicationForHolidaysList) {
        return applicationForHolidaysList
                .stream()
                .map((ApplicationForHolidays::getUserWhoRequestAboutHolidays))
                .collect(Collectors.toList());
    }

    private List<User> filterUsersWithPolishCharacters(List<User> users) {
        return users
                .stream()
                .filter(this::userLoginContainsPolishCharacters)
                .collect(Collectors.toList());
    }

    private boolean userLoginContainsPolishCharacters(User user) {
        final String polishCharactersRegex = ".*[ąĄęĘśŚćĆżŻźŹóÓłŁńŃ].*";
        return user.getLogin().matches((polishCharactersRegex));
    }
}
