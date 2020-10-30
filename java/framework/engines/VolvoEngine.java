package framework.engines;

import framework.interfaces.Randomize;
import framework.interfaces.Engine;

import java.util.Random;

public class VolvoEngine implements Engine, Randomize {
    private int horsepower;
    private int torque;

    @Override
    public int getHorsepower() {
        return this.horsepower;
    }

    @Override
    public int getTorque() {
        return this.torque;
    }

    @Override
    public void randomize() {
        Random rand = new Random();

        horsepower = rand.nextInt(250 + 1) + 100;
        torque = rand.nextInt(400 + 1) + 250;
    }
}
