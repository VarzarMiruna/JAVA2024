package comp;

public record Document(String name, String type) {
    public Document {
        boolean typeIsCorrect = false;
        for (DocumentTypes dt : DocumentTypes.values()) {
            if (dt.name().equals((type))) {
                typeIsCorrect = true;
            }
        }

        
    }
}
