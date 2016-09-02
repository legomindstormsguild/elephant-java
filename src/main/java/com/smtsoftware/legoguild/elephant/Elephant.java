package com.smtsoftware.legoguild.elephant;

import ev3dev.hardware.Sound;
import ev3dev.hardware.motor.EV3LargeRegulatedMotor;
import ev3dev.hardware.motor.EV3MediumRegulatedMotor;
import ev3dev.hardware.port.MotorPort;
import ev3dev.hardware.port.SensorPort;
import ev3dev.hardware.sensor.SensorModes;
import ev3dev.hardware.sensor.ev3.EV3ColorSensor;
import ev3dev.hardware.sensor.ev3.EV3TouchSensor;
import lejos.utility.Delay;

import java.io.File;

/**
 * Created by ukaszek on 02/09/16.
 */
public class Elephant {

    private static volatile Elephant sInstance = null;

    private final EV3MediumRegulatedMotor mHeadMotor = new EV3MediumRegulatedMotor(MotorPort.D);

    private final EV3ColorSensor mHeadColorSensor = new EV3ColorSensor(SensorPort.S4);

    private final EV3LargeRegulatedMotor mTrunkMotor = new EV3LargeRegulatedMotor(MotorPort.B);

    private final EV3TouchSensor mTrunkTouchSensor = new EV3TouchSensor(SensorPort.S1);

    public static Elephant getInstance() {
        if (sInstance == null) {
            sInstance = new Elephant();
        }
        return sInstance;
    }

    private Elephant() {

    }

    public void start() {
        reset();
    }

    private void reset() {
        // raise head until read brick on the head is detected
        mHeadMotor.setSpeed(-10);
        while (mHeadColorSensor.getColorID() != 5) {
            mHeadMotor.forward();
            Delay.msDelay(100);
        }
        mHeadMotor.stop();

        // raise trunk until touch sensor detects touch
        mTrunkMotor.setSpeed(-10);
        while (mTrunkMotor.getPosition() != 5) {
            mTrunkMotor.forward();
            Delay.msDelay(100);
        }
        mTrunkMotor.stop();

        // do elephant woo-woot
        Sound.getInstance().playSample(new File("LEGO Sound Files/Animals/Elephant Call"), 100);

        Delay.msDelay(1000);

        new Thread(new Runnable() {
            public void run() {
                mHeadMotor.setSpeed(50);
                mHeadMotor.rotate(770);
                Delay.msDelay(100);
                mHeadMotor.resetTachoCount();
                mHeadMotor.stop();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                mTrunkMotor.setSpeed(50);
                mTrunkMotor.rotate(770);
                Delay.msDelay(100);
                mTrunkMotor.resetTachoCount();
                mTrunkMotor.stop();
            }
        }).start();
    }
}
