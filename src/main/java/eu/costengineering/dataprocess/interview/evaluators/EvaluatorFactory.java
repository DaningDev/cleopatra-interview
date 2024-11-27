package eu.costengineering.dataprocess.interview.evaluators;

import java.util.Properties;

public class EvaluatorFactory {
    public static Evaluator makeEvaluator(Properties properties) {
        switch (properties.getProperty("company")) {
            case "companyX": {
                return new EvaluatorCompanyX();
            }
            case "companyY": {
                return null;
            }
        }
        return null;
    }
}
