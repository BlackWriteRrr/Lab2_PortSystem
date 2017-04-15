package Modules;

/**
 * Created by Vladislav on 4/15/2017.
 */
public class Stock {
    private int bread;
    private int milk;
    private int meat;
    private int fruit;
    private int potatoes;

    public Stock(){
        bread = 1000;
        milk = 1000;
        meat = 1000;
        fruit = 1000;
        potatoes = 1000;
    }

    public void setBread(int Bread){
        bread = Bread;
    }

    public void setMilk(int Milk){
        milk = Milk;
    }

    public void setMeat(int Meat){
        meat = Meat;
    }

    public void setFruit(int Fruit){
        fruit = Fruit;
    }

    public void setPotatoes(int Potatoes){
        potatoes = Potatoes;
    }

    public int getBread(){
        return bread;
    }

    public int getMilk(){
        return milk;
    }

    public int getMeat(){
        return meat;
    }

    public int getFruit(){
        return fruit;
    }

    public int getPotatoes(){
        return potatoes;
    }
}
