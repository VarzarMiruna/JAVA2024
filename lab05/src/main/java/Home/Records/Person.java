package Home.Records;

import Home.Exceptions.IllegalIdException;

public record Person(String id, String name) {
    public Person {
        if (Integer.parseInt(id) < 0) {
            throw new IllegalIdException("Wrong Id number! Id value must be a positive number.");
        }
    }
}
