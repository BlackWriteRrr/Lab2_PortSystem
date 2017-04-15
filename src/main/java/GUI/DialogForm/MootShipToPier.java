package GUI.DialogForm;

import GUI.GUIMainWindow.MainWindow;
import GUI.GUIMainWindow.UpdateForm;
import GUI.SWTResourceManager;
import Modules.PortSystem;
import Modules.Ship;
import Storage.MapShips;
import Storage.PriorityQueue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import static Modules.CheckInitializationShip.checkQuantity;
import static Modules.CheckInitializationShip.checkTime;

/**
 * Created by Vladislav on 4/15/2017.
 */
public class MootShipToPier extends Dialog {
    private UpdateForm updateForm;
    private Display display;
    private Shell shell;
    protected Object result;
    private Text text;
    private Text text_1;
    private Label IncorrectQuantity;
    private Label IncorrectTime;
    private Ship ship;

    /**
     * Create the dialog.
     *
     * @param parent
     * @param style
     */
    public MootShipToPier(Shell parent, int style, Ship shp) {
        super(parent, style);
        setText("SWT Dialog");
        ship = shp;
    }

    /**
     * Open the dialog.
     *
     * @return the result
     */
    public Object open(MainWindow mainWindow, PortSystem portSystem) {
        updateForm = new UpdateForm(mainWindow);
        createContents(portSystem);
        shell.open();
        shell.layout();
        display = getParent().getDisplay();

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
    private void createContents(final PortSystem portSystem) {
        shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.RESIZE);
        shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
        shell.setSize(357, 225);
        shell.setText(getText());

        Label lblInput = new Label(shell, SWT.NONE);
        lblInput.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
        lblInput.setAlignment(SWT.CENTER);
        lblInput.setBounds(23, 50, 116, 25);
        lblInput.setText("Enter the time");

        text = new Text(shell, SWT.BORDER);
        text.setBounds(156, 51, 66, 24);

        Label lblEnterTheQuantity = new Label(shell, SWT.NONE);
        lblEnterTheQuantity.setText("Enter the quantity");
        lblEnterTheQuantity.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
        lblEnterTheQuantity.setAlignment(SWT.CENTER);
        lblEnterTheQuantity.setBounds(23, 91, 116, 24);

        text_1 = new Text(shell, SWT.BORDER);
        text_1.setBounds(156, 91, 66, 24);

        Button btnMoot = new Button(shell, SWT.NONE);
        btnMoot.setBounds(156, 142, 66, 25);
        btnMoot.setText("Moot");

        btnMoot.addSelectionListener(
                new SelectionAdapter() {
                    public void widgetSelected(SelectionEvent e) {
                        checkAndMoot(portSystem);
                    }
                });

        IncorrectTime = new Label(shell, SWT.NONE);
        showIncorrectValue(50, IncorrectTime);
        IncorrectQuantity = new Label(shell, SWT.NONE);
        showIncorrectValue(91, IncorrectQuantity);

    }

    public void showIncorrectValue(int i, Label label) {
        label.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        label.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
        label.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
        label.setAlignment(SWT.CENTER);
        label.setBounds(228, i, 117, 25);
        label.setText("Incorrect value");
        label.setVisible(false);
    }

    public void checkAndMoot(PortSystem portSystem) {
        int flag = 0;
        if (!checkTime(text.getText())) {
            IncorrectTime.setVisible(true);
            flag = 1;
        } else IncorrectTime.setVisible(false);

        if (!checkQuantity(text_1.getText())) {
            IncorrectQuantity.setVisible(true);
            flag = 1;
        } else IncorrectQuantity.setVisible(false);

        if (flag == 0) {
            int time = Integer.parseInt(text.getText());
            int needQuantity = Integer.parseInt(text_1.getText());
            ship.setTime(time);
            ship.setNeedQuantity(needQuantity);

            PriorityQueue.getPriorityQueue().addShip(ship);

            if (portSystem.checkMootToPier()) {
                try {
                    portSystem.mootToPier(PriorityQueue.getPriorityQueue().getShip());

                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            MapShips.getMapShips().getShip(ship.getName()).setStatus("Wait");
            try {
                updateForm.updateFormListShip();
                updateForm.updateFormQueue(PriorityQueue.getPriorityQueue().getMas());
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            shell.close();

        }
    }
}