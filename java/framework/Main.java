package framework;

import framework.interfaces.Car;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.Set;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Reflections reflect = new Reflections("");

        Set<Class<? extends Car>> carImpls = reflect.getSubTypesOf(Car.class);
        for (Class<? extends Car> impl: carImpls) {
            System.out.println(impl.getName());
        }
    }
}
