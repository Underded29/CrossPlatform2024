package lab_23;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Library implements Externalizable {
    private List<Book> books;
    private List<Reader> readers;
    private List<Rental> rentals;

    public Library() {
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(books);
        out.writeObject(readers);
        out.writeObject(rentals);
    }

    @Override    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        books = (List<Book>) in.readObject();
        readers = (List<Reader>) in.readObject();
        rentals = (List<Rental>) in.readObject();
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public void rentBook(Book book, Reader reader, LocalDate dateRented, LocalDate dateDue) {
        rentals.add(new Rental(book, reader, dateRented, dateDue));
        books.remove(book);
    }

    public void returnBook(Book book, Reader reader) {
        rentals.removeIf(rental -> rental.getBook().equals(book) && rental.getReader().equals(reader));
        books.add(book);
    }

    public void save(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        }
    }

    public static Library load(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Library) ois.readObject();
        }
    }
}