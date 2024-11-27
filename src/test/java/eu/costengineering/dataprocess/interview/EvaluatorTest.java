package eu.costengineering.dataprocess.interview;

import eu.costengineering.dataprocess.interview.evaluators.Evaluator;
import eu.costengineering.dataprocess.interview.evaluators.EvaluatorCompanyX;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EvaluatorTest {
    public static final double DELTA = 0.01;
    public static Evaluator evaluator;

    @BeforeClass
    public static void init() {
        List<String[]> costDataList = new ArrayList<>();
        costDataList.add(new String[]{"item1", "DIRECT", "200", "A"});
        costDataList.add(new String[]{"item2", "INDIRECT","10.0", "A"});
        costDataList.add(new String[]{"item3", "DIRECT", "200", "B"});
        costDataList.add(new String[]{"item4", "DIRECT", "100", "B"});
        evaluator = new EvaluatorCompanyX();
        evaluator.setCostDataList(costDataList);
    }

    @Test
    public void directCostTest() {
        assertEquals(500, evaluator.getDirectCost(), DELTA);
    }

    @Test
    public void indirectCostTest() {
        assertEquals(20, evaluator.getIndirectCost(), DELTA);
    }

    @Test
    public void totalCostTest() {
        assertEquals(520, evaluator.getTotalCost(), DELTA);
    }

    @Test
    public void directGroupingCostTest() {
        assertEquals(200, evaluator.getDirectForGroupingKey("A"), DELTA);
        assertEquals(300, evaluator.getDirectForGroupingKey("B"), DELTA);
    }

    @Test
    public void indirectGroupingCostTest() {
        assertEquals(20, evaluator.getIndirectForGroupingKey("A"), DELTA);
    }

    @Test
    public void totalGroupingCostTest() {
        assertEquals(220, evaluator.getTotalForGroupingKey("A"), DELTA);
    }

}
