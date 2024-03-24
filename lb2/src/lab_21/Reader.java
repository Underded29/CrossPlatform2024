package lab_21;

import java.io.Serializable;

public class Reader implements Serializable {
    private String firstName;
    private String lastName;
    private int readerID;

    // конструктор класа
    public Reader(String firstName, String lastName, int readerID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.readerID = readerID;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getReaderID() {
        return readerID;
    }

    public void setReaderID(int readerID) {
        this.readerID = readerID;
    }

    @Override
    public String toString() {
        return '\n' + "lab_21.Reader: " +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", readerID=" + readerID;
    }
}
