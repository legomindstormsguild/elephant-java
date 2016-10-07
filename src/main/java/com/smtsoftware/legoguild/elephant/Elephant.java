package com.smtsoftware.legoguild.elephant;

import com.smtsoftware.legoguild.elephant.sdk.Delay;
import com.smtsoftware.legoguild.elephant.sdk.LargeMotorController;
import com.smtsoftware.legoguild.elephant.sdk.Logger;
import com.smtsoftware.legoguild.elephant.sdk.MediumMotorController;
import org.ev3dev.hardware.ports.LegoPort;

public class Elephant {

    private static volatile Elephant sInstance = null;

    private LargeMotorController mLargeMotor;

    private MediumMotorController mMediumMotor;

    public static Elephant getInstance() {
        if (sInstance == null) {
            sInstance = new Elephant();
        }
        return sInstance;
    }

    private Elephant() {
        //no-op
    }

    public void start() {
        bootUp();
        reset();
    }

    public void stop() {
        tearDown();
    }

    private void bootUp() {
        Logger.print("Booting up...");

        mLargeMotor = new LargeMotorController(LegoPort.OUTPUT_A);
        mMediumMotor = new MediumMotorController(LegoPort.OUTPUT_D);

        Logger.print("Elephant up and running.");
    }

    private void tearDown() {
        Logger.print("Tearing down...");
        mLargeMotor.stop();
        mMediumMotor.stop();
        Logger.print("Elephant shut down.");
    }

    private void reset() {
        Logger.print("Starting reset routine...");

        mLargeMotor.setPercentageSpeed(100);
        mLargeMotor.runForever();
        Delay.sDelay(4);
        mLargeMotor.stop();
        Delay.sDelay(2);

        mLargeMotor.setPercentageSpeed(-100);
        mLargeMotor.runForever();
        Delay.sDelay(4);
        mLargeMotor.stop();
        Delay.sDelay(2);

        mLargeMotor.setPercentageSpeed(50);
        mLargeMotor.runToPosition(5000);
        Delay.sDelay(20);
        mLargeMotor.stop();
        Delay.sDelay(2);

        mLargeMotor.setPercentageSpeed(-50);
        mLargeMotor.runToPosition(0);
        Delay.sDelay(4);
        mLargeMotor.stop();
        Delay.sDelay(2);

        mLargeMotor.runForSeconds(5);
        mLargeMotor.runForSeconds(5);
        Delay.sDelay(10);
        mLargeMotor.stop();

        Delay.sDelay(5);
        mLargeMotor.runForSeconds(10f);
        Delay.sDelay(10);

        mLargeMotor.setPercentageSpeed(10);
        mLargeMotor.runForDegrees(180);
        Delay.sDelay(60);

        Logger.print("Reset routine completed.");
    }
}
