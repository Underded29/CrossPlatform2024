package lab_22;

import java.io.Serializable;

public class SerializableAuthor implements Serializable {
    private String name;
    private String surname;

    public SerializableAuthor(Author author) {
        this.name = author.getName();
        this.surname = author.getSurname();
    }

    public Author toAuthor() {
        return new Author(name, surname);
    }
}
