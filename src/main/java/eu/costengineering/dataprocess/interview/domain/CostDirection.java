package eu.costengineering.dataprocess.interview.domain;

import java.util.stream.Stream;

public enum CostDirection {
    DIRECT,
    INDIRECT,
    TOTAL;

    public static String[] listNames() {
        return Stream.of(CostDirection.values()).map(CostDirection::name).toList().toArray(new String[0]);
    }
}