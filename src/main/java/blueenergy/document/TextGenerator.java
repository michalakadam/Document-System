package blueenergy.document;

import java.util.List;

class TextGenerator {


    private TextGenerator() {}

    String generateQuestionnaireContent(Questionnaire document) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Question question : document.getQuestions()) {
            stringBuilder.append(appendQuestion(question));
        }
        return stringBuilder.toString();
    }

    private StringBuilder appendQuestion(Question question) {
        return new StringBuilder()
                .append("\n")
                .append("Pytanie: ")
                .append(question.getQuestionText())
                .append("\n\n")
                .append(appendPossibleAnswers(question));
    }

    private StringBuilder appendPossibleAnswers(Question question) {
        List<String> answers = question.getPossibleAnswers();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < answers.size(); i++) {
            stringBuilder
                    .append(i+1)
                    .append(". ")
                    .append(answers.get(i))
                    .append("\n\n");
        }
        return stringBuilder;
    }

    static TextGenerator newInstance() {
        return new TextGenerator();
    }
}
