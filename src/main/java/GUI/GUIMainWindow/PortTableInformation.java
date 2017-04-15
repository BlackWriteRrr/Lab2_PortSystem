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
    private static Table table_5;
    private TableItem tableItemMeat;
    private  TableItem tableItemBread;
    private  TableItem tableItemMilk;
    private  TableItem tableItemFruit;
    private  TableItem tableItemPotatoes;

    public PortTableInformation(Shell shell){
        this.shell = shell;
    }

    public void setContent(Stock stock){
        table_5 = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table_5.setBounds(564, 46, 439, 198);
        table_5.setHeaderVisible(true);
        table_5.setLinesVisible(true);

        TableColumn tblclmnProducts = new TableColumn(table_5, SWT.NONE);
        tblclmnProducts.setWidth(61);
        tblclmnProducts.setText("Products");

        TableColumn tblclmnQuantity = new TableColumn(table_5, SWT.NONE);
        tblclmnQuantity.setWidth(62);
        tblclmnQuantity.setText("Quantity");

        TableColumn tblclmnPierLog = new TableColumn(table_5, SWT.CENTER);
        tblclmnPierLog.setWidth(311);
        tblclmnPierLog.setText("Pier log");

        tableItemMeat = new TableItem(table_5, SWT.NONE);
        tableItemBread = new TableItem(table_5, SWT.NONE);
        tableItemMilk = new TableItem(table_5, SWT.NONE);
        tableItemFruit = new TableItem(table_5, SWT.NONE);
        tableItemPotatoes = new TableItem(table_5, SWT.NONE);

        setTableItems(stock);

        Label lblPortInformation = new Label(shell, SWT.BORDER | SWT.CENTER);
        lblPortInformation.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        lblPortInformation.setAlignment(SWT.CENTER);
        lblPortInformation.setBounds(695, 9, 161, 25);
        lblPortInformation.setText("Port information");
    }

    public void setTableItems(Stock stock){
        tableItemMeat.setText(new String[]{"Meat","" + stock.getMeat()});
        tableItemMilk.setText(new String[]{"Milk","" + stock.getMilk()});
        tableItemBread.setText(new String[]{"Bread","" + stock.getBread()});
        tableItemFruit.setText(new String[]{"Fruit","" + stock.getFruit()});
        tableItemPotatoes.setText(new String[]{"Potatoes","" + stock.getPotatoes()});
    }


}
