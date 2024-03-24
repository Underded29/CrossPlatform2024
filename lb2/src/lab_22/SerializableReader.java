package lab_22;

import java.io.Serializable;

public class SerializableReader implements Serializable {
    private String name;
    private String lastName;

    public SerializableReader(Reader reader) {
        this.name = reader.getName();
        this.lastName = reader.getLastName();
    }

    public Reader toReader() {
        return new Reader(name, lastName);
    }
}
