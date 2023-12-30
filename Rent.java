import java.time.LocalDate;

public class Rent{

    Customer customer;
    Car car;
    LocalDate date_of_purchase;
    // LocalDate date_of_return;

    public Rent(Customer cus, Car car, LocalDate dop)
    {
        customer = cus;
        this.car = car;
        date_of_purchase = dop;
    }

}