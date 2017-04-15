package Run;

import GUI.GUIMainWindow.MainWindow;

/**
 * Created by Vladislav on 4/15/2017.
 */
public class Run {
    public static void main(String[] args) {
        try {
            MainWindow mainWindow = new MainWindow();
            mainWindow.open();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
