import java.time.LocalDate;

public class CarRentalSystem {
    public static void main(String[] args) throws Exception {
        
        CarRental carRental = new CarRental();

        Customer cus1 = carRental.addCustomer("1", "ABC", "9152221342");
        Customer cus2 = carRental.addCustomer("2", "BCD", "9152290342");
        Customer cus3 = carRental.addCustomer("3", "CDE", "9157847482");

        carRental.displayCustomerList();
        System.out.println("");
        Customer updated_cus = carRental.updateCustomer("1", 0, "XYZ");
        carRental.displayCustomerList();
        System.out.println("");

        carRental.deleteCustomer("2");
        carRental.displayCustomerList();

        Car car1 =  carRental.addCar("1", "Getz", "Black","2", "10L");
        Car car2 = carRental.addCar("2", "Abc", "Red","3", "20L");
        Car car3 = carRental.addCar("3", "a", "b","c", "d");

        carRental.displayCarList();

        Rent rent1 = carRental.rentCar(cus1, car1);
        
        carRental.displayAvailable();
        System.out.println("");
        
        LocalDate returnDate = LocalDate.of(2024, 02, 12);
        carRental.returnCar(rent1, returnDate);
        carRental.displayRentalHistory();
        System.out.println("");

        Rent rent2 = carRental.rentCar(cus3, car3);
        carRental.displayAvailable();
        System.out.println("");

        LocalDate returnDate2 = LocalDate.of(2024, 03, 12);
        carRental.returnCar(rent2, returnDate2);
        carRental.displayRentalHistory();
        System.out.println("");

    }
}
