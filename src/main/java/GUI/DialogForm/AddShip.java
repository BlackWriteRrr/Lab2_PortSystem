package GUI.DialogForm;

import GUI.GUIMainWindow.MainWindow;
import GUI.GUIMainWindow.UpdateForm;
import GUI.SWTResourceManager;
import Logger.LoggerApp;
import Modules.Ship;
import Storage.MapShips;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import static Modules.CheckInitializationShip.*;

/**
 * Created by Vladislav on 4/15/2017.
 */
public class AddShip extends Dialog {
    private UpdateForm updateForm;
    private Display display;
    private Shell shell;
    private Text text;
    private Text text_1;
    private Label IncorrectName;
    private Label IncorrectQuantity;
    private Label IncorrectChoosePriority;
    private Label IncorrectChooseProducts;
    private Combo comboPriority;
    private Combo comboProducts;

    /**
     * Create the dialog.
     * @param parent
     * @param style
     */
    public AddShip(Shell parent, int style) {
        super(parent, style);
        setText("SWT Dialog");
    }
    /**
     * Open the dialog.
     * @return the result
     * @param mainWindow
     */
    public void open(MainWindow mainWindow) {
        updateForm = new UpdateForm(mainWindow);
        createContents();
        shell.open();
        shell.layout();
        display = getParent().getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
    /**
     * Create contents of the dialog.
     */
    private void createContents() {
        shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.MIN | SWT.RESIZE);
        shell.setModified(true);
        shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
        shell.setSize(450, 264);
        shell.setText(getText());

        Label lblInputName = new Label(shell, SWT.NONE);
        lblInputName.setAlignment(SWT.CENTER);
        lblInputName.setBounds(36, 38, 131, 21);
        lblInputName.setText("Input Name");

        Label lblInputQuntati = new Label(shell, SWT.NONE);
        lblInputQuntati.setAlignment(SWT.CENTER);
        lblInputQuntati.setBounds(36, 71, 131, 21);
        lblInputQuntati.setText("Input quantity products  ");

        text = new Text(shell, SWT.BORDER);
        text.setBounds(186, 38, 103, 21);

        text_1 = new Text(shell, SWT.BORDER);
        text_1.setBounds(186, 71, 103, 21);

        comboPriority = new Combo(shell, SWT.NONE);
        comboPriority.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
        comboPriority.setBounds(114, 140, 114, 23);
        comboPriority.setText("Choose priority");
        comboPriority.add("Low");
        comboPriority.add("Medium");
        comboPriority.add("High");



        comboProducts = new Combo(shell, SWT.NONE);
        comboProducts.setBounds(114, 105, 114, 23);
        comboProducts.setText("Choose product");
        comboProducts.add("Meat");
        comboProducts.add("Milk");
        comboProducts.add("Bread");
        comboProducts.add("Fruit");
        comboProducts.add("Potatoes");

        IncorrectName = new Label(shell, SWT.NONE);
        showIncorrectValue(295, 38, IncorrectName);
        IncorrectQuantity = new Label(shell, SWT.NONE);
        showIncorrectValue(295, 71, IncorrectQuantity);
        IncorrectChooseProducts = new Label(shell, SWT.NONE);
        showIncorrectValue(234, 105, IncorrectChooseProducts);
        IncorrectChoosePriority = new Label(shell, SWT.NONE);
        showIncorrectValue(234, 140, IncorrectChoosePriority);

        Button btnAddShip = new Button(shell, SWT.NONE);
        btnAddShip.setBounds(125, 180, 91, 25);
        btnAddShip.setText("Add ship");

        btnAddShip.addSelectionListener(
                new SelectionAdapter() {
                    public void widgetSelected(SelectionEvent e) {
                        checkAndAddShop();
                    }
                });
    }



    public void showIncorrectValue(int i, int j, Label label){
        label.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
        label.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_CYAN));
        label.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
        label.setAlignment(SWT.CENTER);
        label.setBounds(i, j, 117, 21);
        label.setText("Incorrect value");
        label.setVisible(false);
    }

    public void checkAndAddShop(){
        int flag = 0;
        if (!checkName(text.getText())){
            IncorrectName.setVisible(true);
            flag = 1;
        }
        else IncorrectName.setVisible(false);

        if(!checkQuantity(text_1.getText())){
            IncorrectQuantity.setVisible(true);
            flag = 1;
        }
        else IncorrectQuantity.setVisible(false);

        if(!checkProducts(comboProducts.getText())){
            IncorrectChooseProducts.setVisible(true);
            flag = 1;
        }
        else IncorrectChooseProducts.setVisible(false);

        if(!checkPriority(comboPriority.getText())){
            IncorrectChoosePriority.setVisible(true);
            flag = 1;
        }
        else IncorrectChoosePriority.setVisible(false);

        if(flag==0){
            int priority = 0;
            if(comboPriority.getText().equals("High")) priority = 2;
            if (comboPriority.getText().equals("Medium")) priority = 1;

            Ship ship = new Ship(text.getText(),comboProducts.getText(),Integer.parseInt(text_1.getText()),priority);

            LoggerApp.getLogger().info("Add ship " + ship.getName() + " in our list");
            MapShips.getMapShips().add(ship);

            try {
                updateForm.updateFormListShip();
            } catch (InterruptedException e1) {
                LoggerApp.getLogger().error(e1);
            }
            shell.close();

        }
    }
}