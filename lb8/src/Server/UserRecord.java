package Server;

import java.io.Serializable;

public class UserRecord implements Serializable {
    String name, surname;
    String title;
    String email;
    String workPlace;

    public UserRecord(String name, String surname, String title, String email, String workPlace) {
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.email = email;
        this.workPlace = workPlace;

    }

    public  UserRecord(){}
    @Override
    public String toString() {
        return "UserRecord{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                ", workPlace='" + workPlace + '\'' +
                '}';
    }
}
