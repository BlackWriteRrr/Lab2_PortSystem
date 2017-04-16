package GUI.GUIMainWindow;

import GUI.SWTResourceManager;
import Modules.Stock;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

/**
 * Created by Vladislav on 4/15/2017.
 */
public class PortTableInformation {
    private Shell shell;
    private static Table tablePortInformation;
    private Table tablePierTimeLimitInf;
    private TableItem tableItemMeat;
    private TableItem tableItemBread;
    private TableItem tableItemMilk;
    private TableItem tableItemFruit;
    private TableItem tableItemPotatoes;

    public PortTableInformation(Shell shell) {
        this.shell = shell;
    }

    public void setContent(Stock stock) {
        tablePortInformation = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        tablePortInformation.setBounds(564, 46, 127, 198);
        tablePortInformation.setHeaderVisible(true);
        tablePortInformation.setLinesVisible(true);

        TableColumn tblclmnProducts = new TableColumn(tablePortInformation, SWT.NONE);
        tblclmnProducts.setWidth(61);
        tblclmnProducts.setText("Products");

        TableColumn tblclmnQuantity = new TableColumn(tablePortInformation, SWT.NONE);
        tblclmnQuantity.setWidth(62);
        tblclmnQuantity.setText("Quantity");


        tableItemMeat = new TableItem(tablePortInformation, SWT.NONE);
        tableItemBread = new TableItem(tablePortInformation, SWT.NONE);
        tableItemMilk = new TableItem(tablePortInformation, SWT.NONE);
        tableItemFruit = new TableItem(tablePortInformation, SWT.NONE);
        tableItemPotatoes = new TableItem(tablePortInformation, SWT.NONE);

        setTableItems(stock);

        Label lblPortInformation = new Label(shell, SWT.BORDER | SWT.CENTER);
        lblPortInformation.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        lblPortInformation.setAlignment(SWT.CENTER);
        lblPortInformation.setBounds(695, 9, 161, 25);
        lblPortInformation.setText("Port information");

        tablePierTimeLimitInf = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        tablePierTimeLimitInf.setBounds(697, 46, 306, 198);
        tablePierTimeLimitInf.setHeaderVisible(true);
        tablePierTimeLimitInf.setLinesVisible(true);

        TableColumn tblclmnPierTimelimitInformation = new TableColumn(tablePierTimeLimitInf, SWT.CENTER);
        tblclmnPierTimelimitInformation.setWidth(300);
        tblclmnPierTimelimitInformation.setText("Pier time-limit information");
    }

    public void setTableItems(Stock stock) {
        tableItemMeat.setText(new String[]{"Meat", "" + stock.getMeat()});
        tableItemMilk.setText(new String[]{"Milk", "" + stock.getMilk()});
        tableItemBread.setText(new String[]{"Bread", "" + stock.getBread()});
        tableItemFruit.setText(new String[]{"Fruit", "" + stock.getFruit()});
        tableItemPotatoes.setText(new String[]{"Potatoes", "" + stock.getPotatoes()});
    }

    public void addShipTimeLimit(String info){
        TableItem tableItem = new TableItem(tablePierTimeLimitInf, SWT.NONE);
        tableItem.setText(info);
    }

}