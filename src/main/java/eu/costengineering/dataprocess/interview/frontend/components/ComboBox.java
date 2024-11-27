package eu.costengineering.dataprocess.interview.frontend.components;

import javax.swing.*;

public class ComboBox extends JComboBox<String> {

    public ComboBox(String[] items) {
        super(items);
        initialise();
    }

    private void initialise() {
        setSelectedIndex(0);
    }

}
