package GUI.GUIMainWindow;

import GUI.DialogForm.AddShip;
import GUI.DialogForm.MootShipToPier;
import GUI.SWTResourceManager;
import Modules.PortSystem;
import Storage.MapShips;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

/**
 * Created by Vladislav on 4/15/2017.
 */
public class MainWindow {
    private UpdateForm updateForm;
    private PortSystem portSystem;
    private PortTableInformation portTableInformation;
    private PierTableInformation pierTableInformation;
    public static Table table_4;
    public static Table table_6;
    private Display display;
    private Shell shell;

    public MainWindow getWindow(){
        return this;
    }
    public UpdateForm getUpdateForm(){
        return updateForm;
    }
    public PortTableInformation getPortTableInformation(){
        return portTableInformation;
    }
    public PierTableInformation getPierTableInformation(){
        return pierTableInformation;
    }

    public void open() {
        display = Display.getDefault();
        createContents();
        updateForm = new UpdateForm(getWindow());

        shell.open();
        shell.layout();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }

    public void createContents() {

        shell = new Shell();
        shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
        shell.setSize(1100, 500);
        shell.setText("SWT Application");

        portSystem = new PortSystem(shell, getWindow());
        pierTableInformation = new PierTableInformation(shell);
        portTableInformation = new PortTableInformation(shell);
        pierTableInformation.setContent();
        portTableInformation.setContent(portSystem.getStock());

        Button btnNewButton = new Button(shell, SWT.NONE);
        btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
        btnNewButton.setBounds(32, 10, 60, 25);
        btnNewButton.setText("Add Ship");

        btnNewButton.addSelectionListener(
                new SelectionAdapter() {
                    public void widgetSelected(SelectionEvent e) {
                        AddShip ship = new AddShip(shell,2);
                        ship.open(getWindow());
                    }
                });


        Button btnNewButton_1 = new Button(shell, SWT.NONE);
        btnNewButton_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
        btnNewButton_1.setBounds(98, 10, 60, 25);
        btnNewButton_1.setText("Moor");

        btnNewButton_1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                int selectedShip = table_4.getSelectionIndex();
                String shipName = table_4.getItem(selectedShip).getText(0);
                if(MapShips.getMapShips().getShip(shipName).getStatus().equals("Nothing")) {
                    MootShipToPier mootShipToPier = new MootShipToPier(shell, 2, MapShips.getMapShips().getShip(shipName));
                    mootShipToPier.open(getWindow(), portSystem);
                }
            }
        });

        table_4 = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table_4.setBounds(32, 46, 168, 198);
        table_4.setHeaderVisible(true);
        table_4.setLinesVisible(true);

        TableColumn tblclmnNewColumn = new TableColumn(table_4, SWT.CENTER);
        tblclmnNewColumn.setWidth(79);
        tblclmnNewColumn.setText("List of ships");

        TableColumn tblclmnStatus = new TableColumn(table_4, SWT.NONE);
        tblclmnStatus.setWidth(84);
        tblclmnStatus.setText("Status");

        table_6 = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
        table_6.setBounds(293, 46, 168, 198);
        table_6.setHeaderVisible(true);
        table_6.setLinesVisible(true);

        TableColumn tblclmnNewColumn_2 = new TableColumn(table_6, SWT.CENTER);
        tblclmnNewColumn_2.setWidth(162);
        tblclmnNewColumn_2.setText("Ship name");

        Label lblQueueShips = new Label(shell, SWT.NONE);
        lblQueueShips.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
        lblQueueShips.setAlignment(SWT.CENTER);
        lblQueueShips.setBounds(324, 10, 100, 25);
        lblQueueShips.setText("Queue ships");

    }


}