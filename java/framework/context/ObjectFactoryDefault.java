package framework.context;

import framework.cars.Volvo;
import framework.context.configurators.factory.DefaultFactoryConfigurator;
import framework.configs.InjectPropertyRandomField;
import framework.context.interfaces.FactoryConfigurator;
import framework.context.interfaces.ObjectConfigurator;
import framework.context.interfaces.ObjectFactory;
import framework.interfaces.Car;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ObjectFactoryDefault implements ObjectFactory {
    private final FactoryConfigurator factoryConfigurator;
    private final Set<ObjectConfigurator> objectConfigurators;

    public ObjectFactoryDefault() {
        Map<Class<?>, Class<?>> abstractToImpl = new HashMap<>();
        abstractToImpl.putIfAbsent(Car.class, Volvo.class);

        factoryConfigurator = new DefaultFactoryConfigurator("framework", abstractToImpl);

        objectConfigurators = new HashSet<>();
        objectConfigurators.add(new InjectPropertyRandomField());
    }

    @SneakyThrows
    @Override
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;

        if(implClass.isInterface()) {
            implClass = factoryConfigurator.getImplementation(type);
        }

        T object = implClass.getConstructor().newInstance();

        // Configure object
        for(ObjectConfigurator objectConfigurator: objectConfigurators) {
            objectConfigurator.configure(object);
        }

        return object;
    }
}
