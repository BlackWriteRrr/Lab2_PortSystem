package Modules;

import GUI.GUIMainWindow.MainWindow;
import Logger.LoggerApp;
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

    /**
     * initialization
     * @param shell set shell from main window
     * @param mainWindow set ref our main window
     */
    public PortSystem(Shell shell, MainWindow mainWindow){
        stock = new Stock();
        this.mainWindow = mainWindow;
    }

    /**
     * in this function we create current to moot to pier
     * @param ship info about ship, which want to moot to pier
     * @throws InterruptedException
     */
    public static void mootToPier(Ship ship) throws InterruptedException {
        int time = Math.abs(ship.getQuantity() - ship.getNeedQuantity());
        new Thread(new Port(time, ship)).start();
        Thread.sleep(400);
    }

    /**
     * Our inner class for work in pier
     */
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
                //acquire semaphore
                SEMAPHORE.acquire();
                //show status info about ship in pier in main window
                Display.getDefault().syncExec(new Runnable() {
                    @Override
                    public void run() {
                        MapShips.getMapShips().getShip(ship.getName()).setStatus("Work");
                        try {
                            mainWindow.getUpdateForm().updateFormListShip();
                        } catch (InterruptedException e) {
                            LoggerApp.getLogger().error(e);
                        }
                    }
                });
                //check free place in stock
                int parkingNumber = -1;
                synchronized (PIER_PLACES){
                    for (int i = 0; i < 4; i++)
                        if (!PIER_PLACES[i]) {
                            PIER_PLACES[i] = true;
                            parkingNumber = i;
                            break;
                        }
                }

                LoggerApp.getLogger().info("Moot ship " + ship.getName() + " to pier " + (parkingNumber+1));

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
                // create new tread to show progress bar
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

                if(time <= ship.getTime())
                Thread.sleep(time*1000);

                else  {
                    Thread.sleep(ship.getTime()*1000);
                    Display.getDefault().asyncExec(new Runnable() {
                        public void run() {
                            mainWindow.getPortTableInformation().addShipTimeLimit("Ship " + ship.getName()
                                    + " in pier " + (finalParkingNumber+1) +  " exceeded the time limit");
                        }
                    });
                    Thread.sleep((time-ship.getTime())*1000);
                }

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
                LoggerApp.getLogger().info("Ship " + ship.getName() + " exit pier " + (finalParkingNumber+1));
                //check info in queue and if ships stay here we moot his in pier
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
                            LoggerApp.getLogger().error(e);
                        }
                    }
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *
     * @return boolean type , if place free return true
     */
    public static boolean checkMootToPier(){
        boolean check = false;
        for (int i = 0; i < 4; i++)
            if (!PIER_PLACES[i]) {
                check = true;
                break;
            }
        return  (check &&  !Storage.PriorityQueue.getPriorityQueue().isEmpty());
    }

    /**
     * set fine in ship
     * @param ship info about ship
     * @param time info about time
     */
    public static void updateShipInf(Ship ship, int time){
        MapShips.getMapShips().getShip(ship.getName()).setQuantity(ship.getNeedQuantity());
        if(time > ship.getTime()){
            int fine = ship.getTime() - time;
            MapShips.getMapShips().getShip(ship.getName()).setFine(ship.getFine() - fine);
            LoggerApp.getLogger().info("Ship " + ship.getName() + " Received a fine " + fine);
        }
    }

    public Stock getStock(){
        return stock;
    }
}
