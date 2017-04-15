package Modules;

/**
 * Created by Vladislav on 4/15/2017.
 */
public class CheckInitializationShip {
    public static boolean checkName(String name){
        return  !(name.isEmpty());
    }

    public static boolean checkQuantity(String Quantity){
        if(Quantity.isEmpty()) return false;
        int quantity = Integer.parseInt(Quantity);
        if(quantity<0 || quantity>50)
            return false;
        else return true;
    }

    public static boolean checkTime(String Time){
        if(Time.isEmpty()) return false;
        int time = Integer.parseInt(Time);
        if(time<0 || time>150)
            return false;
        else return true;
    }

    public static boolean checkPriority(String priority){
        if(priority.equals("Choose priority"))
            return false;
        else return true;
    }

    public static boolean checkProducts(String products){
        if(products.equals("Milk") || products.equals("Meat") || products.equals("Bread")
                || products.equals("Fruit") || products.equals("Potatoes"))
            return true;
        else return false;
    }

}
