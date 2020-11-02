package framework.factory;

import application.configs.InjectPropertyRandomField;
import framework.objects.ObjectConfigurator;
import lombok.SneakyThrows;

import java.util.HashSet;
import java.util.Set;

public class DefaultObjectFactory implements ObjectFactory {
    private final Set<ObjectConfigurator> objectConfigurators;

    public DefaultObjectFactory() {
        objectConfigurators = new HashSet<>();
        objectConfigurators.add(new InjectPropertyRandomField());
    }

    @Override
    public void initializeFactory() {

    }

    @SneakyThrows
    @Override
    public <T> T createObject(Class<T> type) {
        if(type.isInterface()) {
            // todo: throw exception @Object - interface or abstract class
        }

        T object = type.getConstructor().newInstance();

        // Configure object
        for(ObjectConfigurator objectConfigurator: objectConfigurators) {
            objectConfigurator.configure(object);
        }

        return object;
    }
}
