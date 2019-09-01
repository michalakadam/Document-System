package blueenergy.conversion;

import blueenergy.document.Document;

public interface Converter <E extends Document> {

    void convert(E document);
}
