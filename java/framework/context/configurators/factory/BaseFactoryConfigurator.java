package framework.context.configurators.factory;

import framework.context.interfaces.FactoryConfigurator;
import org.reflections.Reflections;

import java.util.Set;

public class BaseFactoryConfigurator implements FactoryConfigurator {
    private final Reflections scanner;

    public BaseFactoryConfigurator() {
        scanner = new Reflections("");
    }

    public BaseFactoryConfigurator(String scanPackage) {
        scanner = new Reflections(scanPackage);
    }

    @Override
    public <T> Class<? extends T> getImplementation(Class<T> abstraction) {
        Set<Class<? extends T>> impls = scanner.getSubTypesOf(abstraction);
        if(impls.size() != 1) {
            throw new RuntimeException(abstraction.getName() + " has a 0 or more than 1 implementations.");
        }

        return impls.iterator().next();
    }
}
