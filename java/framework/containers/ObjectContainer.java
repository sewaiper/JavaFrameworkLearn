package framework.containers;

public interface ObjectContainer {
    void addObject(Object object);
    <T> T getObject(Class<T> type);
}
