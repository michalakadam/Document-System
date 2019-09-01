package blueenergy;

import blueenergy.conversion.QuestionnaireConverter;
import blueenergy.document.*;
import blueenergy.organization.User;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

class ProgrammerService {

    void execute(DocumentDao documentDao) {
        // Task No 1
        // Split documents into distinct lists according to their type
        @SuppressWarnings("unchecked")
        List<Questionnaire> questionnaireList = (List<Questionnaire>) filterDocuments(documentDao, Questionnaire.class);
        @SuppressWarnings("unchecked")
        List<ApplicationForHolidays> applicationForHolidaysList =
                (List<ApplicationForHolidays>) filterDocuments(documentDao, ApplicationForHolidays.class);

        // Task No 2
        // Calculate average number of possible answers for all questions in all questionnaires
        double averageNumberOfPossibleAnswers = calculateAverageNumberOfPossibleAnswers(questionnaireList);
        System.out.println("Average number of possible answers: " + averageNumberOfPossibleAnswers);

        // Task No 3
        // Create a list of all users applying for holidays and check if any of them contains polish characters in login
        List<User> usersApplyingForHolidays = extractUsersFromApplications(applicationForHolidaysList);
        List<User> usersWithPolishCharacters = filterUsersWithPolishCharacters(usersApplyingForHolidays);
        System.out.println("\nUsers' logins with Polish characters: ");
        usersWithPolishCharacters
                .stream()
                .map(User::getLogin)
                .forEach(System.out::println);

        // Task No 4
        // Create a list of holidays' applications that contain dates in wrong order
        List<ApplicationForHolidays> applicationsWithWrongDates =
                checkForApplicationsWithWrongDates(applicationForHolidaysList);
        System.out.println("\nUsers who filled wrong dates in application for holidays: ");
        applicationsWithWrongDates
                .stream()
                .map(ApplicationForHolidays::getUserWhoRequestAboutHolidays)
                .map(User::getLogin)
                .forEach(System.out::println);

        // Task No 5
        // Create a mechanism for converting Questionnaires to a user-friendly file format
        QuestionnaireConverter questionnaireConverter = QuestionnaireConverter.newInstance();
        questionnaireList.forEach(questionnaireConverter::convert);
    }

    private List<? extends Document> filterDocuments(DocumentDao documentDao, Class<? extends Document> classType) {
        return documentDao
                .getAllDocumentsInDatabase()
                .stream()
                .filter(document -> document.getClass().equals(classType))
                .collect(Collectors.toList());
    }

    private double calculateAverageNumberOfPossibleAnswers(List<Questionnaire> questionnaireList) {
        int numberOfQuestions = 0;
        int numberOfPossibleAnswers = 0;
        for (Questionnaire questionnaire : questionnaireList) {
            numberOfQuestions += questionnaire.getQuestions().size();
            numberOfPossibleAnswers += sumAllPossibleAnswers(questionnaire);
        }
        return calculateAverage(numberOfPossibleAnswers, numberOfQuestions);
    }

    private int sumAllPossibleAnswers(Questionnaire questionnaire) {
        return questionnaire
                .getQuestions()
                .stream()
                .mapToInt(question -> question.getPossibleAnswers().size())
                .sum();
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

    private List<ApplicationForHolidays> checkForApplicationsWithWrongDates(List<ApplicationForHolidays> applications) {
        return applications
                .stream()
                .filter(application -> isDateOrderIncorrect(application.getSince(), application.getTo()))
                .collect(Collectors.toList());
    }

    private boolean isDateOrderIncorrect(Date dateSince, Date dateTo) {
        return dateSince.compareTo(dateTo) < 0;
    }
}
