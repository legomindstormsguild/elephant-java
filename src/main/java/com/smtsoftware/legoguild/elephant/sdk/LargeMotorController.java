package com.smtsoftware.legoguild.elephant.sdk;

import org.ev3dev.hardware.motors.LargeMotor;
import org.ev3dev.hardware.motors.Motor;
import org.ev3dev.hardware.ports.LegoPort;

import java.io.IOException;

public class LargeMotorController {

    private Motor mMotor;

    public LargeMotorController(final int port) {
        try {
            mMotor = new LargeMotor(new LegoPort(port));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setPercentageSpeed(final int percent) {
        if (mMotor == null) {
            return;
        }
        Logger.print("setPercentageSpeed(" + percent +  ")");
        mMotor.setDutyCycleSP(percent);
    }

    public void runForever() {
        if (mMotor == null) {
            return;
        }
        Logger.print("runForever()");
        mMotor.setTime_SP(0);
        mMotor.setPosition_SP(0);
        mMotor.runForever();
    }

    public void runForSeconds(final float seconds) {
        if (mMotor == null) {
            return;
        }
        Logger.print("runForSeconds(" + seconds + ")");
        mMotor.setTime_SP(TimeUtil.toMillis(seconds));
        mMotor.setPosition_SP(0);
        mMotor.runTimed();
    }

    public void runToPosition(final int position) {
        if (mMotor == null) {
            return;
        }
        Logger.print("runToPosition(" + position + ")");
        mMotor.setTime_SP(0);
        mMotor.setPosition_SP(position);
        mMotor.runToAbsPos();
    }

    public void runForDegrees(final int position) {
        if (mMotor == null) {
            return;
        }
        Logger.print("runForDegrees(" + position + ")");
        mMotor.setTime_SP(0);
        mMotor.setPosition_SP(position * mMotor.getCountPerRot());
        mMotor.runToRelPos();
    }

    public void stop() {
        if (mMotor == null) {
            return;
        }
        Logger.print("stop()");
        mMotor.stop();
    }
}
