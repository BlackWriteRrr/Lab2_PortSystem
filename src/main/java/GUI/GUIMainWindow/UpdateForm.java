package GUI.GUIMainWindow;

import Modules.Ship;
import Storage.MapShips;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;

import java.util.HashMap;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Vladislav on 4/15/2017.
 */
public class UpdateForm {
    private MainWindow mainWindow;

    public UpdateForm(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void updateFormListShip() throws InterruptedException {

        mainWindow.tableListShip.removeAll();
        int i=0;
        for(HashMap.Entry<String, Ship> entry: MapShips.getMapShips().getShipMap().entrySet()){
            TableItem tableItem = new TableItem(mainWindow.tableListShip, SWT.NONE,i++);
            tableItem.setText(new String[]{
                    entry.getKey() ,
                    entry.getValue().getStatus()
            });
        }
    }

    public void updateFormQueue(PriorityBlockingQueue<Ship> ships) throws InterruptedException {

        mainWindow.tableQueueShips.removeAll();
        int i=0;
        while (!ships.isEmpty()){
            TableItem tableItem = new TableItem(mainWindow.tableQueueShips, SWT.NONE,i++);
            tableItem.setText(new String[]{
                    ships.poll().getName()
            });
        }
    }

}
