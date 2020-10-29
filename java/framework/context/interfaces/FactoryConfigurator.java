package framework.context.interfaces;

public interface FactoryConfigurator {
    <T> Class<? extends T> getImplementation(Class<T> abstraction);
}
