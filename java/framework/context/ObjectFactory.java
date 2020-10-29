package framework.context;

import framework.context.configurators.factory.BaseFactoryConfigurator;
import framework.context.configurators.objects.InjectPropertyRandomField;
import framework.context.interfaces.FactoryConfigurator;
import framework.context.interfaces.ObjectConfigurator;
import lombok.SneakyThrows;

import java.util.HashSet;
import java.util.Set;

public class ObjectFactory {
    private static final ObjectFactory factory = new ObjectFactory();

    private final FactoryConfigurator configurator;
    private final Set<ObjectConfigurator> objectConfigurators;

    private ObjectFactory() {
        configurator = new BaseFactoryConfigurator("framework");
        objectConfigurators = new HashSet<>();
        objectConfigurators.add(new InjectPropertyRandomField());
    }

    public static ObjectFactory getInstance() {
        return factory;
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;

        if(implClass.isInterface()) {
            implClass = configurator.getImplementation(type);
        }

        T object = implClass.getConstructor().newInstance();

        // Configure object
        for(ObjectConfigurator configurator: objectConfigurators) {
            configurator.configure(object);
        }

        return object;
    }
}
