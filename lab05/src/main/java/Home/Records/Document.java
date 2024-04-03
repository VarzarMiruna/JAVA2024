package Home.Records;

import Home.Enum.DocumentTypes;
import Home.Exceptions.WrongDocTypeException;

public record Document(String name, String type) {
    public Document {
        boolean typeIsCorrect = false;
        for (DocumentTypes dt : DocumentTypes.values()) {
            if (dt.name().equals((type))) {
                typeIsCorrect = true;
            }
        }

        if (!typeIsCorrect) {
            throw new WrongDocTypeException("Wrong document type extension!");
        }
    }

    /**
     * @return titlul documentului/numele
     */
    public String getTitle() {
        return name;
    }
}
