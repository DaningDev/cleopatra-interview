package eu.costengineering.dataprocess.interview.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;

import eu.costengineering.dataprocess.interview.evaluators.Evaluator;
import eu.costengineering.dataprocess.interview.evaluators.EvaluatorFactory;

public interface Parser {

    /**This method should take in a CSV file as an input, parse the incoming data, and store it in a fitting manner.
     * With this parsed data, it will return an evaluator that will further handle the calculation logic(see Evaluator).
     *
     * @param file input CSV file
     * @return an evaluator that will carry the logical operations for cost calculations
     */
    Evaluator parseFile(File file) throws FileNotFoundException;

    static Parser createParser(Properties properties){

        Evaluator evaluator = EvaluatorFactory.makeEvaluator(properties);

        switch (properties.getProperty("format")) {
            case "csv" -> {
                return new CSVParseReader(properties.getProperty("delimiter").charAt(0), evaluator);
            }
            case "txt" -> throw new UnsupportedOperationException("Custom readers for CSV files");
            case "json" -> throw new UnsupportedOperationException("Custom readers for Json files");
        }
        return null;
    }

}
