package lab_22;

import java.io.Serializable;

public class SerializableBook implements Serializable {
    private String title;
    private SerializableAuthor author;

    public SerializableBook(Book book) {
        this.title = book.getTitle();
        this.author = new SerializableAuthor(book.getAuthor());
    }

    public Book toBook() {
        return new Book(title, author.toAuthor());
    }
}
