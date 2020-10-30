package framework.context.configurators.factory;

import framework.context.interfaces.FactoryConfigurator;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

public class DefaultFactoryConfigurator implements FactoryConfigurator {
    private final Reflections scanner;
    private final Map<Class<?>, Class<?>> abstractToImpl;

    public DefaultFactoryConfigurator(String scanPackage, Map<Class<?>, Class<?>> abstractToImpl) {
        scanner = new Reflections(scanPackage);
        this.abstractToImpl = abstractToImpl;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Class<? extends T> getImplementation(Class<T> abstraction) {
        Class<?> impl = abstractToImpl.computeIfAbsent(abstraction, k -> {
            Set<Class<? extends T>> impls = scanner.getSubTypesOf(abstraction);
            if(impls.size() != 1) {
                throw new RuntimeException("Abstraction " + abstraction.getName() + " have a 0 or more than 1 implementations");
            }

            return impls.iterator().next();
        });

        if(impl == null) throw new RuntimeException("Implementation of abstraction " + abstraction.getName() + " is null");
        if(impl.isInterface()) throw new RuntimeException("Implementation " + impl.getName() + " haven't constructor");
        if(!abstraction.isAssignableFrom(impl)) throw new RuntimeException("Implementation " + impl.getName() +
                " doesn't implement abstraction " + abstraction.getName());

        return (Class<? extends T>) impl;
    }
}
