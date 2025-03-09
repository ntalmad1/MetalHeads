package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.TrajectoryFactory;

/**
 */
public class RightObsTrajectoryFactory extends TrajectoryFactory {

    /**
     * @param autoBot
     */
    public RightObsTrajectoryFactory(AutoBot autoBot) {
        super(autoBot);
    }

    /**
     * @return tab2
     */
    public TrajectoryActionBuilder stepOne_placeSpeciman(Pose2d initialPose) {
        return this.getAutoBot().getDrive().actionBuilder(initialPose)
           .lineToY(-38);
    }

    /**
     *
     * @return
     */
    public TrajectoryActionBuilder stepTwo_Three_releaseSpeciman_pushSamples() {


        return this.getAutoBot().getDrive().actionBuilder(this.getAutoBot().getDrive().pose)
                /*
                 * First Sample
                 */
                .setTangent(Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(28, -44), Math.toRadians(0),
                        new TranslationalVelConstraint(5))//away + right of bar
                .splineToConstantHeading(new Vector2d(38, -18.6), Math.toRadians(0))// up torwards sample
                .splineToConstantHeading(new Vector2d(43, -12), Math.toRadians(0))//arc onto sample (left)
                .splineToConstantHeading(new Vector2d(45.3, -14), Math.toRadians(270))//arc onto sample (right)
                .lineToYConstantHeading(-52) //Go to Observation zone

                /*
                 * Second Sample
                 */
                .setTangent(Math.toRadians(270))
                .lineToYConstantHeading(-18.6) //towards sample
                .splineToConstantHeading(new Vector2d(52, -12), Math.toRadians(0)) //arc onto sample (left)
                .splineToConstantHeading(new Vector2d(56, -14), Math.toRadians(270)) //arc onto sample (right)
                .lineToYConstantHeading(-52); //Go to Observation zone
    }

    /**
     *
     * @return
     */
    public TrajectoryActionBuilder stepFour_arcToSpecimanPick() {
        return this.getAutoBot().getDrive().actionBuilder(this.getAutoBot().getDrive().pose)
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(51,-44), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(46, -57.4), Math.toRadians(270),
                        new TranslationalVelConstraint(8)
                );
    }

    /**
     *
     * @return
     */
    public TrajectoryActionBuilder splineToPlaceFirstSpeciman() {
        return this.getAutoBot().getDrive().actionBuilder(this.getAutoBot().getDrive().pose)
                .setTangent(Math.toRadians(150))
                .splineToConstantHeading(new Vector2d(5,-48), Math.toRadians(90))
                .lineToYConstantHeading(-39);
    }

    /**
     *
     * @return
     */
    public TrajectoryActionBuilder splineToSecondSpecimanPick() {
        return this.getAutoBot().getDrive().actionBuilder(this.getAutoBot().getDrive().pose)
                .setTangent(Math.toRadians(270))
                .lineToYConstantHeading(-42)//Away from bar

                .splineToConstantHeading(new Vector2d(33,-45), Math.toRadians(0))//Towards Specimen

                .splineToConstantHeading(new Vector2d(46, -57.4), Math.toRadians(270),//Specimen Grab
                        new TranslationalVelConstraint(8)
                );
    }


    /**
     *
     * @return
     */
    public TrajectoryActionBuilder splineToPlaceSecondSpeciman() {
        return this.getAutoBot().getDrive().actionBuilder(this.getAutoBot().getDrive().pose)
                .setTangent(Math.toRadians(150))
                .splineToConstantHeading(new Vector2d(2,-48), Math.toRadians(90))
                .lineToYConstantHeading(-39);
    }

    /**
     *
     * @return
     */
    public TrajectoryActionBuilder parkInObservationStepOne() {
        return this.getAutoBot().getDrive().actionBuilder(this.getAutoBot().getDrive().pose)
                .setTangent(Math.toRadians(270))
                .lineToYConstantHeading(-42);
    }

    /**
     *
     * @return
     */
    public TrajectoryActionBuilder parkInObservationStepTwo() {
        return this.getAutoBot().getDrive().actionBuilder(this.getAutoBot().getDrive().pose)
            .splineToConstantHeading(new Vector2d(33,-45), Math.toRadians(0))
            .splineToConstantHeading(new Vector2d(46, -52), Math.toRadians(270));
    }

}
