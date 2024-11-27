package eu.costengineering.dataprocess.interview.evaluators;

import eu.costengineering.dataprocess.interview.domain.CostData;
import eu.costengineering.dataprocess.interview.domain.CostDirection;

import java.util.List;
import java.util.Objects;

public class EvaluatorCompanyX implements Evaluator{

    @Override
    public void setCostDataList(List<String[]> data) {
        for (String[] d: data) {
            costDataList.add(new CostData(d[0],
                    CostDirection.valueOf(d[1].toUpperCase()),
                    Float.parseFloat(d[2]),
                    d[3]));
            groupingKeys.add(d[3]);
        }
    }

    @Override
    public double getDirectCost() {
        int directCost = 0;

        for (CostData d: costDataList) {
            if (d.direction() == CostDirection.DIRECT) {
                directCost += d.cost();
            }
        }

        return directCost;
    }

    @Override
    public double getIndirectCost() {
        int indirectCost = 0;

        for (String c: groupingKeys) {
            indirectCost += getIndirectForGroupingKey(c);
        }

        return indirectCost;
    }

    @Override
    public double getTotalCost() {
        return (getDirectCost() + getIndirectCost());
    }

    @Override
    public double getDirectForGroupingKey(String key) {
        double directGroupingKeyCost = 0;

        for (CostData d: costDataList) {
            if (Objects.equals(d.type(), key) && d.direction() == CostDirection.DIRECT) {
                directGroupingKeyCost += d.cost();
            }
        }

        return directGroupingKeyCost;
    }

    @Override
    public double getIndirectForGroupingKey(String key) {
        double indirectGroupingKeyCost = 0;

        for (CostData d: costDataList) {
            if (Objects.equals(d.type(), key) && d.direction() == CostDirection.INDIRECT) {
                indirectGroupingKeyCost += d.cost();
            }
        }

        return (getDirectForGroupingKey(key)*(indirectGroupingKeyCost*0.01));
    }

    @Override
    public double getTotalForGroupingKey(String key) {
        return (getDirectForGroupingKey(key) + getIndirectForGroupingKey(key));
    }
}
