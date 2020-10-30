package framework.context.interfaces;

public interface ApplicationContext {
    ObjectContainer getObjectContainer();
    ApplicationScanner getApplicationScanner();
    FactoryConfigurator getFactoryConfigurator();
    ObjectConfigurator getObjectConfigurator();
    ObjectFactory getObjectFactory();
}
