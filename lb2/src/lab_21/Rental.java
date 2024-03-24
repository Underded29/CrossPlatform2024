package lab_21;

import lab_21.Book;
import lab_21.Reader;

import java.io.Serializable;
import java.time.LocalDate;

public class Rental implements Serializable {
    private Book book;
    private Reader reader;
    private LocalDate dateRented;
    private LocalDate dateDue;

    // конструктор класа
    public Rental(Book book, Reader reader, LocalDate dateRented, LocalDate dateDue) {
        this.book = book;
        this.reader = reader;
        this.dateRented = dateRented;
        this.dateDue = dateDue;
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
        return '\n' + "lab_21.Rental" +
                "book=" + book +
                ", reader=" + reader +
                ", dateRented=" + dateRented +
                ", dateDue=" + dateDue;
    }
}

