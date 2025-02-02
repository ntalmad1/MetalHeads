package org.firstinspires.ftc.teamcode.metalheads.compbot;

import org.firstinspires.ftc.teamcode.library.IsaacBot;

/**
 *
 */
public class AutoBotConfig extends CompBotConfig {

    /**
     * Constructor
     *
     * @param robot
     */
    public AutoBotConfig(IsaacBot robot) {
        super(robot);

        this.useBigArm = true;
        this.useLittleArm = true;
        this.useSweeperArm = true;
    }
}
