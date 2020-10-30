package framework.context.interfaces;

public interface ObjectFactory {
    <T> T createObject(Class<T> type);
}
