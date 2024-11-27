package eu.costengineering.dataprocess.interview.parsers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import eu.costengineering.dataprocess.interview.evaluators.Evaluator;

public class CSVParseReader implements Parser{

    private final char delimiter;
    private final Evaluator evaluator;

    /** Constructor of the CSV ParseReader class.
     *
     * This class is a CSV implementation of the Parser interface.
     * The class is initialised by passed a delimiter and the evaluator that is to be used for the final cost calculations.
     * It's main purpose is to read the datafile and properly set the data required in the evaluator class.
     *
     * @param delimiter to-be-parsed CSV file's location
     * @param evaluator Instance of the to be used evaluator
     */
    CSVParseReader(char delimiter, Evaluator evaluator) {
        this.delimiter = delimiter;
        this.evaluator = evaluator;
    }

    @Override
    public Evaluator parseFile(File file) throws FileNotFoundException {
        try {
            FileReader fileReader = new FileReader(file);

            CSVParser csvParser = new CSVParserBuilder()
                    .withSeparator(delimiter)
                    .build();

            CSVReader csvReader = new CSVReaderBuilder(fileReader)
                    .withCSVParser(csvParser)
                    .build();

            List<String[]> data = csvReader.readAll();

            evaluator.setCostDataList(data);
            return evaluator;
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return null;
    }
}
