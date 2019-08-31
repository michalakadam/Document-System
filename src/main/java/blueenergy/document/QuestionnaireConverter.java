package blueenergy.document;

import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QuestionnaireConverter implements Converter<Questionnaire> {

    private final TextGenerator textGenerator;

    private QuestionnaireConverter() {
        this.textGenerator = TextGenerator.newInstance();
    }

    @Override
    public void convert(Questionnaire document) {
        String fileName = document.getTitle().replace(" ", "_").toLowerCase() + ".txt";
        writeConvertedQuestionnaireToFile(textGenerator.generateQuestionnaireContent(document), fileName);
    }

    private void writeConvertedQuestionnaireToFile(String generatedDocumentContent, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)){
            writer.append(generatedDocumentContent);
            writer.flush();
            writer.close();
            System.out.println("Questionnaire successfully converted to "+ fileName);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }

    public static QuestionnaireConverter newInstance() {
        return new QuestionnaireConverter();
    }
}
