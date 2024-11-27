package eu.costengineering.dataprocess.interview.frontend.components;

import javax.swing.*;

public class InputField extends JTextField {

    public InputField() {
        super(10);
        initialize();
    }

    private void initialize() {
        this.setToolTipText("Optional: EnterGroupingKey");
    }

}
