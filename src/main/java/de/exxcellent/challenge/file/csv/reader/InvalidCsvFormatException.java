package de.exxcellent.challenge.file.csv.reader;

public class InvalidCsvFormatException extends RuntimeException{

    public  InvalidCsvFormatException(){
        super();
    }

    public InvalidCsvFormatException(String message, Throwable cause){
        super(message, cause);
    }

    public InvalidCsvFormatException(String message){
        super(message);
    }

    public InvalidCsvFormatException(Throwable cause){
        super(cause);
    }
}
