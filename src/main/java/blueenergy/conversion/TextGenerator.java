package blueenergy.conversion;

import blueenergy.document.Document;

interface TextGenerator <E extends Document> {

    String generateText(E document);
}
