package eu.costengineering.dataprocess.interview.frontend;

import eu.costengineering.dataprocess.interview.domain.CostDirection;
import eu.costengineering.dataprocess.interview.evaluators.Evaluator;

import java.util.Objects;

public final class Query {
    public Evaluator evaluator;
    public String selectedType;
    public CostDirection selectedDirection;

    /** Constructor of the Query class.
     *
     * This class serves as an abstraction between business logic and the frontend.
     * It's main purpose is to properly call the calculation methods of the evaluator class based on user input from
     * the frontend.
     *
     * @param evaluator Instance of the to be used evaluator
     */
    public Query(Evaluator evaluator) {
        this.selectedType = "";
        this.selectedDirection = CostDirection.DIRECT;
        this.evaluator = evaluator;
    }

    public double queryCost() {
        if (Objects.equals(selectedType, "")){
            switch (selectedDirection) {
                case DIRECT -> {
                    return evaluator.getDirectCost();
                }
                case INDIRECT -> {
                    return evaluator.getIndirectCost();
                }
                case TOTAL -> {
                    return evaluator.getTotalCost();
                }
            }
        }

        switch (selectedDirection) {
            case DIRECT -> {
                return evaluator.getDirectForGroupingKey(selectedType);
            }
            case INDIRECT -> {
                return evaluator.getIndirectForGroupingKey(selectedType);
            }
            case TOTAL -> {
                return evaluator.getTotalForGroupingKey(selectedType);
            }
        }

        return 0.0;
    }
}
