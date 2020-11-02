package framework.scanners;

import framework.containers.ObjectContainer;
import framework.objects.ObjectDefinition;
import framework.factory.ObjectFactory;

import java.util.Collection;

public interface ApplicationScanner {
    Class<? extends ObjectContainer> findApplicationObjectContainer();
    Class<? extends ObjectFactory> findObjectFactory();
    Collection<ObjectDefinition> scanApplicationObjectDefinitions();
}
