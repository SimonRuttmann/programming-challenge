package de.exxcellent.challenge.file;

import de.exxcellent.challenge.dataModel.IDataClass;

import java.io.IOException;

/**
 * Receives a path to a file, reads it and transforms it into the data class
 */
public interface IParser<T extends IDataClass> {

    T parse(String pathToFile) throws IOException;

}
