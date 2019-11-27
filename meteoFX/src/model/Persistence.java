package model;

import java.util.List;

public interface Persistence<T> {
    public List<T> load();
    public void save(List<T> list);
}
