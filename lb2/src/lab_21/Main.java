package lab_21;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {

        Library library = new Library();

        Book book1 = new Book("Java для починаючих", "Іван Гордієнко", 2018);
        Book book2 = new Book("Python: основи програмування", "Григорій Симон'ян", 2020);
        Reader reader1 = new Reader("Давид", "Кравенко", 123456);
        Reader reader2 = new Reader("Варвара", "Крушінська", 789012);
        library.addBook(book1);
        library.addBook(book2);
        library.addReader(reader1);
        library.addReader(reader2);

        LocalDate dateRented = LocalDate.now();
        LocalDate dateDue = dateRented.plusDays(14);
        library.rentBook(book1, reader1, dateRented, dateDue);
        library.rentBook(book2, reader2, dateRented, dateDue);

        System.out.println(library.getRentals());

        library.returnBook(book1, reader1);
        library.returnBook(book2, reader2);


        try {
            library.save("library.dat");
        } catch (IOException e) {
            System.out.println("Помилка збереження файлу: " + e.getMessage());
        }

        try {
            Library newLibrary = Library.load("library.dat");
            System.out.println(newLibrary.getRentals());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка збереження файлу: " + e.getMessage());
        }
    }
}
