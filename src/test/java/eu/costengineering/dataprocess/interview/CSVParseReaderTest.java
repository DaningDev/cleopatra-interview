package eu.costengineering.dataprocess.interview;

import eu.costengineering.dataprocess.interview.evaluators.EvaluatorCompanyX;
import eu.costengineering.dataprocess.interview.parsers.CSVParseReader;
import eu.costengineering.dataprocess.interview.parsers.Parser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class CSVParseReaderTest {

    public static Properties properties;
    public static File file;

    public static CSVParseReader parseReader;

    @BeforeClass
    public static void init() {
        String pathNameData = "src/test/java/resources/ExerciseCSV.csv";
        properties = new Properties();

        properties.setProperty("company", "companyX");
        properties.setProperty("format", "csv");
        properties.setProperty("delimiter", ";");

        file = new File(pathNameData);
    }

    @Test
    public void createParserTest() {
        assertEquals(Objects.requireNonNull(Parser.createParser(properties)).getClass(), CSVParseReader.class);
    }

    @Test
    public void createEvaluator() throws FileNotFoundException {
        Parser parser = Parser.createParser(properties);
        assertEquals(Objects.requireNonNull(parser).getClass(), CSVParseReader.class);
        assertEquals(parser.parseFile(file).getClass(), EvaluatorCompanyX.class);
    }

}

