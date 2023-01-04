package de.exxcellent.challenge.file;

import java.io.IOException;

/**
 * Receives a path to a file, reads it and transforms it into the data class
 */
public interface IParser<T> {

    T parse(String pathToFile) throws IOException;

}
