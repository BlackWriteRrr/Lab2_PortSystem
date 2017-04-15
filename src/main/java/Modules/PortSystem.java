package Modules;

import GUI.GUIMainWindow.MainWindow;
import Storage.MapShips;
import Storage.PriorityQueue;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.concurrent.Semaphore;

/**
 * Created by Vladislav on 4/15/2017.
 */
public class PortSystem {
    private static final boolean[] PIER_PLACES = new boolean[4];
    private static final Semaphore SEMAPHORE = new Semaphore(4, true);
    private static Stock stock;
    private static MainWindow mainWindow;

    public PortSystem(Shell shell, MainWindow mainWindow){
        stock = new Stock();
        this.mainWindow = mainWindow;
    }

    public static void mootToPier(Ship ship) throws InterruptedException {
        int time = Math.abs(ship.getQuantity() - ship.getNeedQuantity());
        new Thread(new Port(time, ship)).start();
        Thread.sleep(400);
    }

    public static class Port implements Runnable {
        private int time;
        private Ship ship;

        public Port(int Time, Ship ship){
            time = Time;
            this.ship = ship;
        }

        @Override
        public void run() {
            try {
                SEMAPHORE.acquire();
                Display.getDefault().syncExec(new Runnable() {
                    @Override
                    public void run() {
                        MapShips.getMapShips().getShip(ship.getName()).setStatus("Work");
                        try {
                            mainWindow.getUpdateForm().updateFormListShip();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

                int parkingNumber = -1;
                synchronized (PIER_PLACES){
                    for (int i = 0; i < 4; i++)
                        if (!PIER_PLACES[i]) {
                            PIER_PLACES[i] = true;
                            parkingNumber = i;
                            break;
                        }
                }

                synchronized (stock){
                    int quantity = ship.getQuantity() - ship.getNeedQuantity();
                    switch (ship.getProduct()){
                        case "Bread" : stock.setBread(stock.getBread() + quantity); break;
                        case "Milk" : stock.setMilk(stock.getMilk() + quantity); break;
                        case "Meat" :  stock.setMeat(stock.getMeat() + quantity); break;
                        case "Fruit" :  stock.setFruit(stock.getFruit() + quantity); break;
                        case "Potatoes" :  stock.setPotatoes(stock.getPotatoes() + quantity); break;
                    }

                }

                final int finalParkingNumber = parkingNumber;
                Display.getDefault().syncExec(new Runnable() {
                    @Override
                    public void run() {
                        mainWindow.getPierTableInformation().updatePierTable(ship, finalParkingNumber);
                    }
                });

                final int timeTread = (int) (time * 9.75);
                new Thread() {
                    @Override
                    public void run() {
                        for (final int[] i = new int[1]; i[0] <= 100; i[0]++) {
                            try {
                                Thread.sleep(timeTread);
                            } catch (Throwable th) {
                            }
                            if (Display.getDefault().isDisposed())
                                return;
                            Display.getDefault().asyncExec(new Runnable() {
                                public void run() {
                                    if (mainWindow.getPierTableInformation().getProgressBar(finalParkingNumber).isDisposed())
                                        return;
                                    mainWindow.getPierTableInformation().getProgressBar(finalParkingNumber).setSelection(i[0]);
                                }
                            });
                        }
                        Display.getDefault().asyncExec(new Runnable() {
                            public void run() {
                                mainWindow.getPierTableInformation().getProgressBar(finalParkingNumber).setSelection(0);
                            }
                        });
                    }
                }.start();

                Thread.sleep(time*1000);
                updateShipInf(ship, time);

                synchronized (PIER_PLACES) {
                    PIER_PLACES[parkingNumber] = false;
                }

                Display.getDefault().syncExec(new Runnable() {
                    @Override
                    public void run() {
                        mainWindow.getPierTableInformation().deletePierTable(finalParkingNumber);
                        mainWindow.getPortTableInformation().setTableItems(stock);
                    }
                });

                SEMAPHORE.release();

                Display.getDefault().syncExec(new Runnable() {
                    @Override
                    public void run() {
                        MapShips.getMapShips().getShip(ship.getName()).setStatus("Nothing");
                        try {
                            mainWindow.getUpdateForm().updateFormListShip();
                            if(!Storage.PriorityQueue.getPriorityQueue().isEmpty()){
                                mootToPier(PriorityQueue.getPriorityQueue().getShip());
                                mainWindow.getUpdateForm().updateFormQueue(PriorityQueue.getPriorityQueue().getMas());
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static boolean checkMootToPier(){
        boolean check = false;
        for (int i = 0; i < 4; i++)
            if (!PIER_PLACES[i]) {
                check = true;
                break;
            }
        return  (check &&  !Storage.PriorityQueue.getPriorityQueue().isEmpty());
    }

    public static void updateShipInf(Ship ship, int time){
        MapShips.getMapShips().getShip(ship.getName()).setQuantity(ship.getNeedQuantity());
        if(time > ship.getTime()){
            int fine = time - ship.getTime();
            MapShips.getMapShips().getShip(ship.getName()).setFine(ship.getFine() - time);
        }
    }

    public Stock getStock(){
        return stock;
    }
}