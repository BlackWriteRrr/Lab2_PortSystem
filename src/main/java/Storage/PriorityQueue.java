package Storage;

import Modules.Ship;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Vladislav on 4/15/2017.
 */
public class PriorityQueue {
    private static PriorityQueue queue;
    private static PriorityBlockingQueue<Ship> ships ;

    public static PriorityQueue getPriorityQueue(){
        if(queue == null)
            queue = new PriorityQueue();
        return queue;
    }

    private PriorityQueue() {
        ships = new PriorityBlockingQueue<>();
    }

    public Ship getShip() throws InterruptedException {
        Ship ship = ships.take();
        return ship;
    }

    public void addShip(Ship ship){
        ships.put(ship);
    }

    public boolean isEmpty(){
        return ships.isEmpty();
    }

    public PriorityBlockingQueue<Ship> getMas(){
        PriorityBlockingQueue<Ship> ship = new PriorityBlockingQueue<>(ships);
        return ship;
    }

}
