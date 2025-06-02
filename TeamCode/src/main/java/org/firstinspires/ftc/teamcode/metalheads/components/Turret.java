package org.firstinspires.ftc.teamcode.metalheads.components;


import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.library.potentiometermotor.PotentiometerMotor;

/**
 *
 */
public class Turret extends Component {

    /**
     */
    public TurretConfig config;

    /**
     */
    public EncodedMotor launcher;

    /**
     * Constructor
     *
     * @param turretConfig
     */
    public Turret(TurretConfig turretConfig) {
        super(turretConfig.robot);

        this.config = turretConfig;

        this.launcher = new EncodedMotor(this.config.launcherConfig);
    }

    /**
     *
     */
    @Override
    public void init() {
        super.init();

        this.launcher.init();
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        this.launcher.run();

        if (this.isDebug()) {
            telemetry.addData("Launcher Speed:", this.launcher.getCurrentPosition());
        }
    }
}
