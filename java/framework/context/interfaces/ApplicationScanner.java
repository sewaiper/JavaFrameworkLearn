package framework.context.interfaces;

import java.util.Map;
import java.util.Set;

public interface ApplicationScanner {
    Set<Class<?>> getClassSet();
    Map<Class<?>, Class<?>> getFactoryConfiguratorParameters();
}
