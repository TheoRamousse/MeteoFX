package model;

import java.io.IOException;
import java.util.List;

/**
 * This interface describes the methods implemented by classes that will save the data
 * @param <T> Object persisted
 */
public interface Persistence<T> {
    public List<T> load() throws IOException;
    public void save(List<T> list) throws IOException;
}
