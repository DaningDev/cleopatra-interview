package eu.costengineering.dataprocess.interview.frontend;

import javax.swing.*;

public class Launcher {
    public static void runFrontEnd(Query query) {
        SwingUtilities.invokeLater(() -> {
            MainWindow main = new MainWindow(query);
            main.show();
        });
    }
}
