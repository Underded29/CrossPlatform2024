package lab_22;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalWrapper implements Serializable {

    private Book book;
    private Reader reader;
    private LocalDate date;

    public void Rental(Book book, Reader reader) {
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

    public RentalWrapper(Book book, Reader reader, LocalDate date) {
        this.book = book;
        this.reader = reader;
        this.date = date;
    }

    public static List<RentalWrapper> convertToWrapper(List<Rental> rentals) {
        List<RentalWrapper> rentalWrappers = new ArrayList<>();
        if (rentals != null) {
            for (Rental rental : rentals) {
                rentalWrappers.add(new RentalWrapper(rental.getBook(), rental.getReader(), rental.getDate()));
            }
        }
        return rentalWrappers;
    }
    public static List<RentalWrapper> toWrapper(List<Rental> rentals) {
        return fromRentals(rentals);
    }

    public static List<RentalWrapper> fromRentals(List<Rental> rentals) {
        List<RentalWrapper> rentalWrappers = new ArrayList<>();
        for (Rental rental : rentals) {
            Book book = rental.getBook();
            Reader reader = rental.getReader();
            LocalDate date = rental.getDate();
            RentalWrapper rentalWrapper = new RentalWrapper(book, reader, date);
            rentalWrappers.add(rentalWrapper);
        }
        return rentalWrappers;
    }



    public static List<Rental> toRentals(List<RentalWrapper> rentals) {
        return null;
    }

    public Rental convertToRental() {
        return new Rental(book, reader);
    }
}
