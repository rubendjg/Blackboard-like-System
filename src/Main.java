import Storage.DataControl;
import UserInterface.UI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        UI ui = new UI();
        DataControl.registerAutoSaveOnExit();
        ui.runInterface();
    }
}


