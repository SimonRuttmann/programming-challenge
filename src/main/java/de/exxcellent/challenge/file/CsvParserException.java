package de.exxcellent.challenge.file;

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
