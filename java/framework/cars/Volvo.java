package framework.cars;

import framework.annotations.MagicNumber;
import framework.context.ObjectFactoryDefault;
import framework.interfaces.Car;
import framework.interfaces.Engine;

public class Volvo implements Car {
    private static final String brand = "Volvo";
    @MagicNumber
    private Engine engine;

    @Override
    public void showInfo() {
        System.out.println(brand + " presentation");
        System.out.println("  " + brand + " | Model: S60");
        System.out.println("  Years of issue: 2016");

        System.out.println("  Engine info");
        System.out.println("    Horsepower: " + engine.getHorsepower() + ", Torque: " + engine.getTorque());
    }

    @Override
    public void showBrand() {
        System.out.println(brand);
    }
}
