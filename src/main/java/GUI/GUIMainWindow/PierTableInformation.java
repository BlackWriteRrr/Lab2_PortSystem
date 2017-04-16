package GUI.GUIMainWindow;

import Modules.Ship;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

/**
 * Created by Vladislav on 4/15/2017.
 */
public class PierTableInformation {
    private Shell shell;
    private Table[] tablePier;
    private Label[] lblPort;
    private TableItem[] tableItemStatus;
    private TableItem [] tableItemName;
    private TableItem [] tableItemPriority;
    private TableItem [] tableItemProduct;
    private  ProgressBar[] progressBar;


    public PierTableInformation(Shell shell){
        this.shell = shell;
    }

    public void setContent(){

        tablePier = new Table[4];
        lblPort = new Label[4];
        progressBar = new ProgressBar[4];
        tableItemStatus = new TableItem[4];
        tableItemName = new TableItem[4];
        tableItemProduct = new TableItem[4];
        tableItemPriority = new TableItem[4];
        setTable(32, 0);
        setTable(293, 1);
        setTable(564, 2);
        setTable(835, 3);

    }

    public void updatePierTable(Ship ship, int i){
        tableItemStatus[i].setText(new String[]{"Status","Work" });
        tableItemName[i].setText(new String[]{"Ship name", ship.getName() });
        String priority = "Low";
        if(ship.getPriority()==1) priority = "Medium";
        if(ship.getPriority()==2) priority = "High";
        tableItemPriority[i].setText(new String[]{"Priority", priority });
        tableItemProduct[i].setText(new String[]{"Product", ship.getProduct() });
    }

    public void deletePierTable(int i){
        tableItemStatus[i].setText(new String[]{"Status", "Nothing" });
        tableItemName[i].setText(new String[]{"Ship name", "" });
        tableItemPriority[i].setText(new String[]{"Priority", "" });
        tableItemProduct[i].setText(new String[]{"Product", "" });
    }

    private void setTable(int i, int number){
        tablePier[number] = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        tablePier[number].setLinesVisible(true);
        tablePier[number].setHeaderVisible(true);
        tablePier[number].setBounds(i, 306, 168, 122);

        TableColumn tblclmn1Pier2 = new TableColumn(tablePier[number], SWT.NONE);
        tblclmn1Pier2.setWidth(70);

        TableColumn tableColumnPier2 = new TableColumn(tablePier[number], SWT.NONE);
        tableColumnPier2.setWidth(93);

        tableItemStatus[number] = new TableItem(tablePier[number], SWT.NONE);
        tableItemStatus[number].setText(new String[]{"Status", "Nothing" });

        tableItemName[number] = new TableItem(tablePier[number], SWT.NONE);
        tableItemName[number].setText("Ship name");

        tableItemPriority[number] = new TableItem(tablePier[number], SWT.NONE);
        tableItemPriority[number].setText("Priority");

        progressBar[number] = new ProgressBar(shell, SWT.NONE);
        progressBar[number].setBounds(i, 434, 168, 17);

        tableItemProduct[number] = new TableItem(tablePier[number], SWT.NONE);
        tableItemProduct[number].setText("Product");

        lblPort[number] = new Label(shell, SWT.NONE);
        lblPort[number].setText("Pier " + (number+1));
        lblPort[number].setAlignment(SWT.CENTER);
        lblPort[number].setBounds(i, 283, 168, 17);
    }

    public ProgressBar getProgressBar(int i){
        return progressBar[i];
    }
}
