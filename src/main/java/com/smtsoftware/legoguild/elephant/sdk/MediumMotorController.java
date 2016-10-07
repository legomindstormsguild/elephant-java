package com.smtsoftware.legoguild.elephant.sdk;

import org.ev3dev.hardware.motors.MediumMotor;
import org.ev3dev.hardware.motors.Motor;
import org.ev3dev.hardware.ports.LegoPort;

import java.io.IOException;

public class MediumMotorController {

    private Motor mMotor;

    public MediumMotorController(final int port) {
        try {
            mMotor = new MediumMotor(new LegoPort(port));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void runForever() {
        mMotor.setDutyCycleSP(-50);
        mMotor.setSpeed_SP(-30);
        mMotor.setTime_SP(2000);
        mMotor.runTimed();
    }

    public void stop() {
        mMotor.stop();
    }
}
