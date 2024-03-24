package lab_23;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

class Reader implements Externalizable {
    private String firstName;
    private String lastName;
    private int readerID;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(firstName);
        out.writeUTF(lastName);
        out.writeInt(readerID);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        firstName = in.readUTF();
        lastName = in.readUTF();
        readerID = in.readInt();
    }

    public Reader() {
        this("", "", 0);
    }

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
        return '\n' + "Reader: " +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", readerID=" + readerID;
    }
}