
public class Car {

    String registration_no;
    Boolean avalaible;

    String name;
    String color;
    String model;
    String price;

    public Car(String reg, String name, String color, String model, String price)
    {
        registration_no = reg;
        this.avalaible = true;

        this.name = name;
        this.color = color;
        this.model = model;
        this.price = price;
    }
}