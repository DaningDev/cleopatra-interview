package eu.costengineering.dataprocess.interview;

import eu.costengineering.dataprocess.interview.evaluators.Evaluator;
import eu.costengineering.dataprocess.interview.frontend.Launcher;
import eu.costengineering.dataprocess.interview.frontend.Query;
import eu.costengineering.dataprocess.interview.parsers.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CostEstimation {

    /** Calling the main method in this class will initialize the user interface for the application.
     *
     * The user should be able to calculate the following from the input CSV document:
     * Total direct cost
     * Total indirect cost
     * Total cost (total direct + total indirect cost)
     * Total direct cost for grouping key X
     * Total indirect cost for grouping key X
     * Total cost for grouping key X
     *
     * Please see the respective interfaces for the parse and evaluation functionalities.
     * The implementation is up to the developer as long as the declared methods are present and the app adheres to the UI guidelines.
     * CSV file's location will be passed as an argument to the main function to start the application.
     *
     * @param args to-be-parsed CSV file's location
     */
    public static void main(String[] args) throws FileNotFoundException {
        Properties properties = config();
        File file = new File(args[0]);

        Parser parser = Parser.createParser(properties);
        assert parser != null;

        Evaluator evaluator = parser.parseFile(file);

        Launcher.runFrontEnd(new Query(evaluator));
    }

    private static Properties config(){
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("src/main/java/eu/costengineering/dataprocess/interview/config.properties");

            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return prop;
    }
}
