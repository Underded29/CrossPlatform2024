package lab_22;

import java.time.LocalDate;

public class Rental {
    private Book book;
    private Reader reader;
    private LocalDate date;

    public Rental(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
        this.date = LocalDate.now();
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
