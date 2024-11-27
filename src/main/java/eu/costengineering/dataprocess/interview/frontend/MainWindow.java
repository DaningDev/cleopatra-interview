package eu.costengineering.dataprocess.interview.frontend;

import eu.costengineering.dataprocess.interview.domain.CostDirection;
import eu.costengineering.dataprocess.interview.frontend.components.ComboBox;
import eu.costengineering.dataprocess.interview.frontend.components.InputField;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private JFrame window;
    private final Query query;
    private double cost;

    public MainWindow(Query query) {
        this.cost = 0.0;
        this.query = query;
        initialize();
    }

    private void initialize() {
        window = new JFrame();
        window.setTitle("Cost Estimator");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(400,150);
        window.setLocationRelativeTo(null);
        window.setResizable(false);

        JPanel panel = new JPanel(new GridLayout(2,1,10,20));
        JPanel inputPanel = new JPanel();
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        panel.add(inputPanel);
        panel.add(resultPanel);

        panel.setBackground(Color.orange);
        inputPanel.setBackground(Color.orange);
        resultPanel.setBackground(Color.orange);

        Button button = new Button("Calculate");

        Dimension resultLabelSize = new Dimension(150,30);

        Label results = new Label("0.0");
        results.setPreferredSize(resultLabelSize);
        results.setMaximumSize(resultLabelSize);
        results.setMinimumSize(resultLabelSize);
        results.setBackground(Color.white);

        ComboBox directionComboBox = new ComboBox(CostDirection.listNames());
        InputField groupingKeyInput = new InputField();

        button.addActionListener(e -> {
            this.cost = doQuery(
                    groupingKeyInput.getText(),
                    CostDirection.valueOf((String) directionComboBox.getSelectedItem())
                    );
            results.setText(String.valueOf(this.cost));
        });

        inputPanel.add(directionComboBox);
        inputPanel.add(groupingKeyInput);
        inputPanel.add(button);

        resultPanel.add(results, BorderLayout.WEST);

        window.add(panel);
    }

    public void show() {
        window.setVisible(true);
    }

    private double doQuery(String type, CostDirection direction) {
        query.selectedType = type;
        query.selectedDirection = direction;
        return query.queryCost();
    }

}
