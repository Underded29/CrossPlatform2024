package lab_23;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;

class Rental implements Externalizable {
    private Book book;
    private Reader reader;
    private LocalDate dateRented;
    private LocalDate dateDue;

    public Rental() {}

    public Rental(Book book, Reader reader, LocalDate dateRented, LocalDate dateDue) {
        this.book = book;
        this.reader = reader;
        this.dateRented = dateRented;
        this.dateDue = dateDue;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(book);
        out.writeObject(reader);
        out.writeObject(dateRented);
        out.writeObject(dateDue);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        book = (Book) in.readObject();
        reader = (Reader) in.readObject();
        dateRented = (LocalDate) in.readObject();
        dateDue = (LocalDate) in.readObject();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public LocalDate getDateRented() {
        return dateRented;
    }

    public void setDateRented(LocalDate dateRented) {
        this.dateRented = dateRented;
    }

    public LocalDate getDateDue() {
        return dateDue;
    }

    public void setDateDue(LocalDate dateDue) {
        this.dateDue = dateDue;
    }

    @Override
    public String toString() {
        return '\n' + "Rental" +
                "book=" + book +
                ", reader=" + reader +
                ", dateRented=" + dateRented +
                ", dateDue=" + dateDue;
    }
}