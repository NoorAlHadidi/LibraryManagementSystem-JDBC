package Model;

public class Customer extends Human{
    public Customer(int id, String fName, String lName) {
        super(id, fName, lName);
    }
    public Customer(int id, String fName, String lName, int age) {
        super(id, fName, lName, age);
    }
}
