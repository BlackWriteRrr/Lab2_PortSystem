package Storage;

import Modules.Ship;

import java.util.HashMap;

/**
 * Created by Vladislav on 4/15/2017.
 */
public class MapShips {
    private static MapShips mapShips;
    private static HashMap<String, Ship> shipMap;

    public static MapShips getMapShips(){
        if(mapShips==null)
            mapShips = new MapShips();
        return  mapShips;
    }

    private MapShips(){
        shipMap = new HashMap<>();
    }


    public void add(Ship ship){
        shipMap.put(ship.getName(),ship);
    }

    public Ship getShip(String key){
        return shipMap.get(key);
    }

    public HashMap<String,Ship> getShipMap(){
        return shipMap;
    }
}
