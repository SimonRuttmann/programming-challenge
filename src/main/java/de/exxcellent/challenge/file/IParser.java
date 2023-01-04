package de.exxcellent.challenge.file;

/**
 * Receives a path to a file, reads it and transforms it into the data class
 */
public interface IParser<T> {

    T parse(String pathToFile);

}
