package framework.context;

import framework.context.interfaces.*;

import java.util.Set;

public class ApplicationContextDefault implements ApplicationContext {
    private FactoryConfigurator factoryConfigurator;
    private ObjectContainer container;
    private ObjectFactory objectFactory;

    public ApplicationContextDefault() {
        restart();
    }

    public void restart() {
        container = getObjectContainer();

        factoryConfigurator = getFactoryConfigurator();
        /* @todo: Get Factory configurator settings
                  Map<Class<?>, Class<?>> - relation: @Specified field type - @Specified(id) concrete class
        */
        objectFactory = getObjectFactory();
    }

    @Override
    public ObjectContainer getObjectContainer() {
        return null;
    }

    @Override
    public ApplicationScanner getApplicationScanner() {
        return null;
    }

    @Override
    public FactoryConfigurator getFactoryConfigurator() {
        return null;
    }

    @Override
    public ObjectConfigurator getObjectConfigurator() {
        return null;
    }

    @Override
    public ObjectFactory getObjectFactory() {
        return null;
    }
}
