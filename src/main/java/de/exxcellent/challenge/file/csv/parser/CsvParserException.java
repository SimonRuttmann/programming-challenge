package de.exxcellent.challenge.file.csv.parser;

/**
 * Thrown by the IParser implementations, indicates that a csv file misses a required schema definition
 */
public class CsvParserException extends RuntimeException{

    public CsvParserException(){
        super();
    }

    public CsvParserException(String message, Throwable cause){
        super(message, cause);
    }

    public CsvParserException(String message){
        super(message);
    }

    public CsvParserException(Throwable cause){
        super(cause);
    }
}
