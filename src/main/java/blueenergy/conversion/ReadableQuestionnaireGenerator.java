package blueenergy.conversion;

import blueenergy.document.Question;
import blueenergy.document.Questionnaire;

import java.util.List;

class ReadableQuestionnaireGenerator implements TextGenerator<Questionnaire>{


    private ReadableQuestionnaireGenerator() {}

    @Override
    public String generateText(Questionnaire document) {
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

    static ReadableQuestionnaireGenerator newInstance() {
        return new ReadableQuestionnaireGenerator();
    }
}
