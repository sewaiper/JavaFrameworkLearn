package application.cars;

import application.interfaces.Car;

public class Mercedes implements Car {
    @Override
    public void showInfo() {
        System.out.println("Brand: Mercedes, Model: S500");
    }

    @Override
    public void showBrand() {
        System.out.println("Mercedes");
    }
}
