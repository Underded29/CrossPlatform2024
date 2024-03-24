package lab_22;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryWrapper implements Serializable {
    private Map<String, Integer> shelves;
    private List<RentalWrapper> rentals;

    public LibraryWrapper(Map<String, Integer> shelves, List<RentalWrapper> rentals) {
        this.shelves = new HashMap<>();
        this.rentals = new ArrayList<>();
    }

    public Map<String, Integer> getShelves() {
        return shelves;
    }

    public void setShelves(Map<String, Integer> shelves) {
        this.shelves = shelves;
    }

    public List<RentalWrapper> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalWrapper> rentals) {
        this.rentals = rentals;
    }

    public Library convertToLibrary() {
        Library library = new Library();
        library.setShelves(this.shelves);
        List<Rental> rentalList = new ArrayList<>();
        for (RentalWrapper rentalWrapper : this.rentals) {
            rentalList.add(rentalWrapper.convertToRental());
        }
        library.setRentals(rentalList);
        return library;
    }
}

