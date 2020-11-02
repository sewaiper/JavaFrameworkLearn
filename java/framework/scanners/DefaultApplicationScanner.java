package framework.scanners;

import framework.annotations.Container;
import framework.annotations.Factory;
import framework.containers.DefaultObjectContainer;
import framework.containers.ObjectContainer;
import framework.factory.DefaultObjectFactory;
import framework.factory.ObjectFactory;
import framework.objects.ObjectDefinition;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Set;

public class DefaultApplicationScanner implements ApplicationScanner {
    private Reflections helperScanner;

    public DefaultApplicationScanner(String locationScan) {
        helperScanner = new Reflections(locationScan);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<? extends ObjectContainer> findApplicationObjectContainer() {
        Set<Class<?>> containers =  helperScanner.getTypesAnnotatedWith(Container.class);

        if(containers.isEmpty()) {
            return DefaultObjectContainer.class;
        }

        if(containers.size() > 1) {
            throw new RuntimeException("Found more than one class, which have annotation " + Container.class.getName());
        }

        Class<?> container = containers.iterator().next();

        if(container.isInterface() || Modifier.isAbstract(container.getModifiers())) {
            throw new RuntimeException(container.getName() + " is interface or abstract class");
        }

        if(ObjectContainer.class.isAssignableFrom(container)) {
            return (Class<? extends ObjectContainer>) container;
        }

        throw new RuntimeException(container.getName() + " not implemented " + ObjectContainer.class.getName() + " interface");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class<? extends ObjectFactory> findObjectFactory() {
        Set<Class<?>> factories = helperScanner.getTypesAnnotatedWith(Factory.class);

        if(factories.isEmpty()) {
            return DefaultObjectFactory.class;
        }

        if(factories.size() > 1) {
            throw new RuntimeException("Found more than one class, which have annotation " + Factory.class.getName());
        }

        Class<?> factory = factories.iterator().next();
        if(factory.isInterface() || Modifier.isAbstract(factory.getModifiers())) {
            throw new RuntimeException(factory.getName() + " is interface or abstract class");
        }

        if(ObjectFactory.class.isAssignableFrom(factory)) {
            return (Class<? extends ObjectFactory>)factory;
        }

        throw new RuntimeException(factory.getName() + " not implemented " + ObjectFactory.class.getName() + " interface");
    }

    @Override
    public Collection<ObjectDefinition> scanApplicationObjectDefinitions() {
        return null;
    }
}
