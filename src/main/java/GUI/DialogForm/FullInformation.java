package GUI.DialogForm;

import GUI.SWTResourceManager;
import Modules.Ship;
import Storage.MapShips;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

import java.util.HashMap;

public class FullInformation extends Dialog {

    protected Object result;
    protected Shell shell;
    private Table tableShips;

    /**
     * Create the dialog.
     * @param parent
     * @param style
     */
    public FullInformation(Shell parent, int style) {
        super(parent, style);
        setText("SWT Dialog");
    }

    /**
     * Open the dialog.
     * @return the result
     */
    public Object open() {
        createContents();
        shell.open();
        shell.layout();
        Display display = getParent().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return result;
    }

    /**
     * Create contents of the dialog.
     */
    private void createContents() {
        shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.RESIZE);
        shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
        shell.setSize(586, 314);
        shell.setText(getText());

        tableShips = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        tableShips.setBounds(42, 68, 497, 157);
        tableShips.setHeaderVisible(true);
        tableShips.setLinesVisible(true);

        TableColumn tblclmnName = new TableColumn(tableShips, SWT.CENTER);
        tblclmnName.setWidth(100);
        tblclmnName.setText("Name");

        TableColumn tblclmnQuantity = new TableColumn(tableShips, SWT.LEFT);
        tblclmnQuantity.setWidth(100);
        tblclmnQuantity.setText("Product");

        TableColumn tblclmnQuantity_1 = new TableColumn(tableShips, SWT.LEFT);
        tblclmnQuantity_1.setWidth(100);
        tblclmnQuantity_1.setText("Quantity");

        TableColumn tblclmnPriority = new TableColumn(tableShips, SWT.LEFT);
        tblclmnPriority.setWidth(80);
        tblclmnPriority.setText("Priority");

        TableColumn tblclmnFine = new TableColumn(tableShips, SWT.LEFT);
        tblclmnFine.setWidth(112);
        tblclmnFine.setText("Fine");

        Label lblFullInformationAbout = new Label(shell, SWT.NONE);
        lblFullInformationAbout.setAlignment(SWT.CENTER);
        lblFullInformationAbout.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
        lblFullInformationAbout.setBounds(189, 30, 191, 21);
        lblFullInformationAbout.setText("Full Information about ships");

        setFullInfoAboutShips();

    }

    public void setFullInfoAboutShips(){
        tableShips.removeAll();
        int i=0;
        for(HashMap.Entry<String, Ship> entry: MapShips.getMapShips().getShipMap().entrySet()){
            TableItem tableItem = new TableItem(tableShips, SWT.NONE,i++);
            String priority = "Low";
            if(entry.getValue().getPriority()==1) priority="Medium";
            if(entry.getValue().getPriority()==2) priority="High";
            tableItem.setText(new String[]{
                    entry.getKey() ,
                    entry.getValue().getProduct(),
                    "" + entry.getValue().getQuantity(),
                    priority,
                    "" + entry.getValue().getFine()
            });
        }
    }


}