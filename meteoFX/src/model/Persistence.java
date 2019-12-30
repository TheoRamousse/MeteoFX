package model;

import javafx.beans.property.ListProperty;

import java.io.IOException;
import java.util.List;

public interface Persistence<T> {
    public List<T> load() throws IOException;
    public void save(List<T> list) throws IOException;
}
