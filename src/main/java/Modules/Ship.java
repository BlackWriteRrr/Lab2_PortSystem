package Modules;

/**
 * Created by Vladislav on 4/15/2017.
 */

public class Ship implements Comparable<Ship> {
    private String name;
    private String product;
    private int quantity;
    private int needQuantity;
    private int time;
    private int priority;
    private String status;
    private int fine;

    public Ship(String Name, String Product, int Quantity, int Priority) {
        name = Name;
        quantity = Quantity;
        priority = Priority;
        product = Product;
        time = 0;
        fine = 0;
        status = "Nothing";
    }

    public void setName(String Name) {
        name = Name;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(int Quantity) {
        quantity = Quantity;
    }

    public String getProduct(){
        return product;
    }

    public void setProduct(String Product){
        product = Product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setTime(int Time) {
        time = Time;
    }

    public void setNeedQuantity(int need){
        needQuantity = need;
    }

    public int getNeedQuantity(){
        return needQuantity;
    }

    public int getTime() {
        return time;
    }

    public void setPriority(int Priority) {
        priority = Priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setFine(int Fine) {
        fine -= Fine;
    }

    public int getFine() {
        return fine;
    }

    public void setStatus(String str) {
        status = str;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public int compareTo(Ship o) {

        if (priority > o.getPriority())
            return -1;
        if (priority < o.getPriority())
            return 1;

        if (fine < o.getFine())
            return -1;
        if (fine > o.getFine())
            return 1;

        return 0;
    }
}
