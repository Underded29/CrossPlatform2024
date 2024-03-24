package lab_22;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library implements Serializable {
    protected Map<String, Integer> shelves;
    private List<Rental> rentals;

    public Library() {
        shelves = new HashMap<>();
        rentals = new ArrayList<>();
    }

    public void addBook(Book book) {
        String bookTitle = book.getTitle();
        shelves.put(bookTitle, shelves.getOrDefault(bookTitle, 0) + 1);
    }

    public void rentBook(Book book, Reader reader) throws RentalException {
        String bookTitle = book.getTitle();
        if (!shelves.containsKey(bookTitle) || shelves.get(bookTitle) == 0) {
            throw new RentalException("Немає наявних примірників книги на полиці: " + bookTitle);
        }
        Rental rental = new Rental(book, reader);
        rentals.add(rental);
        shelves.put(bookTitle, shelves.get(bookTitle) - 1);
    }

    public void returnBook(Book book, Reader reader) throws RentalException {
        Rental rental = findRental(book, reader);
        if (rental == null) {
            throw new RentalException("Книга " + book.getTitle() + " не була видана читачу " + reader.getName());
        }
        rentals.remove(rental);
        shelves.put(book.getTitle(), shelves.getOrDefault(book.getTitle(), 0) + 1);
    }

    public List<RentalWrapper> getRentals() {
        List<RentalWrapper> rentalWrapperList = new ArrayList<>();
        for (Rental rental : rentals) {
            rentalWrapperList.add(new RentalWrapper(rental.getBook(), rental.getReader(), rental.getDate()));
        }
        return rentalWrapperList;

    }
    public Map<String, Integer> getShelves() {
        return shelves;
    }

    public Rental findRental(Book book, Reader reader) {
        for (Rental rental : rentals) {
            if (rental.getBook().equals(book) && rental.getReader().equals(reader)) {
                return rental;
            }
        }
        return null;
    }

    public void printCatalog() {
        System.out.println("Каталог книг:");
        for (String bookTitle : shelves.keySet()) {
            int quantity = shelves.get(bookTitle);
            System.out.println("- \"" + bookTitle + "\", кількість наявних примірників: " + quantity);
        }
    }

    public void setShelves(Map<String, Integer> shelves) {
        this.shelves = shelves;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals == null ? new ArrayList<>() : new ArrayList<>(rentals);
    }

    public void printRentals() {
        System.out.println("Видані книги:");
        for (Rental rental : rentals) {
            System.out.println("- \"" + rental.getBook().getTitle() + "\", читач: " + rental.getReader().getName());
        }
    }

    @Override
    public String toString() {
        return "Library{" +
                "shelves=" + shelves +
                ", rentals=" + rentals +
                '}';
    }
}
