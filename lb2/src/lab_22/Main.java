package lab_22;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Author author1 = new Author("Григорій", "Сковорода");
        Author author2 = new Author("Тарас", "Шевченко");
        Reader reader1 = new Reader("Іван", "Петров");
        Reader reader2 = new Reader("Олена", "Іванова");
        Book book1 = new Book("Повість временних літ", author1);
        Book book2 = new Book("Кобзар", author2);
        library.addBook(book1);
        library.addBook(book1);
        library.addBook(book2);
        try {
            library.rentBook(book1, reader1);
            library.rentBook(book2, reader2);
        } catch (RentalException e) {
            System.out.println("Помилка: " + e.getMessage());
        }

        library.printCatalog();
        library.printRentals();

        // серіалізація бібліотеки до файлу
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("library.dat"))) {
            List<RentalWrapper> rentalWrapperList = new ArrayList<>();
            for (RentalWrapper rental : library.getRentals()) {
                rentalWrapperList.add(new RentalWrapper(rental.getBook(), rental.getReader(), rental.getDate()));
            }
            LibraryWrapper libraryWrapper = new LibraryWrapper(library.getShelves(), rentalWrapperList);
            outputStream.writeObject(libraryWrapper);
            System.out.println("Бібліотеку збережено у файл library.dat");
        } catch (IOException e) {
            System.out.println("Помилка збереження бібліотеки у файл: " + e.getMessage());
        }

        // десеріалізація бібліотеки з файлу
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("library.dat"))) {
            LibraryWrapper libraryWrapper = (LibraryWrapper) inputStream.readObject();
            Library deserializedLibrary = new Library();
            deserializedLibrary.setShelves(libraryWrapper.getShelves());
            List<Rental> rentalList = new ArrayList<>();
            for (RentalWrapper rentalWrapper : libraryWrapper.getRentals()) {
                rentalList.add(rentalWrapper.convertToRental());
            }
            deserializedLibrary.setRentals(rentalList);
            System.out.println("Бібліотеку відновлено з файлу library.dat:");
            deserializedLibrary.printCatalog();
            deserializedLibrary.printRentals();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка відновлення бібліотеки з файлу: " + e.getMessage());
        }
    }
}


