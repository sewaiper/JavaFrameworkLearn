package framework.factory;

public interface ObjectFactory {
    void initializeFactory();
    <T> T createObject(Class<T> type);
}
