package model;

import javafx.beans.property.ListProperty;

import java.util.List;

public interface Persistence<T> {
    public List<T> load();
    public void save(List<T> list);
}
