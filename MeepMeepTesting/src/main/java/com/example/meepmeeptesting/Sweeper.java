package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.io.IOException;

public class RedSamples {
    public static void main(String[] args) throws IOException {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35, -61, Math.toRadians(90)))
//                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(35.5, -61, Math.toRadians(90)))
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(8, -61, Math.toRadians(90)))

                        .lineTo(new Vector2d(8, -36))
                        //.lineToY( -39)



                        /**
                        * first sample
                        */
                        .setTangent(Math.toRadians(270))
                        .splineToConstantHeading(new Vector2d(24, -44), Math.toRadians(0)) //Go towards sample
                        //.splineToConstantHeading(new Vector2d(24, -44), Math.toRadians(0))

                        .splineToConstantHeading(new Vector2d(38, -18.6), Math.toRadians(90))
                        //.splineToConstantHeading(new Vector2d(36, -18.6), Math.toRadians(0))

                        .splineToConstantHeading(new Vector2d(43, -12), Math.toRadians(0))
                        //.splineToConstantHeading(new Vector2d(43, -7), Math.toRadians(0))

                        .splineToConstantHeading(new Vector2d(45.3, -14), Math.toRadians(270))
                        //.splineToConstantHeading(new Vector2d(45.3, -14), Math.toRadians(270))

                        .lineToConstantHeading(new Vector2d(45.3, -52))
                        //.lineToYConstantHeading(-52) //Go to Observation zone


                        /**
                        * second sample
                        */
                        .lineTo(new Vector2d(45.3, -18.6))
                        //.lineToY(-18.6)

                        .splineToConstantHeading(new Vector2d(52, -12), Math.toRadians(0))
                        //.splineToConstantHeading(new Vector2d(52, -12), Math.toRadians(0))

                        .splineToConstantHeading(new Vector2d(56, -14), Math.toRadians(270))
                        //.splineToConstantHeading(new Vector2d(56, -14), Math.toRadians(270))

                        .lineToConstantHeading(new Vector2d(56, -52))
                        //.lineToYConstantHeading(-52)

                        /**
                        *grab first specimen
                        */
                        .setTangent(Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(51, -44), Math.toRadians(180))

                        .splineToConstantHeading(new Vector2d(46, -57.5), Math.toRadians(270))
                        //TODO: SLOW!!, Grab Specimen

                        .waitSeconds(0.6)
                        //TODO:Grab Specimen


                        /**
                        * hang specimen
                        */
                        .setTangent(Math.toRadians(160))
                        //.strafeTo(new Vector2d(15,-50))//Go towards bar

                        .splineToConstantHeading(new Vector2d(5,-52), Math.toRadians(90))

                        .lineToConstantHeading(new Vector2d(5,-39))//go forwards to hang specimen


                        /**
                        * Go Back to Obs. Zone
                        */
                        .setTangent(Math.toRadians(270))
                        .lineToConstantHeading(new Vector2d(5,-44))

                        .splineToConstantHeading(new Vector2d(34,-40), Math.toRadians(0))

                        .splineToConstantHeading(new Vector2d(46, -54), Math.toRadians(270))



                        .build());





        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

//        try {
//
//            File pathToFile = new File("resources/intothedeep_rotated.png");
//            Image image = ImageIO.read(pathToFile);
//
//            meepMeep.setBackground(image)
//                    .setDarkMode(true)
//                    .setBackgroundAlpha(0.95f)
//                    .addEntity(myBot)
//                    .start();
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

    }
}