import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.time.LocalDate;
import java.time.Period;

public class CarRental{
    
    HashMap<String, Boolean> carMap = new HashMap<>();
    ArrayList<String> availableList = new ArrayList<>();

    ArrayList<Customer> customerList = new ArrayList<>();
    ArrayList<Car> carList = new ArrayList<>();
    ArrayList<Rent> rentList = new ArrayList<>();
    HashMap<Rent,LocalDate> rentalHistoryList = new HashMap<>();

    public Customer addCustomer(String uid, String name, String contact)
    {
        Customer customer = new Customer(uid, name, contact);
        customerList.add(customer);
        return customer;
    }


    public Customer updateCustomer(String uid, int field, String newField)
    {
        for(Customer cus: customerList)
        {
            if(cus.user_id == uid)
            {
                switch(field)
                {
                    case 0: 
                        System.out.println("Update name:");
                        cus.name = newField;
                        break;
                    case 1:
                        System.out.println("Update contact:");
                        cus.contact = newField;
                        break;
                    default:
                        System.out.println("Invalid field");
                        break;                       
                }
                return cus;
            }
        }
        return null;

    }

    public void deleteCustomer(String uid)
    {
        for(int i=0; i<customerList.size(); i++)
        {
            if(customerList.get(i).user_id == uid)
            {
                customerList.remove(i);
                System.out.println("Deleted Customer with uid: " + uid);
            }
        }

    }

    public void displayCustomerList()
    {
        System.out.println("Customer List:");
        for(int i=0; i<customerList.size(); i++)
        {
            System.out.println(customerList.get(i).user_id + " " + customerList.get(i).name +" " + customerList.get(i).contact);

        }
    }

    public Car addCar(String reg, String name, String color, String model, String price)
    {
        Car car = new Car(reg, name, color, model, price);
        carList.add(car);
        carMap.put(reg, true);
        availableList.add(reg);

        return car;
    }

    public Car updateCar(String reg, int field, String newField)
    {
        for(Car car: carList)
        {
            if(car.registration_no == reg)
            {
                switch(field)
                {
                    case 0:
                        System.out.println("Update name");
                        car.name = newField;
                        break;
                    case 1:
                        System.out.println("Update color");
                        car.color = newField;
                        break;
                    case 2:
                        System.out.println("Update model");
                        car.model = newField;
                        break;
                    case 3:
                        System.out.println("Update price");
                        car.price = newField;
                        break;
                    default: 
                        System.out.println("Invalid field");
                        break;

                }

                return car;
            }
        }
        return null;
    }

    public void deleteCar(String reg)
    {
        for(int i=0; i<carList.size(); i++)
        {
            if(carList.get(i).registration_no == reg)
            {
                carList.remove(i);
                carMap.remove(reg);
                availableList.remove(String.valueOf(reg));
                System.out.println("Deleted Car with reg_no: "+ reg);
            }
        }
    }

    public void displayCarList()
    {
        System.out.println("Car List:");
        for(int i=0; i<carList.size(); i++)
        {
            System.out.println(carList.get(i).registration_no + " " + carList.get(i).name + " " + carList.get(i).color + " " + carList.get(i).model + " " + carList.get(i).price);
        }
    }

    public Rent rentCar(Customer customer, Car car)
    {
        // if(!carMap.containsKey(reg))
        // {
        //     System.out.println("Car doesn't exist");
        //     return;
        // }

        if(!carList.contains(car) || !customerList.contains(customer))
        {
            System.out.println("Invalid car or customer");
            return null;
        }

        if(!carMap.get(car.registration_no))
        {
            System.out.println("Car unavailable");
            return null;
        }

        LocalDate dop = LocalDate.now();
        Rent rent = new Rent(customer, car, dop);
        rentList.add(rent);

        carMap.put(car.registration_no, false);
        availableList.remove(String.valueOf(car.registration_no));

        return rent;
    }

    public void displayAvailable()
    {
        System.out.println("Car availability list");
        for(int i=0; i<availableList.size(); i++)
        {
            System.out.println(availableList.get(i));
        }
    }

    public void returnCar(Rent rent, LocalDate returnDate)
    {
        if(!rentList.contains(rent))
        {
            System.out.println("Invalid rent field");
        }

        Car car = rent.car;
        availableList.add(car.registration_no);
        carMap.put(car.registration_no, true);

        rentalHistoryList.put(rent, returnDate);


    }

    public void displayRentalHistory()
    {
        System.out.println("Rental History:");
        for(Map.Entry<Rent, LocalDate> entry : rentalHistoryList.entrySet())
        {
            Rent rent = entry.getKey();
            LocalDate date_of_return = entry.getValue();
            LocalDate dop = rent.date_of_purchase;

            Period period = Period.between(dop, date_of_return);
            int days = period.getDays();
            System.out.println(rent.customer.user_id + " " + rent.car.registration_no + " " + dop + " " + date_of_return+ " " + days);
        }
    }


}