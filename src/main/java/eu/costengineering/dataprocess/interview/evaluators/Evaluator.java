package eu.costengineering.dataprocess.interview.evaluators;

import eu.costengineering.dataprocess.interview.domain.CostData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Evaluator {

    List<CostData> costDataList = new ArrayList<>();
    Set<String> groupingKeys = new HashSet<>();

    public void setCostDataList(List<String[]>data);

    /** method to get the total direct cost of all the items.
     *
     * @return direct cost of the items in the CSV file
     */
    public double getDirectCost();

    /** method to get the total indirect cost of all the items.
     *
     * @return indirect cost of the items in the CSV file
     */
    public double getIndirectCost();

    /** method to get the total cost of all the items (direct+indirect).
     *
     * @return total cost of the items in the CSV file
     */
    public double getTotalCost();

    /** method to get the total direct cost for items that match a specific grouping key.
     *
     * @return total direct cost of the items that match the input grouping key
     */
    public double getDirectForGroupingKey(String key);

    /** method to get the total indirect cost for items that match a specific grouping key.
     *
     * @return total indirect cost of the items that match the input grouping key
     */
    public double getIndirectForGroupingKey(String key);

    /** method to get the total cost for items that match a specific grouping key (direct+indirect).
     *
     * @return direct cost of the items that match the input grouping key
     */
    public double getTotalForGroupingKey(String key);

}
