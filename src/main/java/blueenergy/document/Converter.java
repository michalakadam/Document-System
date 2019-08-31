package blueenergy.document;

public interface Converter <E extends Document> {

    void convert(E document);
}
