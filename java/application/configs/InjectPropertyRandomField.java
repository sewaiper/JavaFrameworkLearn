package application.configs;

import com.sun.xml.internal.txw2.IllegalAnnotationException;
import application.annotations.MagicNumber;
import framework.objects.ObjectConfigurator;
import application.interfaces.Randomize;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class InjectPropertyRandomField implements ObjectConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object o) {
        for(Field field: o.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(MagicNumber.class)) {
                field.setAccessible(true);
                Object fieldObject = field.get(o);
                if(Randomize.class.isAssignableFrom(fieldObject.getClass())) {
                    Randomize randObject = (Randomize)fieldObject;
                    randObject.randomize();
                }
                else {
                    throw new IllegalAnnotationException(o.getClass().getName() + ": type of field " + field.getName()
                            + ", doesn't implement Randomize interface.");
                }
            }
        }
    }
}
