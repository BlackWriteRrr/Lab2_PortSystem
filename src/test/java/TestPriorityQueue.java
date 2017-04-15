import Modules.Ship;
import Storage.PriorityQueue;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Vladislav on 4/15/2017.
 */
public class TestPriorityQueue {

    @Test
    public void checkPriorityByPriority(){
        Ship ship = new Ship("mama", "Meat", 50, 0);
        Ship ship1 = new Ship("papa", "Meat", 50, 1);
        Ship ship2 = new Ship("lolo", "Meat", 50, 2);
        PriorityQueue.getPriorityQueue().addShip(ship);
        PriorityQueue.getPriorityQueue().addShip(ship2);
        PriorityQueue.getPriorityQueue().addShip(ship1);

        try {
            Assert.assertEquals(ship2,PriorityQueue.getPriorityQueue().getShip());
            Assert.assertEquals(ship1,PriorityQueue.getPriorityQueue().getShip());
            Assert.assertEquals(ship,PriorityQueue.getPriorityQueue().getShip());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void checkPriorityByFine(){
        Ship ship = new Ship("mama", "Meat", 50, 0);
        Ship ship1 = new Ship("papa", "Meat", 50, 0);
        Ship ship2 = new Ship("lolo", "Meat", 50, 0);
        ship.setFine(-10);
        ship1.setFine(0);
        ship2.setFine(-15);
        PriorityQueue.getPriorityQueue().addShip(ship);
        PriorityQueue.getPriorityQueue().addShip(ship2);
        PriorityQueue.getPriorityQueue().addShip(ship1);

        try {
            Assert.assertEquals(ship1,PriorityQueue.getPriorityQueue().getShip());
            Assert.assertEquals(ship,PriorityQueue.getPriorityQueue().getShip());
            Assert.assertEquals(ship2,PriorityQueue.getPriorityQueue().getShip());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void checkPriorityByPriorityAndFine(){
        Ship ship = new Ship("mama", "Meat", 50, 2);
        Ship ship1 = new Ship("papa", "Meat", 50, 1);
        Ship ship2 = new Ship("lolo", "Meat", 50, 1);
        ship.setFine(-10);
        ship1.setFine(0);
        ship2.setFine(-15);
        PriorityQueue.getPriorityQueue().addShip(ship);
        PriorityQueue.getPriorityQueue().addShip(ship2);
        PriorityQueue.getPriorityQueue().addShip(ship1);

        try {
            Assert.assertEquals(ship,PriorityQueue.getPriorityQueue().getShip());
            Assert.assertEquals(ship1,PriorityQueue.getPriorityQueue().getShip());
            Assert.assertEquals(ship2,PriorityQueue.getPriorityQueue().getShip());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
