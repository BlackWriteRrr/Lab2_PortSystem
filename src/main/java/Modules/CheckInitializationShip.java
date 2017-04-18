package Modules;

/**
 * Created by Vladislav on 4/15/2017.
 */
public class CheckInitializationShip {
    /**
     * check correct input info about name of the ship
     * @param name name our ship
     * @return boolean type, if name correct return true
     */
    public static boolean checkName(String name) {
        return  !(name.isEmpty());
    }

    /**
     * check correct input info about quantity of the ship
     * @param Quantity quantity our ships from 0 to 50
     * @return boolean type, if quantity correct return true
     */
    public static boolean checkQuantity(String Quantity){
        if(Quantity.isEmpty()) return false;
        int quantity = Integer.parseInt(Quantity);
        if(quantity<0 || quantity>50)
            return false;
        else return true;
    }


    /**
     * check correct input info about quantity of the ship
     * @param Quantity quantity our ships from 0 to 50
     * @param ship info about our ship, which want to moot
     * @param portSystem info about portSystem, because we need to check quantity product on stock
     * @return boolean type, if quantity correct return true
     */
    public static boolean checkQuantity(String Quantity, Ship ship, PortSystem portSystem){
        int quantity = Integer.parseInt(Quantity);
        switch (ship.getProduct()){
            case "Bread" : if((quantity - ship.getQuantity())<portSystem.getStock().getBread()) return true;
            case "Milk" : if((quantity - ship.getQuantity())<portSystem.getStock().getMilk()) return true;
            case "Meat" : if((quantity - ship.getQuantity())<portSystem.getStock().getMeat()) return true;
            case "Fruit" :  if((quantity - ship.getQuantity())<portSystem.getStock().getFruit()) return true;
            case "Potatoes" :  if((quantity - ship.getQuantity())<portSystem.getStock().getPotatoes()) return true;
        }
       return false;
    }

    /**
     * correct input info about time in pier of the ship
     * @param Time time to stay in pier
     * @return boolean type, if time correct return true
     */
    public static boolean checkTime(String Time){
        if(Time.isEmpty()) return false;
        int time = Integer.parseInt(Time);
        if(time<0 || time>150)
            return false;
        else return true;
    }

    /**
     * correct input info about priority of the ship
     * @param priority priority our ship
     * @return boolean type, if priority correct return true
     */
    public static boolean checkPriority(String priority){
        if(priority.equals("Choose priority"))
            return false;
        else return true;
    }

    /**
     * correct input info about product of the ship
     * @param products type of the product
     * @return boolean type, if product correct return true
     */
    public static boolean checkProducts(String products){
        if(products.equals("Milk") || products.equals("Meat") || products.equals("Bread")
                || products.equals("Fruit") || products.equals("Potatoes"))
            return true;
        else return false;
    }

}
