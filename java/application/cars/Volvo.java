package application.cars;

import application.annotations.MagicNumber;
import application.interfaces.Car;
import application.interfaces.Engine;
import framework.annotations.Bind;
import framework.annotations.Object;

@Object("volvoCar")
public class Volvo implements Car {
    private static final String brand = "Volvo";

    @Bind
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
