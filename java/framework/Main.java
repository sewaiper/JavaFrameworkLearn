package framework;

import framework.context.ObjectFactory;
import framework.interfaces.Car;

public class Main {
    public static void main(String[] args) {
        Car car = ObjectFactory.getInstance().createObject(Car.class);

        car.showInfo();
    }
}
